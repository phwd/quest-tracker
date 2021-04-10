package X;

import android.app.ActivityManager;
import android.content.Context;
import com.facebook.acra.AppComponentStats;
import java.util.HashSet;
import java.util.Set;

/* renamed from: X.1jj  reason: invalid class name and case insensitive filesystem */
public final class C09771jj {
    public static C10461mp A0M = new C10461mp();
    public final Context A00;
    public final AnonymousClass1lV A01;
    public final AnonymousClass1lV A02;
    public final AbstractC00750Ik<AnonymousClass0PF> A03;
    public final AbstractC00750Ik<AnonymousClass0PF> A04;
    public final AbstractC00750Ik<Boolean> A05;
    public final AnonymousClass0JS A06;
    public final AnonymousClass0mj A07;
    public final AnonymousClass1l8 A08;
    public final AnonymousClass0PE A09 = new C03490mn();
    public final AnonymousClass1k3 A0A;
    public final C03440md A0B;
    public final AnonymousClass1lU A0C;
    public final AnonymousClass1jq A0D;
    public final AnonymousClass1Wg A0E;
    public final AnonymousClass1k2 A0F;
    public final AnonymousClass1iP A0G;
    public final AbstractC10031kh A0H;
    public final Set<AnonymousClass1m8> A0I;
    public final Set<AnonymousClass1mI> A0J;
    public final boolean A0K;
    public final boolean A0L;

    /* JADX INFO: finally extract failed */
    public C09771jj(AnonymousClass1jk r4) {
        AnonymousClass1l8 r0;
        AnonymousClass1k3 r02;
        C01060Pq.A00();
        this.A0D = new AnonymousClass1jq(r4.A03);
        Context context = r4.A02;
        this.A03 = new C09661jD((ActivityManager) context.getSystemService(AppComponentStats.TAG_ACTIVITY));
        synchronized (AnonymousClass1l8.class) {
            r0 = AnonymousClass1l8.A00;
            if (r0 == null) {
                r0 = new AnonymousClass1l8();
                AnonymousClass1l8.A00 = r0;
            }
        }
        this.A08 = r0;
        if (context != null) {
            this.A00 = context;
            this.A0C = new AnonymousClass1lU(new AnonymousClass1k1());
            this.A04 = new C09651jC();
            synchronized (AnonymousClass1k3.class) {
                r02 = AnonymousClass1k3.A00;
                if (r02 == null) {
                    r02 = new AnonymousClass1k3();
                    AnonymousClass1k3.A00 = r02;
                }
            }
            this.A0A = r02;
            this.A05 = new AnonymousClass1Vx(this);
            try {
                C01060Pq.A00();
                AnonymousClass1lV r03 = new AnonymousClass1lV(new C10351ls(context));
                C01060Pq.A00();
                this.A01 = r03;
                this.A06 = C05010rh.A00();
                C01060Pq.A00();
                AbstractC10031kh r1 = r4.A01;
                this.A0H = r1 == null ? new C09941kQ(30000) : r1;
                C01060Pq.A00();
                new C09511ii();
                AnonymousClass1iP r12 = new AnonymousClass1iP(new AnonymousClass1iQ());
                this.A0G = r12;
                this.A0F = new AnonymousClass1k2();
                this.A0J = new HashSet();
                this.A0I = new HashSet();
                this.A0L = true;
                this.A02 = this.A01;
                this.A0B = new C03440md(r12.A05.A02.A00);
                this.A0K = true;
                this.A0E = r4.A00;
                this.A07 = new AnonymousClass0mj();
                C01060Pq.A00();
            } catch (Throwable th) {
                C01060Pq.A00();
                throw th;
            }
        } else {
            throw null;
        }
    }
}
