package app.entities.teamG;

import java.util.List;

public class Hacks {

    private int id;
    private String videoLink;
    private String category;
    private String title;
    private String description;

    public Hacks(int id, String videoLink, String category, String title, String description) {
        this.id = id;
        this.videoLink = videoLink;
        this.category = category;
        this.title = title;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public String getCategory() {
        return category;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
