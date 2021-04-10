package defpackage;

import android.view.View;

/* renamed from: M61  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class M61 implements View.OnFocusChangeListener {

    /* renamed from: a  reason: collision with root package name */
    public final U61 f8460a;

    public M61(U61 u61) {
        this.f8460a = u61;
    }

    public void onFocusChange(View view, boolean z) {
        U61 u61 = this.f8460a;
        u61.r = z;
        if (AbstractC4772sd1.d()) {
            u61.b.j(AbstractC5033u71.v, z);
            u61.b.j(AbstractC5033u71.u, z);
        }
    }
}
