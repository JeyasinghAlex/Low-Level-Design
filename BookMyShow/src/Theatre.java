import Enums.City;

import java.util.ArrayList;
import java.util.List;

public class Theatre {

    private int id;
    private String address;
    private City city;
    private List<Screen> screen = new ArrayList<>();
    private List<Show> shows = new ArrayList<>();

    public int getTheatreId() {
        return this.id;
    }

    public void setTheatreId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Screen> getScreen() {
        return this.screen;
    }

    public void setScreen(List<Screen> screen) {
        this.screen = screen;
    }

    public List<Show> getShows() {
        return this.shows;
    }

    public void setShows(List<Show> shows) {
        this.shows = shows;
    }

    public City getCity() {
        return this.city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
