import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class FileEventLogger implements EventLogger {

    private File file;
    private String fileName;

    public FileEventLogger(String fileName) {
        this.fileName = fileName;
    }

    public void logEvent(Event event) {
        try {
            FileUtils.writeStringToFile(this.file, event.toString(), "UTF-8", true);
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    protected boolean init() throws IOException {
        this.file = new File(this.fileName);
        return this.file.delete();
    }
}
