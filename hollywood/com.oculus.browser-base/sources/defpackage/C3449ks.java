package defpackage;

import java.util.Objects;

/* renamed from: ks  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3449ks implements AbstractC3780mo0 {
    public AbstractC2260du0 F;

    public C3449ks(AbstractC1509Ys0 ys0) {
        C3278js jsVar = new C3278js(this);
        C1570Zs0 zs0 = (C1570Zs0) ys0;
        Objects.requireNonNull(zs0.G);
        zs0.F.g(jsVar);
        zs0.get();
    }

    @Override // defpackage.Q31
    public Object get() {
        AbstractC2260du0 du0 = this.F;
        if (du0 == null || !((AbstractC3838n70) du0).C()) {
            return 0;
        }
        return 1;
    }
}
