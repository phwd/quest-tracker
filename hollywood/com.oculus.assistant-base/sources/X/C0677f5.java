package X;

import android.os.Bundle;

/* renamed from: X.f5  reason: case insensitive filesystem */
public final class C0677f5 implements AnonymousClass7i, AnonymousClass7h {
    public final Bundle A00;

    @Override // X.AnonymousClass7h
    public final int A2U(String str, int i) {
        return this.A00.getInt(str, i);
    }

    @Override // X.AnonymousClass7h
    public final String A2x(String str, String str2) {
        return this.A00.getString(str, str2);
    }

    @Override // X.AnonymousClass7i
    public final void A4Z(String str, int i) {
        this.A00.putInt(str, i);
    }

    @Override // X.AnonymousClass7i
    public final void A4a(String str, String str2) {
        if (str2 != null) {
            this.A00.putString(str, str2);
        }
    }

    public C0677f5(Bundle bundle) {
        this.A00 = bundle;
    }

    @Override // X.AnonymousClass7i
    public final Object A59() {
        return this.A00;
    }
}
