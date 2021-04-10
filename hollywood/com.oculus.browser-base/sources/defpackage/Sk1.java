package defpackage;

/* renamed from: Sk1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Sk1 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C1343Wa1 f8912a;
    public int b = -1;

    public Sk1(C1343Wa1 wa1) {
        this.f8912a = wa1;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        if (((Boolean) obj).booleanValue()) {
            int i = this.b;
            this.b = this.f8912a.a();
            if (i != -1) {
                this.f8912a.f9156a.c(i);
                return;
            }
            return;
        }
        C1343Wa1 wa1 = this.f8912a;
        wa1.f9156a.c(this.b);
        this.b = -1;
    }
}
