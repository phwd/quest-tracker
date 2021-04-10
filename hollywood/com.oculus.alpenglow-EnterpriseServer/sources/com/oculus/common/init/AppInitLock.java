package com.oculus.common.init;

import X.AnonymousClass0Lh;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.oculus.alpenglow.induction.InductionService;
import com.oculus.alpenglow.init.AlpenglowAppInitializer;
import java.util.ArrayList;
import java.util.Iterator;

public class AppInitLock {
    public boolean initialized;
    public ArrayList<Listener> listeners = new ArrayList<>();

    public static abstract class Listener {
    }

    public static void A00(AppInitLock appInitLock) {
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
                while (it.hasNext()) {
                    InductionService.A00((Context) AnonymousClass0Lh.A03(6, 4, AlpenglowAppInitializer.this._UL_mInjectionContext));
                }
            }
        };
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            r2.run();
        } else {
            new Handler(Looper.getMainLooper()).post(r2);
        }
    }
}
