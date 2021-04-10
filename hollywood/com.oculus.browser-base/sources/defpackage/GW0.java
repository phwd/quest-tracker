package defpackage;

import org.chromium.base.Callback;

/* renamed from: GW0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class GW0 implements AbstractC3087il0 {
    public final C2746gl0 F;
    public Callback G;

    public GW0(C2746gl0 gl0, Callback callback) {
        this.F = gl0;
        this.G = callback;
    }

    @Override // defpackage.AbstractC3087il0
    public void d(UH0 uh0, int i) {
        if (i == 0) {
            this.F.b(uh0, 1);
        } else {
            this.F.b(uh0, 2);
        }
    }

    @Override // defpackage.AbstractC3087il0
    public void f(UH0 uh0, int i) {
        Callback callback = this.G;
        this.G = null;
        callback.onResult(Integer.valueOf(i));
    }
}
