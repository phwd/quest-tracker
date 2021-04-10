package X;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.ScheduledExecutorService;
import javax.annotation.concurrent.GuardedBy;

/* renamed from: X.0xd  reason: invalid class name and case insensitive filesystem */
public final class C08670xd {
    @GuardedBy("this")
    public Socket A00;
    public final int A01;
    public final InetAddress A02;
    public final InetAddress A03;
    public final ScheduledExecutorService A04;
    public final int A05;
    public final int A06;
    public final AnonymousClass0z6 A07;

    public static void A00(C08670xd r2, Socket socket, InetAddress inetAddress, Socket socket2) throws IOException {
        socket.setTcpNoDelay(true);
        socket.setSoTimeout(0);
        socket.setKeepAlive(false);
        socket.connect(new InetSocketAddress(inetAddress, r2.A05), r2.A06);
        synchronized (r2) {
            if (r2.A00 == null) {
                r2.A00 = socket;
                if (socket2 != null) {
                    try {
                        socket2.close();
                    } catch (IOException unused) {
                    }
                }
            }
        }
    }

    public C08670xd(InetAddress inetAddress, InetAddress inetAddress2, int i, int i2, AnonymousClass0z6 r5, ScheduledExecutorService scheduledExecutorService, int i3) {
        this.A07 = r5;
        this.A03 = inetAddress;
        this.A02 = inetAddress2;
        this.A05 = i;
        this.A06 = i2;
        this.A04 = scheduledExecutorService;
        this.A01 = i3;
    }
}
