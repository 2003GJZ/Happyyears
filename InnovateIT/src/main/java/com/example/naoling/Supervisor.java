package com.example.naoling;
import java.util.concurrent.ExecutorService;

public class Supervisor extends Thread {
    private static ExecutorService executorService;
    private volatile boolean interruptedFlag;

    static {
        executorService = ThreadPoolConfig.getThreadPoolExecutor();// 你可以根据需要调整线程池大小
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (interruptedFlag) {
                    interruptedFlag = false; // 重置标志位
                }
                long nodeWithTime = MyListNode.peekFirstNodeWithTime();
                if (nodeWithTime  >0) {
                    long sleepTime = nodeWithTime- System.currentTimeMillis();
                    if (sleepTime > 0) {
                        System.out.println("等待："+ sleepTime + "ms");
                        Thread.sleep(sleepTime);
                    }
                    if(interruptedFlag){// 检查是否被中断
                        continue;
                    }
                    // 执行任务
                    executorService.execute(MyListNode.getRunFun());
                } else {
                    System.out.println("链表为空，休眠2秒,当前时间为："+System.currentTimeMillis());
                    // 如果链表为空，则在下次检查之前休眠一段时间
                    Thread.sleep(2000); // 根据需要调整休眠时间
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                // 如果需要，处理中断
            }
        }
    }

    // 当添加新节点时唤醒监管者
    public void wakeUp() {
        System.out.println("监管者被唤醒");
        this.interrupt();
        interruptedFlag = true;
    }

    // 关闭监管者和线程池
    public void shutdown() {
        executorService.shutdown();
    }
}