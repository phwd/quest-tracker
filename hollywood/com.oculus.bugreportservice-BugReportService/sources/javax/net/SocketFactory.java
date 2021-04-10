package javax.net;

import java.net.Socket;
import java.net.SocketException;

public abstract class SocketFactory {
    private static SocketFactory theFactory;

    protected SocketFactory() {
    }

    public static SocketFactory getDefault() {
        synchronized (SocketFactory.class) {
            if (theFactory == null) {
                theFactory = new DefaultSocketFactory();
            }
        }
        return theFactory;
    }

    public Socket createSocket() {
        UnsupportedOperationException unsupportedOperationException = new UnsupportedOperationException();
        SocketException socketException = new SocketException("Unconnected sockets not implemented");
        socketException.initCause(unsupportedOperationException);
        throw socketException;
    }
}
