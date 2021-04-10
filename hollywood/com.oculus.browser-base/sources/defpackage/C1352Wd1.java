package defpackage;

/* renamed from: Wd1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1352Wd1 extends QK {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C1413Xd1 f9160a;

    public C1352Wd1(C1413Xd1 xd1) {
        this.f9160a = xd1;
    }

    @Override // defpackage.QK
    public void a() {
        this.f9160a.P = false;
    }

    @Override // defpackage.QK
    public void b(boolean z, boolean z2) {
        C1413Xd1 xd1 = this.f9160a;
        xd1.P = true;
        xd1.b();
    }

    @Override // defpackage.QK
    public void c(boolean z) {
        C1413Xd1 xd1 = this.f9160a;
        xd1.P = false;
        xd1.b();
    }
}
