package com.utils.reactive.observer;

import io.reactivex.Observable;
import io.reactivex.Observer;
import org.reactivestreams.Subscriber;

public class ObserverReactive extends Observable{
    private String result;
    public ObserverReactive() {
        Observable<String> observable = Observable.just("Hello");
        observable.subscribe(s -> result = s);

        String[] letters = {"a", "b", "c", "d", "e", "f", "g"};
        Observable<String> observables = Observable.fromArray(letters);
        observables.subscribe(
                i -> result += i,  //OnNext
                Throwable::printStackTrace, //OnError
                () -> result += "_Completed" //OnCompleted
        );
        
        assertTrue(result.equals("abcdefg_Completed"));
        assertTrue(result.equals("Hello"));
    }

    private void assertTrue(boolean abcdefgCompleted) {
        System.out.println("Assert True: "+abcdefgCompleted);
    }

    public static void startObserver(){

    }

    @Override
    protected void subscribeActual(Observer observer) {

    }
}
