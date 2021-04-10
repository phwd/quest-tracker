package com.facebook.systrace;

import X.AnonymousClass8B;
import X.C0225Kz;
import X.Er;
import X.Ko;
import X.L3;
import X.L4;
import android.os.Process;
import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicInteger;

public final class Systrace {
    public static final ThreadLocal A00 = new Ko();
    public static final AtomicInteger A01 = new AtomicInteger();
    public static final String[] A02 = {"dalvik.system.VMStack", "java.lang.Thread", "com.facebook.systrace.Systrace", "com.facebook.systrace.SystraceMessage", "com.facebook.litho.FbComponentsSystrace", "com.facebook.litho.ComponentsSystrace", "com.facebook.debug.tracer.Tracer"};
    public static final String[][] A03 = {new String[]{"com.facebook.common.fury.FBSystraceReqContextLifecycleCallbacks.onActivate", "com.facebook.common.fury.FBSystraceReqContextLifecycleCallbacks.onDeactivate"}, new String[]{"com.facebook.common.plugins.fblogging.FbPluginsLogger.pluginMarkerStart", "com.facebook.common.plugins.fblogging.FbPluginsLogger.pluginMarkerEnd"}, new String[]{"com.facebook.common.plugins.fblogging.FbPluginsLogger.onSocketGetPluginsStart", "com.facebook.common.plugins.fblogging.FbPluginsLogger.onSocketGetPluginsEnd"}};

    static {
        if (AnonymousClass8B.A03) {
            Method method = AnonymousClass8B.A02;
            Er.A00(method);
            AnonymousClass8B.A00(method, true);
        }
        C0225Kz.A00(false);
    }

    public static void A02(String str, String str2, int i) {
        if (!A03(64)) {
            return;
        }
        if (TraceDirect.checkNative()) {
            TraceDirect.nativeTraceMetadata(str, str2, i);
            return;
        }
        L3 l3 = new L3('M');
        int myPid = Process.myPid();
        StringBuilder sb = l3.A00;
        sb.append('|');
        sb.append(myPid);
        l3.A00(str);
        sb.append('|');
        sb.append(i);
        l3.A00(str2);
        L4.A00(l3.toString());
    }

    public static boolean A03(long j) {
        if ((j & C0225Kz.A01) == 0 && (j & 0) == 0) {
            return false;
        }
        return true;
    }

    public static void A00(long j) {
        if (!A03(j)) {
            return;
        }
        if (TraceDirect.checkNative()) {
            TraceDirect.nativeEndSection();
        } else {
            L4.A00("E");
        }
    }

    public static void A01(long j, String str) {
        if (!A03(j)) {
            return;
        }
        if (TraceDirect.checkNative()) {
            TraceDirect.nativeBeginSection(str);
            return;
        }
        L3 l3 = new L3('B');
        int myPid = Process.myPid();
        StringBuilder sb = l3.A00;
        sb.append('|');
        sb.append(myPid);
        l3.A00(str);
        L4.A00(l3.toString());
    }
}
