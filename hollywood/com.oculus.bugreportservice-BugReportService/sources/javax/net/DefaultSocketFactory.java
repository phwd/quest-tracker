package javax.net;

import java.net.Socket;

/* access modifiers changed from: package-private */
/* compiled from: SocketFactory */
public class DefaultSocketFactory extends SocketFactory {
    DefaultSocketFactory() {
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket() {
        return new Socket();
    }
}
