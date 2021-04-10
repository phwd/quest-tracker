package defpackage;

/* renamed from: TA1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class TA1 implements Runnable {
    public final /* synthetic */ JA1 F;

    public TA1(JA1 ja1, IA1 ia1) {
        this.F = ja1;
    }

    public abstract void a();

    public void run() {
        this.F.b.lock();
        try {
            if (!Thread.interrupted()) {
                a();
                this.F.b.unlock();
            }
        } catch (RuntimeException e) {
            C2313eB1 eb1 = this.F.f8277a;
            eb1.e.sendMessage(eb1.e.obtainMessage(2, e));
        } finally {
            this.F.b.unlock();
        }
    }
}
