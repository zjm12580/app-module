package com.app.TestThreadPool;

import java.util.ArrayList;
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
public abstract class AbstractThreadExcuter {
    volatile boolean RUNNING = true;

    //所有的任务都放在队列中
    static BlockingQueue<Runnable> queue = null;

    final HashSet<Worker> workers = new HashSet<Worker>();

    final List<Thread> threads = new ArrayList<Thread>();

    //工作线程数
    int poolSize = 0;

    int coreSize = 0;

    boolean shutDown = false;

    public abstract void execute(Runnable runnable);
}
