package defpackage;

/* renamed from: L9  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class L9 extends I70 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ M9 f8409a;

    public L9(M9 m9) {
        this.f8409a = m9;
    }

    @Override // defpackage.I70
    public void d(int i, boolean z, boolean z2) {
        if (i == 1) {
            M9 m9 = this.f8409a;
            m9.R = false;
            m9.e();
        }
    }

    @Override // defpackage.I70
    public void e(int i, boolean z) {
        if (i == 1) {
            M9 m9 = this.f8409a;
            m9.R = true;
            m9.e();
        }
    }
}
