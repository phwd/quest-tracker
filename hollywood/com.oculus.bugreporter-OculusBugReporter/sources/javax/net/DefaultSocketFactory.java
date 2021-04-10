package javax.net;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/* access modifiers changed from: package-private */
/* compiled from: SocketFactory */
public class DefaultSocketFactory extends SocketFactory {
    DefaultSocketFactory() {
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket() {
        return new Socket();
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String host, int port) throws IOException, UnknownHostException {
        return new Socket(host, port);
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress address, int port) throws IOException {
        return new Socket(address, port);
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String host, int port, InetAddress clientAddress, int clientPort) throws IOException, UnknownHostException {
        return new Socket(host, port, clientAddress, clientPort);
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress address, int port, InetAddress clientAddress, int clientPort) throws IOException {
        return new Socket(address, port, clientAddress, clientPort);
    }
}
