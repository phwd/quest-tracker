package defpackage;

/* renamed from: o1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C3990o1 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C4673s1 f10527a;

    public C3990o1(C4673s1 s1Var) {
        this.f10527a = s1Var;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C4673s1 s1Var = this.f10527a;
        String str = (String) obj;
        s1Var.N = str;
        AbstractC3901nW0.a(15);
        s1Var.H.l(AbstractC5183v1.e, 1);
        s1Var.b(str);
    }
}
