package defpackage;

import org.chromium.base.Callback;

/* renamed from: by0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1931by0 implements AbstractC3087il0 {
    public final Callback F;

    public C1931by0(Callback callback) {
        this.F = callback;
    }

    @Override // defpackage.AbstractC3087il0
    public void d(UH0 uh0, int i) {
        if (i == 0) {
            this.F.onResult(Boolean.TRUE);
        } else if (i == 1) {
            this.F.onResult(Boolean.FALSE);
        }
    }

    @Override // defpackage.AbstractC3087il0
    public void f(UH0 uh0, int i) {
        this.F.onResult(Boolean.FALSE);
    }
}
