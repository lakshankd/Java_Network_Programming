package UDP_Datagram.Simple_UDP_Client_And_Server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class SimpleUDPClient {
    public static final int CLIENT_LISTENING_PORT = 10006;

    public static void main(String[] args) {

        try {
            // create a datagram object
            DatagramSocket clientSocket = new DatagramSocket(CLIENT_LISTENING_PORT);
            System.out.println("Datagram socket created on " + CLIENT_LISTENING_PORT);

            // Create buffer for storing datagram data in Datagram Packet object
            byte[] buffReceiveData = new byte[1024];

            // Create Datagram object for wrapping a incoming packet (datagram)
            DatagramPacket receivePacket = new DatagramPacket(buffReceiveData, buffReceiveData.length);

            // Receive incoming datagram in to Datagram packet object
            try {                                               // This is blocking systems calls
                clientSocket.receive(receivePacket);            // program blocks here until a datagram
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Display the received data
            String strReceived = new String(receivePacket.getData());
            System.out.println("CLIENT RECEIVED DATA : " + strReceived);

            clientSocket.close();           // Close the DatagramSocket

        } catch(Exception e) {              // Top level try-catch
            e.printStackTrace();
        }
    }
}
