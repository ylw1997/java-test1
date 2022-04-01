package com.yangliwei.test1.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 *  定时任务
 * @author yangliwei
 */
@Component
public class ScheduledJobs {


//    @Scheduled(fixedDelay = 5000)
//    public void fixedDelayJob() {
//        System.out.println("延迟执行: " + System.currentTimeMillis()+Thread.currentThread().getName());
//    }

//    @Scheduled(fixedRate = 5000)
//    public void fixedRateJob(){
//        System.out.println("间隔执行===============>>>"+System.currentTimeMillis()+Thread.currentThread().getName());
//    }
//
//    @Scheduled(cron = "0/5 * * * * ?")
//    public void cronJob() {
//        System.out.println("cronJob===============>>>"+System.currentTimeMillis()+Thread.currentThread().getName());
//    }
}
