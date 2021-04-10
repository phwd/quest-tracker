package X;

import androidx.loader.app.LoaderManagerImpl;

/* renamed from: X.0un  reason: invalid class name */
public class AnonymousClass0un extends AnonymousClass0Ag {
    public static final AnonymousClass0Ah A01 = new C05210uo();
    public C000602w<LoaderManagerImpl.LoaderInfo> A00 = new C000602w<>();

    @Override // X.AnonymousClass0Ag
    public final void A00() {
        super.A00();
        C000602w<LoaderManagerImpl.LoaderInfo> r5 = this.A00;
        if (0 < r5.A01()) {
            if (r5.A01) {
                C000602w.A00(r5);
            }
            throw new NullPointerException("destroy");
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
