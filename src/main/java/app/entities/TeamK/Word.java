package app.entities.TeamK;

public class Word {
    private int id;
    private String word;
    private String hint;
    private String category;
    private int wordLength;

    public Word(int id, String word, String hint, String category, int wordLength) {
        this.id = id;
        this.word = word;
        this.hint = hint;
        this.category = category;
        this.wordLength = wordLength;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getWordLength() {
        return wordLength;
    }

    public void setWordLength(int wordLength) {
        this.wordLength = wordLength;
    }
}
