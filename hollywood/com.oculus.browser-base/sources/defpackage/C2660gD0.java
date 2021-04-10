package defpackage;

import android.app.Activity;

/* renamed from: gD0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2660gD0 extends AbstractC6022zx1 {
    public final Activity G;
    public final /* synthetic */ C2831hD0 H;

    public C2660gD0(C2831hD0 hd0, Activity activity) {
        this.H = hd0;
        this.G = activity;
    }

    @Override // defpackage.AbstractC6022zx1
    public void hasEffectivelyFullscreenVideoChange(boolean z) {
        if (!z) {
            this.H.f(this.G, 7);
        }
    }
}
