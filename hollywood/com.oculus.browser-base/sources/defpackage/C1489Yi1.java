package defpackage;

/* renamed from: Yi1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1489Yi1 extends I70 {

    /* renamed from: a  reason: collision with root package name */
    public boolean f9292a;
    public final /* synthetic */ C1550Zi1 b;

    public C1489Yi1(C1550Zi1 zi1) {
        this.b = zi1;
    }

    @Override // defpackage.I70
    public void d(int i, boolean z, boolean z2) {
        if (i == 1 && this.f9292a) {
            this.b.h.onResult(Boolean.FALSE);
            this.f9292a = false;
        }
    }

    @Override // defpackage.I70
    public void e(int i, boolean z) {
        if (i == 1) {
            C1550Zi1 zi1 = this.b;
            if (zi1.l) {
                zi1.h.onResult(Boolean.TRUE);
                this.f9292a = true;
            }
        }
    }
}
