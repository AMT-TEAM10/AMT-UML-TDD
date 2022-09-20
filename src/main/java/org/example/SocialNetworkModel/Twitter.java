package org.example.SocialNetworkModel;

import org.example.SocialNetworkModel.exception.EmptyListOfSubscribersException;
import org.example.SocialNetworkModel.exception.SubscriberAlreadyExistsException;
import org.example.SocialNetworkModel.exception.SubscriberNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class Twitter implements IObservable{
    private final List<IObserver> observers = new ArrayList<>();
    private final List<String> twits = new ArrayList<>();

    public Twitter(){}

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
            if (observers.contains(ob)) {
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
    public void signal() throws EmptyListOfSubscribersException {
        if (observers.isEmpty()) {
            throw new EmptyListOfSubscribersException();
        }

        for (IObserver ob: observers) {
            ob.update(this);
        }
    }

    public void post(String twit){
        throw new RuntimeException("Not Implemented");
    }

    public String getLastTwit(){
        throw new RuntimeException("Not Implemented");
    }

    public boolean exists(IObserver followerToFind){
        throw new RuntimeException("Not Implemented");
    }
}
