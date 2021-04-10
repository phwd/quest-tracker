package X;

import java.io.IOException;
import java.lang.ref.Reference;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public final class EX extends AbstractC0159Wk {
    public int A00 = 1;
    public int A01;
    public long A02 = Long.MAX_VALUE;
    public Socket A03;
    public Socket A04;
    public XY A05;
    public XP A06;
    public C0158Wj A07;
    public Du A08;
    public Dp A09;
    public boolean A0A;
    public final List<Reference<X0>> A0B = new ArrayList();
    public final C0180Xf A0C;
    public final XI A0D;

    public static void A00(EX ex, int i, int i2) throws IOException {
        Socket createSocket;
        XI xi = ex.A0D;
        Proxy proxy = xi.A01;
        C0190Xp xp = xi.A02;
        if (proxy.type() == Proxy.Type.DIRECT || proxy.type() == Proxy.Type.HTTP) {
            createSocket = xp.A04.createSocket();
        } else {
            createSocket = new Socket(proxy);
        }
        ex.A03 = createSocket;
        createSocket.setSoTimeout(i2);
        try {
            WV wv = WV.A01;
            Socket socket = ex.A03;
            InetSocketAddress inetSocketAddress = xi.A00;
            if (!(wv instanceof E3)) {
                socket.connect(inetSocketAddress, i);
            } else {
                try {
                    socket.connect(inetSocketAddress, i);
                } catch (AssertionError e) {
                    if (XD.A08(e)) {
                        throw new IOException(e);
                    }
                    throw e;
                } catch (SecurityException e2) {
                    IOException iOException = new IOException("Exception in connect");
                    iOException.initCause(e2);
                    throw iOException;
                }
            }
            ex.A09 = new C00148h(WL.A01(ex.A03));
            ex.A08 = new AnonymousClass8i(WL.A00(ex.A03));
        } catch (ConnectException e3) {
            StringBuilder sb = new StringBuilder("Failed to connect to ");
            sb.append(xi.A00);
            ConnectException connectException = new ConnectException(sb.toString());
            connectException.initCause(e3);
            throw connectException;
        }
    }

    public final String toString() {
        Object obj;
        StringBuilder sb = new StringBuilder("Connection{");
        XI xi = this.A0D;
        XT xt = xi.A02.A0A;
        sb.append(xt.A02);
        sb.append(":");
        sb.append(xt.A00);
        sb.append(", proxy=");
        sb.append(xi.A01);
        sb.append(" hostAddress=");
        sb.append(xi.A00);
        sb.append(" cipherSuite=");
        XY xy = this.A05;
        if (xy != null) {
            obj = xy.A01;
        } else {
            obj = "none";
        }
        sb.append(obj);
        sb.append(" protocol=");
        sb.append(this.A06);
        sb.append('}');
        return sb.toString();
    }

    public EX(C0180Xf xf, XI xi) {
        this.A0C = xf;
        this.A0D = xi;
    }
}
