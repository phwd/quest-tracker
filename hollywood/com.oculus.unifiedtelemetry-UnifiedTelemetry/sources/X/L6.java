package X;

import java.io.IOException;
import java.lang.ref.Reference;
import java.net.ConnectException;
import java.net.Proxy;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public final class L6 extends AbstractC0338d6 {
    public int A00 = 1;
    public int A01;
    public long A02 = Long.MAX_VALUE;
    public Socket A03;
    public Socket A04;
    public C0371du A05;
    public EnumC0364dl A06;
    public C0337d5 A07;
    public KJ A08;
    public KC A09;
    public boolean A0A;
    public final List<Reference<C0350dM>> A0B = new ArrayList();
    public final e1 A0C;
    public final C0357de A0D;

    public static void A00(L6 l6, int i, int i2) throws IOException {
        Socket createSocket;
        C0357de deVar = l6.A0D;
        Proxy proxy = deVar.A01;
        eB eBVar = deVar.A02;
        if (proxy.type() == Proxy.Type.DIRECT || proxy.type() == Proxy.Type.HTTP) {
            createSocket = eBVar.A04.createSocket();
        } else {
            createSocket = new Socket(proxy);
        }
        l6.A03 = createSocket;
        createSocket.setSoTimeout(i2);
        try {
            C0324cr.A00.A06(l6.A03, deVar.A00, i);
            l6.A09 = new AnonymousClass93(C0318ch.A01(l6.A03));
            l6.A08 = new AnonymousClass94(C0318ch.A00(l6.A03));
        } catch (ConnectException e) {
            StringBuilder sb = new StringBuilder("Failed to connect to ");
            sb.append(deVar.A00);
            ConnectException connectException = new ConnectException(sb.toString());
            connectException.initCause(e);
            throw connectException;
        }
    }

    @Override // X.AbstractC0338d6
    public final void A01(C0337d5 d5Var) {
        synchronized (this.A0C) {
            this.A00 = d5Var.A00();
        }
    }

    @Override // X.AbstractC0338d6
    public final void A02(C0335d3 d3Var) throws IOException {
        d3Var.A05(dD.REFUSED_STREAM);
    }

    public final String toString() {
        Object obj;
        StringBuilder sb = new StringBuilder("Connection{");
        C0357de deVar = this.A0D;
        C0367dp dpVar = deVar.A02.A0A;
        sb.append(dpVar.A02);
        sb.append(":");
        sb.append(dpVar.A00);
        sb.append(", proxy=");
        sb.append(deVar.A01);
        sb.append(" hostAddress=");
        sb.append(deVar.A00);
        sb.append(" cipherSuite=");
        C0371du duVar = this.A05;
        if (duVar != null) {
            obj = duVar.A01;
        } else {
            obj = "none";
        }
        sb.append(obj);
        sb.append(" protocol=");
        sb.append(this.A06);
        sb.append('}');
        return sb.toString();
    }

    public L6(e1 e1Var, C0357de deVar) {
        this.A0C = e1Var;
        this.A0D = deVar;
    }
}
