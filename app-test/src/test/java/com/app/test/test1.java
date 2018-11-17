package com.app.test;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * com.app.test
 *
 * @author zhujiamin
 * @date 2017/8/11
 */
public class test1 {

    public static void main(String[] args) {
//        Map map=new HashMap();
//        map.put(1,"aaa");
//        map.put(1,"222");
//        System.out.println(map.get(1));

        List<students> s=new ArrayList<>();
        s.add(new students("FW0101"));
        s.add(new students("FW0101"));
        s.add(new students("AA-13-13-13"));
        s.add(new students("HZDF-01-01"));
        s.add(new students("HZDF-01-02"));
        s.add(new students("HADF-01-01"));
        s.add(new students("AZDF-01-01"));
        s.add(new students("HEDF-02-01"));
        s.add(new students("HZCF-D-01"));
        s.add(new students("HZCR-01-01"));
        s.add(new students(null));
        s.add(new students(null));


        Collections.sort(s, new Comparator<students>() {
            @Override
            public int compare(students o1, students o2) {
                if (o1.getName()!=null&&o2.getName()==null){
                    return 1;
                }
                if (o1.getName()==null&&o2.getName()!=null){
                    return -1;
                }
                if (o1.getName()==null&&o2.getName()==null){
                    return 0;
                }
                return o1.getName().compareTo(o2.getName());
            }
        });

        for (students stu:s){
            System.out.println(stu.getName());
        }

    }

    @Test
    public void test(){

        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            try {
                Thread.sleep(index * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cachedThreadPool.execute(new Runnable() {
                public void run() {
                    System.out.println(index);
                }
            });

    }
    }

    @Test
    public void test2(){
        SimpleDateFormat sf=new SimpleDateFormat("yyyyMMdd");
        System.out.println(sf.format(new Date()));
        System.out.println(new Date().getTime());
    }
}

class students{
    private String name;
    private String pass;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public students(String name){
        this.name=name;
    }


}