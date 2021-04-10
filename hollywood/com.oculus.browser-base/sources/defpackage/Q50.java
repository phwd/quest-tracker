package defpackage;

/* renamed from: Q50  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Q50 implements Du1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ R50 f8738a;

    public Q50(R50 r50) {
        this.f8738a = r50;
    }

    @Override // defpackage.Du1
    public void a(int i, float f, int i2) {
        for (E81 e81 : this.f8738a.H) {
            e81.a(i, f, i2);
        }
    }

    @Override // defpackage.Du1
    public void b(int i) {
        for (E81 e81 : this.f8738a.H) {
            e81.b = e81.c;
            e81.c = i;
        }
    }

    @Override // defpackage.Du1
    public void c(int i) {
        for (E81 e81 : this.f8738a.H) {
            e81.c(i);
        }
    }
}
