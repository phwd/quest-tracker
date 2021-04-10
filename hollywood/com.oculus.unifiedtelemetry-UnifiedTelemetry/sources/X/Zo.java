package X;

import androidx.loader.app.LoaderManagerImpl;

public class Zo extends Af {
    public static final Ag A01 = new Zp();
    public C00072p<LoaderManagerImpl.LoaderInfo> A00 = new C00072p<>();

    @Override // X.Af
    public final void A00() {
        super.A00();
        C00072p<LoaderManagerImpl.LoaderInfo> r5 = this.A00;
        if (0 < r5.A01()) {
            if (r5.A01) {
                C00072p.A00(r5);
            }
            throw null;
        }
        int i = r5.A00;
        Object[] objArr = r5.A03;
        for (int i2 = 0; i2 < i; i2++) {
            objArr[i2] = null;
        }
        r5.A00 = 0;
        r5.A01 = false;
    }
}
