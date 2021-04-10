package X;

import android.os.PersistableBundle;

public final class fC implements AnonymousClass7i, AnonymousClass7h {
    public final PersistableBundle A00;

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
        this.A00.putString(str, str2);
    }

    public fC(PersistableBundle persistableBundle) {
        this.A00 = persistableBundle;
    }

    @Override // X.AnonymousClass7i
    public final Object A59() {
        return this.A00;
    }
}
