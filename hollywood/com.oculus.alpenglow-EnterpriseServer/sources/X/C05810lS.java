package X;

import java.net.ProxySelector;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;

/* renamed from: X.0lS  reason: invalid class name and case insensitive filesystem */
public final class C05810lS {
    public ProxySelector A00 = ProxySelector.getDefault();
    public List<AnonymousClass0kh> A01 = AnonymousClass0Qs.A0N;
    public SocketFactory A02 = SocketFactory.getDefault();
    public HostnameVerifier A03 = C04650hD.A00;
    public AnonymousClass0nt A04;
    public AnonymousClass0nt A05;
    public AnonymousClass0m7 A06;
    public AbstractC06150m3 A07 = AbstractC06150m3.A00;
    public C06110ly A08 = new C06110ly();
    public AbstractC06050ls A09;
    public int A0A;
    public int A0B;
    public int A0C;
    public List<AnonymousClass0m5> A0D = AnonymousClass0Qs.A0M;
    public SSLSocketFactory A0E;
    public C06330ma A0F = C06330ma.A02;
    public AbstractC04660hF A0G;
    public final List<AbstractC05850lW> A0H = new ArrayList();
    public final List<AbstractC05850lW> A0I = new ArrayList();

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

    public C05810lS() {
        AnonymousClass0nt r0 = AnonymousClass0nt.A00;
        this.A05 = r0;
        this.A04 = r0;
        this.A06 = new AnonymousClass0m7();
        this.A09 = AbstractC06050ls.A00;
        this.A0A = 10000;
        this.A0B = 10000;
        this.A0C = 10000;
    }
}
