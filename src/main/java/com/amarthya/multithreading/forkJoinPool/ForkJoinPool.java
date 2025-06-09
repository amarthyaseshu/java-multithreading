package com.amarthya.multithreading.forkJoinPool;

import java.util.stream.LongStream;

public class ForkJoinPool {

    ForkJoinPool pool = new ForkJoinPool();
    long[] numbers = LongStream.rangeClosed(1, 1_000_000).toArray();
    //SumTask task = new SumTask(numbers, 0, numbers.length);

    //long result = pool.invoke(task);
//System.out.println("Sum: " + result);

    //ForkJoinPool commonPool = ForkJoinPool.commonPool();
}
