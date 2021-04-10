package defpackage;

import org.chromium.content.browser.ScreenOrientationProviderImpl;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: bP0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C1837bP0 extends Vy1 {
    public final ScreenOrientationProviderImpl F;
    public final Zy1 G;
    public final boolean H;
    public final byte I;

    /* renamed from: J  reason: collision with root package name */
    public boolean f9540J;

    public C1837bP0(ScreenOrientationProviderImpl screenOrientationProviderImpl, Zy1 zy1, boolean z, byte b) {
        this.F = screenOrientationProviderImpl;
        this.G = zy1;
        this.H = z;
        this.I = b;
        zy1.F.b(this);
    }

    @Override // defpackage.Wy1, defpackage.Vy1
    public void x(WindowAndroid windowAndroid) {
        if (windowAndroid != null) {
            if (this.H) {
                this.F.c(windowAndroid, this.I);
            } else {
                this.F.e(windowAndroid);
            }
            this.G.F.c(this);
            this.f9540J = true;
        }
    }
}
