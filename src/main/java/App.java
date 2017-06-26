import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

public class App {

    private Client client;
    private EventLogger defaultEventLogger;
    private Map<EventType, EventLogger> loggers;
    private static ConfigurableApplicationContext ctx;

    public App(Client client, Map<EventType, EventLogger> loggers, EventLogger defaultEventLogger) {
        this.client = client;
        this.loggers = loggers;
        this.defaultEventLogger = defaultEventLogger;
    }

    public static void main(String[] args) {
        ctx = new ClassPathXmlApplicationContext("spring.xml");
        App app = (App) ctx.getBean("app");
        app.logEvent(EventType.ERROR, "Some event for 1");
        app.logEvent(EventType.INFO, "Some event for 2");
        app.logEvent(null, "Some event for 3");
        ctx.registerShutdownHook();
    }

    private void logEvent(EventType type, String msg) {
        EventLogger logger = loggers.get(type);
        if(logger == null) {
            logger = defaultEventLogger;
        }
        Event event = (Event) ctx.getBean("event");
        event.setMsg(msg.replaceAll(this.client.getId(), this.client.getFullName()));
        logger.logEvent(event);
    }
}