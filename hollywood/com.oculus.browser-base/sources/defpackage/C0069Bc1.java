package defpackage;

import org.chromium.base.Callback;

/* renamed from: Bc1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C0069Bc1 implements R80 {
    public final Callback F;

    public C0069Bc1(Callback callback) {
        this.F = callback;
    }

    @Override // defpackage.R80
    public void I(UH0 uh0) {
        this.F.onResult(Integer.valueOf(uh0.f(Y80.f)));
    }
}
