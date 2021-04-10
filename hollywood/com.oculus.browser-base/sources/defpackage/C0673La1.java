package defpackage;

/* renamed from: La1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C0673La1 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C0733Ma1 f8426a;

    public C0673La1(C0733Ma1 ma1) {
        this.f8426a = ma1;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C0733Ma1 ma1 = this.f8426a;
        AbstractC0124Ca1 ca1 = (AbstractC0124Ca1) obj;
        ma1.f8485J = ca1;
        ((AbstractC0246Ea1) ca1).c(ma1);
    }
}
