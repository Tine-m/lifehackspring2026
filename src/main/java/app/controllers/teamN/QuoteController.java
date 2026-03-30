package app.controllers.teamN;

import app.entities.teamN.Quote;
import app.exceptions.DatabaseException;
import app.persistence.ConnectionPool;
import app.persistence.teamN.QuoteMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;
import java.util.Random;

public class QuoteController {

    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {
        app.get("teamN/index", ctx -> pickRandomQuote(ctx, connectionPool));
    }

    public static void pickRandomQuote(Context ctx, ConnectionPool connectionPool) {
        try {
            List<Quote> allQuotes = QuoteMapper.getAllQuotes(connectionPool);
            Random r = new Random();
            int selectedQuoteId = r.nextInt(allQuotes.size());
            Quote selectedQuote = allQuotes.get(selectedQuoteId);
            ctx.attribute("selectedquote", selectedQuote);

            //session thing
            //Vi bruger session her, fordi da vi redirecter til samme .get route som denne
            //funktion vil den resette alle data fra FunQuoteController
            ctx.attribute("funSetup", ctx.sessionAttribute("funSetup"));
            ctx.attribute("funPunchLine", ctx.sessionAttribute("funPunchLine"));

            ctx.render("teamN/index.html");
        } catch (DatabaseException e) {
            System.out.println(e.getMessage());
        }
    }
}