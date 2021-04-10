package X;

import android.os.SystemClock;

/* renamed from: X.22h  reason: invalid class name and case insensitive filesystem */
public class C141922h {
    public final /* synthetic */ AnonymousClass22J A00;

    public C141922h(AnonymousClass22J r1) {
        this.A00 = r1;
    }

    public final void A00() {
        AnonymousClass22J r1 = this.A00;
        synchronized (r1) {
            r1.notifyAll();
        }
    }

    public final void A01(EnumC141822g r4, EnumC142922r r5, Throwable th) {
        AnonymousClass22J r2 = this.A00;
        if (r2.A0X != AnonymousClass007.A04) {
            AnonymousClass22J.A02(r2, r4, r5, th);
        }
    }

    public final void A02(String str, String str2) {
        AnonymousClass22J r3 = this.A00;
        r3.A0S = SystemClock.elapsedRealtime();
        r3.A0A.A02(String.format("O %s%s", str, str2));
        r3.A0B.A05(str, str2, r3.A0Z);
        r3.A08.A00();
    }

    public final void A03(Throwable th) {
        C142322l r2 = this.A00.A0W;
        if (r2 != null) {
            r2.A01.A06.post(new RunnableC145323r(r2, th));
        }
    }
}
