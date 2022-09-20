package org.example.SocialNetworkModel;

import java.util.ArrayList;
import java.util.List;

public class Twitter implements IObservable{
    private final List<IObserver> observers = new ArrayList<>();
    private final List<String> twits = new ArrayList<>();

    public Twitter(){}

    public Twitter(List<IObserver> observers) {
        throw new RuntimeException("Not Implemented");
    }

    public List<IObserver> getObservers() {
        return observers;
    }

    public List<String> getTwits() {
        return twits;
    }

    @Override
    public void subscribe(List<IObserver> observer) {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public void unsubscribe(IObserver observer) {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public void signal() {
        throw new RuntimeException("Not Implemented");
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
