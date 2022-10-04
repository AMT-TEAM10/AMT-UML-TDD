package org.example.SocialNetworkModel;

import org.example.SocialNetworkModel.exception.EmptyListOfSubscribersException;
import org.example.SocialNetworkModel.exception.SubscriberAlreadyExistsException;
import org.example.SocialNetworkModel.exception.SubscriberNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class Twitter implements IObservable {
    private final List<IObserver> observers = new ArrayList<>();
    private final List<String> twits = new ArrayList<>();

    public Twitter() {
    }

    public Twitter(List<IObserver> observers) throws SubscriberAlreadyExistsException {
        subscribe(observers);
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
            }
            observers.add(ob);
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
    public void signal() throws EmptyListOfSubscribersException {
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
}
