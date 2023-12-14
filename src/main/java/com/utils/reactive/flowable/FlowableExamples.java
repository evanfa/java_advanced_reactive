package com.utils.reactive.flowable;

import com.utils.commons.lib.FileReader;
import init.env.global.vars.EnvVars;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import java.util.ArrayList;

public class FlowableExamples {
    private static int indexId = 0;
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
            Flowable.range(1, 5)
                    .subscribeOn(Schedulers.io())
                    .map(v -> v * v)
                    .blockingSubscribe(System.out::println);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Function that performs a list search - Benchmark comparison againt streams
     * RxJava Implementation using Observable Class for Reactive Programming
     * @param searchString
     * @return ListWithMatchesFound
     */
    public static ArrayList<String> execSearchUsingSchedulersInCSVtoRecord(String searchString) throws Exception {
        ArrayList<String> searchResultList = new ArrayList<>();

        System.out.println("Size: "+FileReader.getRecordsInCSVFile(EnvVars.PATH_FILE_BITACORAS,0).size());
        Disposable disposable =
                Observable.fromIterable(FileReader.getRecordsInCSVFile(EnvVars.PATH_FILE_BITACORAS,0))
                .subscribeOn(Schedulers.io())
                .filter(item->item.matches(searchString))
                .subscribe(item-> System.out.println("Found: "+item),
                        error-> System.out.println("Fail: "+error),
                        ()-> System.out.println("Done Execution")
                );

        ((Disposable) disposable).dispose();
        /*try {
            Observable.fromIterable(FileReader.getRecordsInCSVFile(EnvVars.PATH_FILE_BITACORAS,0))
                    .subscribeOn(Schedulers.io())
                    .filter(item->item.matches(searchString))
                    .subscribe(item-> System.out.println("Found: "+item),
                            error-> System.out.println("Fail: "+error),
                            ()-> System.out.println("Done Execution")
                    );
        } catch (Exception e) {
            System.out.println("Exception:"+e);
        }*/
        return searchResultList;
    }
}
