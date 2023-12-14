import com.utils.commons.lib.FileReader;
import com.utils.reactive.flowable.FlowableExamples;
import com.utils.reactive.observer.ObserverReactive;
import init.env.global.vars.EnvVars;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Flow;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.utils.commons.lib.FileReader.loadTotalRecordsFromCSVFile;

public class RunApp {

    static ArrayList<String> lstValues = new ArrayList<String>();
    public static void main(String[] args) {
        Flowable.just("Hello world").subscribe(System.out::println);
        System.out.println("------------------------------------------------");
        System.out.println("Matches every divisible number for 3 OR 5 OR 7");
        System.out.println("------------------------------------------------");
        //FlowableExamples.intFlowFewEmitted();
        System.out.println("------------------------------------------------");
        System.out.println("Matches every divisible number for 3 AND 5 AND 7");
        System.out.println("------------------------------------------------");
        //FlowableExamples.intFlowForThreeConditions();
        //FlowableExamples.intFlowEmittedSchedule();

        try {
            /*Using blockingSubscriber due this will ensure that the main thread waits for the Observable to complete before exiting.*/
            long startTime1 = System.nanoTime();
            FlowableExamples.listMatcherScheduler("ASEA");
            long endTime1 = System.nanoTime();

            long benchmark1 =endTime1-startTime1;

            long startTime2 = System.nanoTime();
            FlowableExamples.listMatcherLinear("ASEA");
            long endTime2 = System.nanoTime();

            long benchmark2 =endTime2-startTime2;

            long startTime3 = System.nanoTime();
            FlowableExamples.listMatcherStreamParallel("ASEA");
            long endTime3 = System.nanoTime();

            long benchmark3 =endTime3-startTime3;

            System.out.println("Lineal: "+benchmark1);
            System.out.println("Observer: "+benchmark2);
            System.out.println("Stream: "+benchmark3);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
