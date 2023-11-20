package NonBlockingIO;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

public class NIODayTimeServer {
    public final static int SERVER_PORT = 13;

    public static void main(String[] args) {
        try{
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            Selector selector = Selector.open();

            // Setting the socket channel to be non-blocking
            serverSocketChannel.configureBlocking(false);

            // Creating a socket address
                // InetAddress consists of an IP Address and Hostname
                // InetSocketAddress consists of IP Address (or Hostname) + port number pair.
            InetSocketAddress socketAddress = new InetSocketAddress("localhost", SERVER_PORT);

            // Bind the server socket to the ip/port
            serverSocketChannel.socket().bind(socketAddress);
            System.out.println("ServerSocketChannel : " + serverSocketChannel.toString());

            // The interestSet is the set of events that you are interested in 'selecting'
            int interestSet = SelectionKey.OP_ACCEPT;
            SelectionKey key = serverSocketChannel.register(selector, interestSet);

            // The repeating activities.
            while (true) {
                selector.select();  // This line block until some event has occurred

                // Once you have call one of the select() method nd its return value as indicated
                // that one or more channel are ready, you can access the ready channel, vai the selected key set
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectedKeys.iterator();

                while (iterator.hasNext()) {
                    SelectionKey selectionKey = (SelectionKey) iterator.next();
                    iterator.remove(); // Remove the fetch kay

                    if (!selectionKey.isValid()) { continue;}

                    if (selectionKey.isAcceptable()) {  // Connection accepted by server socket
                        OnConnectionAcceptable(selectionKey);
                    }

                    if (selectionKey.isReadable()) {
                        // A channel is ready for reading
                    }
                    if (selectionKey.isWritable()) {
                        // A channel is ready for writing
                    }
                }
            } // while(true) ends here
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void OnConnectionAcceptable(SelectionKey selectionKey) {
        try {
            // Get the channel
            ServerSocketChannel channel = (ServerSocketChannel) selectionKey.channel();

            // Accept it
            SocketChannel socketChannel = channel.accept();
            System.out.println("Accepted new client connection : " + socketChannel.toString());

            // Send data time
            PrintWriter out = new PrintWriter(socketChannel.socket().getOutputStream(), true);
            Date now = new Date();
            out.println(now);
            // out.close();  // comment to keep the connection active for the demonstration

            // Make this new socket connection with client non-blocking
            // socketChannel.configureBlocking(false);
            // Register it with selector we used above
            // SelectionKey readKey = socketChannel.register(selector, SelectionKey.OP_READ|SelectionKey.OP_WRITE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
