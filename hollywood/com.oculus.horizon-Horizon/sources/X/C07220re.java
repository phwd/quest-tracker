package X;

import androidx.loader.app.LoaderManagerImpl;

/* renamed from: X.0re  reason: invalid class name and case insensitive filesystem */
public class C07220re extends AnonymousClass0Af {
    public static final AnonymousClass0Ag A01 = new C07230rf();
    public C000702p<LoaderManagerImpl.LoaderInfo> A00 = new C000702p<>();

    @Override // X.AnonymousClass0Af
    public final void A00() {
        super.A00();
        C000702p<LoaderManagerImpl.LoaderInfo> r5 = this.A00;
        if (0 < r5.A01()) {
            if (r5.A01) {
                C000702p.A00(r5);
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
