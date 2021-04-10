package defpackage;

import android.view.View;

/* renamed from: It1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class It1 implements View.OnClickListener {
    public final Jt1 F;
    public final Runnable G;

    public It1(Jt1 jt1, Runnable runnable) {
        this.F = jt1;
        this.G = runnable;
    }

    public void onClick(View view) {
        Jt1 jt1 = this.F;
        Runnable runnable = this.G;
        jt1.b.setVisibility(8);
        runnable.run();
    }
}
