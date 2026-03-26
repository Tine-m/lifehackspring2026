package app.controllers.TeamN;

import app.entities.TeamN.Quote;
import app.exceptions.common.DatabaseException;
import app.persistence.ConnectionPool;
import app.persistence.TeamN.QuoteMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;
import java.util.Random;

public class QuoteController {

    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {
        app.get("/randomquote", ctx -> pickRandomQuote(ctx, connectionPool));
    }

    public static void pickRandomQuote(Context ctx, ConnectionPool connectionPool) {
        try {
            List<Quote> allQuotes = QuoteMapper.getAllQuotes(connectionPool);
            Random r = new Random();
            int selectedQuoteId = r.nextInt(allQuotes.size());
            Quote selectedQuote = QuoteMapper.getQuoteById(selectedQuoteId, connectionPool);
            ctx.attribute("selectedquote", selectedQuote);
            ctx.render("../../../../resources/templates/TeamN/index.html");
        } catch (DatabaseException e) {
            System.out.println(e.getMessage());
        }
    }
}