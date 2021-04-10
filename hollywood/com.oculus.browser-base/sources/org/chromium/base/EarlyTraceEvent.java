package org.chromium.base;

import J.N;
import android.os.SystemClock;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class EarlyTraceEvent {

    /* renamed from: a  reason: collision with root package name */
    public static boolean f10586a;
    public static final Object b = new Object();
    public static volatile int c;
    public static List d;
    public static List e;

    public static void a(String str, boolean z) {
        if (f()) {
            SJ sj = new SJ(str, true, z);
            synchronized (b) {
                if (f()) {
                    d.add(sj);
                }
            }
        }
    }

    public static void b() {
        synchronized (b) {
            if (f()) {
                if (!d.isEmpty()) {
                    d(d);
                    d.clear();
                }
                if (!e.isEmpty()) {
                    c(e);
                    e.clear();
                }
                c = 2;
                d = null;
                e = null;
            }
        }
    }

    public static void c(List list) {
        long MklbOJun = (N.MklbOJun() * 1000) - SystemClock.elapsedRealtimeNanos();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            RJ rj = (RJ) it.next();
            if (rj.f8826a) {
                N.M_Gv8TwM(rj.b, rj.c, rj.d + MklbOJun);
            } else {
                N.MrKsqeCD(rj.b, rj.c, rj.d + MklbOJun);
            }
        }
    }

    public static void d(List list) {
        long MklbOJun = (N.MklbOJun() * 1000) - SystemClock.elapsedRealtimeNanos();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            SJ sj = (SJ) it.next();
            if (sj.f8888a) {
                if (sj.b) {
                    N.M7UXCmoq(sj.c, sj.e + MklbOJun, sj.d, sj.f);
                } else {
                    N.MrWG2uUW(sj.c, sj.e + MklbOJun, sj.d, sj.f);
                }
            } else if (sj.b) {
                N.MRlw2LEn(sj.c, sj.e + MklbOJun, sj.d, sj.f);
            } else {
                N.MmyrhqXB(sj.c, sj.e + MklbOJun, sj.d, sj.f);
            }
        }
    }

    public static void e() {
        synchronized (b) {
            if (c == 0) {
                d = new ArrayList();
                e = new ArrayList();
                c = 1;
            }
        }
    }

    public static boolean f() {
        return c == 1;
    }

    public static void g(String str, boolean z) {
        if (f()) {
            SJ sj = new SJ(str, false, z);
            synchronized (b) {
                if (f()) {
                    d.add(sj);
                }
            }
        }
    }

    public static boolean getBackgroundStartupTracingFlag() {
        return f10586a;
    }

    public static void setBackgroundStartupTracingFlag(boolean z) {
        AbstractC3983nz.f10523a.edit().putBoolean("bg_startup_tracing", z).apply();
    }
}
