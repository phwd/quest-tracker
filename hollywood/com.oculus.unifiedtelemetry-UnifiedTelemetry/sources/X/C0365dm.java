package X;

import java.net.Proxy;
import java.net.ProxySelector;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;

/* renamed from: X.dm  reason: case insensitive filesystem */
public final class C0365dm {
    public int A00;
    public Proxy A01;
    public ProxySelector A02;
    public List<EnumC0364dl> A03;
    public SocketFactory A04;
    public eA A05;
    public eA A06;
    public e1 A07;
    public AbstractC0374dx A08;
    public C0373dw A09;
    public AbstractC0372dv A0A;
    public boolean A0B;
    public boolean A0C;
    public boolean A0D;
    public int A0E;
    public int A0F;
    public int A0G;
    public List<C0376dz> A0H;
    public HostnameVerifier A0I;
    public SSLSocketFactory A0J;
    public e4 A0K;
    public cq A0L;
    public final List<Cdo> A0M;
    public final List<Cdo> A0N;

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

    public C0365dm() {
        this.A0M = new ArrayList();
        this.A0N = new ArrayList();
        this.A09 = new C0373dw();
        this.A03 = LD.A0P;
        this.A0H = LD.A0O;
        this.A02 = ProxySelector.getDefault();
        this.A08 = AbstractC0374dx.A00;
        this.A04 = SocketFactory.getDefault();
        this.A0I = C0323co.A00;
        this.A0K = e4.A02;
        eA eAVar = eA.A00;
        this.A06 = eAVar;
        this.A05 = eAVar;
        this.A07 = new e1();
        this.A0A = AbstractC0372dv.A00;
        this.A0C = true;
        this.A0B = true;
        this.A0D = true;
        this.A0E = 10000;
        this.A0F = 10000;
        this.A0G = 10000;
        this.A00 = 0;
    }

    public C0365dm(LD ld) {
        ArrayList arrayList = new ArrayList();
        this.A0M = arrayList;
        this.A0N = new ArrayList();
        this.A09 = ld.A0J;
        this.A01 = ld.A05;
        this.A03 = ld.A0A;
        this.A0H = ld.A07;
        arrayList.addAll(ld.A08);
        this.A0N.addAll(ld.A09);
        this.A02 = ld.A06;
        this.A08 = ld.A0I;
        this.A04 = ld.A0B;
        this.A0J = ld.A0D;
        this.A0L = ld.A01;
        this.A0I = ld.A0C;
        this.A0K = ld.A0G;
        this.A06 = ld.A0F;
        this.A05 = ld.A0E;
        this.A07 = ld.A0H;
        this.A0A = ld.A0K;
        this.A0C = ld.A0M;
        this.A0B = ld.A0L;
        this.A0D = ld.A0N;
        this.A0E = ld.A02;
        this.A0F = ld.A03;
        this.A0G = ld.A04;
        this.A00 = ld.A00;
    }
}
