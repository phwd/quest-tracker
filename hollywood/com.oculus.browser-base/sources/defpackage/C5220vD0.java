package defpackage;

/* renamed from: vD0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C5220vD0 implements AbstractC0838Ns0 {

    /* renamed from: a  reason: collision with root package name */
    public final C5390wD0 f11467a;

    public C5220vD0(C5390wD0 wd0) {
        this.f11467a = wd0;
    }

    @Override // defpackage.AbstractC0838Ns0
    public void b(Exception exc) {
        C5390wD0 wd0 = this.f11467a;
        wd0.e = null;
        wd0.f = 0;
        wd0.g = 0;
        AbstractC1220Ua0.d("PlayInline", "pullCurrentState() failed.", new Object[0]);
        C5390wD0.f(3);
        wd0.e();
    }
}
