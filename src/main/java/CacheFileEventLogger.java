import java.util.ArrayList;
import java.util.List;

public class CacheFileEventLogger extends FileEventLogger {

    private int cacheSize;
    private List<Event> cache;

    public CacheFileEventLogger(String fileName, int cacheSize) {
        super(fileName);
        this.cacheSize = cacheSize;
        cache = new ArrayList<Event>();
    }

    @Override
    public void logEvent(Event event) {
        cache.add(event);
        if(cache.size() == cacheSize) {
            writeEventsFromCache();
        }
    }

    private void writeEventsFromCache() {
        for(Event event : cache) {
            super.logEvent(event);
        }
        cache.clear();
    }

    private void destroy() {
        if(cache.size() > 0) {
            writeEventsFromCache();
        }
    }
}
