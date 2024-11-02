package com.amarthya.multithreading.executorsservice;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;


public class ExecutorsServiceTest {

    public void test() throws ExecutionException, InterruptedException {
        ExecutorService executorService= Executors.newFixedThreadPool(10);

     Future<List<Integer>> futureInt= executorService.submit(()->{
            return Arrays.asList(1,2,3,4);
        });

        futureInt.get();

    }
}
