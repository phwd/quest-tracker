package X;

import android.os.Bundle;

/* renamed from: X.0q9  reason: invalid class name */
public class AnonymousClass0q9 implements AnonymousClass0HV, AnonymousClass0HW<Bundle> {
    public final Bundle A00;

    @Override // X.AnonymousClass0HV
    public final int A3e(String str, int i) {
        return this.A00.getInt(str, i);
    }

    @Override // X.AnonymousClass0HV
    public final String A4R(String str, String str2) {
        String string = this.A00.getString(str);
        if (string == null) {
            return str2;
        }
        return string;
    }

    @Override // X.AnonymousClass0HW
    public final void A7j(String str, int i) {
        this.A00.putInt(str, i);
    }

    @Override // X.AnonymousClass0HW
    public final void A7k(String str, String str2) {
        this.A00.putString(str, str2);
    }

    public AnonymousClass0q9(Bundle bundle) {
        this.A00 = bundle;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass0HW
    public final Bundle A9V() {
        return this.A00;
    }
}
