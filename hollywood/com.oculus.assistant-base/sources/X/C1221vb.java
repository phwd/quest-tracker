package X;

import android.os.Bundle;

/* renamed from: X.vb  reason: case insensitive filesystem */
public final class C1221vb implements AbstractC0106Ak, AbstractC0388Vb {
    public static final Vm A05 = new Vm();
    public final Bundle A00;
    public final String A01;
    public final String A02;
    public final String A03;
    public final byte[] A04;

    public C1221vb(String str, String str2, byte[] bArr, Bundle bundle, String str3) {
        C0514bB.A02(str, "appId");
        C0514bB.A02(str2, "actionId");
        C0514bB.A02(bundle, "slotBundle");
        this.A02 = str;
        this.A01 = str2;
        this.A04 = bArr;
        this.A00 = bundle;
        this.A03 = str3;
    }

    @Override // X.AbstractC0388Vb
    public final Object A2C(Bundle bundle) {
        C0514bB.A02(bundle, "bundle");
        throw new UnsupportedOperationException();
    }

    @Override // X.AbstractC0106Ak
    public final Object A2L() {
        return this;
    }
}
