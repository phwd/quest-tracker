package java.net;

/* access modifiers changed from: package-private */
public interface InetAddressImpl {
    InetAddress anyLocalAddress();

    String getHostByAddr(byte[] bArr);

    InetAddress[] lookupAllHostAddr(String str, int i);

    InetAddress[] loopbackAddresses();
}
