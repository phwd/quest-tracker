package defpackage;

import java.util.Objects;

/* renamed from: J50  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class J50 extends MW0 {

    /* renamed from: J  reason: collision with root package name */
    public C1782b50 f8268J;

    public J50(C1794b90 b90, NW0 nw0, LW0 lw0, C1782b50 b50) {
        super(b90, nw0, lw0);
        this.f8268J = b50;
    }

    @Override // defpackage.AbstractC1649aL0, defpackage.MW0
    public void c(Object obj) {
        Objects.requireNonNull(this.f8268J);
        ((AbstractC2127d60) obj).y();
    }
}
