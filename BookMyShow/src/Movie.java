public class Movie {

    private int id;
    private String name;
    int movieDurationInMinutes;
    //other details like Genere, Language etc.

    public int getMovieId() {
        return this.id;
    }

    public void setMovieId(int id) {
        this.id = id;
    }

    public String getMovieName() {
        return this.name;
    }

    public void setMovieName(String name) {
        this.name = name;
    }

    public int getMovieDuration() {
        return this.movieDurationInMinutes;
    }

    public void setMovieDuration(int movieDuration) {
        this.movieDurationInMinutes = movieDuration;
    }
}
