package UDP_Datagram.Simple_UDP_Client_And_Server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;

public class SimpleUDPServer {
    public final static int SERVER_PORT = 10005;
    public final static int CLIENT_LISTENING_PORT = 10006;

    public static void main(String[] args) {

        try {
            // Create datagram socket
            DatagramSocket serverSocket = new DatagramSocket(SERVER_PORT);

            InetAddress clientAddress = InetAddress.getLocalHost();     // Receive address and port

            int iCounter = 0;

            while (true) {
                Date today = new Date();
                String sDateToSend = "Seq [" + iCounter + "] Data [" + today.toString() + "]";

                // Create datagram to send
                DatagramPacket packetOut = new DatagramPacket(
                        sDateToSend.getBytes(),
                        sDateToSend.length(),
                        clientAddress,
                        CLIENT_LISTENING_PORT
                );

                // send the response datagram
                try {
                    serverSocket.send(packetOut);
                    String strOutData = new String(packetOut.getData());
                    System.out.println("Sent UDP Data : " + strOutData);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Thread.sleep(1000);
                iCounter++;
            }

            // close the DatagramSocket
            // serverSocket.close;
        } catch (Exception e)  {
            e.printStackTrace();
        }
    }
}
