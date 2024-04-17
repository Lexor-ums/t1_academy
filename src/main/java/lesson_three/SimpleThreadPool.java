package lesson_three;
import org.openjdk.nashorn.internal.IntDeque;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class SimpleThreadPool implements Runnable{
    private final int threadCount;
    private final AtomicBoolean isShuttingDown = new AtomicBoolean(false);;
    private final LinkedList<Runnable> tasksList = new LinkedList<>();
    private ArrayList<Thread> busyThreads = null;
    private final Lock lock = new ReentrantLock();


    public SimpleThreadPool(int threadCount)
    {
        IntDeque
        this.threadCount = threadCount;
        busyThreads = new ArrayList<>(threadCount);
    }

    public void execute(Runnable task) {
        if (!isShuttingDown.get()) {
            tasksList.add(task);
        }
        else {
            throw new IllegalStateException();
        }
    }

    public void shutDown() {
        isShuttingDown.set(true);
        String remainigTasks = tasksList.stream().map(runnable -> ((SimpleTask) runnable).getName()).collect(Collectors.joining(","));
        System.out.println(String.format("Thread pool is shutting down! Tasks will not be started: %s", remainigTasks));
    }

    @Override
    public void run() {
        while (!isShuttingDown.get()) {
            if (lock.tryLock()) {
                busyThreads.removeIf(thread -> thread.getState() == Thread.State.TERMINATED);
                if (!tasksList.isEmpty() && busyThreads.size() < threadCount) {
                    Thread executor = new Thread(tasksList.pop());
                    busyThreads.add(executor);
                    executor.start();
                }
            }
            lock.unlock();
        }
    }
}

