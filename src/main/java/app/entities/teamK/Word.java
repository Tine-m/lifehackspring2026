package app.entities.teamK;

import java.util.Objects;

public class Word {

    private int id;
    private String word;
    private String hint;
    private String category;
    private int wordLength;
    private String[] wordArray;

    public Word(int id, String word, String hint, String category, int wordLength) {
        this.id = id;
        this.word = word;
        this.hint = hint;
        this.category = category;
        this.wordLength = wordLength;
        this.wordArray = word.split("");
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

    public String[] getWordArray() {
        return wordArray;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Word word1 = (Word) o;
        return id == word1.id
                && wordLength == word1.wordLength
                && Objects.equals(word, word1.word)
                && Objects.equals(hint, word1.hint)
                && Objects.equals(category, word1.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, word, hint, category, wordLength);
    }
}
