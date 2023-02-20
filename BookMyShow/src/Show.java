import java.util.ArrayList;
import java.util.List;

public class Show {

    private int id;
    private Movie movie;
    private Screen screen;
    private int startTime;
    private List<Integer> bookedSeatIds = new ArrayList<>();

    public int getShowId() {
        return this.id;
    }

    public void setShowId(int id) {
        this.id = id;
    }

    public Movie getMovie() {
        return this.movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Screen getScreen() {
        return this.screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public int getShowStartTime() {
        return this.startTime;
    }

    public void setShowStartTime(int startTime) {
        this.startTime = startTime;
    }

    public List<Integer> getBookedSeatIds() {
        return bookedSeatIds;
    }

    public void setBookedSeatIds(List<Integer> bookedSeatIds) {
        this.bookedSeatIds = bookedSeatIds;
    }
}
