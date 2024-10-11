package com.amarthya.multithreading;

public class Synchroniaztion {
    // If its synchronized at mtd level then this (current cls object) will be used as lock
    public synchronized String returnVal(){
       return "a";
    }

    public Integer returnNum(){
        synchronized (this){
            return 1;
        }
    }
}
