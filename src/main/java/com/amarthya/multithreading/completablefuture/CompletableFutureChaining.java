package com.amarthya.multithreading.completablefuture;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureChaining {
    // thenApply - Returns completable future
    // thenAccept - Doesn't return anything
    public static void synchronous() {
         CompletableFuture.supplyAsync(() -> {
            System.out.println("Executing a task in supplyAsync: " + Thread.currentThread().getName());
            return 10;
        }).thenApply(result -> {
            System.out.println("Transforming the result in thenApply: " + Thread.currentThread().getName());
            return result * 2;
        }).thenAccept(result1->{
            System.out.println("Transforming the result in thenApply: " + Thread.currentThread().getName());
        });
    }

    // thenApplyAsync - Returns completable future with diff thread
    // thenAcceptAsync - Doesn't return anything with diff thread
    public static void aSynchronous() {
        CompletableFuture.supplyAsync(() -> {
            System.out.println("Executing a task in supplyAsync: " + Thread.currentThread().getName());
            return 10;
        }).thenApplyAsync(result -> {
            System.out.println("Transforming the result in thenApply: " + Thread.currentThread().getName());
            return result * 2;
        }).thenAcceptAsync(result1->{
            System.out.println("Transforming the result in thenApply: " + Thread.currentThread().getName());
        });
    }

}
