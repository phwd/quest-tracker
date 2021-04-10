package X;

import android.content.Intent;

/* renamed from: X.0sm  reason: invalid class name */
public final class AnonymousClass0sm implements AnonymousClass03U {
    public final int A00;
    public final Intent A01;
    public final /* synthetic */ AnonymousClass03W A02;

    public AnonymousClass0sm(AnonymousClass03W r1, Intent intent, int i) {
        this.A02 = r1;
        this.A01 = intent;
        this.A00 = i;
    }

    @Override // X.AnonymousClass03U
    public final void A1m() {
        this.A02.stopSelf(this.A00);
    }

    @Override // X.AnonymousClass03U
    public final Intent A3f() {
        return this.A01;
    }
}
