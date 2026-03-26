package app.services.teamM;

import app.entities.teamM.Subscription;
import app.persistence.ConnectionPool;
import app.persistence.teamM.SubscriptionMapper;

import java.util.ArrayList;

public class TracklySubscriptionService {
    private SubscriptionMapper subscriptionMapper;

    public TracklySubscriptionService(ConnectionPool connectionPool) {
        this.subscriptionMapper = new SubscriptionMapper(connectionPool);
    }

    public void addSubscription(Subscription subscription) {
        subscriptionMapper.addSubscription(subscription);
    }

    public ArrayList<Subscription> getAllSubscriptions() {
        return subscriptionMapper.getAllSubscriptions();
    }

    public void deleteSubscriptionByName(String name) {
        subscriptionMapper.deleteSubscriptionByName(name);
    }
}