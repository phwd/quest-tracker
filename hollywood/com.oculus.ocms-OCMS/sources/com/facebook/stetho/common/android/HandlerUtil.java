package com.facebook.stetho.common.android;

import android.os.Handler;
import android.os.Looper;
import com.facebook.stetho.common.UncheckedCallable;
import com.facebook.stetho.common.Util;

public final class HandlerUtil {
    private HandlerUtil() {
    }

    public static boolean checkThreadAccess(Handler handler) {
        return Looper.myLooper() == handler.getLooper();
    }

    public static void verifyThreadAccess(Handler handler) {
        Util.throwIfNot(checkThreadAccess(handler));
    }

    public static <V> V postAndWait(Handler handler, final UncheckedCallable<V> uncheckedCallable) {
        if (!checkThreadAccess(handler)) {
            return (V) new WaitableRunnable<V>() {
                /* class com.facebook.stetho.common.android.HandlerUtil.AnonymousClass1 */

                /* access modifiers changed from: protected */
                @Override // com.facebook.stetho.common.android.HandlerUtil.WaitableRunnable
                public V onRun() {
                    return (V) uncheckedCallable.call();
                }
            }.invoke(handler);
        }
        try {
            return uncheckedCallable.call();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void postAndWait(Handler handler, final Runnable runnable) {
        if (checkThreadAccess(handler)) {
            try {
                runnable.run();
            } catch (RuntimeException e) {
                throw new RuntimeException(e);
            }
        } else {
            new WaitableRunnable<Void>() {
                /* class com.facebook.stetho.common.android.HandlerUtil.AnonymousClass2 */

                /* access modifiers changed from: protected */
                @Override // com.facebook.stetho.common.android.HandlerUtil.WaitableRunnable
                public Void onRun() {
                    runnable.run();
                    return null;
                }
            }.invoke(handler);
        }
    }

    /* access modifiers changed from: private */
    public static abstract class WaitableRunnable<V> implements Runnable {
        private Exception mException;
        private boolean mIsDone;
        private V mValue;

        /* access modifiers changed from: protected */
        public abstract V onRun();

        protected WaitableRunnable() {
        }

        public final void run() {
            try {
                this.mValue = onRun();
                this.mException = null;
                synchronized (this) {
                    this.mIsDone = true;
                    notifyAll();
                }
            } catch (Exception e) {
                try {
                    this.mValue = null;
                    this.mException = e;
                    synchronized (this) {
                        this.mIsDone = true;
                        notifyAll();
                    }
                } catch (Throwable th) {
                    synchronized (this) {
                        this.mIsDone = true;
                        notifyAll();
                        throw th;
                    }
                }
            }
        }

        public V invoke(Handler handler) {
            if (handler.post(this)) {
                join();
                Exception exc = this.mException;
                if (exc == null) {
                    return this.mValue;
                }
                throw new RuntimeException(exc);
            }
            throw new RuntimeException("Handler.post() returned false");
        }

        /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
        /* JADX WARNING: Missing exception handler attribute for start block: B:1:0x0001 */
        /* JADX WARNING: Removed duplicated region for block: B:1:0x0001 A[LOOP:0: B:1:0x0001->B:11:0x0001, LOOP_START, SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void join() {
            /*
                r1 = this;
                monitor-enter(r1)
            L_0x0001:
                boolean r0 = r1.mIsDone     // Catch:{ all -> 0x000b }
                if (r0 != 0) goto L_0x0009
                r1.wait()     // Catch:{ InterruptedException -> 0x0001 }
                goto L_0x0001
            L_0x0009:
                monitor-exit(r1)
                return
            L_0x000b:
                r0 = move-exception
                monitor-exit(r1)
                goto L_0x000f
            L_0x000e:
                throw r0
            L_0x000f:
                goto L_0x000e
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.stetho.common.android.HandlerUtil.WaitableRunnable.join():void");
        }
    }
}
