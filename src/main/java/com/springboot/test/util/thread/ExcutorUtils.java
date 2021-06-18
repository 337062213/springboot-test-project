 package com.springboot.test.util.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

public class ExcutorUtils {
     
     public static void getExcutorService() {

       ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("thread-pool-d%").build();
       //FixedThreadPool
       ExecutorService ftp = Executors.newFixedThreadPool(5,threadFactory);
       //SingleThreadExecutor
       ExecutorService nslt = Executors.newSingleThreadExecutor();
       //CachedThreadPool
       ExecutorService ctp = Executors.newCachedThreadPool(threadFactory);
       //ScheduledThreadPool
       int corePoolSize = 5;
       ExecutorService nsdt = Executors.newScheduledThreadPool(corePoolSize);
     }

}
