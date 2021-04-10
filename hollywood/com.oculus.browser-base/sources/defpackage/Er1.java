package defpackage;

/* renamed from: Er1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class Er1 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final Jr1 f7981a;
    public final long b;
    public final long c;

    public Er1(Jr1 jr1, long j, long j2) {
        this.f7981a = jr1;
        this.b = j;
        this.c = j2;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        Jr1 jr1 = this.f7981a;
        Exception exc = (Exception) obj;
        jr1.c.a(this.b, this.c).a(new Hr1());
    }
}
