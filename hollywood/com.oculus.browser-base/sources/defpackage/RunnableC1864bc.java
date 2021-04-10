package defpackage;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;

/* renamed from: bc  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC1864bc implements Runnable {
    public final Drawable F;

    public RunnableC1864bc(Drawable drawable) {
        this.F = drawable;
    }

    public void run() {
        Drawable drawable = this.F;
        if (drawable.isVisible()) {
            ((Animatable) drawable).start();
        }
    }
}
