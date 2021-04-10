package defpackage;

import android.view.WindowInsets;

/* renamed from: iz1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3130iz1 extends AbstractC2960hz1 {
    public X10 m = null;

    public C3130iz1(C3985nz1 nz1, WindowInsets windowInsets) {
        super(nz1, windowInsets);
    }

    @Override // defpackage.C3814mz1
    public C3985nz1 b() {
        return C3985nz1.h(this.i.consumeStableInsets());
    }

    @Override // defpackage.C3814mz1
    public C3985nz1 c() {
        return C3985nz1.h(this.i.consumeSystemWindowInsets());
    }

    @Override // defpackage.C3814mz1
    public final X10 f() {
        if (this.m == null) {
            this.m = X10.a(this.i.getStableInsetLeft(), this.i.getStableInsetTop(), this.i.getStableInsetRight(), this.i.getStableInsetBottom());
        }
        return this.m;
    }

    @Override // defpackage.C3814mz1
    public boolean i() {
        return this.i.isConsumed();
    }
}
