package me.daqiang.utils.host;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;
import static java.lang.System.out;

/**
 * @author daqiang
 * @date 2020/4/16 10:54 上午
 **/
public class HostInfo {

    public static void getNetInfo() throws Exception {
        Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
        for (NetworkInterface netint : Collections.list(nets))
            displayInterfaceInformation(netint);
    }

    static void displayInterfaceInformation(NetworkInterface netint) throws Exception {
        out.printf("Display name: %s\n", netint.getDisplayName());
        out.printf("Name: %s\n", netint.getName());
        Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
        for (InetAddress inetAddress : Collections.list(inetAddresses)) {
            out.printf("InetAddress -> : %s\n", inetAddress);
        }

        out.printf("Host Info : %s \n", InetAddress.getLocalHost().getHostName());

        out.printf("\n");
    }

    public static void main(String[] args) throws Exception {
//        HostInfo.getNetInfo();
        System.out.println("Ip: " + Host.GetAddress("ip"));
        System.out.println("Mac: " + Host.GetAddress("mac"));
    }
}
