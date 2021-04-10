package defpackage;

/* renamed from: fP0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2520fP0 implements AbstractC5135ul0 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ boolean f9919a;
    public final /* synthetic */ Runnable b;
    public final /* synthetic */ C2691gP0 c;

    public C2520fP0(C2691gP0 gp0, boolean z, Runnable runnable) {
        this.c = gp0;
        this.f9919a = z;
        this.b = runnable;
    }

    @Override // defpackage.AbstractC5135ul0
    public void a(boolean z) {
        if (z) {
            this.c.b(this.f9919a, this.b);
        } else if (this.f9919a) {
            this.c.d();
        }
    }
}
