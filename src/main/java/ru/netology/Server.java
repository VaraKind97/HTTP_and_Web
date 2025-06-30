package ru.netology;

import java.io.*;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.util.concurrent.TimeUnit.SECONDS;

public class Server {

    public static void main(String[] args) {
        final ExecutorService executorService = Executors.newFixedThreadPool(64);
        try (ServerSocket server = new ServerSocket(9999)) {
            System.out.println("Server started!");
            while (true) {
                for (int i = 0; i < 100; i++) {
                    executorService.execute(new Task(i));
                }
                executorService.awaitTermination(10, SECONDS);

                executorService.shutdownNow();
                System.out.println(executorService);
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}