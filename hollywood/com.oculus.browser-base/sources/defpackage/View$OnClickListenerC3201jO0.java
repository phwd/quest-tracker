package defpackage;

import android.view.View;

/* renamed from: jO0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class View$OnClickListenerC3201jO0 implements View.OnClickListener {
    public final /* synthetic */ boolean F;
    public final /* synthetic */ Runnable G;

    public View$OnClickListenerC3201jO0(C3372kO0 ko0, boolean z, Runnable runnable) {
        this.F = z;
        this.G = runnable;
    }

    public void onClick(View view) {
        C3372kO0.X(this.F, 1);
        this.G.run();
    }
}
