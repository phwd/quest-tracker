package X;

import java.io.IOException;
import java.lang.ref.Reference;
import java.net.ConnectException;
import java.net.Proxy;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/* renamed from: X.0Pw  reason: invalid class name and case insensitive filesystem */
public final class C01990Pw extends AbstractC04790hV {
    public int A00 = 1;
    public int A01;
    public long A02 = Long.MAX_VALUE;
    public Socket A03;
    public Socket A04;
    public C06040lr A05;
    public AnonymousClass0kh A06;
    public C04780hU A07;
    public AnonymousClass0Oe A08;
    public AnonymousClass0Od A09;
    public boolean A0A;
    public final List<Reference<C05360jA>> A0B = new ArrayList();
    public final AnonymousClass0m7 A0C;
    public final C05640kB A0D;

    public static void A00(C01990Pw r5, int i, int i2) throws IOException {
        Socket createSocket;
        C05640kB r3 = r5.A0D;
        Proxy proxy = r3.A01;
        C06800nu r2 = r3.A02;
        if (proxy.type() == Proxy.Type.DIRECT || proxy.type() == Proxy.Type.HTTP) {
            createSocket = r2.A03.createSocket();
        } else {
            createSocket = new Socket(proxy);
        }
        r5.A03 = createSocket;
        createSocket.setSoTimeout(i2);
        try {
            C04670hG.A00.A06(r5.A03, r3.A00, i);
            r5.A09 = new AnonymousClass0HO(C04600h6.A01(r5.A03));
            r5.A08 = new AnonymousClass0HP(C04600h6.A00(r5.A03));
        } catch (ConnectException e) {
            ConnectException connectException = new ConnectException("Failed to connect to " + r3.A00);
            connectException.initCause(e);
            throw connectException;
        }
    }

    @Override // X.AbstractC04790hV
    public final void A01(C04780hU r3) {
        synchronized (this.A0C) {
            this.A00 = r3.A00();
        }
    }

    @Override // X.AbstractC04790hV
    public final void A02(C04760hS r2) throws IOException {
        r2.A05(EnumC04880hs.REFUSED_STREAM);
    }

    public final String toString() {
        Object obj;
        StringBuilder sb = new StringBuilder("Connection{");
        C05640kB r3 = this.A0D;
        C05890la r1 = r3.A02.A09;
        sb.append(r1.A02);
        sb.append(":");
        sb.append(r1.A00);
        sb.append(", proxy=");
        sb.append(r3.A01);
        sb.append(" hostAddress=");
        sb.append(r3.A00);
        sb.append(" cipherSuite=");
        C06040lr r0 = this.A05;
        if (r0 != null) {
            obj = r0.A01;
        } else {
            obj = "none";
        }
        sb.append(obj);
        sb.append(" protocol=");
        sb.append(this.A06);
        sb.append('}');
        return sb.toString();
    }

    public C01990Pw(AnonymousClass0m7 r3, C05640kB r4) {
        this.A0C = r3;
        this.A0D = r4;
    }
}
