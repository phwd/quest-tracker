package defpackage;

import J.N;
import android.os.MessageQueue;
import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicBoolean;
import org.chromium.base.ContextUtils;
import org.chromium.base.EarlyTraceEvent;
import org.chromium.base.ThreadUtils;

/* renamed from: xm1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5649xm1 implements MessageQueue.IdleHandler {

    /* renamed from: a  reason: collision with root package name */
    public Class f11633a;
    public Method b;
    public Method c;
    public Method d;
    public Method e;
    public Method f;
    public Class g;
    public Method h;
    public final AtomicBoolean i = new AtomicBoolean();
    public final AtomicBoolean j = new AtomicBoolean();
    public final AtomicBoolean k = new AtomicBoolean();
    public final long l;
    public boolean m;

    public C5649xm1(long j2) {
        try {
            Class<?> cls = Class.forName("android.os.Trace");
            this.f11633a = cls;
            Class<?> cls2 = Long.TYPE;
            this.b = cls.getMethod("isTagEnabled", cls2);
            this.c = this.f11633a.getMethod("traceBegin", cls2, String.class);
            this.d = this.f11633a.getMethod("traceEnd", cls2);
            Class cls3 = this.f11633a;
            Class<?> cls4 = Integer.TYPE;
            this.e = cls3.getMethod("asyncTraceBegin", cls2, String.class, cls4);
            this.f = this.f11633a.getMethod("asyncTraceEnd", cls2, String.class, cls4);
            Class<?> cls5 = Class.forName("android.os.SystemProperties");
            this.g = cls5;
            this.h = cls5.getMethod("get", String.class);
        } catch (Exception e2) {
            AbstractC1220Ua0.f("ATrace", "Reflection error", e2);
            this.b = null;
        }
        this.l = j2;
        b();
    }

    public final String a(String str) {
        try {
            return (String) this.h.invoke(this.g, str);
        } catch (Exception unused) {
            return null;
        }
    }

    public final boolean b() {
        boolean z;
        boolean z2;
        boolean z3 = this.k.get();
        try {
            z = ((Boolean) this.b.invoke(this.f11633a, Long.valueOf(this.l))).booleanValue();
        } catch (Exception unused) {
            z = false;
        }
        if (z3 == z) {
            return false;
        }
        this.k.set(z);
        Integer num = null;
        if (!z) {
            EarlyTraceEvent.b();
            N.MOgCa3d$();
            this.m = false;
            ThreadUtils.c().setMessageLogging(null);
            return true;
        }
        String str = "";
        String a2 = a("debug.atrace.app_number");
        if (a2 != null) {
            try {
                num = Integer.decode(a2);
            } catch (NumberFormatException unused2) {
            }
        }
        if (num == null || num.intValue() <= 0 || ContextUtils.getApplicationContext() == null) {
            z2 = true;
        } else {
            String packageName = ContextUtils.getApplicationContext().getPackageName();
            z2 = true;
            for (int i2 = 0; i2 < num.intValue(); i2++) {
                String a3 = a("debug.atrace.app_" + i2);
                if (a3 != null && a3.startsWith(packageName)) {
                    String substring = a3.substring(packageName.length());
                    if (substring.startsWith("/")) {
                        String[] split = substring.substring(1).split(":");
                        for (String str2 : split) {
                            if (str2.equals("-atrace")) {
                                z2 = false;
                            } else {
                                if (str.length() > 0) {
                                    str = AbstractC2531fV.f(str, ",");
                                }
                                str = AbstractC2531fV.f(str, str2);
                            }
                        }
                    }
                }
            }
        }
        this.m = false;
        if (this.i.get()) {
            if (z2) {
                N.MRN$Vid3(str);
            } else {
                N.MlFM5bdC(str);
            }
        } else if (z2) {
            this.m = true;
        } else {
            EarlyTraceEvent.e();
        }
        if (!z2) {
            ThreadUtils.c().setMessageLogging(Am1.f7694a);
        }
        return true;
    }

    public final boolean queueIdle() {
        b();
        return true;
    }
}
