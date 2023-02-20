import java.util.ArrayList;
import java.util.List;

public class Screen {

    private int id;
    private List<Seat> seats = new ArrayList<>();

    public int getScreenId() {
        return this.id;
    }

    public void setScreenId(int id) {
        this.id = id;
    }

    public List<Seat> getSeats() {
        return this.seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }
}
