package app.controllers.teamM;

import app.entities.teamM.TracklySubscription;
import app.persistence.ConnectionPool;
import app.services.teamM.TracklySubscriptionService;
import io.javalin.Javalin;

public class TracklySubscriptionController {
    private TracklySubscriptionService subscriptionService;

    public TracklySubscriptionController(ConnectionPool connectionPool) {
        this.subscriptionService = new TracklySubscriptionService(connectionPool);
    }

    public void addRoutes(Javalin app) {
        app.get("/teamM", ctx -> ctx.render("teamM/index.html"));

        app.get("/teamM/add", ctx -> ctx.render("teamM/add.html"));

        app.post("/teamM/add", ctx -> {
            String name = ctx.formParam("name");
            String priceAsString = ctx.formParam("price");
            String dueDate = ctx.formParam("Ddate");

            double price = Double.parseDouble(priceAsString);

            TracklySubscription subscription = new TracklySubscription(name, price, dueDate);
            subscriptionService.addSubscription(subscription);

            ctx.redirect("/teamM/view");
        });

        app.get("/teamM/remove", ctx -> {
            ctx.attribute("subscriptions", subscriptionService.getAllSubscriptions());
            ctx.render("teamM/remove.html");
        });

        app.post("/teamM/remove", ctx -> {
            String name = ctx.formParam("subscriptionName");
            subscriptionService.deleteSubscriptionByName(name);
            ctx.redirect("/teamM/view");
        });

        app.get("/teamM/view", ctx -> {
            ctx.attribute("subscriptions", subscriptionService.getAllSubscriptions());
            ctx.render("teamM/view.html");
        });
    }
}