package app.controllers.teamR;

import app.entities.teamR.Subscription;
import app.entities.teamR.User;
import app.exceptions.DatabaseException;
import app.persistence.ConnectionPool;
import app.persistence.teamR.SubscriptionMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;

public class SubscriptionController {
    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {
        app.post("/teamA/deleteSubscriptions", ctx -> deleteSubscription(ctx, connectionPool));
        app.post("/teamA/createSubscriptions", ctx -> addSubscription(ctx, connectionPool));
        app.get("/teamA/createSubscriptions", ctx -> ctx.render("teamA/add-subscription.html"));
        app.get("/teamA/removeSubscriptions", ctx -> listAllSubscriptions(ctx, connectionPool));

    }

    public static void listAllSubscriptions(Context ctx, ConnectionPool connectionPool) throws DatabaseException {
        User user = ctx.sessionAttribute("currentUser");
        assert user != null;
        List<Subscription> allSubscriptions = SubscriptionMapper.getAllSubscriptionInfo(user.getId(), connectionPool);
        ctx.attribute("allSubscriptions", allSubscriptions);
        ctx.render("teamA/remove-subscription.html");
    }

    public static void addSubscription(Context ctx, ConnectionPool connectionPool) throws DatabaseException {

        String subName = ctx.formParam("subName");
        String subCostString = ctx.formParam("subCost");
        String subUsageString = ctx.formParam("subUsage");
        String subCategory = ctx.formParam("subCategory");
        User currentUser = ctx.sessionAttribute("currentUser");

        try {
            if (subName.isEmpty() || subCostString.isEmpty() || subUsageString.isEmpty() || subCategory.isEmpty()) {
                ctx.render("teamA/add-subscription.html");
                return;
            }
            double subCost = Double.parseDouble(subCostString);
            int subUsage = Integer.parseInt(subUsageString);

            assert currentUser != null;
            int userId = currentUser.getId();
            SubscriptionMapper.createSubscription(subName, subCost, subUsage, subCategory, userId, connectionPool);
            ctx.render("teamA/add-subscription.html");

        } catch (DatabaseException e) {
            ctx.render("teamA/add-subscription.html");
            throw new DatabaseException("Could not add subscription");

        }
    }

    public static void deleteSubscription(Context ctx, ConnectionPool connectionPool) throws DatabaseException {
        List<String> subIds = ctx.formParams("subId");
        User user = ctx.sessionAttribute("currentUser");
        for (String id : subIds) {
            int subId = Integer.parseInt(id);
            SubscriptionMapper.deleteSubscription(user.getId(), subId, connectionPool);
        }

        List<Subscription> subscriptions = SubscriptionMapper.getAllSubscriptionInfo(user.getId(), connectionPool);
        ctx.attribute("allSubscriptions", subscriptions);
        ctx.redirect("/teamA/removeSubscriptions");
    }
}
