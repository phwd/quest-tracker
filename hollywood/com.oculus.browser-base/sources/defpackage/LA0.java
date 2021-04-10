package defpackage;

/* renamed from: LA0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class LA0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f8410a;
    public final /* synthetic */ TA0 b;

    public LA0(TA0 ta0, int i) {
        this.b = ta0;
        this.f8410a = i;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        this.b.o(this.f8410a, (C5084uR0) obj);
        this.b.q();
    }
}
