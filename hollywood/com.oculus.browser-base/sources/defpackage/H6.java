package defpackage;

import android.graphics.drawable.Drawable;

/* renamed from: H6  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class H6 implements Drawable.Callback {
    public final /* synthetic */ L6 F;

    public H6(L6 l6) {
        this.F = l6;
    }

    public void invalidateDrawable(Drawable drawable) {
        this.F.invalidateSelf();
    }

    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        this.F.scheduleSelf(runnable, j);
    }

    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        this.F.unscheduleSelf(runnable);
    }
}
