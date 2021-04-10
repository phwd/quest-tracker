package X;

import android.content.Intent;

/* renamed from: X.dY  reason: case insensitive filesystem */
public final class C0633dY implements AnonymousClass16 {
    public final int A00;
    public final Intent A01;
    public final /* synthetic */ AnonymousClass18 A02;

    public C0633dY(AnonymousClass18 r1, Intent intent, int i) {
        this.A02 = r1;
        this.A01 = intent;
        this.A00 = i;
    }

    @Override // X.AnonymousClass16
    public final void A1S() {
        this.A02.stopSelf(this.A00);
    }

    @Override // X.AnonymousClass16
    public final Intent A2V() {
        return this.A01;
    }
}
