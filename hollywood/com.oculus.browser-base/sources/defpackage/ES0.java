package defpackage;

import java.util.ArrayDeque;
import java.util.concurrent.Executor;

/* renamed from: ES0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ES0 implements Executor {

    /* renamed from: a  reason: collision with root package name */
    public final ArrayDeque f7961a = new ArrayDeque();
    public Runnable b;

    public synchronized void a() {
        Runnable runnable = (Runnable) this.f7961a.poll();
        this.b = runnable;
        if (runnable != null) {
            ((ExecutorC1463Ya) AbstractC2032cb.f9616a).execute(runnable);
        }
    }

    public synchronized void execute(Runnable runnable) {
        this.f7961a.offer(new DS0(this, runnable));
        if (this.b == null) {
            a();
        }
    }
}
