package UDP_Datagram.Simple_UDP_Client_For_Receiving_Multi_Pckts;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServer {
    public final static int SERVICE_PORT = 50001;

    public static void main(String[] args) {

        try {
            // 1. Create a DatagramSocket object
            DatagramSocket serverSocket = new DatagramSocket(SERVICE_PORT);

            // 2. Create buffers for storing datagram data in DatagramPacket Object
            byte[] buffReceivedData = new byte[1024];       // For coming data
            byte[] buffSendData = new byte[1024];           // For outgoing data

            // 3. Create DatagramPacket object for wrapping incoming packets
            DatagramPacket packetIn = new DatagramPacket(buffReceivedData, buffReceivedData.length);

            // 4. Receive incoming datagram into data packet object.
            try {                                       // This is a blocking system call
                serverSocket.receive(packetIn);         // Program block here
            } catch (IOException e) {                   // Until a datagram is received
                e.printStackTrace();
            }

            // 5. Get the data from received packet
            String strInData = new String(packetIn.getData());
            System.out.println("SERVER RECEIVED DATA : " + strInData);
            buffSendData = strInData.toUpperCase().getBytes();

            // 6. Find sender's address and port from the received packet
            InetAddress inAddress = packetIn.getAddress();
            int inPort = packetIn.getPort();

            // 7. Create a Datagram to send
            DatagramPacket packetOut = new DatagramPacket
                    (
                            buffSendData,
                            buffSendData.length,
                            inAddress,
                            inPort
                    );

            // 8. Send the response datagram
            try {
                serverSocket.send(packetOut);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // 9. Close the Datagram Socket
            serverSocket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
