package javax.net.ssl;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.SocketException;

/* compiled from: SSLServerSocketFactory */
class DefaultSSLServerSocketFactory extends SSLServerSocketFactory {
    private final Exception reason;

    DefaultSSLServerSocketFactory(Exception reason2) {
        this.reason = reason2;
    }

    private ServerSocket throwException() throws SocketException {
        throw ((SocketException) new SocketException(this.reason.toString()).initCause(this.reason));
    }

    @Override // javax.net.ServerSocketFactory
    public ServerSocket createServerSocket() throws IOException {
        return throwException();
    }

    @Override // javax.net.ServerSocketFactory
    public ServerSocket createServerSocket(int port) throws IOException {
        return throwException();
    }

    @Override // javax.net.ServerSocketFactory
    public ServerSocket createServerSocket(int port, int backlog) throws IOException {
        return throwException();
    }

    @Override // javax.net.ServerSocketFactory
    public ServerSocket createServerSocket(int port, int backlog, InetAddress ifAddress) throws IOException {
        return throwException();
    }

    @Override // javax.net.ssl.SSLServerSocketFactory
    public String[] getDefaultCipherSuites() {
        return new String[0];
    }

    @Override // javax.net.ssl.SSLServerSocketFactory
    public String[] getSupportedCipherSuites() {
        return new String[0];
    }
}
