package app.entities.teamO;

public class Movie {
    private int film_id;
    private String title;
    private int year;

    public Movie(int film_id, String title, int year) {
        this.film_id = film_id;
        this.title = title;
        this.year = year;
    }

    public int getFilm_id() {
        return film_id;
    }

    public void setFilm_id(int film_id) {
        this.film_id = film_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
