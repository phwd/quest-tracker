package defpackage;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks2;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: He  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class ComponentCallbacks2C0439He implements Application.ActivityLifecycleCallbacks, ComponentCallbacks2 {
    public static final ComponentCallbacks2C0439He F = new ComponentCallbacks2C0439He();
    public final AtomicBoolean G = new AtomicBoolean();
    public final AtomicBoolean H = new AtomicBoolean();
    public final ArrayList I = new ArrayList();

    /* renamed from: J  reason: collision with root package name */
    public boolean f8168J = false;

    public final void a(boolean z) {
        synchronized (F) {
            ArrayList arrayList = this.I;
            int size = arrayList.size();
            int i = 0;
            while (i < size) {
                Object obj = arrayList.get(i);
                i++;
                Handler handler = ((C2826hB1) obj).f10052a.S;
                handler.sendMessage(handler.obtainMessage(1, Boolean.valueOf(z)));
            }
        }
    }

    public final void onActivityCreated(Activity activity, Bundle bundle) {
        boolean compareAndSet = this.G.compareAndSet(true, false);
        this.H.set(true);
        if (compareAndSet) {
            a(false);
        }
    }

    public final void onActivityDestroyed(Activity activity) {
    }

    public final void onActivityPaused(Activity activity) {
    }

    public final void onActivityResumed(Activity activity) {
        boolean compareAndSet = this.G.compareAndSet(true, false);
        this.H.set(true);
        if (compareAndSet) {
            a(false);
        }
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

    public final void onTrimMemory(int i) {
        if (i == 20 && this.G.compareAndSet(false, true)) {
            this.H.set(true);
            a(true);
        }
    }
}
