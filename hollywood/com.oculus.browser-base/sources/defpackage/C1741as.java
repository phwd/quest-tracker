package defpackage;

/* renamed from: as  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1741as extends I70 {

    /* renamed from: a  reason: collision with root package name */
    public int f9495a = -1;
    public final /* synthetic */ C2253ds b;

    public C1741as(C2253ds dsVar) {
        this.b = dsVar;
    }

    @Override // defpackage.I70
    public void c(int i) {
        int i2 = this.f9495a;
        if (i2 != -1 && i == 0) {
            ((C0820Nj0) this.b.f9813a).F.e.c(i2);
            this.f9495a = -1;
        }
    }

    @Override // defpackage.I70
    public void e(int i, boolean z) {
        if (this.f9495a == -1 && i != 0) {
            this.f9495a = ((C0820Nj0) this.b.f9813a).F.e.a();
        }
    }
}
