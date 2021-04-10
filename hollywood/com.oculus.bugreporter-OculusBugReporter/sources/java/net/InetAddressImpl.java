package java.net;

import java.io.IOException;

/* access modifiers changed from: package-private */
public interface InetAddressImpl {
    InetAddress anyLocalAddress();

    void clearAddressCache();

    String getHostByAddr(byte[] bArr) throws UnknownHostException;

    boolean isReachable(InetAddress inetAddress, int i, NetworkInterface networkInterface, int i2) throws IOException;

    InetAddress[] lookupAllHostAddr(String str, int i) throws UnknownHostException;

    InetAddress[] loopbackAddresses();
}
