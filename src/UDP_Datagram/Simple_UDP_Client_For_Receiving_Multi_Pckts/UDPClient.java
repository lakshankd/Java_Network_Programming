package UDP_Datagram.Simple_UDP_Client_For_Receiving_Multi_Pckts;

import java.io.IOException;
import java.net.*;

public class UDPClient {
    public final static int SERVICE_PORT = 50001;

    public static void main(String[] args) {
        try {
            // 1. Create a datagram socket object
            DatagramSocket clientSocket = new DatagramSocket();

            // 2. Find the IP Address of the server by name
            InetAddress IPAddress = null;
            try {
                IPAddress = InetAddress.getByName("localhost");
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }

            // 3. Create buffers for storing datagram data in datagram packet object
            byte[] buffReceivedData = new byte[1024];       // for incoming data
            byte[] buffSendData = new byte[1024];            // for outgoing data

            // 4. Data to send to the server
            String sentence = "Hello World from UDP client...";
            buffSendData = sentence.getBytes();

            // 5. Create DatagramPacket object for wrapping outgoing packet
            DatagramPacket sendPacket = new DatagramPacket
                    (
                            buffSendData,
                            buffSendData.length,
                            IPAddress,
                            SERVICE_PORT
                    );

            // 6. Sending the datagram to the above specified destination
            try {
                clientSocket.send(sendPacket);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // 7. Create Datagram Packet object for wrapping incoming packets
            DatagramPacket receivePacket = new DatagramPacket(buffReceivedData, buffReceivedData.length);

            // 8. Receive incoming datagram into DatagramPacket object
            try {                                           // this is blocking system call
                clientSocket.receive(receivePacket);        // Program blocks here until a datagram is received
            } catch (IOException e) {
                e.printStackTrace();
            }

            // 9. Display the received data
            String strReceived = new String(receivePacket.getData());
            System.out.println("CLIENT RECEIVED DATA: " + strReceived);

            // 10. Close the Datagram Socket
            clientSocket.close();
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
}
