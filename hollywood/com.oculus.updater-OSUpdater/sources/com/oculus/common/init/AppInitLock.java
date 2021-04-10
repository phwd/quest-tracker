package com.oculus.common.init;

import com.oculus.util.thread.ThreadUtils;
import java.util.ArrayList;
import java.util.Iterator;

public class AppInitLock {
    private boolean initialized;
    private ArrayList<Listener> listeners = new ArrayList<>();

    public static abstract class Listener {
        public abstract void onInitialized();
    }

    public void notifyAppInitializationComplete() {
        synchronized (this) {
            this.initialized = true;
            notifyAll();
        }
        notifyListeners();
    }

    private void notifyListeners() {
        ThreadUtils.runOnUiThread(new Runnable() {
            /* class com.oculus.common.init.AppInitLock.AnonymousClass1 */

            public void run() {
                AppInitLock.this.notifyListenersOnUiThread();
            }
        });
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
        while (it.hasNext()) {
            it.next().onInitialized();
        }
    }
}
