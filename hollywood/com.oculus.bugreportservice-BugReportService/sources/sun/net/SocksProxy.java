package sun.net;

import java.net.Proxy;
import java.net.SocketAddress;

public final class SocksProxy extends Proxy {
    private final int version;

    private SocksProxy(SocketAddress socketAddress, int i) {
        super(Proxy.Type.SOCKS, socketAddress);
        this.version = i;
    }

    public static SocksProxy create(SocketAddress socketAddress, int i) {
        return new SocksProxy(socketAddress, i);
    }
}
