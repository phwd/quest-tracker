package X;

import java.net.Proxy;
import java.net.ProxySelector;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;

public final class XQ {
    public int A00;
    public Proxy A01;
    public ProxySelector A02;
    public List<XP> A03;
    public SocketFactory A04;
    public AbstractC0189Xo A05;
    public AbstractC0189Xo A06;
    public C0180Xf A07;
    public AbstractC0176Xb A08;
    public C0175Xa A09;
    public XZ A0A;
    public boolean A0B;
    public boolean A0C;
    public boolean A0D;
    public int A0E;
    public int A0F;
    public int A0G;
    public List<C0178Xd> A0H;
    public HostnameVerifier A0I;
    public SSLSocketFactory A0J;
    public C0183Xi A0K;
    public WU A0L;
    public final List<XS> A0M;
    public final List<XS> A0N;

    public static int A00(long j, TimeUnit timeUnit) {
        StringBuilder sb;
        String str;
        if (j >= 0) {
            long millis = timeUnit.toMillis(j);
            if (millis > 2147483647L) {
                sb = new StringBuilder();
                sb.append("timeout");
                str = " too large.";
            } else if (millis != 0 || j <= 0) {
                return (int) millis;
            } else {
                sb = new StringBuilder();
                sb.append("timeout");
                str = " too small.";
            }
        } else {
            sb = new StringBuilder();
            sb.append("timeout");
            str = " < 0";
        }
        sb.append(str);
        throw new IllegalArgumentException(sb.toString());
    }

    public XQ() {
        this.A0M = new ArrayList();
        this.A0N = new ArrayList();
        this.A09 = new C0175Xa();
        this.A03 = AbstractC0054Ej.A0P;
        this.A0H = AbstractC0054Ej.A0O;
        this.A02 = ProxySelector.getDefault();
        this.A08 = AbstractC0176Xb.A00;
        this.A04 = SocketFactory.getDefault();
        this.A0I = WS.A00;
        this.A0K = C0183Xi.A02;
        AbstractC0189Xo xo = AbstractC0189Xo.A00;
        this.A06 = xo;
        this.A05 = xo;
        this.A07 = new C0180Xf();
        this.A0A = XZ.A00;
        this.A0C = true;
        this.A0B = true;
        this.A0D = true;
        this.A0E = 10000;
        this.A0F = 10000;
        this.A0G = 10000;
        this.A00 = 0;
    }

    public XQ(AbstractC0054Ej ej) {
        ArrayList arrayList = new ArrayList();
        this.A0M = arrayList;
        this.A0N = new ArrayList();
        this.A09 = ej.A0J;
        this.A01 = ej.A05;
        this.A03 = ej.A0A;
        this.A0H = ej.A07;
        arrayList.addAll(ej.A08);
        this.A0N.addAll(ej.A09);
        this.A02 = ej.A06;
        this.A08 = ej.A0I;
        this.A04 = ej.A0B;
        this.A0J = ej.A0D;
        this.A0L = ej.A01;
        this.A0I = ej.A0C;
        this.A0K = ej.A0G;
        this.A06 = ej.A0F;
        this.A05 = ej.A0E;
        this.A07 = ej.A0H;
        this.A0A = ej.A0K;
        this.A0C = ej.A0M;
        this.A0B = ej.A0L;
        this.A0D = ej.A0N;
        this.A0E = ej.A02;
        this.A0F = ej.A03;
        this.A0G = ej.A04;
        this.A00 = ej.A00;
    }
}
