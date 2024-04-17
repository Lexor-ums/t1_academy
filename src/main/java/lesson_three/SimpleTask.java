package lesson_three;

import static java.lang.Thread.sleep;

public class SimpleTask implements Runnable {
    private final String name;
    private final int executionTime;

    public SimpleTask(String name, int executionTime) {
        this.name = name;
        this.executionTime = executionTime;
    }

    @Override
    public void run() {
        System.out.println(String.format("Thread name %s started execution\n", name));
        try {
            sleep(executionTime * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(String.format("Thread name %s finished execution\n", name));
    }

    public String getName() {
        return name;
    }
}
