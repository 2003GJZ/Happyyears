package com.example.naoling;

// 按两次 Shift 打开“随处搜索”对话框并输入 `show whitespaces`，
// 然后按 Enter 键。现在，您可以在代码中看到空格字符。
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Supervisor supervisor = new Supervisor();
        MyListNode myListNode = new MyListNode(supervisor);
        MyThread text1 = new MyThread("测试1");
        MyThread text2 = new MyThread("测试2");
        MyThread text3 = new MyThread("测试3");
        MyThread text4= new MyThread("测试4");
        MyListNode.add(text1,System.currentTimeMillis()+3000);
        MyListNode.add(text2,System.currentTimeMillis()-1060);//过期任务
        MyListNode.add(text3,System.currentTimeMillis()+2000);
        MyListNode.add(text4,System.currentTimeMillis()+8000);
        supervisor.join();
    }
}


