package org.example.SocialNetworkModel;

import org.example.SocialNetworkModel.exception.EmptyListOfSubscribersException;
import org.example.SocialNetworkModel.exception.SubscriberAlreadyExistsException;
import org.example.SocialNetworkModel.exception.SubscriberNotFoundException;

import java.util.List;

public interface IObservable {
    void subscribe(List<IObserver> observer) throws SubscriberAlreadyExistsException;
    void unsubscribe(IObserver observer) throws SubscriberNotFoundException, EmptyListOfSubscribersException;
    void signal() throws EmptyListOfSubscribersException;
}
