package app.entities.teamK;

import app.persistence.ConnectionPool;
import app.persistence.teamK.WordMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class WordList {

    WordMapper wm;
    List<Word> wordList = new ArrayList<>();
    int wordsAmount;
    int wordsGuessedAmount;

    public WordList (ConnectionPool cp){
        this.wm = new WordMapper(cp);
        generateFullList();
        wordsAmount = wordList.size();
        wordsGuessedAmount = 0;
    }

    public void generateFullList(){
        List<Word> result = wm.generateWordList();
        wordList = Objects.requireNonNullElseGet(result, ArrayList::new);
    }

    public void removeWordFromList(int id){
        wordList.removeIf(word -> word.getId() == id);
        if (wordList.isEmpty()){
            generateFullList();
        }
    }

    public void incrementWordsGuessed(){
        wordsGuessedAmount++;
    }

    public Word getRandomWordFromList(){
        Random rand = new Random();
        return wordList.get(rand.nextInt(wordList.size()));
    }

    public int getWordsAmount() {
        return wordsAmount;
    }

    public int getWordsGuessedAmount() {
        return wordsGuessedAmount;
    }
}