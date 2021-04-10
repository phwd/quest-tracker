package X;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;

/* renamed from: X.1en  reason: invalid class name */
public class AnonymousClass1en implements Runnable {
    public static final String __redex_internal_original_name = "fi.iki.elonen.NanoHTTPD$ServerRunnable";
    public IOException A00;
    public boolean A01 = false;
    public final int A02;
    public final /* synthetic */ AnonymousClass1ea A03;

    public AnonymousClass1en(AnonymousClass1ea r2, int i) {
        this.A03 = r2;
        this.A02 = i;
    }

    public final void run() {
        InetSocketAddress inetSocketAddress;
        try {
            AnonymousClass1ea r4 = this.A03;
            ServerSocket serverSocket = r4.myServerSocket;
            String str = r4.hostname;
            if (str != null) {
                inetSocketAddress = new InetSocketAddress(str, r4.myPort);
            } else {
                inetSocketAddress = new InetSocketAddress(r4.myPort);
            }
            serverSocket.bind(inetSocketAddress);
            this.A01 = true;
            do {
                try {
                    Socket accept = r4.myServerSocket.accept();
                    int i = this.A02;
                    if (i > 0) {
                        accept.setSoTimeout(i);
                    }
                    r4.asyncRunner.A2Z(new AnonymousClass1eZ(r4, accept.getInputStream(), accept));
                } catch (IOException e) {
                    AnonymousClass1ea.LOG.log(Level.FINE, "Communication with the client broken", (Throwable) e);
                }
            } while (!r4.myServerSocket.isClosed());
        } catch (IOException e2) {
            this.A00 = e2;
        }
    }
}
