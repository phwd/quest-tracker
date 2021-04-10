package com.facebook.systrace;

import X.Hy;
import X.PJ;
import X.PK;
import X.PM;
import X.QA;
import X.QB;
import android.os.Process;
import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicInteger;

public final class Systrace {
    public static final ThreadLocal<QA> A00 = new QB();
    public static final AtomicInteger A01 = new AtomicInteger();
    public static final String[] A02 = {"dalvik.system.VMStack", "java.lang.Thread", "com.facebook.systrace.Systrace", "com.facebook.systrace.SystraceMessage", "com.facebook.litho.FbComponentsSystrace", "com.facebook.litho.ComponentsSystrace", "com.facebook.debug.tracer.Tracer"};
    public static final String[][] A03 = {new String[]{"com.facebook.common.fury.FBSystraceReqContextLifecycleCallbacks.onActivate", "com.facebook.common.fury.FBSystraceReqContextLifecycleCallbacks.onDeactivate"}, new String[]{"com.facebook.common.plugins.fblogging.FbPluginsLogger.pluginMarkerStart", "com.facebook.common.plugins.fblogging.FbPluginsLogger.pluginMarkerEnd"}, new String[]{"com.facebook.common.plugins.fblogging.FbPluginsLogger.onSocketGetPluginsStart", "com.facebook.common.plugins.fblogging.FbPluginsLogger.onSocketGetPluginsEnd"}};

    static {
        if (Hy.A03) {
            Method method = Hy.A02;
            if (method != null) {
                Hy.A00(method, true);
            } else {
                throw new AssertionError();
            }
        }
        PM.A00(false);
    }

    public static void A02(String str, String str2, int i) {
        if (!A03(64)) {
            return;
        }
        if (TraceDirect.checkNative()) {
            TraceDirect.nativeTraceMetadata(str, str2, i);
            return;
        }
        PK pk = new PK('M');
        int myPid = Process.myPid();
        StringBuilder sb = pk.A00;
        sb.append('|');
        sb.append(myPid);
        pk.A00(str);
        sb.append('|');
        sb.append(i);
        pk.A00(str2);
        PJ.A00(pk.toString());
    }

    public static boolean A03(long j) {
        if ((j & PM.A01) == 0 && (j & 0) == 0) {
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
            PJ.A00("E");
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
        PK pk = new PK('B');
        int myPid = Process.myPid();
        StringBuilder sb = pk.A00;
        sb.append('|');
        sb.append(myPid);
        pk.A00(str);
        PJ.A00(pk.toString());
    }
}
