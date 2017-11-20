package com.app.entity.test;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 生产者线程
 * com.app.entity.test
 *
 * @param ${param}
 * @author zhujiamin
 * @date 2017/8/4
 */
public class Producer implements Runnable {


    private volatile boolean isRunning=true;
    private BlockingQueue queue;
    private static AtomicInteger count=new AtomicInteger();//自动更新的值


    public Producer(BlockingQueue blockingQueue){
        this.queue=blockingQueue;
    }
    @Override
    public void run() {
        String data=null;
        Random random=new Random();
        System.out.println("启动生产者线程。。。。。。。。。。。。。。。。。。");
        try {
            while (isRunning){
                System.out.println("正在产生数据。。。。。。。。。。");
                Thread.sleep(random.nextInt(1000));
                data="data"+count.incrementAndGet();//以原子方式将count当前值加1
                System.out.println("将数据："+data+"放入队列。。。。");
                if (!queue.offer(data,2, TimeUnit.SECONDS)) {//设定等待时间两秒，如果超过2秒还没加进去返回 true
                    System.out.println("加入数据失败" + data);
                }else{
                    System.out.println("加入数据成功" + data);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();

        }finally {
            System.out.println("退出生产者线程");
        }

    }

    public void stop(){
        isRunning=false;
    }
}
