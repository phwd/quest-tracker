package defpackage;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import com.google.android.material.button.MaterialButton;

/* renamed from: Hc0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0434Hc0 {

    /* renamed from: a  reason: collision with root package name */
    public final MaterialButton f8164a;
    public C3553lT0 b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;
    public PorterDuff.Mode i;
    public ColorStateList j;
    public ColorStateList k;
    public ColorStateList l;
    public Drawable m;
    public boolean n = false;
    public boolean o;
    public LayerDrawable p;

    public C0434Hc0(MaterialButton materialButton, C3553lT0 lt0) {
        this.f8164a = materialButton;
        this.b = lt0;
    }

    public AbstractC5258vT0 a() {
        LayerDrawable layerDrawable = this.p;
        if (layerDrawable == null || layerDrawable.getNumberOfLayers() <= 1) {
            return null;
        }
        if (this.p.getNumberOfLayers() > 2) {
            return (AbstractC5258vT0) this.p.getDrawable(2);
        }
        return (AbstractC5258vT0) this.p.getDrawable(1);
    }

    public C3234jd0 b() {
        return c(false);
    }

    public final C3234jd0 c(boolean z) {
        LayerDrawable layerDrawable = this.p;
        if (layerDrawable == null || layerDrawable.getNumberOfLayers() <= 0) {
            return null;
        }
        return (C3234jd0) ((LayerDrawable) ((InsetDrawable) this.p.getDrawable(0)).getDrawable()).getDrawable(!z ? 1 : 0);
    }

    public void d(C3553lT0 lt0) {
        this.b = lt0;
        if (b() != null) {
            C3234jd0 b2 = b();
            b2.H.f10150a = lt0;
            b2.invalidateSelf();
        }
        if (c(true) != null) {
            C3234jd0 c2 = c(true);
            c2.H.f10150a = lt0;
            c2.invalidateSelf();
        }
        if (a() != null) {
            a().a(lt0);
        }
    }
}
