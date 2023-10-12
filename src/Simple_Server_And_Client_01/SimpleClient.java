package Simple_Server_And_Client_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class SimpleClient {
    public static void main(String[] args) {
        try {
            Socket client = new Socket("localhost", 50001);
            InputStream inputStream = client.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String receivedData = bufferedReader.readLine();
            System.out.println(receivedData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
