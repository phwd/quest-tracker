package X;

import java.io.IOException;
import java.lang.ref.Reference;
import java.net.ConnectException;
import java.net.Proxy;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/* renamed from: X.0Mm  reason: invalid class name and case insensitive filesystem */
public final class C01250Mm extends AbstractC07930vc {
    public int A00 = 1;
    public int A01;
    public long A02 = Long.MAX_VALUE;
    public Socket A03;
    public Socket A04;
    public C08430wZ A05;
    public EnumC08350wP A06;
    public C07920vb A07;
    public AnonymousClass0Lx A08;
    public AnonymousClass0Lw A09;
    public boolean A0A;
    public final List<Reference<C08090vs>> A0B = new ArrayList();
    public final C08510wh A0C;
    public final C08200wA A0D;

    public static void A00(C01250Mm r5, int i, int i2) throws IOException {
        Socket createSocket;
        C08200wA r3 = r5.A0D;
        Proxy proxy = r3.A01;
        C08610wt r2 = r3.A02;
        if (proxy.type() == Proxy.Type.DIRECT || proxy.type() == Proxy.Type.HTTP) {
            createSocket = r2.A04.createSocket();
        } else {
            createSocket = new Socket(proxy);
        }
        r5.A03 = createSocket;
        createSocket.setSoTimeout(i2);
        try {
            C07790vM.A00.A06(r5.A03, r3.A00, i);
            r5.A09 = new C00560Au(C07690vC.A01(r5.A03));
            r5.A08 = new C00570Av(C07690vC.A00(r5.A03));
        } catch (ConnectException e) {
            StringBuilder sb = new StringBuilder("Failed to connect to ");
            sb.append(r3.A00);
            ConnectException connectException = new ConnectException(sb.toString());
            connectException.initCause(e);
            throw connectException;
        }
    }

    @Override // X.AbstractC07930vc
    public final void A01(C07920vb r3) {
        synchronized (this.A0C) {
            this.A00 = r3.A00();
        }
    }

    @Override // X.AbstractC07930vc
    public final void A02(C07900vZ r2) throws IOException {
        r2.A05(EnumC08000vj.REFUSED_STREAM);
    }

    public final String toString() {
        Object obj;
        StringBuilder sb = new StringBuilder("Connection{");
        C08200wA r3 = this.A0D;
        C08400wU r1 = r3.A02.A0A;
        sb.append(r1.A02);
        sb.append(":");
        sb.append(r1.A00);
        sb.append(", proxy=");
        sb.append(r3.A01);
        sb.append(" hostAddress=");
        sb.append(r3.A00);
        sb.append(" cipherSuite=");
        C08430wZ r0 = this.A05;
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

    public C01250Mm(C08510wh r3, C08200wA r4) {
        this.A0C = r3;
        this.A0D = r4;
    }
}
