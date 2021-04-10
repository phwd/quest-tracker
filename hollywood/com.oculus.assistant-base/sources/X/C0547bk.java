package X;

import java.net.ProxySelector;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;

/* renamed from: X.bk  reason: case insensitive filesystem */
public final class C0547bk {
    public ProxySelector A00 = ProxySelector.getDefault();
    public List A01 = C0548bl.A0M;
    public List A02 = C0548bl.A0N;
    public SocketFactory A03 = SocketFactory.getDefault();
    public HostnameVerifier A04 = C0598ch.A00;
    public AbstractC0524bN A05;
    public AbstractC0524bN A06;
    public C0533bW A07;
    public AbstractC0537ba A08 = AbstractC0537ba.A00;
    public C0538bb A09 = new C0538bb();
    public AbstractC0539bc A0A;
    public int A0B;
    public int A0C;
    public int A0D;
    public C0530bT A0E = C0530bT.A02;
    public final List A0F = new ArrayList();
    public final List A0G = new ArrayList();

    public static int A00(long j, TimeUnit timeUnit) {
        if (j < 0) {
            throw new IllegalArgumentException(AnonymousClass08.A04("timeout", " < 0"));
        } else if (timeUnit != null) {
            long millis = timeUnit.toMillis(j);
            if (millis > 2147483647L) {
                throw new IllegalArgumentException(AnonymousClass08.A04("timeout", " too large."));
            } else if (millis != 0 || j <= 0) {
                return (int) millis;
            } else {
                throw new IllegalArgumentException(AnonymousClass08.A04("timeout", " too small."));
            }
        } else {
            throw new NullPointerException("unit == null");
        }
    }

    public C0547bk() {
        AbstractC0524bN bNVar = AbstractC0524bN.A00;
        this.A06 = bNVar;
        this.A05 = bNVar;
        this.A07 = new C0533bW();
        this.A0A = AbstractC0539bc.A00;
        this.A0B = AbstractC0447Yr.LOG_RATE_LIMIT;
        this.A0C = AbstractC0447Yr.LOG_RATE_LIMIT;
        this.A0D = AbstractC0447Yr.LOG_RATE_LIMIT;
    }
}
