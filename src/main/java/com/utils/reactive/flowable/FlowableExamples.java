package com.utils.reactive.flowable;

import com.utils.commons.lib.FileReader;
import init.env.global.vars.EnvVars;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.utils.commons.lib.FileReader.loadTotalRecordsFromCSVFile;

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
     * This function has an observer that blocking it current thread to search, match and end.
     * RxJava - Reactive programming
     * @param inputToSearch
     */
    public  static void listMatcherScheduler(String inputToSearch){
        Pattern pattern = Pattern.compile(inputToSearch, Pattern.CASE_INSENSITIVE);

        List<String> str = loadTotalRecordsFromCSVFile(EnvVars.PATH_FILE_TVDR);
        System.out.println("Size: "+str.size());

        Observable.fromIterable(str)
                .subscribeOn(Schedulers.io())
                .blockingSubscribe(item->{
                            Matcher matcher = pattern.matcher(item);
                            if (matcher.find()) {
                                System.out.println("Match Found: "+item);
                            }
                        },
                        error-> System.out.println("Fail: "+error),
                        ()-> System.out.println("Done Execution"));
    }

    public static void listMatcherLinear(String inputToSearch){
        Pattern pattern = Pattern.compile(inputToSearch, Pattern.CASE_INSENSITIVE);
        List<String> str = loadTotalRecordsFromCSVFile(EnvVars.PATH_FILE_TVDR);
        System.out.println("Size: "+str.size());
        for (String st:str) {
            Matcher matcher = pattern.matcher(st);
            if (matcher.find()) {
                System.out.println("Match Found: "+st);
            }
        }
    }

    public  static void listMatcherStreamParallel(String inputToSearch) {
        Pattern pattern = Pattern.compile(inputToSearch, Pattern.CASE_INSENSITIVE);
        List<String> str = loadTotalRecordsFromCSVFile(EnvVars.PATH_FILE_TVDR);
        System.out.println("Size: " + str.size());
        str.parallelStream().forEach(
                item->{
                    Matcher matcher = pattern.matcher(item);
                    if(matcher.find()){
                        System.out.println("Match Found: "+item);
                    }
                }
        );

    }

}
