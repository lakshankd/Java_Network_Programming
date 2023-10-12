package Concurrent_Execution_With_Threads;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServerWithLoop {
    public static void main(String[] args) {
        try {
            System.out.println("1.  Before creating the server socket");
            ServerSocket serverSocket = new ServerSocket(50001);
            System.out.println("2.  After creating the server socket");

            for (;;) {
                System.out.println("3.  Before accept client");
                Socket client = serverSocket.accept();
                System.out.println("4.  After accept client");

                System.out.println("5.  Before writing data to the client");
                DataOutputStream outputStream = new DataOutputStream(client.getOutputStream());
                outputStream.writeBytes("Hello Sockets\n");
                System.out.println("6.  After writing data to the client");


                System.out.println("7.  Before closing connection to the client");
                client.close();
                System.out.println("8.  After closing connection to the client");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// This program will lead to a error - if exception happens in one client, the whole program will be terminated.
// Because the exception catch from the top try catch block.