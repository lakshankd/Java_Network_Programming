package Concurrent_Execution_With_Threads;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class SimpleServerWithThreads {
    public static void main(String[] args) {
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
                Thread t = new Thread(requestProcessor);
                t.start();
                System.out.println("After starting a new thread");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class RequestProcessorThread implements Runnable {

    private Socket client;

    //  New constructor
    public RequestProcessorThread(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            System.out.println("5. Before writing data to the client");
            DataOutputStream dataOutputStream = new DataOutputStream(client.getOutputStream());

            Date today = new Date();
            dataOutputStream.writeBytes(today.toString());
            System.out.println("6. After writing data to the client.");

            InputStream is = client.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String receivedData = br.readLine();
            System.out.println("7. Received from client : " + receivedData);

            System.out.println("8. Before closing connection to the client");
            client.close();
            System.out.println("9. After closing connection to the client");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


//  This server will send the current date to the client
//  And also uses Threads for concurrent execution
//      1. extending thread class
//      2. implementing runnable interface
