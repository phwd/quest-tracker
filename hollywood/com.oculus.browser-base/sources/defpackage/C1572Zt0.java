package defpackage;

import J.N;
import android.graphics.RectF;
import android.view.ViewGroup;
import java.util.List;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.resources.ResourceManager;

/* renamed from: Zt0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1572Zt0 extends AbstractC0705Lm0 implements LO0 {
    public final C1748au0 G;
    public final Runnable H;
    public boolean I;

    /* renamed from: J  reason: collision with root package name */
    public float f9380J;

    public C1572Zt0(WindowAndroid windowAndroid, ViewGroup viewGroup, Runnable runnable) {
        super(viewGroup);
        this.G = new C1748au0(windowAndroid, viewGroup);
        this.H = runnable;
    }

    @Override // defpackage.LO0
    public boolean a() {
        return false;
    }

    @Override // defpackage.AbstractC0705Lm0
    public void b(float f) {
        this.f9380J = f;
        if (this.I) {
            this.H.run();
        }
    }

    @Override // defpackage.LO0
    public VL c() {
        return null;
    }

    @Override // defpackage.AbstractC0705Lm0
    public void d(float f, float f2) {
        C1748au0 au0 = this.G;
        au0.I = 0.0f;
        N.M2aBK_gn(au0.H, au0, f, f2, au0.G.getWidth(), au0.G.getHeight());
        h(true);
    }

    @Override // defpackage.LO0
    public boolean e(long j, long j2) {
        return true;
    }

    @Override // defpackage.AbstractC0705Lm0
    public void f() {
        C1748au0 au0 = this.G;
        N.MHbgPcOD(au0.H, au0, null, 0.0f, 0.0f);
        au0.I = 0.0f;
        this.f9380J = 0.0f;
    }

    @Override // defpackage.AbstractC0705Lm0
    public void g() {
        h(false);
        C1748au0 au0 = this.G;
        N.Mmy$6vNW(au0.H, au0);
    }

    public final void h(boolean z) {
        this.I = z;
        this.f9380J = 0.0f;
    }

    @Override // defpackage.LO0
    public boolean k() {
        return false;
    }

    @Override // defpackage.LO0
    public void m(float f, float f2, float f3, int i) {
    }

    @Override // defpackage.LO0
    public MO0 q(RectF rectF, RectF rectF2, ResourceManager resourceManager, float f) {
        boolean z;
        C1748au0 au0 = this.G;
        float f2 = this.f9380J;
        float f3 = -(f2 - au0.I);
        au0.I = f2;
        if (f3 == 0.0f) {
            z = true;
        } else {
            z = N.MHbgPcOD(au0.H, au0, resourceManager, f2, f3);
        }
        if (!z) {
            h(false);
        }
        return this.G;
    }

    @Override // defpackage.LO0
    public void s(List list) {
    }

    @Override // defpackage.LO0
    public boolean u() {
        return this.I;
    }

    @Override // defpackage.LO0
    public boolean v() {
        return false;
    }
}
