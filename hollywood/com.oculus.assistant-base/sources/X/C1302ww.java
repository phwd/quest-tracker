package X;

import android.os.Bundle;

/* renamed from: X.ww  reason: case insensitive filesystem */
public final class C1302ww implements AW {
    public final /* synthetic */ Bundle A00;
    public final /* synthetic */ String A01;
    public final /* synthetic */ String A02;

    public C1302ww(String str, String str2, Bundle bundle) {
        this.A02 = str;
        this.A01 = str2;
        this.A00 = bundle;
    }

    @Override // X.AW
    public final boolean A4v(Object obj) {
        X1 x1 = (X1) obj;
        C0514bB.A02(x1, "listener");
        return x1.A42(this.A02, this.A01, this.A00);
    }
}
