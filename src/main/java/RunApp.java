import com.utils.commons.lib.FileReader;
import com.utils.reactive.flowable.FlowableExamples;
import com.utils.reactive.observer.ObserverReactive;
import init.env.global.vars.EnvVars;
import io.reactivex.Flowable;
import io.reactivex.Observable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Flow;
import java.util.function.Consumer;

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
            //System.out.println("Size: "+FileReader.getRecordsInCSVFile(EnvVars.PATH_FILE_BITACORAS,0));
            List<String> str = loadTotalRecordsFromCSVFile(EnvVars.PATH_FILE_BITACORAS);
            System.out.println("Size: "+str.size());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
