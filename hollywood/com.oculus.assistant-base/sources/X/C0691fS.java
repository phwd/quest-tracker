package X;

import android.os.Bundle;

/* renamed from: X.fS  reason: case insensitive filesystem */
public final class C0691fS implements AnonymousClass7i, AnonymousClass7h {
    public final Bundle A00;

    @Override // X.AnonymousClass7h
    public final int A2U(String str, int i) {
        return this.A00.getInt(str, i);
    }

    @Override // X.AnonymousClass7h
    public final String A2x(String str, String str2) {
        String string = this.A00.getString(str);
        if (string == null) {
            return str2;
        }
        return string;
    }

    @Override // X.AnonymousClass7i
    public final void A4Z(String str, int i) {
        this.A00.putInt(str, i);
    }

    @Override // X.AnonymousClass7i
    public final void A4a(String str, String str2) {
        this.A00.putString(str, str2);
    }

    public C0691fS(Bundle bundle) {
        this.A00 = bundle;
    }

    @Override // X.AnonymousClass7i
    public final Object A59() {
        return this.A00;
    }
}
