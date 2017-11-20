package com.app.TestThreadPool;

import java.util.HashSet;
import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * com.app.TestThreadPool
 *
 * @param ${param}
 * @author zhujiamin
 * @date 2017/8/4
 */
public class Worker implements Runnable {

    private BlockingQueue<Runnable> queue = null;

    private HashSet<Worker> workers = null;

    private  List<Thread> threads = null;

    private volatile boolean RUNNING = true;

    boolean shutDown = false;


    public Worker(Runnable runnable, BlockingQueue<Runnable> queue, HashSet<Worker> workers, boolean isRunning, boolean shutDown, List<Thread> threads) {
        this.queue = queue;
        this.queue.offer(runnable);
        this.workers = workers;
        this.RUNNING = isRunning;
        this.shutDown = shutDown;
        this.threads = threads;
    }

    @Override
    public void run() {

        while (true && RUNNING) {
            if (!shutDown) {
                Thread.interrupted();
            }
            Runnable task = null;
            try {

                task = getTask();
                task.run();
            } catch (InterruptedException e) {

            }

        }
    }

    public void interrupIfDle() {
        for (Thread thread : threads) {
            System.out.println(thread.getName() + "interrcupt");
            thread.interrupt();
        }
    }

    public Runnable getTask() throws InterruptedException {
        return queue.take();
    }
}
