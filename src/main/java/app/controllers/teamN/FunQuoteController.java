package app.controllers.teamN;

import app.entities.teamN.FunQuote;
import app.exceptions.DatabaseException;
import app.persistence.ConnectionPool;
import app.persistence.teamN.FunQuoteMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;
import java.util.Random;

public class FunQuoteController {

    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {
        app.post("/generateLaugh", ctx -> addALaugh(ctx, connectionPool));

    }

    public static void addALaugh(Context ctx, ConnectionPool connectionPool) {
        try {
            List<FunQuote> allFunQuotes = FunQuoteMapper.getAllFunQuotes(connectionPool);
            Random randomInt = new Random();
            int number = randomInt.nextInt(allFunQuotes.size());
            FunQuote funQuote = FunQuoteMapper.getFunQuoteById(number, connectionPool);
            ctx.attribute("funSetup", funQuote.getSetup());
            ctx.attribute("funPunchLine", funQuote.getPunchLine());
            ctx.render("TeamN/index.html");
        } catch (DatabaseException e) {

        }
    }

}
