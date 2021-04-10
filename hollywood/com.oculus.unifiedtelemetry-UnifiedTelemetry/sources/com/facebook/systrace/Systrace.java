package com.facebook.systrace;

import X.C0456mp;
import X.C0457mq;
import X.Hf;
import X.P6;
import X.mZ;
import X.ma;
import X.ml;
import android.os.Process;
import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicInteger;

public final class Systrace {
    public static final ThreadLocal<ma> A00 = new mZ();
    public static final AtomicInteger A01 = new AtomicInteger();
    public static final String[] A02 = {"dalvik.system.VMStack", "java.lang.Thread", "com.facebook.systrace.Systrace", "com.facebook.systrace.SystraceMessage", "com.facebook.litho.FbComponentsSystrace", "com.facebook.litho.ComponentsSystrace", "com.facebook.debug.tracer.Tracer"};
    public static final String[][] A03 = {new String[]{"com.facebook.common.fury.FBSystraceReqContextLifecycleCallbacks.onActivate", "com.facebook.common.fury.FBSystraceReqContextLifecycleCallbacks.onDeactivate"}, new String[]{"com.facebook.common.plugins.fblogging.FbPluginsLogger.pluginMarkerStart", "com.facebook.common.plugins.fblogging.FbPluginsLogger.pluginMarkerEnd"}, new String[]{"com.facebook.common.plugins.fblogging.FbPluginsLogger.onSocketGetPluginsStart", "com.facebook.common.plugins.fblogging.FbPluginsLogger.onSocketGetPluginsEnd"}};

    static {
        if (Hf.A03) {
            Method method = Hf.A02;
            P6.A00(method);
            Hf.A00(method, true);
        }
        ml.A00(false);
    }

    public static void A02(String str, String str2, int i) {
        if (!A03(64)) {
            return;
        }
        if (TraceDirect.checkNative()) {
            TraceDirect.nativeTraceMetadata(str, str2, i);
            return;
        }
        C0456mp mpVar = new C0456mp('M');
        int myPid = Process.myPid();
        StringBuilder sb = mpVar.A00;
        sb.append('|');
        sb.append(myPid);
        mpVar.A00(str);
        sb.append('|');
        sb.append(i);
        mpVar.A00(str2);
        C0457mq.A00(mpVar.toString());
    }

    public static boolean A03(long j) {
        if ((j & ml.A01) == 0 && (j & 0) == 0) {
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
            C0457mq.A00("E");
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
        C0456mp mpVar = new C0456mp('B');
        int myPid = Process.myPid();
        StringBuilder sb = mpVar.A00;
        sb.append('|');
        sb.append(myPid);
        mpVar.A00(str);
        C0457mq.A00(mpVar.toString());
    }
}
