package X;

import android.graphics.drawable.Drawable;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* renamed from: X.1fT  reason: invalid class name */
public final class AnonymousClass1fT<R> implements AnonymousClass0Dy<R>, AnonymousClass1fS<R> {
    public static final C09011ft A0A = new C09011ft();
    @Nullable
    @GuardedBy("this")
    public AnonymousClass1Or A00;
    @Nullable
    @GuardedBy("this")
    public AnonymousClass1h5 A01;
    @Nullable
    @GuardedBy("this")
    public R A02;
    @GuardedBy("this")
    public boolean A03;
    @GuardedBy("this")
    public boolean A04;
    @GuardedBy("this")
    public boolean A05;
    public final int A06;
    public final int A07;
    public final C09011ft A08;
    public final boolean A09 = true;

    private synchronized R A00(Long l) throws ExecutionException, InterruptedException, TimeoutException {
        R r;
        if (this.A09 && !isDone() && !C08381eW.A05()) {
            throw new IllegalArgumentException("You must call this method on a background thread");
        } else if (this.A03) {
            throw new CancellationException();
        } else if (this.A04) {
            throw new ExecutionException(this.A00);
        } else if (this.A05) {
            r = this.A02;
        } else {
            if (l == null) {
                wait(0);
            } else {
                long longValue = l.longValue();
                if (longValue > 0) {
                    long currentTimeMillis = System.currentTimeMillis();
                    long j = longValue + currentTimeMillis;
                    while (!isDone() && currentTimeMillis < j) {
                        wait(j - currentTimeMillis);
                        currentTimeMillis = System.currentTimeMillis();
                    }
                }
            }
            if (Thread.interrupted()) {
                throw new InterruptedException();
            } else if (this.A04) {
                throw new ExecutionException(this.A00);
            } else if (this.A03) {
                throw new CancellationException();
            } else if (this.A05) {
                r = this.A02;
            } else {
                throw new TimeoutException();
            }
        }
        return r;
    }

    @Override // X.AnonymousClass1fS
    public final synchronized boolean A7G(@Nullable AnonymousClass1Or r2, Object obj, AbstractC08781fH<R> r4, boolean z) {
        this.A04 = true;
        this.A00 = r2;
        notifyAll();
        return false;
    }

    @Override // X.AnonymousClass1fS
    public final synchronized boolean A7x(R r, Object obj, AbstractC08781fH<R> r4, AnonymousClass1fM r5, boolean z) {
        this.A05 = true;
        this.A02 = r;
        notifyAll();
        return false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001b, code lost:
        if (r0 == null) goto L_0x0020;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001d, code lost:
        r0.clear();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0020, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean cancel(boolean r4) {
        /*
            r3 = this;
            monitor-enter(r3)
            boolean r0 = r3.isDone()     // Catch:{ all -> 0x0021 }
            if (r0 == 0) goto L_0x000a
            r0 = 0
            monitor-exit(r3)     // Catch:{ all -> 0x0021 }
            return r0
        L_0x000a:
            r2 = 1
            r3.A03 = r2     // Catch:{ all -> 0x0021 }
            r3.notifyAll()     // Catch:{ all -> 0x0021 }
            r1 = 0
            if (r4 == 0) goto L_0x0014
            goto L_0x0016
        L_0x0014:
            r0 = r1
            goto L_0x001a
        L_0x0016:
            X.1h5 r0 = r3.A01     // Catch:{ all -> 0x0021 }
            r3.A01 = r1     // Catch:{ all -> 0x0021 }
        L_0x001a:
            monitor-exit(r3)     // Catch:{ all -> 0x0021 }
            if (r0 == 0) goto L_0x0020
            r0.clear()
        L_0x0020:
            return r2
        L_0x0021:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1fT.cancel(boolean):boolean");
    }

    @Override // X.AbstractC08781fH
    @Nullable
    public final synchronized AnonymousClass1h5 getRequest() {
        return this.A01;
    }

    public final synchronized boolean isCancelled() {
        return this.A03;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x000c, code lost:
        if (r2.A04 != false) goto L_0x000e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized boolean isDone() {
        /*
            r2 = this;
            monitor-enter(r2)
            boolean r0 = r2.A03     // Catch:{ all -> 0x0011 }
            if (r0 != 0) goto L_0x000e
            boolean r0 = r2.A05     // Catch:{ all -> 0x0011 }
            if (r0 != 0) goto L_0x000e
            boolean r1 = r2.A04     // Catch:{ all -> 0x0011 }
            r0 = 0
            if (r1 == 0) goto L_0x000f
        L_0x000e:
            r0 = 1
        L_0x000f:
            monitor-exit(r2)
            return r0
        L_0x0011:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1fT.isDone():boolean");
    }

    @Override // X.AbstractC08541eo
    public final void onDestroy() {
    }

    @Override // X.AbstractC08781fH
    public final void onLoadCleared(@Nullable Drawable drawable) {
    }

    @Override // X.AbstractC08781fH
    public final synchronized void onLoadFailed(@Nullable Drawable drawable) {
    }

    @Override // X.AbstractC08781fH
    public final void onLoadStarted(@Nullable Drawable drawable) {
    }

    @Override // X.AbstractC08781fH
    public final synchronized void onResourceReady(@NonNull R r, @Nullable AbstractC08911fj<? super R> r2) {
    }

    @Override // X.AbstractC08541eo
    public final void onStart() {
    }

    @Override // X.AbstractC08541eo
    public final void onStop() {
    }

    @Override // X.AbstractC08781fH
    public final void removeCallback(@NonNull AbstractC09041fz r1) {
    }

    @Override // X.AbstractC08781fH
    public final synchronized void setRequest(@Nullable AnonymousClass1h5 r2) {
        this.A01 = r2;
    }

    public AnonymousClass1fT(int i, int i2) {
        C09011ft r1 = A0A;
        this.A07 = i;
        this.A06 = i2;
        this.A08 = r1;
    }

    @Override // X.AbstractC08781fH
    public final void getSize(@NonNull AbstractC09041fz r3) {
        r3.A84(this.A07, this.A06);
    }

    @Override // java.util.concurrent.Future
    public final R get() throws InterruptedException, ExecutionException {
        try {
            return A00(null);
        } catch (TimeoutException e) {
            throw new AssertionError(e);
        }
    }

    @Override // java.util.concurrent.Future
    public final R get(long j, @NonNull TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        return A00(Long.valueOf(timeUnit.toMillis(j)));
    }
}
