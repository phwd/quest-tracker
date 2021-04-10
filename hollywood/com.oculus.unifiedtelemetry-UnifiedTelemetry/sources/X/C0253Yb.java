package X;

import android.os.Bundle;

/* renamed from: X.Yb  reason: case insensitive filesystem */
public class C0253Yb implements H6, H7<Bundle> {
    public final Bundle A00;

    @Override // X.H6
    public final int A2X(String str, int i) {
        return this.A00.getInt(str, i);
    }

    @Override // X.H6
    public final String A2t(String str, String str2) {
        String string = this.A00.getString(str);
        if (string == null) {
            return str2;
        }
        return string;
    }

    @Override // X.H7
    public final void A4M(String str, int i) {
        this.A00.putInt(str, i);
    }

    @Override // X.H7
    public final void A4N(String str, String str2) {
        this.A00.putString(str, str2);
    }

    public C0253Yb(Bundle bundle) {
        this.A00 = bundle;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.H7
    public final Bundle A5P() {
        return this.A00;
    }
}
