package com.test.util;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AsyncDeclarationsTask {

    @Async
    public void task1(int task){

        for(int i=0;i<task;i++)
        {
            try {
                System.out.println(Thread.currentThread().getName()+" ---- " +i);
                Thread.sleep(1000);
            }catch (Exception ee)
            {ee.printStackTrace();}

        }

    }

    @Async
    public void task2(int task){

        for(int i=0;i<task;i++)
        {
            try {
                Thread.sleep(1000);
            }catch (Exception e)
            {e.printStackTrace();}
            System.out.println(Thread.currentThread().getName()+" ---- " +i);
        }

    }

}
