package defpackage;

import org.chromium.base.Callback;

/* renamed from: ky0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3468ky0 implements AbstractC3087il0 {
    public Callback F;

    public C3468ky0(Callback callback) {
        this.F = callback;
    }

    @Override // defpackage.AbstractC3087il0
    public void d(UH0 uh0, int i) {
        if (i == 0) {
            this.F.onResult(1);
        } else if (i == 1) {
            this.F.onResult(2);
        }
    }

    @Override // defpackage.AbstractC3087il0
    public void f(UH0 uh0, int i) {
        this.F.onResult(Integer.valueOf(i));
    }
}
