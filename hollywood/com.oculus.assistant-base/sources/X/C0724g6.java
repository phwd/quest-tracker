package X;

import android.os.SystemClock;

/* renamed from: X.g6  reason: case insensitive filesystem */
public final class C0724g6 implements AbstractC00558f {
    public long A00 = 0;
    public final /* synthetic */ C0740gP A01;

    public C0724g6(C0740gP gPVar) {
        this.A01 = gPVar;
    }

    @Override // X.AbstractC00558f
    public final void A4Q(double d) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (elapsedRealtime >= this.A00 + 50) {
            this.A00 = elapsedRealtime;
            this.A01.A0F.post(new AnonymousClass8L(this, d));
        }
    }
}
