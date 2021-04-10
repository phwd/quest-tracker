package com.oculus.common.init;

import android.os.Handler;
import android.os.Looper;
import com.oculus.util.thread.ThreadUtils;
import java.util.ArrayList;
import java.util.Iterator;

public class AppInitLock {
    public boolean initialized;
    public ArrayList<Listener> listeners = new ArrayList<>();

    public static abstract class Listener {
    }

    public final void A00() {
        synchronized (this) {
            this.initialized = true;
            notifyAll();
        }
        AnonymousClass1 r2 = new Runnable() {
            /* class com.oculus.common.init.AppInitLock.AnonymousClass1 */

            public final void run() {
                ArrayList<Listener> arrayList;
                AppInitLock appInitLock = AppInitLock.this;
                synchronized (appInitLock) {
                    arrayList = appInitLock.listeners;
                    appInitLock.listeners = new ArrayList<>();
                }
                Iterator<Listener> it = arrayList.iterator();
                if (it.hasNext()) {
                    it.next();
                    throw null;
                }
            }
        };
        if (ThreadUtils.A04()) {
            r2.run();
        } else {
            new Handler(Looper.getMainLooper()).post(r2);
        }
    }

    public final synchronized void A01() {
        while (!this.initialized) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
