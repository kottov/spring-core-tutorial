import java.text.DateFormat;
import java.util.Date;

public class Event {

    private Integer id;
    private String msg;
    private Date date;
    private DateFormat df;

    public Event(Date date, DateFormat df) {
        this.date = date;
        this.df = df;
        this.id = (int) Math.floor(Math.random()*1000000);
    }

    public String toString() {
        return this.id + " - " + this.df.format(date) + " - " + this.msg + "\n";
    }

    void setMsg(String msg) {
        this.msg = msg;
    }
}
