import com.utils.reactive.flowable.FlowableExamples;
import com.utils.reactive.observer.ObserverReactive;
import io.reactivex.Flowable;
import io.reactivex.Observable;

import java.util.concurrent.Flow;
import java.util.function.Consumer;

public class RunApp {
    public static void main(String[] args) {
        Flowable.just("Hello world").subscribe(System.out::println);
        //ObserverReactive ns = new ObserverReactive();
        //Observable<String> observable = Observable.just("how","to","do","in");
        //Consumer<? super String> consumer = System.out::println;

        System.out.println("------------------------------------------------");
        System.out.println("Matches every divisible number for 3 OR 5 OR 7");
        System.out.println("------------------------------------------------");
        FlowableExamples.intFlowFewEmitted();
        System.out.println("------------------------------------------------");
        System.out.println("Matches every divisible number for 3 AND 5 AND 7");
        System.out.println("------------------------------------------------");
        FlowableExamples.intFlowForThreeConditions();
    }
}
