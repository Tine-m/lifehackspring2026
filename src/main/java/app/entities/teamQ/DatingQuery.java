package app.entities.teamQ;

public class DatingQuery {
    private int id;
    private String title;
    private String content;
    private int like_counter;

    public DatingQuery(int id, String title, String content, int like_counter){
        this.id = id;
        this.title = title;
        this.content = content;
        this.like_counter = like_counter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLike_counter() {
        return like_counter;
    }
}
