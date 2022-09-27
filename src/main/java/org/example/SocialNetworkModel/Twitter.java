package org.example.SocialNetworkModel;

import java.util.ArrayList;
import java.util.List;

public class Twitter implements IObservable {
    private final List<IObserver> observers = new ArrayList<>();
    private final List<String> twits = new ArrayList<>();

    public Twitter() {
    }

    public Twitter(List<IObserver> observers) {
        this.observers.addAll(observers);
    }

    public List<IObserver> getObservers() {
        return observers;
    }

    public List<String> getTwits() {
        return twits;
    }

    @Override
    public void subscribe(List<IObserver> observer) throws SubscriberAlreadyExistsException {
        for (IObserver ob : observer) {
            if (exists(ob)) {
                throw new SubscriberAlreadyExistsException();
            } else {
                observers.add(ob);
            }
        }
    }

    @Override
    public void unsubscribe(IObserver observer) throws SubscriberNotFoundException, EmptyListOfSubscribersException {
        if (observers.isEmpty()) {
            throw new EmptyListOfSubscribersException();
        }

        if (!observers.remove(observer))
            throw new SubscriberNotFoundException();
    }

    @Override
    public void notifyObservers() throws EmptyListOfSubscribersException {
        if (observers.isEmpty()) {
            throw new EmptyListOfSubscribersException();
        }

        for (IObserver ob : observers) {
            ob.update(this);
        }
    }

    public void post(String twit) {
        twits.add(twit);
    }

    public String getLastTwit() {
        if (twits.isEmpty()) {
            return null;
        }
        return twits.get(twits.size() - 1);
    }

    public boolean exists(IObserver followerToFind) {
        return observers.contains(followerToFind);
    }


    public static class TwitterException extends RuntimeException {
    }

    public static class EmptyListOfSubscribersException extends TwitterException {
    }

    public static class SubscriberAlreadyExistsException extends TwitterException {
    }

    public static class SubscriberNotFoundException extends TwitterException {
    }
}
