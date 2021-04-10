package X;

import android.app.ActivityManager;
import android.content.Context;
import com.facebook.acra.AppComponentStats;
import java.util.HashSet;
import java.util.Set;

/* renamed from: X.1qu  reason: invalid class name and case insensitive filesystem */
public final class C10061qu {
    public static C10661ue A0M = new C10661ue();
    public final Context A00;
    public final C09581ob A01;
    public final C09581ob A02;
    public final AnonymousClass0KW<C10411sn> A03;
    public final AnonymousClass0KW<C10411sn> A04;
    public final AnonymousClass0KW<Boolean> A05;
    public final AbstractC10671uj A06;
    public final AnonymousClass1rV A07;
    public final C10331rw A08;
    public final AbstractC10681un A09 = new AnonymousClass1tX();
    public final AnonymousClass1uI A0A;
    public final AnonymousClass1o9 A0B;
    public final C09571oa A0C;
    public final C10381si A0D;
    public final C09781oy A0E;
    public final AnonymousClass1tj A0F;
    public final C10071qv A0G;
    public final AnonymousClass1k9 A0H;
    public final Set<AnonymousClass1t0> A0I;
    public final Set<AnonymousClass1tF> A0J;
    public final boolean A0K;
    public final boolean A0L;

    /* JADX INFO: finally extract failed */
    public C10061qu(C10081qw r4) {
        C10331rw r0;
        AnonymousClass1uI r02;
        AnonymousClass1uS r03;
        AnonymousClass1zo.A00();
        this.A0D = new C10381si(r4.A03);
        Context context = r4.A02;
        this.A03 = new C10211rk((ActivityManager) context.getSystemService(AppComponentStats.TAG_ACTIVITY));
        synchronized (C10331rw.class) {
            r0 = C10331rw.A00;
            if (r0 == null) {
                r0 = new C10331rw();
                C10331rw.A00 = r0;
            }
        }
        this.A08 = r0;
        if (context != null) {
            this.A00 = context;
            this.A0C = new C09571oa(new C09661oj());
            this.A04 = new C10201rj();
            synchronized (AnonymousClass1uI.class) {
                r02 = AnonymousClass1uI.A00;
                if (r02 == null) {
                    r02 = new AnonymousClass1uI();
                    AnonymousClass1uI.A00 = r02;
                }
            }
            this.A0A = r02;
            this.A05 = new AnonymousClass1kA(this);
            try {
                AnonymousClass1zo.A00();
                C09581ob r04 = new C09581ob(new C09601od(context));
                AnonymousClass1zo.A00();
                this.A01 = r04;
                synchronized (AnonymousClass1uS.class) {
                    r03 = AnonymousClass1uS.A00;
                    if (r03 == null) {
                        r03 = new AnonymousClass1uS();
                        AnonymousClass1uS.A00 = r03;
                    }
                }
                this.A06 = r03;
                AnonymousClass1zo.A00();
                AnonymousClass1k9 r1 = r4.A01;
                this.A0H = r1 == null ? new AnonymousClass1jw(30000) : r1;
                AnonymousClass1zo.A00();
                new AnonymousClass1tP();
                C10071qv r12 = new C10071qv(new AnonymousClass1r6());
                this.A0G = r12;
                this.A0F = new AnonymousClass1tj();
                this.A0J = new HashSet();
                this.A0I = new HashSet();
                this.A0L = true;
                this.A02 = this.A01;
                this.A0B = new AnonymousClass1o9(r12.A05.A02.A00);
                this.A0K = true;
                this.A0E = r4.A00;
                this.A07 = new AnonymousClass1rV();
                AnonymousClass1zo.A00();
            } catch (Throwable th) {
                AnonymousClass1zo.A00();
                throw th;
            }
        } else {
            throw null;
        }
    }
}
