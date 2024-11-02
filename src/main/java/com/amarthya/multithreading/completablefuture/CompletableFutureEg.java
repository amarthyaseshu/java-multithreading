package com.amarthya.multithreading.completablefuture;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureEg {

    public void eg() throws ExecutionException, InterruptedException {
        CompletableFuture completableFuture=new CompletableFuture<>();
        // get() will block the thread till it completes
        completableFuture.get();
        // forcefully complete
        completableFuture.complete("return default");

        // Run Async

        // In below if 2nd parameter is not passed completable future will take thread from Fork/Join pool
        CompletableFuture<Void> cfl1=CompletableFuture.runAsync(()->{
            System.out.println("hi");
        });

        cfl1.get();
        // In below eg completable future takes thread from executorService as its passed as 2nd parameter
        ExecutorService executorService= Executors.newFixedThreadPool(2);

        CompletableFuture<Void> cfl2=CompletableFuture.runAsync(()->{
            System.out.println("hi");
        },executorService);

        cfl2.get();

        // Supply Async

        CompletableFuture<List<String>> cfs1=CompletableFuture.supplyAsync(()->{
            return List.of("hi");
        });

        cfs1.get();


    }
}
