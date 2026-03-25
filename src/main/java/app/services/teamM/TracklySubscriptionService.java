package app.services.teamM;

import app.entities.teamM.TracklySubscription;
import app.persistence.ConnectionPool;
import app.persistence.teamM.TracklySubscriptionMapper;

import java.util.ArrayList;

public class TracklySubscriptionService {
    private TracklySubscriptionMapper subscriptionMapper;

    public TracklySubscriptionService(ConnectionPool connectionPool) {
        this.subscriptionMapper = new TracklySubscriptionMapper(connectionPool);
    }

    public void addSubscription(TracklySubscription subscription) {
        subscriptionMapper.addSubscription(subscription);
    }

    public ArrayList<TracklySubscription> getAllSubscriptions() {
        return subscriptionMapper.getAllSubscriptions();
    }

    public void deleteSubscriptionByName(String name) {
        subscriptionMapper.deleteSubscriptionByName(name);
    }
}