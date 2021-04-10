package X;

import androidx.loader.app.LoaderManagerImpl;

/* renamed from: X.0cU  reason: invalid class name and case insensitive filesystem */
public class C03470cU extends AnonymousClass0Do {
    public static final AbstractC01120Dp A01 = new C03480cV();
    public AnonymousClass06E<LoaderManagerImpl.LoaderInfo> A00 = new AnonymousClass06E<>();

    @Override // X.AnonymousClass0Do
    public final void A00() {
        super.A00();
        AnonymousClass06E<LoaderManagerImpl.LoaderInfo> r5 = this.A00;
        if (0 < r5.A01()) {
            if (r5.A01) {
                AnonymousClass06E.A00(r5);
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
