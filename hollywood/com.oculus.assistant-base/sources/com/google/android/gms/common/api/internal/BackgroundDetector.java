package com.google.android.gms.common.api.internal;

import X.AbstractC0316Qn;
import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks2;
import android.content.res.Configuration;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public final class BackgroundDetector implements Application.ActivityLifecycleCallbacks, ComponentCallbacks2 {
    public static final BackgroundDetector A04 = new BackgroundDetector();
    public boolean A00 = false;
    public final ArrayList A01 = new ArrayList();
    public final AtomicBoolean A02 = new AtomicBoolean();
    public final AtomicBoolean A03 = new AtomicBoolean();

    public final void onActivityDestroyed(Activity activity) {
    }

    public final void onActivityPaused(Activity activity) {
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public final void onActivityStarted(Activity activity) {
    }

    public final void onActivityStopped(Activity activity) {
    }

    public final void onConfigurationChanged(Configuration configuration) {
    }

    public final void onLowMemory() {
    }

    private final void A00(boolean z) {
        synchronized (A04) {
            ArrayList arrayList = this.A01;
            int size = arrayList.size();
            int i = 0;
            while (i < size) {
                Object obj = arrayList.get(i);
                i++;
                ((AbstractC0316Qn) obj).A3t(z);
            }
        }
    }

    public final void onActivityCreated(Activity activity, Bundle bundle) {
        boolean compareAndSet = this.A02.compareAndSet(true, false);
        this.A03.set(true);
        if (compareAndSet) {
            A00(false);
        }
    }

    public final void onActivityResumed(Activity activity) {
        boolean compareAndSet = this.A02.compareAndSet(true, false);
        this.A03.set(true);
        if (compareAndSet) {
            A00(false);
        }
    }

    public final void onTrimMemory(int i) {
        if (i == 20 && this.A02.compareAndSet(false, true)) {
            this.A03.set(true);
            A00(true);
        }
    }
}
