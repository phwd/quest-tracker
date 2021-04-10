package X;

import com.oculus.horizon.notifications.core.NotificationBuilder;
import java.net.Proxy;
import java.net.ProxySelector;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;

/* renamed from: X.0wQ  reason: invalid class name and case insensitive filesystem */
public final class C08360wQ {
    public int A00;
    public Proxy A01;
    public ProxySelector A02;
    public List<EnumC08350wP> A03;
    public SocketFactory A04;
    public AbstractC08600ws A05;
    public AbstractC08600ws A06;
    public C08510wh A07;
    public AbstractC08470wd A08;
    public C08450wb A09;
    public AbstractC08440wa A0A;
    public boolean A0B;
    public boolean A0C;
    public boolean A0D;
    public int A0E;
    public int A0F;
    public int A0G;
    public List<C08490wf> A0H;
    public HostnameVerifier A0I;
    public SSLSocketFactory A0J;
    public C08540wk A0K;
    public AbstractC07780vL A0L;
    public final List<AbstractC08380wS> A0M;
    public final List<AbstractC08380wS> A0N;

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

    public C08360wQ() {
        this.A0M = new ArrayList();
        this.A0N = new ArrayList();
        this.A09 = new C08450wb();
        this.A03 = AnonymousClass0N1.A0P;
        this.A0H = AnonymousClass0N1.A0O;
        this.A02 = ProxySelector.getDefault();
        this.A08 = AbstractC08470wd.A00;
        this.A04 = SocketFactory.getDefault();
        this.A0I = C07760vJ.A00;
        this.A0K = C08540wk.A02;
        AbstractC08600ws r0 = AbstractC08600ws.A00;
        this.A06 = r0;
        this.A05 = r0;
        this.A07 = new C08510wh();
        this.A0A = AbstractC08440wa.A00;
        this.A0C = true;
        this.A0B = true;
        this.A0D = true;
        this.A0E = NotificationBuilder.CANCELLABLE_NOTIFICATION_FIRST_ID;
        this.A0F = NotificationBuilder.CANCELLABLE_NOTIFICATION_FIRST_ID;
        this.A0G = NotificationBuilder.CANCELLABLE_NOTIFICATION_FIRST_ID;
        this.A00 = 0;
    }

    public C08360wQ(AnonymousClass0N1 r3) {
        ArrayList arrayList = new ArrayList();
        this.A0M = arrayList;
        this.A0N = new ArrayList();
        this.A09 = r3.A0J;
        this.A01 = r3.A05;
        this.A03 = r3.A0A;
        this.A0H = r3.A07;
        arrayList.addAll(r3.A08);
        this.A0N.addAll(r3.A09);
        this.A02 = r3.A06;
        this.A08 = r3.A0I;
        this.A04 = r3.A0B;
        this.A0J = r3.A0D;
        this.A0L = r3.A01;
        this.A0I = r3.A0C;
        this.A0K = r3.A0G;
        this.A06 = r3.A0F;
        this.A05 = r3.A0E;
        this.A07 = r3.A0H;
        this.A0A = r3.A0K;
        this.A0C = r3.A0M;
        this.A0B = r3.A0L;
        this.A0D = r3.A0N;
        this.A0E = r3.A02;
        this.A0F = r3.A03;
        this.A0G = r3.A04;
        this.A00 = r3.A00;
    }
}
