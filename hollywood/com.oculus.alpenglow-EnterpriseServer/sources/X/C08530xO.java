package X;

import android.os.SystemClock;

/* renamed from: X.0xO  reason: invalid class name and case insensitive filesystem */
public class C08530xO {
    public final /* synthetic */ C08300wz A00;

    public C08530xO(C08300wz r1) {
        this.A00 = r1;
    }

    public final void A00() {
        C08300wz r1 = this.A00;
        synchronized (r1) {
            r1.notifyAll();
        }
    }

    public final void A01(EnumC08720xi r4, AnonymousClass0y3 r5, Throwable th) {
        C08300wz r2 = this.A00;
        if (r2.A0X != AnonymousClass007.A0D) {
            C08300wz.A03(r2, r4, r5, th);
        }
    }

    public final void A02(String str, String str2) {
        C08300wz r4 = this.A00;
        r4.A0S = SystemClock.elapsedRealtime();
        r4.A08.A02(String.format("O %s%s", str, str2));
        r4.A0Q = r4.A0S;
        r4.A09.A07(str, str2, r4.A0Z, false);
        r4.A06.A00();
    }

    public final void A03(Throwable th) {
        C08520xN r2 = this.A00.A0W;
        if (r2 != null) {
            r2.A01.A05.post(new RunnableC09420zO(r2, th));
        }
    }
}
