package lesson_three;

import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) throws InterruptedException {
        List<SimpleTask> tasks = List.of(
                new SimpleTask("task 1 ", 5),
                new SimpleTask("task 2 ", 9),
                new SimpleTask("task 3 ", 11),
                new SimpleTask("task 4 ", 3),
                new SimpleTask("task 5 ", 7),
                new SimpleTask("task 6 ", 2),
                new SimpleTask("task 7 ", 14),
                new SimpleTask("task 8 ", 6),
                new SimpleTask("task 9 ", 10),
                new SimpleTask("task 10 ", 15),
                new SimpleTask("task 11 ", 12));
        SimpleThreadPool simpleThreadPool = new SimpleThreadPool(5);
        Thread poolThread = new Thread(simpleThreadPool);
        poolThread.start();
        tasks.forEach(simpleThreadPool::execute);
        Thread.sleep(10000);
        simpleThreadPool.shutDown();
        try {
            simpleThreadPool.execute(new SimpleTask("task 12 ", 12));
        } catch (IllegalStateException e) {
            System.out.println("Threedpool is shutting down");

        }

    }
}
