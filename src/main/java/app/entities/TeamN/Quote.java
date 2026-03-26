package app.entities.TeamN;

public class Quote {

    private int id;
    private String name;
    private String quote;
    private int year;

    public Quote(int id, String name, String quote, int year) {
        this.id = id;
        this.name = name;
        this.quote = quote;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String toString() {
        return quote + "\n- " + name + ", " + year;
    }
}