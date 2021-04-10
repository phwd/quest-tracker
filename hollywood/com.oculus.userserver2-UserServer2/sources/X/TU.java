package X;

import androidx.loader.app.LoaderManagerImpl;

public class TU extends C7 {
    public static final C8 A01 = new TV();
    public AnonymousClass3D<LoaderManagerImpl.LoaderInfo> A00 = new AnonymousClass3D<>();

    @Override // X.C7
    public final void A00() {
        super.A00();
        AnonymousClass3D<LoaderManagerImpl.LoaderInfo> r5 = this.A00;
        if (0 < r5.A01()) {
            if (r5.A01) {
                AnonymousClass3D.A00(r5);
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
