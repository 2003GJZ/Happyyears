package com.example.naoling;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池配置类
 */
public class ThreadPoolConfig {

    // 核心线程数
    private static final int CORE_POOL_SIZE = 5;
    // 最大线程数
    private static final int MAXIMUM_POOL_SIZE = 10;
    // 线程空闲时间
    private static final long KEEP_ALIVE_TIME = 60L;
    // 时间单位
    private static final TimeUnit TIME_UNIT = TimeUnit.SECONDS;
    // 任务队列
    private static final BlockingQueue<Runnable> WORK_QUEUE = new LinkedBlockingQueue<>();

    // 线程池对象
    private static ThreadPoolExecutor threadPoolExecutor;

    /**
     * 获取线程池对象
     *
     * @return 线程池对象
     */
    public static ThreadPoolExecutor getThreadPoolExecutor() {
        if (threadPoolExecutor == null || threadPoolExecutor.isShutdown() || threadPoolExecutor.isTerminated()) {
            synchronized (ThreadPoolConfig.class) {
                if (threadPoolExecutor == null || threadPoolExecutor.isShutdown() || threadPoolExecutor.isTerminated()) {
                    threadPoolExecutor = new ThreadPoolExecutor(
                            CORE_POOL_SIZE,
                            MAXIMUM_POOL_SIZE,
                            KEEP_ALIVE_TIME,
                            TIME_UNIT,
                            WORK_QUEUE
                    );
                }
            }
        }
        return threadPoolExecutor;
    }

    /**
     * 主方法，用于示例线程池的使用
     *
     * @param args 命令行参数
     */

}