package app.controllers.teamK;

import app.entities.teamK.Word;
import app.entities.teamK.WordList;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.http.Context;

import java.util.List;

public class WordController {

    private final WordList wordList;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public WordController (WordList wordList){
        this.wordList = wordList;
    }

    public void getAllWords(Context context){

        wordList.generateFullList();
        List<Word> words = wordList.getWordList();

        try {
            context.contentType("application/json");
            context.result(objectMapper.writeValueAsString(words));
        } catch (Exception e) {
            context.status(500).result("{\"error\":\"kunne ikke hente ord\"}");
        }
    }

    public void getWordsByCategory(Context context){
        String category = context.queryParam("category");

        wordList.generateFullList();
        List<Word> words = wordList.getWordList()
                .stream()
                .filter(w -> w.getCategory().equalsIgnoreCase(category))
                .toList();

        try {
            context.contentType("application/json");
            context.result(objectMapper.writeValueAsString(words));
        } catch (Exception e) {
            context.status(500).result("{\"error\":\"kunne ikke filtrere ord\"}");
        }
    }

    public void getCategory (Context context){
        wordList.generateFullList();
        List<String> categories = wordList.getWordList()
                .stream()
                .map(Word::getCategory)
                .distinct()
                .sorted()
                .toList();

        try {
            context.contentType("application/json");
            context.result(objectMapper.writeValueAsString(categories));
        } catch (Exception e) {
            context.status(500).result("{\"error\":\"kunne ikke hente kategorier\"}");
        }
    }

}
