package com.amarthya.multithreading.structuredconcurrency;

public class StructuredConcurrency {

    public static void structuredConcurrency(String[] args) {
        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            // Launch task 1: Fetch user details
            var userFuture = scope.fork(() -> {
                System.out.println("Fetching user details...");
                sleep(1000);
                return "User123";
            });

            // Launch task 2: Fetch account balance
            var balanceFuture = scope.fork(() -> {
                System.out.println("Fetching account balance...");
                sleep(1500);
                return 2345.67;
            });

            // Wait for all tasks to complete or one to fail
            scope.join();       // Join all subtasks
            scope.throwIfFailed();  // Propagate any exception from a subtask

            // Combine both results
            String userId = userFuture.resultNow();
            Double balance = balanceFuture.resultNow();

            System.out.println("User ID: " + userId + ", Balance: $" + balance);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    // Helper method to simulate delay
    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
