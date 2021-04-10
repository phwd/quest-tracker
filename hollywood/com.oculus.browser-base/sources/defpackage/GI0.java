package defpackage;

import android.graphics.drawable.Drawable;
import android.view.animation.Interpolator;

/* renamed from: GI0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class GI0 extends Drawable.ConstantState {

    /* renamed from: a  reason: collision with root package name */
    public int f8083a;
    public int b;
    public long c;
    public float d;
    public Interpolator e;
    public FI0 f;

    public GI0(Interpolator interpolator, FI0 fi0) {
        this.e = new II0(interpolator);
        this.f = fi0;
    }

    public int getChangingConfigurations() {
        return 0;
    }

    public Drawable newDrawable() {
        return new HI0(this, new EI0(null));
    }

    public GI0(GI0 gi0) {
        this.f8083a = gi0.f8083a;
        this.b = gi0.b;
        this.c = gi0.c;
        this.e = gi0.e;
        this.f = gi0.f;
    }
}
