package Java_Port_Scanner_Implementation_01;

import java.io.IOException;
import java.net.Socket;

public class PortScanner {
    public static void main(String[] args) {
        for (int i = 0; i < 65535; i++) {
            try {
                Socket socClient = new Socket("localhost", i);
                System.out.println("***** Service active on " + i);
                socClient.close();
            } catch (IOException e) {
                //  System.out.println("No service on port " + i);
            }
        }
    }
}
