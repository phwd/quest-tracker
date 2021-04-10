package defpackage;

/* renamed from: Q01  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class Q01 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final T01 f8731a;

    public Q01(T01 t01) {
        this.f8731a = t01;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        AbstractC2642g70 g70 = (AbstractC2642g70) obj;
        Y01 y01 = this.f8731a.f8931a;
        y01.j = g70;
        X01 x01 = new X01(y01);
        y01.k = x01;
        ((D70) g70).Q.b(x01);
    }
}
