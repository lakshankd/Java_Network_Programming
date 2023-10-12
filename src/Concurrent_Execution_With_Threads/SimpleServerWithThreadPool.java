package Concurrent_Execution_With_Threads;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleServerWithThreadPool {
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        try {
            System.out.println("1. Before creating the server socket");
            ServerSocket serverSocket = new ServerSocket(50001);
            System.out.println("2. After creating the server socket");

            for (;;) {
                System.out.println("3. Before accept the client");
                Socket client = serverSocket.accept();
                System.out.println("4. After accept client");

                RequestProcessorThread requestProcessor = new RequestProcessorThread(client);

                System.out.println("About to start a new thread");

                executorService.execute(requestProcessor);

                System.out.println("After starting a new thread");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

//  In this program to create a thread pool, we have used ExecutorService