package app.controllers.TeamN;

import app.entities.TeamN.FunQuote;
import app.exceptions.common.DatabaseException;
import app.persistence.ConnectionPool;
import app.persistence.TeamN.FunQuoteMapper;
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
            ctx.render("../../../../resources/templates/TeamN/index.html");
        } catch (DatabaseException e) {

        }
    }

}
