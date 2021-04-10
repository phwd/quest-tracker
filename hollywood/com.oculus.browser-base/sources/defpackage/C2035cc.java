package defpackage;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;

/* renamed from: cc  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C2035cc extends D6 {
    public final Handler b = new Handler(Looper.getMainLooper());

    public C2035cc(AbstractC1527Zb zb) {
    }

    @Override // defpackage.D6
    public void a(Drawable drawable) {
        if (drawable instanceof Animatable) {
            this.b.post(new RunnableC1864bc(drawable));
        }
    }
}
