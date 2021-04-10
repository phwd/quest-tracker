package defpackage;

import android.app.Application;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Process;
import androidx.browser.customtabs.CustomTabsSessionToken;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import org.chromium.base.BundleUtils;
import org.chromium.base.ContextUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.AppHooks;
import org.chromium.chrome.browser.background_task_scheduler.ChromeBackgroundTaskFactory;
import org.chromium.chrome.browser.customtabs.CustomTabsConnection;
import org.chromium.chrome.browser.flags.CachedFeatureFlags;
import org.chromium.chrome.browser.vr.VrModuleProvider;

/* renamed from: TY0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class TY0 extends Application {
    public Q31 F;
    public C3614lq G;

    public static boolean e() {
        return !ContextUtils.c().contains(":");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:50:0x012f, code lost:
        if (r7 != false) goto L_0x0138;
     */
    /* JADX WARNING: Removed duplicated region for block: B:130:0x025f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void attachBaseContext(android.content.Context r15) {
        /*
        // Method dump skipped, instructions count: 801
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.TY0.attachBaseContext(android.content.Context):void");
    }

    public final C3614lq d() {
        if (this.G == null) {
            C3614lq lqVar = (C3614lq) this.F.get();
            this.G = lqVar;
            lqVar.a(this);
        }
        return this.G;
    }

    public void f() {
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        Objects.requireNonNull(d());
        if (e()) {
            if (O51.f8599a == null) {
                O51.f8599a = new O51();
            }
            O51 o51 = O51.f8599a;
            boolean z = o51.c;
            o51.a();
            if (z != o51.c) {
                Iterator it = o51.b.iterator();
                while (true) {
                    C1261Uq0 uq0 = (C1261Uq0) it;
                    if (uq0.hasNext()) {
                        ((FV) uq0.next()).f();
                    } else {
                        return;
                    }
                }
            }
        }
    }

    public void onCreate() {
        super.onCreate();
        int i = Build.VERSION.SDK_INT;
        if (i >= 26 && i < 28 && Process.isIsolated()) {
            try {
                Bundle bundle = getPackageManager().getApplicationInfo(getPackageName(), 128).metaData;
                if (bundle != null) {
                    if (bundle.containsKey("preloaded_fonts")) {
                        Class<?> cls = Class.forName("android.app.ActivityThread");
                        cls.getMethod("currentActivityThread", new Class[0]);
                        Method method = cls.getMethod("getPackageManager", new Class[0]);
                        Field declaredField = cls.getDeclaredField("sPackageManager");
                        declaredField.setAccessible(true);
                        Class<?> cls2 = Class.forName("android.content.pm.IPackageManager");
                        Class[] clsArr = {cls2};
                        declaredField.set(null, Proxy.newProxyInstance(cls2.getClassLoader(), clsArr, new CR(method.invoke(null, new Object[0]))));
                    }
                }
            } catch (Exception e) {
                AbstractC1220Ua0.f("FontWorkaround", "Installing workaround failed, continuing without", e);
            }
        }
        C3762mi0 mi0 = C3762mi0.f10441a;
        Objects.requireNonNull(mi0);
        Object obj = ThreadUtils.f10596a;
        ContextUtils.getApplicationContext().registerComponentCallbacks(new ComponentCallbacks2C3591li0(mi0));
        Objects.requireNonNull(d());
        if (e()) {
            if (CachedFeatureFlags.isEnabled("EarlyLibraryLoad")) {
                new Thread(new RunnableC3443kq()).start();
            }
            Boolean bool = BundleUtils.f10583a;
            ChromeBackgroundTaskFactory.setAsDefault();
            Objects.requireNonNull(AppHooks.get());
        }
    }

    public void onTrimMemory(int i) {
        PF pf;
        super.onTrimMemory(i);
        Objects.requireNonNull(d());
        if (AbstractApplicationC3785mq.h(i) && (pf = CV.f7814a) != null) {
            for (OF of : pf.f8679a) {
                of.f8611a = null;
            }
            pf.f8679a.clear();
        }
        if ((CustomTabsConnection.c != null) && AbstractApplicationC3785mq.h(i)) {
            C3287jv jvVar = CustomTabsConnection.f().f;
            synchronized (jvVar) {
                Iterator it = new ArrayList(jvVar.b.keySet()).iterator();
                while (it.hasNext()) {
                    CustomTabsSessionToken customTabsSessionToken = (CustomTabsSessionToken) it.next();
                    if (((C3116iv) jvVar.b.get(customTabsSessionToken)).b == null) {
                        jvVar.a(customTabsSessionToken);
                    }
                }
            }
        }
    }

    public void startActivity(Intent intent) {
        startActivity(intent, null);
    }

    public void startActivity(Intent intent, Bundle bundle) {
        C3614lq d = d();
        Objects.requireNonNull(d);
        Objects.requireNonNull(VrModuleProvider.b());
        super.startActivity(intent, bundle);
    }
}
