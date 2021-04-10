package defpackage;

import java.util.Objects;

/* renamed from: Mv1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class Mv1 implements HB0 {

    /* renamed from: a  reason: collision with root package name */
    public final Sv1 f8510a;
    public final int b;

    public Mv1(Sv1 sv1, int i) {
        this.f8510a = sv1;
        this.b = i;
    }

    @Override // defpackage.HB0
    public void b(String[] strArr, int[] iArr) {
        Sv1 sv1 = this.f8510a;
        int i = this.b;
        Objects.requireNonNull(sv1);
        if (iArr.length == 1) {
            if (iArr[0] == 0) {
                sv1.h(i);
            } else {
                ((View$OnKeyListenerC0001Aa0) sv1.f8925a).F.u();
            }
        }
    }
}
