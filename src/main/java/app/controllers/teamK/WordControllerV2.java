package app.controllers.teamK;

import app.entities.teamK.Word;
import app.entities.teamK.WordList;
import app.persistence.ConnectionPool;
import io.javalin.Javalin;

public class WordControllerV2 {

    private Javalin app;
    private WordList wordList;

    public WordControllerV2 (Javalin app, ConnectionPool cp){
        this.app = app;
        this.wordList = new WordList(cp);
        addRoutes();
    }

    public void addRoutes(){
        app.get("/word", ctx ->{
            Word word = wordList.getRandomWordFromList();
            ctx.json(word);
        });
    }


}
