package com.xiyou.common.mq.consumer;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * 存放消费者监听器线程的单例线程池
 */
@Slf4j
public class IRedisThreadPool {
    // 线程池创建类
    private ThreadPoolExecutor threadPoolExecutor;

    /*
    定时任务线程池
    处理失败的消息
    延迟初始化此线程池（可能用不上）
     */
    private volatile ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;

    // volatile保证内存可见性和防止指令重排
    private volatile static IRedisThreadPool instance;

    private static final int QUEUE_SIZE = 100;
    private static final int CORE_POOL_SIZE = 2;
    private static final long KEEP_ALIVE_TIME = 10L;
    private static final long DELAY_TIME = 2L;

    private IRedisThreadPool() {
        // 获取java虚拟机最大可用
        // 用单例模式创建线程池，保留coreNum个核心线程，最多线程为CPU个数的2n+1的两倍.
        threadPoolExecutor = new ThreadPoolExecutor(CORE_POOL_SIZE,
                CORE_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(QUEUE_SIZE),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }

    public static IRedisThreadPool getInstance() {
        // 双重锁模式保证单例
        if (instance == null) {
            synchronized (IRedisThreadPool.class) {
                if (instance == null) {
                    instance = new IRedisThreadPool();
                    log.info("IRedisThreadPool线程池已启动");
                }
            }
        }
        return instance;
    }

    private ScheduledThreadPoolExecutor scheduledThreadPoolExecutor() {
        if (scheduledThreadPoolExecutor == null) {
            synchronized (IRedisThreadPool.class) {
                if (scheduledThreadPoolExecutor == null) {
                    scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(CORE_POOL_SIZE);
                }
            }
        }
        return scheduledThreadPoolExecutor;
    }

    public void executor(Runnable runnable) {
        if (null == runnable) {
            return;
        }
        threadPoolExecutor.execute(runnable);
    }

    public void schedule(Runnable runnable) {
        if (null == runnable) {
            return;
        }
        scheduledThreadPoolExecutor = scheduledThreadPoolExecutor();
        scheduledThreadPoolExecutor.schedule(runnable, DELAY_TIME, TimeUnit.HOURS);
    }

    public void printThreadInfo() {
        if (null == threadPoolExecutor) {
            return;
        }
        int queueSize = threadPoolExecutor.getQueue().size();
        log.info("当前排队线程数：" + queueSize);

        int activeCount = threadPoolExecutor.getActiveCount();
        log.info("当前活动线程数：" + activeCount);

        long completedTaskCount = threadPoolExecutor.getCompletedTaskCount();
        log.info("执行完成线程数：" + completedTaskCount);

        long taskCount = threadPoolExecutor.getTaskCount();
        log.info("总线程数：" + taskCount);
    }

    public void destroy() {
        // 终止线程池
        if (threadPoolExecutor != null) {
            threadPoolExecutor.shutdown();
            threadPoolExecutor = null;
        }
        if (scheduledThreadPoolExecutor != null) {
            scheduledThreadPoolExecutor.shutdown();
            scheduledThreadPoolExecutor = null;
        }
    }
}
