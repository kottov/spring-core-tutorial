import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    private Client client;
    private EventLogger eventLogger;
    private static ConfigurableApplicationContext ctx;

    public App(Client client, EventLogger eventLogger) {
        this.client = client;
        this.eventLogger = eventLogger;
    }

    public static void main(String[] args) {
        ctx = new ClassPathXmlApplicationContext("spring.xml");
        App app = (App) ctx.getBean("app");
        for (int i = 0; i < 5; i++)
        app.logEvent("Some event for " + i);
        ctx.registerShutdownHook();
    }

    private void logEvent(String msg) {
        Event event = (Event) ctx.getBean("event");
        event.setMsg(msg.replaceAll(this.client.getId(), this.client.getFullName()));
        this.eventLogger.logEvent(event);
    }
}