package app.entities.teamK;

import app.persistence.ConnectionPool;
import app.persistence.teamK.WordMapper;

import java.util.ArrayList;
import java.util.List;
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
        if (result != null){
            wordList = result;
        } else {
            wordList = new ArrayList<>();
        }
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

    public List<Word> getWordList() {
        return wordList;
    }

    public int getWordsAmount() {
        return wordsAmount;
    }

    public int getWordsGuessedAmount() {
        return wordsGuessedAmount;
    }
}