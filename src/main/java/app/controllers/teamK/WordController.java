package app.controllers.teamK;

import app.entities.teamK.Word;
import app.entities.teamK.WordList;
import app.persistence.ConnectionPool;
import io.javalin.Javalin;

import java.nio.file.Files;
import java.nio.file.Path;

public class WordController {

    public static void addRoutes(Javalin app, ConnectionPool cp){

        WordList wordList = new WordList(cp);

        app.get("/word", ctx ->{
            Word word = wordList.getRandomWordFromList();
            ctx.json(word);
        });

        app.delete("/word/delete/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            wordList.removeWordFromList(id);
            ctx.status(204);
        });

        app.get("/words-Amount", ctx -> {
            int amount = wordList.getWordsAmount();
            ctx.json(amount);
        });

        app.get("/words-Amount-Guessed", ctx -> {
            int amountGuessed = wordList.getWordsGuessedAmount();
            ctx.json(amountGuessed);
        });

        app.post("increment-guessed-words", ctx -> wordList.incrementWordsGuessed());

        app.get("/codle", ctx -> {
            String html = Files.readString(Path.of("src/main/resources/templates/teamK/index.html"));
            ctx.html(html);
        });

        app.get("codle/play", ctx -> {
            String html = Files.readString(Path.of("src/main/resources/templates/teamK/screen-game.html"));
            ctx.html(html);
        });

        app.get("codle/completed", ctx -> {
            String html = Files.readString(Path.of("src/main/resources/templates/teamK/screen-complete.html"));
            ctx.html(html);
        });

    }
}
