package org.example.SocialNetworkModel;

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
    public void subscribe(List<IObserver> observer) {
        observers.addAll(observer);
    }

    @Override
    public void unsubscribe(IObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void signal() {
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
