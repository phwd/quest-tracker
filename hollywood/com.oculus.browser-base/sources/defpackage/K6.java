package defpackage;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

/* renamed from: K6  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class K6 extends Drawable.ConstantState {

    /* renamed from: a  reason: collision with root package name */
    public final Drawable.ConstantState f8341a;

    public K6(Drawable.ConstantState constantState) {
        this.f8341a = constantState;
    }

    public boolean canApplyTheme() {
        return this.f8341a.canApplyTheme();
    }

    public int getChangingConfigurations() {
        return this.f8341a.getChangingConfigurations();
    }

    public Drawable newDrawable() {
        L6 l6 = new L6(null, null, null);
        Drawable newDrawable = this.f8341a.newDrawable();
        l6.F = newDrawable;
        newDrawable.setCallback(l6.L);
        return l6;
    }

    public Drawable newDrawable(Resources resources) {
        L6 l6 = new L6(null, null, null);
        Drawable newDrawable = this.f8341a.newDrawable(resources);
        l6.F = newDrawable;
        newDrawable.setCallback(l6.L);
        return l6;
    }

    public Drawable newDrawable(Resources resources, Resources.Theme theme) {
        L6 l6 = new L6(null, null, null);
        Drawable newDrawable = this.f8341a.newDrawable(resources, theme);
        l6.F = newDrawable;
        newDrawable.setCallback(l6.L);
        return l6;
    }
}
