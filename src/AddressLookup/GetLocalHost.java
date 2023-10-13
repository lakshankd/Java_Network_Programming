package AddressLookup;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class GetLocalHost {
    public static void main(String[] args) {
        try{
            InetAddress address = InetAddress.getLocalHost();
            System.out.println(address.getHostAddress() + " - " + address.getHostName());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
