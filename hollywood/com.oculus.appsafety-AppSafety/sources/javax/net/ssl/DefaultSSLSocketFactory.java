package javax.net.ssl;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;

/* access modifiers changed from: package-private */
/* compiled from: SSLSocketFactory */
public class DefaultSSLSocketFactory extends SSLSocketFactory {
    private Exception reason;

    DefaultSSLSocketFactory(Exception reason2) {
        this.reason = reason2;
    }

    private Socket throwException() throws SocketException {
        throw ((SocketException) new SocketException(this.reason.toString()).initCause(this.reason));
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket() throws IOException {
        return throwException();
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String host, int port) throws IOException {
        return throwException();
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public Socket createSocket(Socket s, String host, int port, boolean autoClose) throws IOException {
        return throwException();
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress address, int port) throws IOException {
        return throwException();
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String host, int port, InetAddress clientAddress, int clientPort) throws IOException {
        return throwException();
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress address, int port, InetAddress clientAddress, int clientPort) throws IOException {
        return throwException();
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public String[] getDefaultCipherSuites() {
        return new String[0];
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public String[] getSupportedCipherSuites() {
        return new String[0];
    }
}
