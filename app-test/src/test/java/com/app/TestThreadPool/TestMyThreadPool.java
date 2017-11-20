package com.app.TestThreadPool;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * com.app.TestThreadPool
 *
 * @author zhujiamin
 * @date 2017/8/4
 */
public class TestMyThreadPool {
    public static void main(String[] args) throws InterruptedException {
//        ThreadExcutor excutor = new ThreadExcutor(3);
//        for (int i = 0; i < 10; i++) {
//            excutor.execute(new Runnable() {
//                @Override
//                public void run() {
//                    System.out.println("线程 " + Thread.currentThread().getName() + " 在帮我干活");
//                }
//            });
//        }
//        excutor.shutDown();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(10));

        for(int i=0;i<20;i++){
            MyTask myTask = new MyTask(i);
            executor.execute(myTask);
            System.out.println("线程池中线程数目："+executor.getPoolSize()+"，队列中等待执行的任务数目："+
                    executor.getQueue().size()+"，已执行玩别的任务数目："+executor.getCompletedTaskCount());
        }

//        Thread.currentThread().sleep(4000);
//        System.out.println("线程开始结束");
        executor.shutdown();
    }


    @Test
    public void testThreadPool() throws InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(5));

        for(int i=0;i<15;i++){
            MyTask myTask = new MyTask(i);
            executor.execute(myTask);
            System.out.println("线程池中线程数目："+executor.getPoolSize()+"，队列中等待执行的任务数目："+
                    executor.getQueue().size()+"，已执行玩别的任务数目："+executor.getCompletedTaskCount());
        }

//        Thread.currentThread().sleep(4000);
//        System.out.println("线程开始结束");
        executor.shutdown();


    }

}


