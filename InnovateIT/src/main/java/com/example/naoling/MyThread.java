package com.example.naoling;

public class MyThread extends Thread {//测试
    String threadName;

    public MyThread(String threadName) {
        this.threadName = threadName;
    }

    // 重写run方法
    @Override
    public void run()  {
        System.out.println("Thread "+ threadName + " 执行 ");
    }
}
