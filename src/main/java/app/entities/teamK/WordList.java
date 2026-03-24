package app.entities.teamK;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WordList {

    List<Word> wordList = new ArrayList<>();

    public void addWordToList(Word word){
        wordList.add(word);
    }

    public void removeWordFromList(Word word){
        wordList.remove(word);
    }

    public Word getRandomWordFromList(){
        Random rand = new Random();
        Word randomWord = wordList.get(rand.nextInt(wordList.size()));
        return randomWord;
    }

    public List<Word> getWordList() {
        return wordList;
    }
}