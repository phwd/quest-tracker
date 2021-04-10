package defpackage;

import android.graphics.drawable.Animatable2;
import android.graphics.drawable.Drawable;
import java.util.Objects;

/* renamed from: C6  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C6 extends Animatable2.AnimationCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ D6 f7785a;

    public C6(D6 d6) {
        this.f7785a = d6;
    }

    public void onAnimationEnd(Drawable drawable) {
        this.f7785a.a(drawable);
    }

    public void onAnimationStart(Drawable drawable) {
        Objects.requireNonNull(this.f7785a);
    }
}
