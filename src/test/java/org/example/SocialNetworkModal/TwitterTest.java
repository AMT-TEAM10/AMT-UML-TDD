package org.example.SocialNetworkModal;

import org.example.SocialNetworkModel.Follower;
import org.example.SocialNetworkModel.IObserver;
import org.example.SocialNetworkModel.Twitter;
import org.example.SocialNetworkModel.exception.EmptyListOfSubscribersException;
import org.example.SocialNetworkModel.exception.SubscriberAlreadyExistsException;
import org.example.SocialNetworkModel.exception.SubscriberNotFoundException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TwitterTest {

    private static Twitter twitter;

    @BeforeAll
    public static void setup() {
        twitter = new Twitter();
    }

    @Test
    public void Observers_AfterInstanciationWithoutObservers_Success() {
        int exceptedAmountOfObservers = 0;
        assertEquals(exceptedAmountOfObservers, twitter.getObservers().size());
    }

    @Test
    public void Observers_AfterInstanciationWithObservers_Succes() {
        int exceptedAmountOfObservers = 10;
        twitter = new Twitter(generateObserver(exceptedAmountOfObservers));
        assertEquals(exceptedAmountOfObservers, twitter.getObservers().size());
    }

    @Test
    public void Twits_AfterInstanciation_Success() {
        int exceptedAmountOfTwits = 0;
        twitter = new Twitter();
        assertEquals(exceptedAmountOfTwits, twitter.getTwits().size());
    }

    @Test
    public void Notify_EmptyListOfSubscriber_ThrowsException() {
        assertThrows(EmptyListOfSubscribersException.class, () -> twitter.signal());
    }

    @Test
    public void Subscribe_AddFirstSubscribers_Success() {
        int expectedAmountOfSubscribers = 15;
        List<IObserver> followers = generateObserver(expectedAmountOfSubscribers);
        twitter.subscribe(followers);

        assertEquals(expectedAmountOfSubscribers, twitter.getObservers().size());
    }

    @Test
    public void Subscribe_AddSubscribersToExistingList_Success() {
        int expectedAmountOfSubscribers = 30;
        List<IObserver> followers = generateObserver(expectedAmountOfSubscribers / 2);
        twitter.subscribe(followers);
        List<IObserver> followers2nd = generateObserver(expectedAmountOfSubscribers / 2);
        twitter.subscribe(followers2nd);
        assertEquals(expectedAmountOfSubscribers, twitter.getObservers().size());
    }

    @Test
    public void Subscribe_SubscriberAlreadyExists_ThrowsException() {
        int expectedAmountOfSubscribers = 15;
        List<IObserver> followers = generateObserver(expectedAmountOfSubscribers);
        twitter.subscribe(followers);
        List<IObserver> followersDuplicate = new LinkedList<>();
        followersDuplicate.add(followers.get(0));

        assertThrows(SubscriberAlreadyExistsException.class, () -> twitter.subscribe(followersDuplicate));
    }

    @Test
    public void Unsubscribe_NominalCase_Success() {
        List<IObserver> followers = generateObserver(20);
        twitter.subscribe(followers);
        twitter.unsubscribe(followers.get(10));
        assertEquals(followers.size() - 1, twitter.getObservers().size());
    }

    @Test
    public void Unsubscribe_EmptyListOfSubscriber_ThrowsException() {
        Follower followerToRemove = new Follower();
        assertThrows(EmptyListOfSubscribersException.class, () -> twitter.unsubscribe(followerToRemove));
    }

    @Test
    public void Unsubscribe_SubscriberNotFound_ThrowsException() {
        IObserver followerNotFound = new Follower();
        twitter.subscribe(generateObserver(10));
        assertThrows(SubscriberNotFoundException.class, () -> twitter.unsubscribe(followerNotFound));
    }


    private List<IObserver> generateObserver(int amountOfObserverToCreate) {
        List<IObserver> observers = new LinkedList<>();
        for (int i = 0; i < amountOfObserverToCreate; i++) {
            observers.add(new Follower());
        }
        return observers;
    }
}
