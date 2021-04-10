package X;

import java.io.IOException;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/* renamed from: X.th  reason: case insensitive filesystem */
public final class C1141th extends cP {
    public int A00 = 1;
    public int A01;
    public long A02 = Long.MAX_VALUE;
    public Socket A03;
    public Socket A04;
    public C0540bd A05;
    public EnumC0549bm A06;
    public C0584cQ A07;
    public t6 A08;
    public t4 A09;
    public boolean A0A;
    public final List A0B = new ArrayList();
    public final C0533bW A0C;
    public final C0556bt A0D;

    public static void A00(C1141th thVar, int i, int i2) {
        Socket createSocket;
        C0556bt btVar = thVar.A0D;
        Proxy proxy = btVar.A01;
        C0523bM bMVar = btVar.A02;
        if (proxy.type() == Proxy.Type.DIRECT || proxy.type() == Proxy.Type.HTTP) {
            createSocket = bMVar.A03.createSocket();
        } else {
            createSocket = new Socket(proxy);
        }
        thVar.A03 = createSocket;
        createSocket.setSoTimeout(i2);
        try {
            C0595ce ceVar = C0595ce.A01;
            Socket socket = thVar.A03;
            InetSocketAddress inetSocketAddress = btVar.A00;
            if (!(ceVar instanceof C1117tF)) {
                socket.connect(inetSocketAddress, i);
            } else {
                try {
                    socket.connect(inetSocketAddress, i);
                } catch (AssertionError e) {
                    if (C0561by.A08(e)) {
                        throw new IOException(e);
                    }
                    throw e;
                } catch (SecurityException e2) {
                    IOException iOException = new IOException("Exception in connect");
                    iOException.initCause(e2);
                    throw iOException;
                }
            }
            thVar.A09 = new C00222y(C0604cn.A01(thVar.A03));
            thVar.A08 = new AnonymousClass30(C0604cn.A00(thVar.A03));
        } catch (ConnectException e3) {
            StringBuilder sb = new StringBuilder("Failed to connect to ");
            sb.append(btVar.A00);
            ConnectException connectException = new ConnectException(sb.toString());
            connectException.initCause(e3);
            throw connectException;
        }
    }

    public final String toString() {
        Object obj;
        StringBuilder sb = new StringBuilder("Connection{");
        C0556bt btVar = this.A0D;
        C0544bh bhVar = btVar.A02.A09;
        sb.append(bhVar.A02);
        sb.append(":");
        sb.append(bhVar.A00);
        sb.append(", proxy=");
        sb.append(btVar.A01);
        sb.append(" hostAddress=");
        sb.append(btVar.A00);
        sb.append(" cipherSuite=");
        C0540bd bdVar = this.A05;
        if (bdVar != null) {
            obj = bdVar.A01;
        } else {
            obj = "none";
        }
        sb.append(obj);
        sb.append(" protocol=");
        sb.append(this.A06);
        sb.append('}');
        return sb.toString();
    }

    public C1141th(C0533bW bWVar, C0556bt btVar) {
        this.A0C = bWVar;
        this.A0D = btVar;
    }
}
