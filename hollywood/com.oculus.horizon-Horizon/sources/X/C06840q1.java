package X;

import android.os.PowerManager;

/* renamed from: X.0q1  reason: invalid class name and case insensitive filesystem */
public class C06840q1 implements AnonymousClass0HZ {
    public final AnonymousClass0Hl A00;
    public final /* synthetic */ C00970Hv A01;

    public C06840q1(C00970Hv r1, AnonymousClass0Hl r2) {
        this.A01 = r1;
        this.A00 = r2;
    }

    @Override // X.AnonymousClass0HZ
    public final void A65() {
        PowerManager.WakeLock wakeLock = this.A01.A00;
        if (wakeLock != null) {
            wakeLock.release();
        }
    }

    @Override // X.AnonymousClass0HZ
    public final void A7D(boolean z) {
        C00970Hv r1;
        AnonymousClass0Hr r0;
        if (z && (r0 = (r1 = this.A01).A07) != null) {
            this.A00.A00(r1.A02, r0.A02, r1.A06, r0.A01, r0.A00);
        }
    }
}
