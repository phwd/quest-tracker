package com.facebook.proxygen;

import com.facebook.proxygen.utils.Preconditions;

public class HTTPThread implements Runnable {
    public EventBase mEventBase;
    public boolean mEventBaseInitError = false;
    public EvbExceptionHandler mExHandler;

    public synchronized boolean eventBaseInitErrored() {
        return this.mEventBaseInitError;
    }

    public void run() {
        synchronized (this) {
            try {
                this.mEventBase = new EventBase();
            } catch (UnsatisfiedLinkError unused) {
                this.mEventBaseInitError = true;
            } catch (Throwable th) {
                notifyAll();
                throw th;
            }
            notifyAll();
        }
        try {
            EventBase eventBase = this.mEventBase;
            if (eventBase != null) {
                eventBase.loopForever();
            }
        } catch (Throwable th2) {
            EvbExceptionHandler evbExceptionHandler = this.mExHandler;
            if (evbExceptionHandler != null) {
                evbExceptionHandler.handle(th2);
                return;
            }
            throw th2;
        }
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:1:0x0001 */
    /* JADX WARNING: Removed duplicated region for block: B:1:0x0001 A[LOOP:0: B:1:0x0001->B:13:0x0001, LOOP_START, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void waitForInitialization() {
        /*
            r1 = this;
            monitor-enter(r1)
        L_0x0001:
            com.facebook.proxygen.EventBase r0 = r1.mEventBase     // Catch:{ all -> 0x000f }
            if (r0 != 0) goto L_0x000d
            boolean r0 = r1.mEventBaseInitError     // Catch:{ all -> 0x000f }
            if (r0 != 0) goto L_0x000d
            r1.wait()     // Catch:{ InterruptedException -> 0x0001 }
            goto L_0x0001
        L_0x000d:
            monitor-exit(r1)
            return
        L_0x000f:
            r0 = move-exception
            monitor-exit(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.proxygen.HTTPThread.waitForInitialization():void");
    }

    public EventBase getEventBase() {
        Preconditions.checkNotNull(this.mEventBase, "EventBase has not been created yet");
        return this.mEventBase;
    }

    public HTTPThread setExceptionHandler(EvbExceptionHandler evbExceptionHandler) {
        this.mExHandler = evbExceptionHandler;
        return this;
    }
}
