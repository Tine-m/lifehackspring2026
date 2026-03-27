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

        app.delete("/word/delete", ctx -> {
            Word word = ctx.bodyAsClass(Word.class);
            wordList.removeWordFromList(word);
            ctx.status(204);
        });

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
