package defpackage;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;

/* renamed from: Es1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Es1 extends Drawable.ConstantState {

    /* renamed from: a  reason: collision with root package name */
    public final Drawable.ConstantState f7983a;

    public Es1(Drawable.ConstantState constantState) {
        this.f7983a = constantState;
    }

    public boolean canApplyTheme() {
        return this.f7983a.canApplyTheme();
    }

    public int getChangingConfigurations() {
        return this.f7983a.getChangingConfigurations();
    }

    public Drawable newDrawable() {
        Fs1 fs1 = new Fs1();
        fs1.F = (VectorDrawable) this.f7983a.newDrawable();
        return fs1;
    }

    public Drawable newDrawable(Resources resources) {
        Fs1 fs1 = new Fs1();
        fs1.F = (VectorDrawable) this.f7983a.newDrawable(resources);
        return fs1;
    }

    public Drawable newDrawable(Resources resources, Resources.Theme theme) {
        Fs1 fs1 = new Fs1();
        fs1.F = (VectorDrawable) this.f7983a.newDrawable(resources, theme);
        return fs1;
    }
}
