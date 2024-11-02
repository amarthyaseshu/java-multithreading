package com.amarthya.multithreading.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CombineFuture {
    public static void combineFuture(String[] args) {
        // Combining 2 dependent tasks
        // thenCompose - Chaining two dependent tasks
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Getting user details in future1: " + Thread.currentThread().getName());
            return "User123";
        }).thenCompose(userId -> CompletableFuture.supplyAsync(() -> {
            System.out.println("Fetching orders for " + userId + " in future2: " + Thread.currentThread().getName());
            return "Order123 for " + userId;
        }));

        try {
            System.out.println("Result of thenCompose: " + future1.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        // Combining 2 independent tasks
        // thenCombine - Combining two independent tasks
        CompletableFuture<Integer> future3 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Fetching price from service A: " + Thread.currentThread().getName());
            return 50;
        });

        CompletableFuture<Integer> future4 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Fetching discount from service B: " + Thread.currentThread().getName());
            return 10;
        });

        CompletableFuture<Integer> resultFuture = future3.thenCombine(future4, (price, discount) -> {
            System.out.println("Combining results in thenCombine: " + Thread.currentThread().getName());
            return price - discount;
        });

        try {
            System.out.println("Result of thenCombine: " + resultFuture.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        //Combine multiple independent completable future
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.allOf(future1, future3, future4);

        // To transform data after all futures completed

        voidCompletableFuture.thenRun(()->{
            String f1=   future1.join();
            future3.join();
            future4.join();
        }).join();

        //Combine multiple independent completable future - Return if any one is returned first

        CompletableFuture<Object> completableFutureAny = CompletableFuture.anyOf(future1, future3, future4);

    }
}
