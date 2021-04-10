package defpackage;

/* renamed from: iL0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC3025iL0 implements Runnable {
    public final /* synthetic */ C3366kL0 F;

    public RunnableC3025iL0(C3366kL0 kl0) {
        this.F = kl0;
    }

    public void run() {
        C3366kL0 kl0 = this.F;
        int size = kl0.h.size();
        for (int i = 0; i < size; i++) {
            ((C4050oL0) kl0.h.valueAt(i)).a(null, null);
        }
        kl0.h.clear();
    }
}
