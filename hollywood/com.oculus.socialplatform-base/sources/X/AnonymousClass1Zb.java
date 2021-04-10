package X;

import android.os.ConditionVariable;
import androidx.annotation.Nullable;
import com.facebook.msys.util.NotificationScope;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* renamed from: X.1Zb  reason: invalid class name */
public final class AnonymousClass1Zb<T> implements AnonymousClass1ZX<T> {
    @Nullable
    public AnonymousClass1YZ<T> A00;
    @Nullable
    public NotificationScope A01;
    @Nullable
    public T A02;
    @Nullable
    public String A03;
    public boolean A04;
    public boolean A05;
    public boolean A06;
    public final Runnable A07 = new AnonymousClass1Zd(this);
    public final ConditionVariable A08 = new ConditionVariable();
    public final AnonymousClass1Z6 A09;

    private synchronized void A00() {
        if (!(this.A03 == null || this.A01 == null)) {
            this.A09.A9T(new AnonymousClass1Za(this));
        }
    }

    private synchronized void A01() {
        if (this.A00 != null && this.A06) {
            this.A09.A9T(new AnonymousClass1Zc(this));
        }
    }

    /* JADX WARN: Incorrect return type in method signature: (LX/1YZ<TT;>;)LX/1Zb<TT;>; */
    public final synchronized void A02(AnonymousClass1YZ r3) {
        if (!this.A05) {
            this.A05 = true;
            if (!this.A04) {
                this.A00 = r3;
                A01();
            }
        } else {
            throw new IllegalStateException("Cannot set multiple callbacks");
        }
    }

    /* JADX WARN: Incorrect return type in method signature: (TT;)LX/1Zb<TT;>; */
    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: java.lang.Object */
    /* JADX WARN: Multi-variable type inference failed */
    public final synchronized void A03(Object obj) {
        if (!this.A06) {
            this.A06 = true;
            this.A02 = obj;
            this.A08.open();
            A01();
        } else {
            throw new IllegalStateException("Cannot set multiple results");
        }
    }

    /* JADX WARN: Incorrect return type in method signature: (Ljava/lang/String;Lcom/facebook/msys/util/NotificationScope;)LX/1Zb<TT;>; */
    public final synchronized void A04(String str, NotificationScope notificationScope) {
        if (this.A03 == null && this.A01 == null) {
            this.A03 = str;
            this.A01 = notificationScope;
            if (this.A04) {
                A00();
            }
        } else {
            throw new IllegalStateException("Cannot set multiple notifications");
        }
    }

    public final synchronized boolean isCancelled() {
        return this.A04;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x000a, code lost:
        if (isCancelled() != false) goto L_0x000c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized boolean isDone() {
        /*
            r2 = this;
            monitor-enter(r2)
            boolean r0 = r2.A06     // Catch:{ all -> 0x000f }
            if (r0 != 0) goto L_0x000c
            boolean r1 = r2.isCancelled()     // Catch:{ all -> 0x000f }
            r0 = 0
            if (r1 == 0) goto L_0x000d
        L_0x000c:
            r0 = 1
        L_0x000d:
            monitor-exit(r2)
            return r0
        L_0x000f:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1Zb.isDone():boolean");
    }

    public AnonymousClass1Zb(AnonymousClass1Z6 r2) {
        this.A09 = r2;
    }

    @Override // X.AnonymousClass1ZX
    public final /* bridge */ /* synthetic */ AnonymousClass1ZX AA9(AnonymousClass1YZ r1) {
        A02(r1);
        return this;
    }

    @Override // X.AnonymousClass1ZX
    public final void cancel() {
        cancel(true);
    }

    public final synchronized boolean cancel(boolean z) {
        boolean z2;
        z2 = false;
        if (!isDone()) {
            z2 = true;
        }
        if (!this.A04) {
            this.A04 = true;
            this.A00 = null;
            A00();
        }
        return z2;
    }

    @Override // java.util.concurrent.Future
    public final T get() {
        try {
            return get(0, TimeUnit.MILLISECONDS);
        } catch (TimeoutException e) {
            throw new IllegalStateException(e);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0012, code lost:
        if (com.facebook.msys.mci.Execution.getExecutionContext() == 0) goto L_0x001c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001b, code lost:
        throw new java.lang.IllegalStateException("The task cannot run on any MSYS thread");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001c, code lost:
        r3.A08.block(java.util.concurrent.TimeUnit.MILLISECONDS.convert(r4, r6));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0027, code lost:
        monitor-enter(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x002a, code lost:
        if (r3.A06 == false) goto L_0x0030;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x002c, code lost:
        r0 = r3.A02;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x002e, code lost:
        monitor-exit(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x002f, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0035, code lost:
        throw new java.util.concurrent.TimeoutException();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x000c, code lost:
        if (com.facebook.msys.mci.Execution.sInitialized == false) goto L_0x001c;
     */
    @Override // java.util.concurrent.Future
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final T get(long r4, java.util.concurrent.TimeUnit r6) throws java.util.concurrent.TimeoutException {
        /*
            r3 = this;
            monitor-enter(r3)
            boolean r0 = r3.A06     // Catch:{ all -> 0x0039 }
            if (r0 == 0) goto L_0x0009
            T r0 = r3.A02     // Catch:{ all -> 0x0039 }
            monitor-exit(r3)     // Catch:{ all -> 0x0039 }
            return r0
        L_0x0009:
            monitor-exit(r3)     // Catch:{ all -> 0x0039 }
            boolean r0 = com.facebook.msys.mci.Execution.sInitialized
            if (r0 == 0) goto L_0x001c
            int r0 = com.facebook.msys.mci.Execution.getExecutionContext()
            if (r0 == 0) goto L_0x001c
            java.lang.String r1 = "The task cannot run on any MSYS thread"
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>(r1)
            throw r0
        L_0x001c:
            android.os.ConditionVariable r2 = r3.A08
            java.util.concurrent.TimeUnit r0 = java.util.concurrent.TimeUnit.MILLISECONDS
            long r0 = r0.convert(r4, r6)
            r2.block(r0)
            monitor-enter(r3)
            boolean r0 = r3.A06     // Catch:{ all -> 0x0036 }
            if (r0 == 0) goto L_0x0030
            T r0 = r3.A02     // Catch:{ all -> 0x0036 }
            monitor-exit(r3)     // Catch:{ all -> 0x0036 }
            return r0
        L_0x0030:
            java.util.concurrent.TimeoutException r0 = new java.util.concurrent.TimeoutException     // Catch:{ all -> 0x0036 }
            r0.<init>()     // Catch:{ all -> 0x0036 }
            throw r0     // Catch:{ all -> 0x0036 }
        L_0x0036:
            r0 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x0036 }
            throw r0
        L_0x0039:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1Zb.get(long, java.util.concurrent.TimeUnit):java.lang.Object");
    }
}
