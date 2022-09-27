package org.example.SocialNetworkModel;

public class Follower implements IObserver {
    @Override
    public void update(IObservable observable) {
        observable.notify();
    }
}
