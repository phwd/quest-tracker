package X;

import android.util.Pair;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

/* renamed from: X.1r1  reason: invalid class name */
public abstract class AnonymousClass1r1<T> {
    @GuardedBy("this")
    public float A00 = 0.0f;
    @GuardedBy("this")
    public Integer A01 = AnonymousClass007.A00;
    @GuardedBy("this")
    @Nullable
    public T A02 = null;
    @GuardedBy("this")
    @Nullable
    public Throwable A03 = null;
    @Nullable
    public Map<String, Object> A04;
    @GuardedBy("this")
    public boolean A05 = false;
    public final ConcurrentLinkedQueue<Pair<AnonymousClass1tB<T>, Executor>> A06 = new ConcurrentLinkedQueue<>();

    private void A00() {
        boolean z;
        synchronized (this) {
            z = false;
            if (this.A01 == AnonymousClass007.A0C) {
                z = true;
            }
        }
        boolean A012 = A01(this);
        Iterator<Pair<AnonymousClass1tB<T>, Executor>> it = this.A06.iterator();
        while (it.hasNext()) {
            Pair<AnonymousClass1tB<T>, Executor> next = it.next();
            ((Executor) next.second).execute(new AnonymousClass1tD(this, z, (AnonymousClass1tB) next.first, A012));
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x000a, code lost:
        if (r2.A05() != false) goto L_0x000c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized boolean A01(X.AnonymousClass1r1 r2) {
        /*
            monitor-enter(r2)
            boolean r0 = r2.A05     // Catch:{ all -> 0x000f }
            if (r0 == 0) goto L_0x000c
            boolean r1 = r2.A05()     // Catch:{ all -> 0x0011 }
            r0 = 1
            if (r1 == 0) goto L_0x000d
        L_0x000c:
            r0 = 0
        L_0x000d:
            monitor-exit(r2)
            return r0
        L_0x000f:
            r0 = move-exception
            throw r0
        L_0x0011:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1r1.A01(X.1r1):boolean");
    }

    @Nullable
    public synchronized T A02() {
        return this.A02;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0013, code lost:
        A03(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001a, code lost:
        if (A05() != false) goto L_0x001f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001c, code lost:
        A00();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001f, code lost:
        monitor-enter(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        r3.A06.clear();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0025, code lost:
        monitor-exit(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0026, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0011, code lost:
        if (r1 == null) goto L_0x0016;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean A04() {
        /*
            r3 = this;
            monitor-enter(r3)
            boolean r0 = r3.A05     // Catch:{ all -> 0x002a }
            if (r0 == 0) goto L_0x0008
            r2 = 0
            monitor-exit(r3)     // Catch:{ all -> 0x002a }
            return r2
        L_0x0008:
            r2 = 1
            r3.A05 = r2     // Catch:{ all -> 0x002a }
            T r1 = r3.A02     // Catch:{ all -> 0x002a }
            r0 = 0
            r3.A02 = r0     // Catch:{ all -> 0x002a }
            monitor-exit(r3)     // Catch:{ all -> 0x002a }
            if (r1 == 0) goto L_0x0016
            r3.A03(r1)
        L_0x0016:
            boolean r0 = r3.A05()
            if (r0 != 0) goto L_0x001f
            r3.A00()
        L_0x001f:
            monitor-enter(r3)
            java.util.concurrent.ConcurrentLinkedQueue<android.util.Pair<X.1tB<T>, java.util.concurrent.Executor>> r0 = r3.A06     // Catch:{ all -> 0x0027 }
            r0.clear()     // Catch:{ all -> 0x0027 }
            monitor-exit(r3)     // Catch:{ all -> 0x0027 }
            return r2
        L_0x0027:
            r0 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x0027 }
            throw r0
        L_0x002a:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1r1.A04():boolean");
    }

    public final synchronized boolean A05() {
        boolean z;
        z = false;
        if (this.A01 != AnonymousClass007.A00) {
            z = true;
        }
        return z;
    }

    public boolean A07(Throwable th) {
        return A08(th, null);
    }

    public final boolean A08(Throwable th, @Nullable Map<String, Object> map) {
        synchronized (this) {
            if (this.A05 || this.A01 != AnonymousClass007.A00) {
                return false;
            }
            this.A01 = AnonymousClass007.A0C;
            this.A03 = th;
            this.A04 = map;
            A00();
            return true;
        }
    }

    private final void A03(@Nullable T t) {
        T t2;
        if ((this instanceof AnonymousClass1qK) && (t2 = t) != null) {
            t2.close();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x002c, code lost:
        A00();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0030, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0036, code lost:
        if (r5 == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0038, code lost:
        A03(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:?, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:?, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean A06(@javax.annotation.Nullable T r5, boolean r6, @javax.annotation.Nullable java.util.Map<java.lang.String, java.lang.Object> r7) {
        /*
            r4 = this;
            r4.A04 = r7
            r3 = r4
            r2 = 0
            monitor-enter(r3)     // Catch:{ all -> 0x0040 }
            boolean r0 = r4.A05     // Catch:{ all -> 0x003d }
            if (r0 != 0) goto L_0x0031
            java.lang.Integer r1 = r4.A01     // Catch:{ all -> 0x003d }
            java.lang.Integer r0 = X.AnonymousClass007.A00     // Catch:{ all -> 0x003d }
            if (r1 != r0) goto L_0x0031
            if (r6 == 0) goto L_0x0019
            java.lang.Integer r0 = X.AnonymousClass007.A01     // Catch:{ all -> 0x003d }
            r4.A01 = r0     // Catch:{ all -> 0x003d }
            r0 = 1065353216(0x3f800000, float:1.0)
            r4.A00 = r0     // Catch:{ all -> 0x003d }
        L_0x0019:
            T r1 = r4.A02     // Catch:{ all -> 0x003d }
            if (r1 == r5) goto L_0x0025
            r4.A02 = r5     // Catch:{ all -> 0x0020 }
            goto L_0x0023
        L_0x0020:
            r0 = move-exception
            r2 = r1
            goto L_0x003e
        L_0x0023:
            r5 = r1
            goto L_0x0026
        L_0x0025:
            r5 = r2
        L_0x0026:
            monitor-exit(r3)     // Catch:{ all -> 0x0033 }
            if (r5 == 0) goto L_0x002c
            r4.A03(r5)
        L_0x002c:
            r0 = 1
            r4.A00()
            return r0
        L_0x0031:
            monitor-exit(r3)
            goto L_0x0036
        L_0x0033:
            r0 = move-exception
            r2 = r5
            goto L_0x003e
        L_0x0036:
            if (r5 == 0) goto L_0x003b
            r4.A03(r5)
        L_0x003b:
            r0 = 0
            return r0
        L_0x003d:
            r0 = move-exception
        L_0x003e:
            monitor-exit(r3)
            throw r0
        L_0x0040:
            r0 = move-exception
            if (r2 == 0) goto L_0x0046
            r4.A03(r2)
        L_0x0046:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1r1.A06(java.lang.Object, boolean, java.util.Map):boolean");
    }
}
