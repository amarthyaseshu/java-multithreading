package com.amarthya.multithreading;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MultithreadingApplication {

	public static void main(String[] args) {
		System.out.println(Thread.currentThread().getName());
		Thread thread1=new Thread1();
		// to set thread as daemon
		//thread1.setDaemon(true);
		thread1.start();

		Thread thread2=new Thread(new Thread2());
		thread2.start();

		Thread thread3= new Thread(()->{
			for(int i=0;i<5;i++){
				System.out.println("t3"+i+Thread.currentThread().getName());
			}
		});
		thread3.start();
	}

}
