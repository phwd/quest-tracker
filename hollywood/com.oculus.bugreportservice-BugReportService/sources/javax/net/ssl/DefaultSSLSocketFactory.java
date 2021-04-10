package javax.net.ssl;

import java.net.Socket;
import java.net.SocketException;

/* access modifiers changed from: package-private */
/* compiled from: SSLSocketFactory */
public class DefaultSSLSocketFactory extends SSLSocketFactory {
    private Exception reason;

    DefaultSSLSocketFactory(Exception exc) {
        this.reason = exc;
    }

    private Socket throwException() {
        throw ((SocketException) new SocketException(this.reason.toString()).initCause(this.reason));
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket() {
        throwException();
        throw null;
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public Socket createSocket(Socket socket, String str, int i, boolean z) {
        throwException();
        throw null;
    }
}
