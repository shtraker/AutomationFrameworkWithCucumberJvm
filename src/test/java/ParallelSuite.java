import com.automation.tools.Tools;
import org.junit.runners.Suite;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.RunnerBuilder;
import org.junit.runners.model.RunnerScheduler;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ParallelSuite extends Suite {

    public ParallelSuite(Class<?> clazz, RunnerBuilder builder) throws InitializationError, IOException {

        super(builder, clazz, findRunnableClasses());

        setScheduler(new RunnerScheduler() {

            private final ExecutorService service = Executors.newFixedThreadPool(1);

            public void schedule(Runnable childStatement) {
                service.submit(childStatement);
            }

            public void finished() {
                try {
                    service.shutdown();
                    service.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace(System.err);
                }
            }
        });
    }

    private static Class<?>[] findRunnableClasses() throws IOException {
        URL scannedUrl = Thread.currentThread().getContextClassLoader().getResource("");
        if (scannedUrl == null) {
            throw new IllegalArgumentException("No runnable classes found!");
        }
        String current = new java.io.File( "." ).getCanonicalPath() + "/src/test/java";
        File scannedDir = new File(current);
        List<Class<?>> classes = new ArrayList<>();
        for (File file : scannedDir.listFiles()) {
            String fileName = file.getName();
            if (fileName.startsWith("Parallel") && fileName.endsWith("IT.java")) {
                try {
                    classes.add(Class.forName(fileName.replace(".java", "")));
                } catch (ClassNotFoundException e) {
                    System.out.println(e.getLocalizedMessage());
                }
            }
        }
        return classes.toArray(new Class<?>[classes.size()]);
    }
}
