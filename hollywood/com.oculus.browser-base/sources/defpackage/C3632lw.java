package defpackage;

/* renamed from: lw  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3632lw implements AbstractC3841n80 {

    /* renamed from: a  reason: collision with root package name */
    public final ZU[] f10387a;

    public C3632lw(ZU[] zuArr) {
        this.f10387a = zuArr;
    }

    @Override // defpackage.AbstractC3841n80
    public void a(AbstractC4524r80 r80, EnumC3157j80 j80) {
        C5132uk0 uk0 = new C5132uk0();
        for (ZU zu : this.f10387a) {
            zu.a(r80, j80, false, uk0);
        }
        for (ZU zu2 : this.f10387a) {
            zu2.a(r80, j80, true, uk0);
        }
    }
}
