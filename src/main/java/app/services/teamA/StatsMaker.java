package app.services.teamA;
import app.entities.teamA.Subscription;
import java.util.*;
import java.util.stream.Collectors;

public class StatsMaker {

    public static double monthlySubscriptionTotal(ArrayList<Subscription> subscriptions) {
        double sum = 0;
        for (Subscription subscription : subscriptions) {
            sum += subscription.getSubCost();
        }
        return sum;
    }

    public static double dailySubscriptionTotal(ArrayList<Subscription> subscriptions) {
        double sum = 0;
        for (Subscription subscription : subscriptions) {
            sum += subscription.getSubCost();
        }
        return sum / 30.5;
    }

    public static String mostExpensiveSubscriptionPerUsage(ArrayList<Subscription> subscriptions) {
        String name = "";
        double sum = 0;
        for (Subscription subscription : subscriptions) {
            if (sum < subscription.getSubCost() / subscription.getSubUsage()) {
                sum = subscription.getSubCost() / subscription.getSubUsage();
                name = subscription.getSubName();
            }
        }
        return name;
    }

    public static String cheapestSubscriptionPerUsage(ArrayList<Subscription> subscriptions) {
        String name = "";
        double sum = 0;
        for (Subscription subscription : subscriptions) {
            if (sum > subscription.getSubCost() / subscription.getSubUsage() || sum == 0) {
                sum = subscription.getSubCost() / subscription.getSubUsage();
                name = subscription.getSubName();
            }
        }
        return name;
    }

    public static HashMap<String, Double> allSubscriptionsPricePerUsage(ArrayList<Subscription> subscriptions) {
        HashMap<String, Double> subscriptionsPerUsageMap = new HashMap<>();

        for (Subscription subscription : subscriptions) {
            subscriptionsPerUsageMap.put(subscription.getSubName(), subscription.getSubCost() / subscription.getSubUsage());
        }
        return subscriptionsPerUsageMap;
    }

    public static List<Double> allSubscriptionsCosts(ArrayList<Subscription> subscriptions) {
        List<Double> allSupscriptions = new ArrayList<>();
        for (Subscription subscription : subscriptions) {
            allSupscriptions.add(subscription.getSubCost());
        }
        return allSupscriptions;
    }

    public static HashMap<String, Double> allSubscriptionCategoriesByPercent(ArrayList<Subscription> subscriptions) {
        double totalCost = 0;
        for (Subscription subscription : subscriptions) {
            totalCost += subscription.getSubCost();
        }
        double percentagePrice = totalCost / 100;
        Map<String, Double> byCategory = subscriptions.stream()
                .collect(Collectors.groupingBy(Subscription::getSubCategory, Collectors.summingDouble(Subscription::getSubCost)));

        byCategory.forEach((category, cost) -> {
            double percentage = (cost / percentagePrice);
            byCategory.put(category, percentage);
        });
        return (HashMap<String, Double>) byCategory;
    }

    public static HashMap<String, Integer> allUsersSubscriptionCount(ArrayList<Subscription> subscriptions) {
        HashMap<String, Integer> subscriptionList = new HashMap<>();

        List<Subscription> sortedSubscriptions = subscriptions.stream().sorted(Comparator.comparing(Subscription::getUserId)).toList();

        int userId = sortedSubscriptions.getFirst().getUserId();
        int tempUserId = 0;
        int subscriptionCount = 0;
        String userName = sortedSubscriptions.getFirst().getUserName();

        for (Subscription subscription : sortedSubscriptions) {
            tempUserId = subscription.getUserId();

            if (userId != tempUserId) {
                subscriptionList.put(userName, subscriptionCount);
                userId = subscription.getUserId();
                subscriptionCount = 0;
            }
            userName = subscription.getUserName();
            subscriptionCount++;
        }
        subscriptionList.put(userName, subscriptionCount);
        return subscriptionList;
    }


    public static Map<String, Long> threeMostCommonSubscriptions(ArrayList<Subscription> subscriptions) {

        List<Subscription> sortedSubs = subscriptions.stream()
                .sorted(Comparator.comparing(Subscription::getSubName, String.CASE_INSENSITIVE_ORDER))
                .toList();

        Map<String, Long> sortedSubs2 = sortedSubs.stream().collect(Collectors.groupingBy(Subscription::getSubName, Collectors.counting()));


        Map<String, Long> top3 = new LinkedHashMap<>();
        sortedSubs2.entrySet().stream()
                .sorted((a, b) -> Long.compare(b.getValue(), a.getValue()))
                .limit(3)
                .forEach(entry -> top3.put(entry.getKey(), entry.getValue()));


        return top3;

    }

    public static Map<String, Double> totalPricePerCategory(ArrayList<Subscription> subscriptions){

        Map<String, Double> newMap = subscriptions.stream()
                .collect(Collectors.groupingBy(Subscription::getSubCategory, Collectors.summingDouble(Subscription::getSubCost)));

        return newMap;
    }

}

