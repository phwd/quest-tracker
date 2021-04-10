package defpackage;

import android.graphics.Typeface;

/* renamed from: uM0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC5074uM0 implements Runnable {
    public final /* synthetic */ Typeface F;
    public final /* synthetic */ AbstractC5414wM0 G;

    public RunnableC5074uM0(AbstractC5414wM0 wm0, Typeface typeface) {
        this.G = wm0;
        this.F = typeface;
    }

    public void run() {
        this.G.d(this.F);
    }
}
