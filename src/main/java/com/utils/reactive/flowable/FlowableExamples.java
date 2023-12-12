package com.utils.reactive.flowable;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

public class FlowableExamples {
    /**
     * Function that display a flow with pow(n,n) for each number (range:1 to 10) and filter when n%3 = true
     *         //Example: Pow of each element and after that filter only where n%3 = 0 is true.
     *         // 1*1=1, 2*2=4, 3*3= 9
     */
    public static void intFlowEmitted(){
        //Assembly Time
        //Using map is in order to transform the emmited items from the observable
        Flowable<Integer> flow = Flowable.range(1,10).map(v->v*v).filter(v->v % 3==0);
        flow.subscribe(System.out::println, Throwable::printStackTrace);
    }

    /**
     * Function that filter each numbers (range: 1 to 10001) and filter n%3,n%5,n%7 are true
     */
    public static void intFlowFewEmitted() {
        Flowable<Integer> intStream = Flowable.range(1, 10001).filter(v -> v % 3 == 0 || v % 5 == 0 || v % 7 == 0);
        intStream.subscribe(System.out::println, Throwable::printStackTrace);
    }

    /**
     * Function that match the values that are divisible between 3,5,7
     */
    public static void intFlowForThreeConditions(){
        Flowable<Integer> intStream = Flowable.range(1, 10001).filter(v -> v % 3 == 0).filter(v -> v % 5 == 0).filter(v -> v % 7 == 0);
        intStream.subscribe(System.out::println, Throwable::printStackTrace);
    }

    /**
     *
     */
    public static void intFlowEmittedSchedule(){
        try {
             Flowable.range(1,10).subscribeOn(Schedulers.newThread()).map(v->v*v).blockingSubscribe(System.out::println);
            Flowable.range(1, 5)
                    .subscribeOn(Schedulers.io())
                    .map(v -> v * v)
                    .blockingSubscribe(System.out::println);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
