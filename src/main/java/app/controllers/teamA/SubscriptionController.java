package app.controllers.teamA;
import app.entities.teamA.Subscription;
import app.entities.teamA.User;
import app.exceptions.DatabaseException;
import app.persistence.ConnectionPool;
import app.persistence.teamA.SubscriptionMapper;
import app.services.teamA.StatsMaker;
import io.javalin.Javalin;
import io.javalin.http.Context;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubscriptionController {
    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {
        app.post("/teamA/deleteSubscriptions", ctx -> deleteSubscription(ctx, connectionPool));
        app.post("/teamA/createSubscriptions", ctx -> addSubscription(ctx, connectionPool));
        app.get("/teamA/createSubscriptions", ctx -> renderAddSubscription(ctx));
        app.get("/teamA/removeSubscriptions", ctx -> listAllSubscriptionsInRemove(ctx, connectionPool));
        app.get("/teamA/categoryData", ctx -> allSubscriptionCategoriesByPercent(ctx, connectionPool));
        app.get("/teamA/subscriptionCost", ctx -> allSubscriptionsCost(ctx, connectionPool));
        app.get("/teamA/subscriptionUsage", ctx -> allSubscriptionsUsage(ctx, connectionPool));
        app.get("/teamA/allUsersSubscriptionsCount", ctx -> allUsersSubscriptionsCount(ctx, connectionPool));
        app.get("/teamA/totalPricePerCategory", ctx -> totalPricePerCategory(ctx, connectionPool));
    }


    public static void listAllSubscriptionsInRemove(Context ctx, ConnectionPool connectionPool) throws DatabaseException {
        User user = ctx.sessionAttribute("currentUser");
        assert user != null;
        List<Subscription> allSubscriptions = SubscriptionMapper.getAllSubscriptionInfo(user.getId(), connectionPool);
        UserController.showUsername(ctx);
        ctx.attribute("allSubscriptions", allSubscriptions);
        ctx.render("teamA/remove-subscription.html");
    }

    public static void renderAddSubscription(Context ctx){
        UserController.showUsername(ctx);
        ctx.render("teamA/add-subscription.html");
    }

    public static void addSubscription(Context ctx, ConnectionPool connectionPool) throws DatabaseException {

        String subName = ctx.formParam("subName");
        String subCostString = ctx.formParam("subCost");
        String subUsageString = ctx.formParam("subUsage");
        String subCategory = ctx.formParam("subCategory");
        User currentUser = ctx.sessionAttribute("currentUser");

        try {
            if (subName.isEmpty() || subCostString.isEmpty() || subUsageString.isEmpty() || subCategory.isEmpty()) {
                UserController.showUsername(ctx);
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
        UserController.showUsername(ctx);
        ctx.redirect("/teamA/removeSubscriptions");
    }


    public static void getAllStats(Context ctx, ConnectionPool connectionPool) throws DatabaseException {
        monthlySubscriptionTotal(ctx, connectionPool);
        dailySubscriptionTotal(ctx, connectionPool);
        mostExpensiveSubscriptionPerUsage(ctx, connectionPool);
        cheapestSubscriptionPerUsage(ctx, connectionPool);
        allSubscriptionsUsage(ctx, connectionPool);
        allSubscriptionsCost(ctx, connectionPool);
        topThreeSubscriptions(ctx, connectionPool);
        UserController.showUsername(ctx);
    }


    public static void monthlySubscriptionTotal(Context ctx, ConnectionPool connectionPool) throws DatabaseException {
        User currentUser = ctx.sessionAttribute("currentUser");
        double totalMonthlyCost = StatsMaker.monthlySubscriptionTotal(SubscriptionMapper.getAllSubscriptionInfo(currentUser.getId(), connectionPool));
        String totalMonthlyCostString = String.format("%.2f", totalMonthlyCost) + " DKK";
        ctx.attribute("totalMonthlyCost", totalMonthlyCostString);
    }

    public static void dailySubscriptionTotal(Context ctx, ConnectionPool connectionPool) throws DatabaseException {
        User currentUser = ctx.sessionAttribute("currentUser");
        double totalDailyCost = StatsMaker.dailySubscriptionTotal(SubscriptionMapper.getAllSubscriptionInfo(currentUser.getId(), connectionPool));
        String totalDailyCostString = String.format("%.2f", totalDailyCost) + " DKK";
        ctx.attribute("totalDailyCost", totalDailyCostString);
    }

    public static void mostExpensiveSubscriptionPerUsage(Context ctx, ConnectionPool connectionPool) throws DatabaseException {
        User currentUser = ctx.sessionAttribute("currentUser");
        String mostExpensiveSub = StatsMaker.mostExpensiveSubscriptionPerUsage(SubscriptionMapper.getAllSubscriptionInfo(currentUser.getId(), connectionPool));
        ctx.attribute("mostExpensiveSub", mostExpensiveSub);
    }

    public static void cheapestSubscriptionPerUsage(Context ctx, ConnectionPool connectionPool) throws DatabaseException {
        User currentUser = ctx.sessionAttribute("currentUser");
        String cheapestSub = StatsMaker.cheapestSubscriptionPerUsage(SubscriptionMapper.getAllSubscriptionInfo(currentUser.getId(), connectionPool));
        ctx.attribute("cheapestSub", cheapestSub);
    }

    public static void allSubscriptionCategoriesByPercent(Context ctx, ConnectionPool connectionPool) throws DatabaseException {
        User currentUser = ctx.sessionAttribute("currentUser");
        Map<String, Double> data = StatsMaker.allSubscriptionCategoriesByPercent(SubscriptionMapper.getAllSubscriptionInfo(currentUser.getId(), connectionPool));
        ctx.json(data);
    }

    public static void allSubscriptionsUsage(Context ctx, ConnectionPool connectionPool) throws DatabaseException {
        User currentUser = ctx.sessionAttribute("currentUser");
        Map<String, Double> data = StatsMaker.allSubscriptionsPricePerUsage(SubscriptionMapper.getAllSubscriptionInfo(currentUser.getId(), connectionPool));
        ctx.json(data);

    }

    public static void allSubscriptionsCost(Context ctx, ConnectionPool connectionPool) throws DatabaseException {
        User currentUser = ctx.sessionAttribute("currentUser");
        int userId = currentUser.getId();

        List<Subscription> subs = SubscriptionMapper.getAllSubscriptionInfo(userId, connectionPool);
        List<Double> costs = StatsMaker.allSubscriptionsCosts(SubscriptionMapper.getAllSubscriptionInfo(currentUser.getId(), connectionPool));

        Map<String, Double> data = new HashMap<>();

        for (int i = 0; i < subs.size(); i++) {
            data.put(subs.get(i).getSubName(), costs.get(i));
        }
        ctx.json(data);
    }

    public static void allUsersSubscriptionsCount(Context ctx, ConnectionPool connectionPool) throws DatabaseException {
        Map<String, Integer> data = StatsMaker.allUsersSubscriptionCount(SubscriptionMapper.getAllSubscriptions(connectionPool));

        ctx.json(data);
    }

    public static void topThreeSubscriptions(Context ctx, ConnectionPool connectionPool) throws DatabaseException {
        Map<String, Long> topThree = StatsMaker.threeMostCommonSubscriptions(SubscriptionMapper.getAllSubscriptions(connectionPool));
        List<String> threeNames = new ArrayList<>(topThree.keySet());

        String first = threeNames.getFirst();
        String second = threeNames.get(1);
        String third = threeNames.getLast();

        ctx.attribute("first", first);
        ctx.attribute("second", second);
        ctx.attribute("third", third);
    }

    public static void totalPricePerCategory(Context ctx, ConnectionPool connectionPool) throws DatabaseException {
        User currentUser = ctx.sessionAttribute("currentUser");
        Map<String, Double> data = StatsMaker.totalPricePerCategory(SubscriptionMapper.getAllSubscriptionInfo(currentUser.getId(), connectionPool));

        ctx.json(data);
    }
}
