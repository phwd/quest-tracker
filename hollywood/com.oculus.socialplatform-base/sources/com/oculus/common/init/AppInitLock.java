package com.oculus.common.init;

import com.oculus.util.thread.ThreadUtils;
import java.util.ArrayList;
import java.util.Iterator;

public class AppInitLock {
    public boolean initialized;
    public ArrayList<Listener> listeners = new ArrayList<>();

    public static abstract class Listener {
        public abstract void onInitialized();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void notifyListenersOnUiThread() {
        ArrayList<Listener> arrayList;
        synchronized (this) {
            arrayList = this.listeners;
            this.listeners = new ArrayList<>();
        }
        Iterator<Listener> it = arrayList.iterator();
        if (it.hasNext()) {
            it.next();
            throw new NullPointerException("onInitialized");
        }
    }

    public void addInitializationListener(Listener listener) {
        boolean z;
        synchronized (this) {
            if (!this.listeners.contains(listener)) {
                this.listeners.add(listener);
            }
            z = this.initialized;
        }
        if (z) {
            notifyListeners();
        }
    }

    public synchronized boolean isInitialized() {
        return this.initialized;
    }

    public void notifyAppInitializationComplete() {
        synchronized (this) {
            this.initialized = true;
            notifyAll();
        }
        notifyListeners();
    }

    public synchronized void waitForInitialization() {
        while (!this.initialized) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void notifyListeners() {
        ThreadUtils.runOnUiThread(new Runnable() {
            /* class com.oculus.common.init.AppInitLock.AnonymousClass1 */

            public void run() {
                AppInitLock.this.notifyListenersOnUiThread();
            }
        });
    }
}
