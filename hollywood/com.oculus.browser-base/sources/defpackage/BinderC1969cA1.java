package defpackage;

import android.os.Bundle;

/* renamed from: cA1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class BinderC1969cA1 extends AbstractBinderC2310eA1 {
    public BinderC1969cA1(Vz1 vz1, C2140dA1 da1) {
        super(vz1, new Rz1("OnCompleteUpdateCallback"), da1);
    }

    @Override // defpackage.AbstractBinderC2310eA1, defpackage.AbstractC4019oA1
    public final void v(Bundle bundle) {
        this.c.c.a();
        this.f9837a.a(4, "onCompleteUpdate", new Object[0]);
        if (bundle.getInt("error.code", -2) != 0) {
            this.b.a(new Z10(bundle.getInt("error.code", -2)));
        } else {
            this.b.b(null);
        }
    }
}
