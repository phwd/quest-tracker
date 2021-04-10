package defpackage;

import org.chromium.content_public.browser.NavigationController;

/* renamed from: vz0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC5347vz0 implements Runnable {
    public final View$OnLayoutChangeListenerC6027zz0 F;

    public RunnableC5347vz0(View$OnLayoutChangeListenerC6027zz0 zz0) {
        this.F = zz0;
    }

    public void run() {
        NavigationController f = this.F.f11784J.f();
        if (f != null && f.h()) {
            f.e();
        }
    }
}
