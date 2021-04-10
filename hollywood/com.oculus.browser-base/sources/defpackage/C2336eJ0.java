package defpackage;

/* renamed from: eJ0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2336eJ0 implements HB0 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C2678gJ0 f9847a;

    public C2336eJ0(C2678gJ0 gj0) {
        this.f9847a = gj0;
    }

    @Override // defpackage.HB0
    public void b(String[] strArr, int[] iArr) {
        if (iArr.length != 0) {
            if (iArr[0] == 0) {
                this.f9847a.b.j(AbstractC3703mJ0.f10414a, true);
                return;
            }
            if (!this.f9847a.d.canRequestPermission("android.permission.CAMERA")) {
                this.f9847a.b.j(AbstractC3703mJ0.b, false);
            }
            this.f9847a.b.j(AbstractC3703mJ0.f10414a, false);
        }
    }
}
