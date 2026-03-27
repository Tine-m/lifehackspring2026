package app.controllers.teamM;

import app.entities.teamM.Subscription;
import app.persistence.ConnectionPool;
import app.services.teamM.TracklySubscriptionService;
import io.javalin.Javalin;

import java.util.ArrayList;

public class SubscriptionController {
    private TracklySubscriptionService subscriptionService;

    public SubscriptionController(ConnectionPool connectionPool) {
        this.subscriptionService = new TracklySubscriptionService(connectionPool);
    }

    public void addRoutes(Javalin app) {
        app.get("/teamM", ctx -> ctx.render("teamM/index.html"));

        app.get("/teamM/add", ctx -> ctx.render("teamM/add.html"));

        app.post("/teamM/add", ctx -> {
            String subscriptionName = ctx.formParam("name");
            String subscriptionPriceInput = ctx.formParam("price");
            String subscriptionDueDate = ctx.formParam("dueDate");

            double subscriptionPrice = Double.parseDouble(subscriptionPriceInput);

            Subscription subscription = new Subscription(subscriptionName, subscriptionPrice, subscriptionDueDate);
            subscriptionService.addSubscription(subscription);

            ctx.redirect("/teamM");
        });

        app.get("/teamM/remove", ctx -> {
            ctx.attribute("subscriptions", subscriptionService.getAllSubscriptions());
            ctx.render("teamM/remove.html");
        });

        app.post("/teamM/remove", ctx -> {
            String subscriptionName = ctx.formParam("subscriptionName");
            subscriptionService.deleteSubscriptionByName(subscriptionName);
            ctx.redirect("/teamM");
        });

        app.get("/teamM/view", ctx -> {

            ArrayList<Subscription> subscriptions = subscriptionService.getAllSubscriptions();

            double totalMonthly = subscriptionService.calculateTotalMonthly(subscriptions);
            double totalYearly = subscriptionService.calculateTotalYearly(totalMonthly);

            ctx.attribute("subscriptions", subscriptions);
            ctx.attribute("totalMonthly", totalMonthly);
            ctx.attribute("totalYearly", totalYearly);

            ctx.render("teamM/view.html");
        });
    }
}