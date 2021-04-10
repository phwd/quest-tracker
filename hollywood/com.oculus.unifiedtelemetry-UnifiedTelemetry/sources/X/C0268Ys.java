package X;

import android.os.PersistableBundle;

/* renamed from: X.Ys  reason: case insensitive filesystem */
public class C0268Ys implements H6, H7<PersistableBundle> {
    public final PersistableBundle A00;

    @Override // X.H6
    public final int A2X(String str, int i) {
        return this.A00.getInt(str, i);
    }

    @Override // X.H6
    public final String A2t(String str, String str2) {
        return this.A00.getString(str, str2);
    }

    @Override // X.H7
    public final void A4M(String str, int i) {
        this.A00.putInt(str, i);
    }

    @Override // X.H7
    public final void A4N(String str, String str2) {
        this.A00.putString(str, str2);
    }

    public C0268Ys(PersistableBundle persistableBundle) {
        this.A00 = persistableBundle;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.H7
    public final PersistableBundle A5P() {
        return this.A00;
    }
}
