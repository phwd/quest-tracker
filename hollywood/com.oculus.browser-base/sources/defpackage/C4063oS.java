package defpackage;

import android.view.View;

/* renamed from: oS  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C4063oS implements AbstractC2919hm {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AbstractComponentCallbacksC3550lS f10549a;

    public C4063oS(AbstractComponentCallbacksC3550lS lSVar) {
        this.f10549a = lSVar;
    }

    @Override // defpackage.AbstractC2919hm
    public void a() {
        if (this.f10549a.v() != null) {
            View v = this.f10549a.v();
            this.f10549a.S0(null);
            v.clearAnimation();
        }
        this.f10549a.T0(null);
    }
}
