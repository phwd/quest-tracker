package defpackage;

import java.lang.ref.WeakReference;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/* renamed from: Xl  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C1433Xl implements AbstractFutureC5208v90 {
    public final WeakReference F;
    public final AbstractC4669s G = new C1372Wl(this);

    public C1433Xl(C1250Ul ul) {
        this.F = new WeakReference(ul);
    }

    @Override // defpackage.AbstractFutureC5208v90
    public void a(Runnable runnable, Executor executor) {
        this.G.a(runnable, executor);
    }

    public boolean cancel(boolean z) {
        C1250Ul ul = (C1250Ul) this.F.get();
        boolean cancel = this.G.cancel(z);
        if (cancel && ul != null) {
            ul.f9047a = null;
            ul.b = null;
            ul.c.i(null);
        }
        return cancel;
    }

    @Override // java.util.concurrent.Future
    public Object get() {
        return this.G.get();
    }

    public boolean isCancelled() {
        return this.G.I instanceof C3473l;
    }

    public boolean isDone() {
        return this.G.isDone();
    }

    public String toString() {
        return this.G.toString();
    }

    @Override // java.util.concurrent.Future
    public Object get(long j, TimeUnit timeUnit) {
        return this.G.get(j, timeUnit);
    }
}
