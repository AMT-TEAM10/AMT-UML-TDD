package org.example.SocialNetworkModel;

import java.util.List;

public interface IObservable {
    void subscribe(List<IObserver> observer);
    void unsubscribe(IObserver observer);
    void signal();
}
