package Simple_Server_And_Client_01;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(50001);
            Socket client = serverSocket.accept();
            DataOutputStream outputStream = new DataOutputStream(client.getOutputStream());
            outputStream.writeBytes("Hello Sockets\n");
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
