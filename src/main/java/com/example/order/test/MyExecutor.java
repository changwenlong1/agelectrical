package com.example.order.test;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyExecutor {
    /**
     * 线程池
     * @param args
     */
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(2);
       /* pool.submit(new MyRunnable());
        pool.submit(new MyRunnable());
        pool.submit(new MyRunnable());*/
        for (int i = 0; i <=5 ; i++) {
            pool.submit(new MyRunnable());
        }
        pool.shutdown();
    }
}
