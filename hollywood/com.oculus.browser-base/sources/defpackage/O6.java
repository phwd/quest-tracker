package defpackage;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Handler;

/* renamed from: O6  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class O6 {

    /* renamed from: a  reason: collision with root package name */
    public final Handler f8600a = new Handler();
    public final Animatable b;
    public final D6 c;
    public boolean d;

    public O6(Drawable drawable) {
        this.b = (Animatable) drawable;
        this.c = new N6(this);
    }
}
