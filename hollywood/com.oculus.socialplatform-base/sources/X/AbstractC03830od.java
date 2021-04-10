package X;

import android.util.Pair;
import com.oculus.vrshell.panels.AndroidPanelLayer;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

/* renamed from: X.0od  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC03830od<T> implements AnonymousClass0M8<T> {
    @GuardedBy("this")
    public float A00 = AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z;
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
    public final ConcurrentLinkedQueue<Pair<AnonymousClass0MB<T>, Executor>> A06 = new ConcurrentLinkedQueue<>();

    private void A00() {
        boolean z;
        synchronized (this) {
            z = false;
            if (this.A01 == AnonymousClass007.A03) {
                z = true;
            }
        }
        boolean A012 = A01();
        Iterator<Pair<AnonymousClass0MB<T>, Executor>> it = this.A06.iterator();
        while (it.hasNext()) {
            Pair<AnonymousClass0MB<T>, Executor> next = it.next();
            ((Executor) next.second).execute(new AnonymousClass0M4(this, z, (AnonymousClass0MB) next.first, A012));
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x000c, code lost:
        if (A5y() != false) goto L_0x000e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized boolean A01() {
        /*
            r2 = this;
            monitor-enter(r2)
            boolean r0 = r2.A04()     // Catch:{ all -> 0x0011 }
            if (r0 == 0) goto L_0x000e
            boolean r1 = r2.A5y()     // Catch:{ all -> 0x0011 }
            r0 = 1
            if (r1 == 0) goto L_0x000f
        L_0x000e:
            r0 = 0
        L_0x000f:
            monitor-exit(r2)
            return r0
        L_0x0011:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AbstractC03830od.A01():boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0015, code lost:
        r3 = r4.A06.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001f, code lost:
        if (r3.hasNext() == false) goto L_0x0039;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0021, code lost:
        r0 = r3.next();
        ((java.util.concurrent.Executor) r0.second).execute(new X.AnonymousClass0M5(r4, (X.AnonymousClass0MB) r0.first));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A02(float r5) {
        /*
            r4 = this;
            r2 = r4
            monitor-enter(r2)
            boolean r0 = r4.A05     // Catch:{ all -> 0x003a }
            if (r0 != 0) goto L_0x0038
            java.lang.Integer r1 = r4.A01     // Catch:{ all -> 0x003a }
            java.lang.Integer r0 = X.AnonymousClass007.A00     // Catch:{ all -> 0x003a }
            if (r1 != r0) goto L_0x0038
            float r0 = r4.A00     // Catch:{ all -> 0x003a }
            int r0 = (r5 > r0 ? 1 : (r5 == r0 ? 0 : -1))
            if (r0 < 0) goto L_0x0038
            r4.A00 = r5     // Catch:{ all -> 0x003a }
            monitor-exit(r2)
            java.util.concurrent.ConcurrentLinkedQueue<android.util.Pair<X.0MB<T>, java.util.concurrent.Executor>> r0 = r4.A06
            java.util.Iterator r3 = r0.iterator()
        L_0x001b:
            boolean r0 = r3.hasNext()
            if (r0 == 0) goto L_0x0039
            java.lang.Object r0 = r3.next()
            android.util.Pair r0 = (android.util.Pair) r0
            java.lang.Object r2 = r0.first
            X.0MB r2 = (X.AnonymousClass0MB) r2
            java.lang.Object r1 = r0.second
            java.util.concurrent.Executor r1 = (java.util.concurrent.Executor) r1
            X.0M5 r0 = new X.0M5
            r0.<init>(r4, r2)
            r1.execute(r0)
            goto L_0x001b
        L_0x0038:
            monitor-exit(r2)
        L_0x0039:
            return
        L_0x003a:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AbstractC03830od.A02(float):void");
    }

    public void A03(@Nullable T t) {
    }

    public final synchronized boolean A04() {
        return this.A05;
    }

    public boolean A06(Throwable th) {
        return A07(th, null);
    }

    public final boolean A07(Throwable th, @Nullable Map<String, Object> map) {
        synchronized (this) {
            if (this.A05 || this.A01 != AnonymousClass007.A00) {
                return false;
            }
            this.A01 = AnonymousClass007.A03;
            this.A03 = th;
            this.A04 = map;
            A00();
            return true;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0013, code lost:
        A03(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001a, code lost:
        if (A5y() != false) goto L_0x001f;
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
    @Override // X.AnonymousClass0M8
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean A29() {
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
            boolean r0 = r3.A5y()
            if (r0 != 0) goto L_0x001f
            r3.A00()
        L_0x001f:
            monitor-enter(r3)
            java.util.concurrent.ConcurrentLinkedQueue<android.util.Pair<X.0MB<T>, java.util.concurrent.Executor>> r0 = r3.A06     // Catch:{ all -> 0x0027 }
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
        throw new UnsupportedOperationException("Method not decompiled: X.AbstractC03830od.A29():boolean");
    }

    @Override // X.AnonymousClass0M8
    @Nullable
    public final synchronized Throwable A3w() {
        return this.A03;
    }

    @Override // X.AnonymousClass0M8
    public final synchronized float A4g() {
        return this.A00;
    }

    @Override // X.AnonymousClass0M8
    @Nullable
    public synchronized T A4p() {
        return this.A02;
    }

    @Override // X.AnonymousClass0M8
    public final boolean A5Q() {
        return false;
    }

    @Override // X.AnonymousClass0M8
    public final synchronized boolean A5S() {
        boolean z;
        z = false;
        if (this.A02 != null) {
            z = true;
        }
        return z;
    }

    @Override // X.AnonymousClass0M8
    public final synchronized boolean A5y() {
        boolean z;
        z = false;
        if (this.A01 != AnonymousClass007.A00) {
            z = true;
        }
        return z;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002c, code lost:
        if (A01() != false) goto L_0x002e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0030, code lost:
        if (r0 == false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0032, code lost:
        monitor-enter(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        r2 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0038, code lost:
        if (r4.A01 != X.AnonymousClass007.A03) goto L_0x003f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x003e, code lost:
        r2 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x003f, code lost:
        monitor-exit(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0040, code lost:
        r6.execute(new X.AnonymousClass0M4(r4, r2, r5, A01()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:?, code lost:
        return;
     */
    @Override // X.AnonymousClass0M8
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void AAY(X.AnonymousClass0MB<T> r5, java.util.concurrent.Executor r6) {
        /*
            r4 = this;
            r3 = r4
            if (r5 == 0) goto L_0x0052
            if (r6 == 0) goto L_0x0050
            monitor-enter(r3)
            boolean r0 = r4.A05     // Catch:{ all -> 0x004d }
            if (r0 == 0) goto L_0x000c
            monitor-exit(r3)     // Catch:{ all -> 0x004d }
            return
        L_0x000c:
            java.lang.Integer r1 = r4.A01     // Catch:{ all -> 0x004d }
            java.lang.Integer r0 = X.AnonymousClass007.A00     // Catch:{ all -> 0x004d }
            if (r1 != r0) goto L_0x001b
            java.util.concurrent.ConcurrentLinkedQueue<android.util.Pair<X.0MB<T>, java.util.concurrent.Executor>> r1 = r4.A06     // Catch:{ all -> 0x004d }
            android.util.Pair r0 = android.util.Pair.create(r5, r6)     // Catch:{ all -> 0x004d }
            r1.add(r0)     // Catch:{ all -> 0x004d }
        L_0x001b:
            boolean r0 = r4.A5S()     // Catch:{ all -> 0x004d }
            if (r0 != 0) goto L_0x002e
            boolean r0 = r4.A5y()     // Catch:{ all -> 0x004d }
            if (r0 != 0) goto L_0x002e
            boolean r1 = r4.A01()     // Catch:{ all -> 0x004d }
            r0 = 0
            if (r1 == 0) goto L_0x002f
        L_0x002e:
            r0 = 1
        L_0x002f:
            monitor-exit(r3)     // Catch:{ all -> 0x004d }
            if (r0 == 0) goto L_0x004c
            monitor-enter(r3)
            java.lang.Integer r1 = r4.A01     // Catch:{ all -> 0x003b }
            java.lang.Integer r0 = X.AnonymousClass007.A03     // Catch:{ all -> 0x003b }
            r2 = 0
            if (r1 != r0) goto L_0x003f
            goto L_0x003e
        L_0x003b:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        L_0x003e:
            r2 = 1
        L_0x003f:
            monitor-exit(r3)
            boolean r1 = r4.A01()
            X.0M4 r0 = new X.0M4
            r0.<init>(r4, r2, r5, r1)
            r6.execute(r0)
        L_0x004c:
            return
        L_0x004d:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        L_0x0050:
            r0 = 0
            throw r0
        L_0x0052:
            r0 = 0
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AbstractC03830od.AAY(X.0MB, java.util.concurrent.Executor):void");
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
    public boolean A05(@javax.annotation.Nullable T r5, boolean r6, @javax.annotation.Nullable java.util.Map<java.lang.String, java.lang.Object> r7) {
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
        throw new UnsupportedOperationException("Method not decompiled: X.AbstractC03830od.A05(java.lang.Object, boolean, java.util.Map):boolean");
    }

    @Override // X.AnonymousClass0M8
    @Nullable
    public final Map<String, Object> A3v() {
        return this.A04;
    }
}
