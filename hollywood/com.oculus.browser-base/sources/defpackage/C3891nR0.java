package defpackage;

/* renamed from: nR0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3891nR0 extends AbstractC2941ht0 {
    public String I;

    /* renamed from: J  reason: collision with root package name */
    public C4935tb0 f10491J;
    public R80 K;
    public Runnable L;
    public boolean M;

    public C3891nR0(String str, boolean z, Runnable runnable) {
        this.I = str;
        u(true);
        this.L = runnable;
        this.M = z;
    }

    @Override // defpackage.AbstractC2941ht0
    public int s() {
        return 2;
    }

    @Override // defpackage.AbstractC2941ht0
    public void t(PX0 px0) {
    }

    public boolean v() {
        return this.L != null;
    }

    public void w() {
        this.M = !this.M;
        n(0, 1, null);
        this.L.run();
    }
}
