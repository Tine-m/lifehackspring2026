package app.controllers.teamteachers;
import app.entities.teamteachers.Quote;
import app.persistence.ConnectionPool;
import app.persistence.teamteachers.QuoteMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;
import org.jetbrains.annotations.NotNull;

public class QuoteController {

    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {
        app.get("/philosophers", ctx -> philosophersHome(ctx));
        app.post("/philosophers", ctx -> ask(ctx, connectionPool));
        // app.get("/ask", ctx -> ctx.render("answer.html"));
    }

    private static void philosophersHome(@NotNull Context ctx) {
        ctx.render("teamteachers/index-quiz.html");
    }

    private static void ask(@NotNull Context ctx, ConnectionPool connectionPool) {
        String input = ctx.formParam("ask");
        Quote philosophicalAnswer = QuoteMapper.getPhilosophicalAnswer(input, connectionPool);
        ctx.attribute("quote", philosophicalAnswer.getQuote());
        ctx.attribute("philosopher", philosophicalAnswer.getPhilosopher());
        ctx.attribute("philosopherImage", philosophicalAnswer.getPicture());
        ctx.render("teamteachers/index-quiz.html");
        /*
        Her søges og hentes fra quote mapper - indtil da hardcodes et citat.
        Tænker også at vi kan gemme billednavn i db */
    }

    private static void getAnswer(Context ctx, ConnectionPool connectionPool){
    }

}
