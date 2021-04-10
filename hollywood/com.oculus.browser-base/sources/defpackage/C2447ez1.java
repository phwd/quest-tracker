package defpackage;

import android.view.WindowInsets;

/* renamed from: ez1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2447ez1 extends AbstractC2789gz1 {
    public final WindowInsets.Builder b;

    public C2447ez1() {
        this.b = new WindowInsets.Builder();
    }

    @Override // defpackage.AbstractC2789gz1
    public C3985nz1 a() {
        return C3985nz1.h(this.b.build());
    }

    @Override // defpackage.AbstractC2789gz1
    public void b(X10 x10) {
        this.b.setStableInsets(x10.b());
    }

    @Override // defpackage.AbstractC2789gz1
    public void c(X10 x10) {
        this.b.setSystemWindowInsets(x10.b());
    }

    public C2447ez1(C3985nz1 nz1) {
        WindowInsets.Builder builder;
        WindowInsets g = nz1.g();
        if (g != null) {
            builder = new WindowInsets.Builder(g);
        } else {
            builder = new WindowInsets.Builder();
        }
        this.b = builder;
    }
}
