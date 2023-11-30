import com.utils.reactive.observer.ObserverReactive;
import io.reactivex.Flowable;

public class RunApp {
    public static void main(String[] args) {
        Flowable.just("Hello world").subscribe(System.out::println);

        ObserverReactive ns = new ObserverReactive();

    }
}
