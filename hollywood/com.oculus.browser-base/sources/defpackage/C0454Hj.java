package defpackage;

/* renamed from: Hj  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0454Hj implements AbstractC2280e01 {

    /* renamed from: a  reason: collision with root package name */
    public int f8177a;
    public final /* synthetic */ AbstractC2451f01 b;
    public final /* synthetic */ C0515Ij c;

    public C0454Hj(C0515Ij ij, AbstractC2451f01 f01) {
        this.c = ij;
        this.b = f01;
    }

    @Override // defpackage.AbstractC2280e01
    public void a(int i, boolean z) {
        if (this.f8177a != i) {
            this.f8177a = i;
            C0515Ij ij = this.c;
            C0515Ij.l(ij, ij.U.H, Integer.valueOf(i));
            if (i == 1) {
                this.c.T.k();
            }
        }
    }
}
