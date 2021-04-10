package defpackage;

/* renamed from: OF1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class OF1 implements RF1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ RF1 f8612a;
    public final /* synthetic */ MF1 b;

    public OF1(MF1 mf1, RF1 rf1) {
        this.b = mf1;
        this.f8612a = rf1;
    }

    @Override // defpackage.RF1
    public final void a(long j) {
        RF1 rf1 = this.f8612a;
        if (rf1 != null) {
            rf1.a(j);
        }
    }

    @Override // defpackage.RF1
    public final void b(long j, int i, Object obj) {
        this.b.h = null;
        RF1 rf1 = this.f8612a;
        if (rf1 != null) {
            rf1.b(j, i, obj);
        }
    }
}
