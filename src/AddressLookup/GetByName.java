package AddressLookup;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class GetByName {
    public static void main(String[] args) {
        try {
            InetAddress address = InetAddress.getByName("www.google.com");
            System.out.println(address.getHostName() + " - " + address.getHostAddress() );
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}

//    At which line, calling the DNS occurs ?
//    Line 9 or Line 10 ?