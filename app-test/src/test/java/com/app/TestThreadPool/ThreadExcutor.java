package com.app.TestThreadPool;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * com.app.TestThreadPool
 *
 * @param ${param}
 * @author zhujiamin
 * @date 2017/8/4
 */
public class ThreadExcutor extends AbstractThreadExcuter {


    public ThreadExcutor(int poolSize) {
        this.poolSize = poolSize;
        queue = new LinkedBlockingQueue<Runnable>(poolSize);

    }

    public ThreadExcutor() {

    }

    @Override
    public void execute(Runnable runnable) {
        if (runnable == null) {
            throw new NullPointerException();
        }
        if (coreSize < poolSize) {
            addThread(runnable);
        } else {
            try {
                queue.put(runnable);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void addThread(Runnable runnable) {
        coreSize++;
        queue.offer(runnable);
        Worker worker = new Worker(runnable, queue, workers, RUNNING, shutDown,threads);
        workers.add(worker);
        Thread thread = new Thread(worker);
        threads.add(thread);
        try {
            thread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void shutDown() {
        RUNNING = false;
        if (!workers.isEmpty()) {
            for (Worker worker : workers) {
                worker.interrupIfDle();
            }
            shutDown = true;
            Thread.currentThread().interrupt();
        }
    }
}
