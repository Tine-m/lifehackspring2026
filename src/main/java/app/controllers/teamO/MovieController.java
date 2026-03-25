package app.controllers.teamO;

import app.entities.teamO.Movie;
import app.exceptions.common.DatabaseException;
import app.persistence.ConnectionPool;
import app.persistence.teamO.MovieMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Random;

public class MovieController {

    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {
        app.get("/randommovies", ctx -> index(ctx));
        app.get("/teamO/randommovie", ctx -> showRandomMovie(ctx, connectionPool));
    }

    private static void index(@NotNull Context ctx) {
        ctx.render("teamO/index.html");
    }

    private static void showRandomMovie(@NotNull Context ctx, ConnectionPool connectionPool) {
        try {
            List<Movie> movies = MovieMapper.getAllMovies(connectionPool);

            if (movies.isEmpty()) {
                ctx.attribute("error", "Ingen film fundet");
                ctx.render("teamO/movie.html");
                return;
            }

            Random random = new Random();
            Movie randomMovie = movies.get(random.nextInt(movies.size()));

            ctx.attribute("movie", randomMovie);
            ctx.render("teamO/movie.html");

        } catch (DatabaseException e) {
            ctx.attribute("error", e.getMessage());
            ctx.render("teamO/movie.html");
        }
    }
}
