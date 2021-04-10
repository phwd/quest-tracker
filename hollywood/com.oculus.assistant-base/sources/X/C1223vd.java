package X;

import android.os.Bundle;

/* renamed from: X.vd  reason: case insensitive filesystem */
public final class C1223vd implements AbstractC0106Ak, AbstractC0388Vb {
    public static final Vo A04 = new Vo();
    public final String A00;
    public final String A01;
    public final String A02;
    public final byte[] A03;

    @Override // X.AbstractC0388Vb
    public final Object A2C(Bundle bundle) {
        C0514bB.A02(bundle, "bundle");
        return new C1223vd(bundle.getString("appId"), bundle.getString("actionId"), bundle.getString("debugPhrase"), bundle.getByteArray("commandInput"));
    }

    public C1223vd(String str, String str2, String str3, byte[] bArr) {
        this.A01 = str;
        this.A00 = str2;
        this.A02 = str3;
        this.A03 = bArr;
    }

    @Override // X.AbstractC0106Ak
    public final Object A2L() {
        return this;
    }
}
