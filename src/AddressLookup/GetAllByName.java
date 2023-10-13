package AddressLookup;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class GetAllByName {
    public static void main(String[] args) {
        try {
            InetAddress[] addresses = InetAddress.getAllByName("www.google.com");
            for (int i = 0; i < addresses.length; i++) {
                System.out.println(addresses[i]);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}

//  Google is having several servers which map to one name. So lets take some of them.
