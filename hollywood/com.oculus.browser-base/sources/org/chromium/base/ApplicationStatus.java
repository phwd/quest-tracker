package org.chromium.base;

import android.app.Activity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ApplicationStatus {

    /* renamed from: a  reason: collision with root package name */
    public static final Map f10581a = Collections.synchronizedMap(new HashMap());
    public static int b = 0;
    public static Object c = new Object();
    public static Integer d;
    public static Activity e;
    public static AbstractC1678aa f;
    public static final C1322Vq0 g = new C1322Vq0();
    public static final C1322Vq0 h = new C1322Vq0();
    public static final C1322Vq0 i = new C1322Vq0();

    public static void a(Activity activity, int i2) {
        Y9 y9;
        if (activity != null) {
            if (e == null || i2 == 1 || i2 == 3 || i2 == 2) {
                e = activity;
            }
            int stateForApplication = getStateForApplication();
            Map map = f10581a;
            synchronized (map) {
                if (i2 == 1) {
                    map.put(activity, new Y9(null));
                }
                y9 = (Y9) map.get(activity);
                y9.f9257a = i2;
                if (i2 == 6) {
                    map.remove(activity);
                    if (activity == e) {
                        e = null;
                    }
                }
                b = c();
            }
            Iterator it = y9.b.iterator();
            while (true) {
                C1261Uq0 uq0 = (C1261Uq0) it;
                if (!uq0.hasNext()) {
                    break;
                }
                ((Z9) uq0.next()).t(activity, i2);
            }
            Iterator it2 = g.iterator();
            while (true) {
                C1261Uq0 uq02 = (C1261Uq0) it2;
                if (!uq02.hasNext()) {
                    break;
                }
                ((Z9) uq02.next()).t(activity, i2);
            }
            int stateForApplication2 = getStateForApplication();
            if (stateForApplication2 != stateForApplication) {
                Iterator it3 = h.iterator();
                while (true) {
                    C1261Uq0 uq03 = (C1261Uq0) it3;
                    if (uq03.hasNext()) {
                        ((AbstractC1678aa) uq03.next()).a(stateForApplication2);
                    } else {
                        return;
                    }
                }
            }
        } else {
            throw new IllegalArgumentException("null activity is not supported");
        }
    }

    public static void b(int i2) {
        synchronized (c) {
            d = Integer.valueOf(i2);
        }
        Iterator it = h.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((AbstractC1678aa) uq0.next()).a(i2);
            } else {
                return;
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0017, code lost:
        r0 = org.chromium.base.ApplicationStatus.f10581a.values().iterator();
        r1 = false;
        r3 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0028, code lost:
        if (r0.hasNext() == false) goto L_0x0043;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002a, code lost:
        r4 = ((defpackage.Y9) r0.next()).f9257a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0033, code lost:
        if (r4 == 4) goto L_0x003b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0035, code lost:
        if (r4 == 5) goto L_0x003b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0038, code lost:
        if (r4 == 6) goto L_0x003b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x003a, code lost:
        return 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x003b, code lost:
        if (r4 != 4) goto L_0x003f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x003d, code lost:
        r1 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x003f, code lost:
        if (r4 != 5) goto L_0x0023;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0041, code lost:
        r3 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0043, code lost:
        if (r1 == false) goto L_0x0047;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0045, code lost:
        return 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0047, code lost:
        if (r3 == false) goto L_0x004b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0049, code lost:
        return 3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x004b, code lost:
        return 4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int c() {
        /*
            java.lang.Object r0 = org.chromium.base.ApplicationStatus.c
            monitor-enter(r0)
            java.lang.Integer r1 = org.chromium.base.ApplicationStatus.d     // Catch:{ all -> 0x004c }
            r2 = 1
            if (r1 == 0) goto L_0x0016
            int r1 = r1.intValue()     // Catch:{ all -> 0x004c }
            if (r1 != r2) goto L_0x0016
            java.lang.Integer r1 = org.chromium.base.ApplicationStatus.d     // Catch:{ all -> 0x004c }
            int r1 = r1.intValue()     // Catch:{ all -> 0x004c }
            monitor-exit(r0)     // Catch:{ all -> 0x004c }
            return r1
        L_0x0016:
            monitor-exit(r0)     // Catch:{ all -> 0x004c }
            java.util.Map r0 = org.chromium.base.ApplicationStatus.f10581a
            java.util.Collection r0 = r0.values()
            java.util.Iterator r0 = r0.iterator()
            r1 = 0
            r3 = r1
        L_0x0023:
            boolean r4 = r0.hasNext()
            r5 = 4
            if (r4 == 0) goto L_0x0043
            java.lang.Object r4 = r0.next()
            Y9 r4 = (defpackage.Y9) r4
            int r4 = r4.f9257a
            r6 = 5
            if (r4 == r5) goto L_0x003b
            if (r4 == r6) goto L_0x003b
            r7 = 6
            if (r4 == r7) goto L_0x003b
            return r2
        L_0x003b:
            if (r4 != r5) goto L_0x003f
            r1 = r2
            goto L_0x0023
        L_0x003f:
            if (r4 != r6) goto L_0x0023
            r3 = r2
            goto L_0x0023
        L_0x0043:
            if (r1 == 0) goto L_0x0047
            r0 = 2
            return r0
        L_0x0047:
            if (r3 == 0) goto L_0x004b
            r0 = 3
            return r0
        L_0x004b:
            return r5
        L_0x004c:
            r1 = move-exception
            monitor-exit(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.base.ApplicationStatus.c():int");
    }

    public static List d() {
        ArrayList arrayList;
        Map map = f10581a;
        synchronized (map) {
            arrayList = new ArrayList(map.keySet());
        }
        return arrayList;
    }

    public static int e(Activity activity) {
        Y9 y9;
        if (activity == null || (y9 = (Y9) f10581a.get(activity)) == null) {
            return 6;
        }
        return y9.f9257a;
    }

    public static boolean f() {
        return f10581a.isEmpty();
    }

    public static void g(Z9 z9, Activity activity) {
        ((Y9) f10581a.get(activity)).b.b(z9);
    }

    public static int getStateForApplication() {
        int i2;
        synchronized (f10581a) {
            i2 = b;
        }
        return i2;
    }

    public static void h(Z9 z9) {
        g.c(z9);
        Map map = f10581a;
        synchronized (map) {
            for (Y9 y9 : map.values()) {
                y9.b.c(z9);
            }
        }
    }

    public static boolean hasVisibleActivities() {
        int stateForApplication = getStateForApplication();
        return stateForApplication == 1 || stateForApplication == 2;
    }

    public static void registerThreadSafeNativeApplicationStateListener() {
        ThreadUtils.g(new X9());
    }
}
