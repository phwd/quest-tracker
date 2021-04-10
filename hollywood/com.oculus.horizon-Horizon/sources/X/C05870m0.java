package X;

import android.os.SystemClock;

/* renamed from: X.0m0  reason: invalid class name and case insensitive filesystem */
public class C05870m0 {
    public final /* synthetic */ AnonymousClass0ZF A00;

    public C05870m0(AnonymousClass0ZF r1) {
        this.A00 = r1;
    }

    public final void A00() {
        AnonymousClass0ZF r1 = this.A00;
        synchronized (r1) {
            r1.notifyAll();
        }
    }

    public final void A01(EnumC01660Wk r4, AnonymousClass0ZP r5, Throwable th) {
        AnonymousClass0ZF r2 = this.A00;
        if (r2.A0X != AnonymousClass007.A0D) {
            AnonymousClass0ZF.A03(r2, r4, r5, th);
        }
    }

    public final void A02(String str, String str2) {
        AnonymousClass0ZF r4 = this.A00;
        r4.A0S = SystemClock.elapsedRealtime();
        r4.A08.A02(String.format("O %s%s", str, str2));
        r4.A0Q = r4.A0S;
        r4.A09.A07(str, str2, r4.A0Z, false);
        r4.A06.A00();
    }

    public final void A03(Throwable th) {
        C06120mX r2 = this.A00.A0W;
        if (r2 != null) {
            r2.A01.A05.post(new AnonymousClass0YX(r2, th));
        }
    }
}
