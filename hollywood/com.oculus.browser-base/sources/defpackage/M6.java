package defpackage;

import android.graphics.drawable.Animatable;

/* renamed from: M6  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class M6 implements Runnable {
    public final Animatable F;

    public M6(Animatable animatable) {
        this.F = animatable;
    }

    public void run() {
        this.F.start();
    }
}
