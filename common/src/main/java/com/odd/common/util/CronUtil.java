package com.odd.common.util;


import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CronUtil {


    public static void main(String[] args) throws InterruptedException {
        int corePoolSize = 5;
        int maxPoolSize = 10;
        int queueSize = 20;
        int keepAliveSeconds = 60;

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                corePoolSize,
                maxPoolSize,
                keepAliveSeconds,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(queueSize)
        );

        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setThreadNamePrefix("CronScheduler-");
        scheduler.setPoolSize(1);
        scheduler.setAwaitTerminationSeconds(60);
        scheduler.initialize();

        // 定义任务
        Runnable task = () -> {
            // 在这里编写你的定时任务逻辑
            System.out.println("定时任务执行：" + System.currentTimeMillis());
        };

        // 定义 Cron 表达式
        String cronExpression = "0/5 * * * * ?"; // 每隔 5 秒执行一次

        // 提交定时任务
        scheduler.schedule(() -> threadPoolExecutor.execute(task), new CronTrigger(cronExpression));

        // 程序不退出，以保持线程池运行
        Thread.sleep(60000);
        scheduler.shutdown();
        threadPoolExecutor.shutdown();
        System.out.println("over...");
    }

    private static final Map<String, String> CRON_MAP = new HashMap<>();

    static {
        CRON_MAP.put("HOUR", "0 %d %d/1 * * ?");
        CRON_MAP.put("DAY", "0 %d %d * * ?");
        CRON_MAP.put("WEEK", "0 %d %d ? * %d");
        CRON_MAP.put("MONTH", "0 %d %d L * ?");
    }

    // 获取cron表达式，待测试
    public static String getCronExpression(String inputTime, String type) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date time = null;
        try {
            time = sdf.parse(inputTime);
        } catch (ParseException e) {
            return null;
        }

        int minute = time.getMinutes();
        int hour = time.getHours();
        String expression ;

        if ("WEEK".equals(type)) {
            expression = String.format(CRON_MAP.get(type), minute, hour, 7);
        } else {
            expression = String.format(CRON_MAP.get(type), minute, hour);
        }
        // 生成定时执行的 Cron 表达式
        return expression;
    }


}
