package app.controllers.teamB;

import app.entities.teamB.Quotes;
import app.persistence.ConnectionPool;
import app.persistence.teamB.QuoteMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;
import org.jetbrains.annotations.NotNull;

public class TeamBQuoteController {

    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {
        app.get("/teamB", ctx -> quoteHome(ctx, connectionPool));
        app.post("/teamB", ctx -> newQuote(ctx, connectionPool));
    }

    private static void quoteHome(@NotNull Context ctx, ConnectionPool connectionPool) {
        Quotes quote = QuoteMapper.getRandomQuote(connectionPool);
        ctx.attribute("quote", quote.getQuote());
        ctx.render("teamB/index.html");
    }

    private static void newQuote(@NotNull Context ctx, ConnectionPool connectionPool) {
        Quotes quote = QuoteMapper.getRandomQuote(connectionPool);
        ctx.attribute("quote", quote.getQuote());
        ctx.render("teamB/index.html");
    }
}