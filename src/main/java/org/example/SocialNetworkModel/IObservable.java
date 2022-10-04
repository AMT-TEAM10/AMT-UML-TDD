package org.example.SocialNetworkModel;

import java.util.List;

public interface IObservable {
    void subscribe(List<IObserver> observer) throws Twitter.SubscriberAlreadyExistsException;
    void unsubscribe(IObserver observer) throws Twitter.SubscriberNotFoundException, Twitter.EmptyListOfSubscribersException;
    void notifyObservers() throws Twitter.EmptyListOfSubscribersException;
}
