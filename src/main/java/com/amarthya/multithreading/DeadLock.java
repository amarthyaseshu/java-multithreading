package com.amarthya.multithreading;

public class DeadLock {

    String lock1="l1";
    String lock2="l2";

    public void deadLock(){
        Thread th=new Thread(()->{
            synchronized (lock1){
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {

                }
                synchronized (lock2){
                    System.out.println("local acquired");
                }
            }

        });
        Thread th2=new Thread(()->{
            synchronized (lock2){
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {

                }
                synchronized (lock1){
                    System.out.println("local acquired");
                }
            }

        });
    }
}
