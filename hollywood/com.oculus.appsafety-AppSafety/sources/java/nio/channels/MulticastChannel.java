package java.nio.channels;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;

public interface MulticastChannel extends NetworkChannel {
    @Override // java.io.Closeable, java.lang.AutoCloseable, java.nio.channels.Channel
    void close() throws IOException;

    MembershipKey join(InetAddress inetAddress, NetworkInterface networkInterface) throws IOException;

    MembershipKey join(InetAddress inetAddress, NetworkInterface networkInterface, InetAddress inetAddress2) throws IOException;
}
