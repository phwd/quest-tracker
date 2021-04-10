package defpackage;

/* renamed from: sA1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractRunnableC4702sA1 implements Runnable {
    public final C2140dA1 F;

    public AbstractRunnableC4702sA1() {
        this.F = null;
    }

    public AbstractRunnableC4702sA1(C2140dA1 da1) {
        this.F = da1;
    }

    public abstract void a();

    public final void run() {
        try {
            a();
        } catch (Exception e) {
            if (this.F != null) {
                this.F.a(e);
            }
        }
    }
}
