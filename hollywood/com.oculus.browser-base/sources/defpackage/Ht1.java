package defpackage;

import android.view.View;

/* renamed from: Ht1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class Ht1 implements View.OnClickListener {
    public final Runnable F;

    public Ht1(Runnable runnable) {
        this.F = runnable;
    }

    public void onClick(View view) {
        this.F.run();
    }
}
