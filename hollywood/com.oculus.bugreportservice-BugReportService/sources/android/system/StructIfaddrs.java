package android.system;

import java.net.InetAddress;

public final class StructIfaddrs {
    public final byte[] hwaddr;
    public final InetAddress ifa_addr;
    public final InetAddress ifa_broadaddr;
    public final String ifa_name;
    public final InetAddress ifa_netmask;
}
