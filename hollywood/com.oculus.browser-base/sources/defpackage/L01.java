package defpackage;

/* renamed from: L01  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class L01 extends AbstractC1740ar1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ M01 f8400a;

    public L01(M01 m01) {
        this.f8400a = m01;
    }

    @Override // defpackage.AbstractC1740ar1
    public void e(boolean z) {
        M01 m01 = this.f8400a;
        int i = m01.T;
        if (i == 1 || i == 4 || i == 5 || i == 6 || (i == 3 && !m01.I.h(N01.e))) {
            if (this.f8400a.I.h(N01.f)) {
                this.f8400a.O.j(AbstractC5798yf1.f11692a, !z);
            } else {
                this.f8400a.j(!z);
            }
        }
        this.f8400a.g();
    }
}
