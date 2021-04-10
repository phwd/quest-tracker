package sun.net.spi.nameservice;

import java.net.InetAddress;

public interface NameService {
    String getHostByAddr(byte[] bArr);

    InetAddress[] lookupAllHostAddr(String str, int i);
}
