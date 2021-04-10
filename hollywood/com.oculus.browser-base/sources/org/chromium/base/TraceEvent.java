package org.chromium.base;

import J.N;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class TraceEvent implements AutoCloseable {
    public static volatile boolean F;
    public static C5649xm1 G;
    public final String H;

    public TraceEvent(String str, String str2) {
        this.H = str;
        Y(str, str2);
    }

    public static void Y(String str, String str2) {
        EarlyTraceEvent.a(str, false);
        if (F) {
            N.M9XfPu17(str, str2);
            return;
        }
        C5649xm1 xm1 = G;
        if (xm1 != null && xm1.m) {
            try {
                xm1.c.invoke(xm1.f11633a, Long.valueOf(xm1.l), str);
            } catch (Exception unused) {
            }
        }
    }

    public static void f0(String str) {
        EarlyTraceEvent.g(str, false);
        if (F) {
            N.Mw73xTww(str, null);
            return;
        }
        C5649xm1 xm1 = G;
        if (xm1 != null && xm1.m) {
            try {
                xm1.d.invoke(xm1.f11633a, Long.valueOf(xm1.l));
            } catch (Exception unused) {
            }
        }
    }

    public static void g0(String str, long j) {
        if (EarlyTraceEvent.f()) {
            RJ rj = new RJ(str, j, false);
            synchronized (EarlyTraceEvent.b) {
                if (EarlyTraceEvent.f()) {
                    EarlyTraceEvent.e.add(rj);
                }
            }
        }
        if (F) {
            N.MffNhCLU(str, j);
            return;
        }
        C5649xm1 xm1 = G;
        if (xm1 != null) {
            int i = (int) j;
            if (xm1.m) {
                try {
                    xm1.f.invoke(xm1.f11633a, Long.valueOf(xm1.l), str, Integer.valueOf(i));
                } catch (Exception unused) {
                }
            }
        }
    }

    public static void h0(String str) {
        if (F) {
            N.ML40H8ed(str, null);
        }
    }

    public static void i0(String str, String str2) {
        if (F) {
            N.ML40H8ed(str, str2);
        }
    }

    public static TraceEvent j0(String str) {
        return k0(str, null);
    }

    public static TraceEvent k0(String str, String str2) {
        if (EarlyTraceEvent.f() || F) {
            return new TraceEvent(str, str2);
        }
        return null;
    }

    public static void l0(String str, long j) {
        if (EarlyTraceEvent.f()) {
            RJ rj = new RJ(str, j, true);
            synchronized (EarlyTraceEvent.b) {
                if (EarlyTraceEvent.f()) {
                    EarlyTraceEvent.e.add(rj);
                }
            }
        }
        if (F) {
            N.MHopMqLX(str, j);
            return;
        }
        C5649xm1 xm1 = G;
        if (xm1 != null) {
            int i = (int) j;
            if (xm1.m) {
                try {
                    xm1.e.invoke(xm1.f11633a, Long.valueOf(xm1.l), str, Integer.valueOf(i));
                } catch (Exception unused) {
                }
            }
        }
    }

    public static void setEnabled(boolean z) {
        if (z) {
            EarlyTraceEvent.b();
        }
        if (F != z) {
            F = z;
            C5649xm1 xm1 = G;
            if (xm1 != null && !xm1.k.get()) {
                ThreadUtils.c().setMessageLogging(z ? Am1.f7694a : null);
            }
        }
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        f0(this.H);
    }
}
