package com.example.naoling;

//package org.example;
//public class MyListNode {
//    public static final String YYN="Y";
//
//    public static int size;//链表长度
//    public static MyListNode ROOT;//根节点
//    static {
//        size=0;
//        ROOT= new MyListNode(null,0l);
//    }
//
//    public MyListNode(Thread runfun, Long runtime) {
//        this.runfun = runfun;
//        this.runtime = runtime;
//    }
//
//    public Thread runfun;
//    public Long runtime;
//    public MyListNode next;
//    public boolean add(MyListNode node){
//        synchronized (YYN){
//            MyListNode ptr= ROOT;
//            if(ptr.next == null){
//                ptr.next=node;
//                size++;
//                return true;
//            }
//            while (true){
//                if(ptr.next==null){
//                    ptr.next=node;
//                    size++;
//                    break;
//                }else if(ptr.next.runtime>=node.runtime){
//                    MyListNode n=ptr.next;
//                    ptr.next=node;
//                    node.next=n;
//                    size++;
//                    break;
//                }
//                ptr=ptr.next;
//            }
//
//            return true;
//        }
//
//    }
//
//
//}
public class MyListNode {
    private static final Object lock = new Object();
    private static  Supervisor regulator;//监管者

    private static int size;
    private static MyListNode root;

    public MyListNode( Supervisor regulator) {
        MyListNode.size = 0;
        MyListNode.root = new MyListNode(null, 0L);
        MyListNode.regulator=regulator;
        regulator.start();
    }

    private MyListNode(Thread runFun, Long runtime) {
        this.runFun = runFun;
        this.runtime = runtime;
    }

    private Thread runFun;
    private Long runtime;
    private MyListNode next;
public static Thread getRunFun(){//探测时间节点
    MyListNode ptr=root;
    Thread putrun=ptr.next.runFun;
    ptr.next=ptr.next.next;
    return putrun;
}
    public static long peekFirstNodeWithTime(){//仅仅探测时间

    MyListNode ptr=root;

        while (true){
            if(ptr.next!=null){
                if(ptr.next.runtime-System.currentTimeMillis()>0){//跳过已经过期任务
                    size--;
                    return ptr.next.runtime;
                }else {
                    System.out.println("跳过已经过期任务");//写入日志overdue.log
                    ptr.next=ptr.next.next;//删除
                    size--;
                }
            }else {
                return -100;//空队列
            }
        }

    }
    public static boolean add(Thread runFun, Long runtime) {
        synchronized (lock) {
            MyListNode newNode = new MyListNode(runFun, runtime);
            MyListNode ptr = root;
            while (ptr.next != null && ptr.next.runtime < runtime) {
                ptr = ptr.next;
            }
            newNode.next = ptr.next;
            ptr.next = newNode;
            size++;
            regulator.wakeUp();//打断休眠
            return true;
        }


    }
}