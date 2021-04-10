package defpackage;

import android.graphics.drawable.Drawable;

/* renamed from: dd0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2210dd0 implements Drawable.Callback {
    public final /* synthetic */ C2551fd0 F;

    public C2210dd0(C2551fd0 fd0) {
        this.F = fd0;
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
