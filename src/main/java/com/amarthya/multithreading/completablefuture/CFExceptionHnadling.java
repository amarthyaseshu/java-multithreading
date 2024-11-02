package com.amarthya.multithreading.completablefuture;

import java.util.concurrent.CompletableFuture;

public class CFExceptionHnadling {
    // to handle exception block lvl
    public void completableFutureExceptionHandling(){
        CompletableFuture.supplyAsync(()->1).exceptionally(e->{
            System.out.println(e);
            return 1;
        });
    }

    // To handle exception globally

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
    }).handle((res,ex)->{
        return res;
    });


}
