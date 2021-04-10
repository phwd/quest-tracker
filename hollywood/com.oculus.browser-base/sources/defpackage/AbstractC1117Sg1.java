package defpackage;

import android.content.Context;
import android.content.res.ColorStateList;
import java.util.Iterator;

/* renamed from: Sg1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC1117Sg1 {
    public final ColorStateList F;
    public final ColorStateList G;
    public int H;
    public Boolean I;

    /* renamed from: J  reason: collision with root package name */
    public final C1322Vq0 f8908J = new C1322Vq0();
    public final C1322Vq0 K = new C1322Vq0();

    public AbstractC1117Sg1(Context context) {
        this.F = AbstractC1300Vg1.b(context, true);
        this.G = AbstractC1300Vg1.b(context, false);
    }

    public ColorStateList a() {
        return d() ? this.F : this.G;
    }

    public void b(int i, boolean z) {
        if (this.H != i) {
            this.H = i;
            Iterator it = this.f8908J.iterator();
            while (true) {
                C1261Uq0 uq0 = (C1261Uq0) it;
                if (!uq0.hasNext()) {
                    break;
                }
                ((AbstractC0995Qg1) uq0.next()).a(i, z);
            }
            boolean g = AbstractC1270Uv.g(this.H);
            Boolean bool = this.I;
            if (bool == null || g != bool.booleanValue()) {
                this.I = Boolean.valueOf(g);
                ColorStateList colorStateList = g ? this.F : this.G;
                Iterator it2 = this.K.iterator();
                while (true) {
                    C1261Uq0 uq02 = (C1261Uq0) it2;
                    if (uq02.hasNext()) {
                        ((AbstractC1056Rg1) uq02.next()).c(colorStateList, g);
                    } else {
                        return;
                    }
                }
            }
        }
    }

    public boolean d() {
        Boolean bool = this.I;
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }
}
