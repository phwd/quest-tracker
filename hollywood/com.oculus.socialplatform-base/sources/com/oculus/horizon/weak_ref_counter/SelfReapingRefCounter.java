package com.oculus.horizon.weak_ref_counter;

import com.oculus.util.WeakRef;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.annotation.Nullable;

public abstract class SelfReapingRefCounter<T> {
    public static final int REAPER_INTERVAL_MS = 1000;
    public static final String TAG = "SelfReapingRefCounter";
    public final List<WeakRef<T>> mClients = new ArrayList();
    public final Object mClientsLock = new Object();
    public boolean mInitialized = false;
    public final Object mInitializedLock = new Object();
    @Nullable
    public Timer mReaper = null;
    public final Object mReaperLock = new Object();

    public interface DoSomething<U> {
        void doSomethingQuicklyUnderMutex(U u);
    }

    public abstract void oneTimeSetup();

    public abstract void oneTimeTeardown();

    private void startSelfReapingReaper() {
        synchronized (this.mReaperLock) {
            if (this.mReaper == null) {
                Timer timer = new Timer();
                this.mReaper = timer;
                timer.scheduleAtFixedRate(new TimerTask() {
                    /* class com.oculus.horizon.weak_ref_counter.SelfReapingRefCounter.AnonymousClass1 */

                    public void run() {
                        SelfReapingRefCounter.this.removeDeadRefs();
                    }
                }, 0, 1000);
            }
        }
    }

    private void stopReaper() {
        synchronized (this.mReaperLock) {
            Timer timer = this.mReaper;
            if (timer != null) {
                timer.cancel();
                this.mReaper = null;
            }
        }
    }

    public final boolean clientListContains(T t) {
        synchronized (this.mClientsLock) {
            for (int i = 0; i < this.mClients.size(); i++) {
                WeakRef<T> weakRef = this.mClients.get(i);
                if (weakRef != null && weakRef.get() == t) {
                    return true;
                }
            }
            return false;
        }
    }

    public WeakRef<T> createWeakRef(T t) {
        return new WeakRef<>(t);
    }

    public void disconnect(T t) {
        synchronized (this.mClientsLock) {
            if (clientListContains(t)) {
                this.mClients.remove(new WeakRef(t));
                this.mClients.size();
            }
            if (this.mClients.isEmpty()) {
                teardown();
            }
        }
    }

    public final void doSomethingQuicklyOnEveryClient(DoSomething<T> doSomething) {
        synchronized (this.mClientsLock) {
            for (WeakRef<T> weakRef : this.mClients) {
                T t = weakRef.get();
                if (t != null) {
                    doSomething.doSomethingQuicklyUnderMutex(t);
                }
            }
        }
    }

    public final int getClientCount() {
        int size;
        synchronized (this.mClientsLock) {
            size = this.mClients.size();
        }
        return size;
    }

    public boolean isInitialized() {
        boolean z;
        synchronized (this.mInitializedLock) {
            z = this.mInitialized;
        }
        return z;
    }

    public final boolean isReaperRunning() {
        if (this.mReaper != null) {
            return true;
        }
        return false;
    }

    public void removeDeadRefs() {
        synchronized (this.mClientsLock) {
            Iterator<WeakRef<T>> it = this.mClients.iterator();
            while (it.hasNext()) {
                if (it.next().get() == null) {
                    it.remove();
                }
            }
            if (this.mClients.isEmpty()) {
                teardown();
            }
        }
    }

    public void teardown() {
        synchronized (this.mInitializedLock) {
            if (this.mInitialized) {
                this.mInitialized = false;
                oneTimeTeardown();
                stopReaper();
            }
        }
    }

    public void connect(T t) {
        connect(t, true);
    }

    public void connect(T t, boolean z) {
        synchronized (this.mClientsLock) {
            if (t != null) {
                if (this.mClients.isEmpty()) {
                    oneTimeSetup();
                }
                synchronized (this.mInitializedLock) {
                    this.mInitialized = true;
                }
                if (z) {
                    startSelfReapingReaper();
                }
                if (!clientListContains(t)) {
                    this.mClients.add(new WeakRef<>(t));
                }
            }
        }
    }
}
