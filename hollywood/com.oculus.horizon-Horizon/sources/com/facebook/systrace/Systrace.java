package com.facebook.systrace;

import X.AnonymousClass0I3;
import X.AnonymousClass0P5;
import X.C04350hT;
import X.C04360hU;
import X.C04430hf;
import X.C04440hj;
import X.C04450hk;
import android.os.Process;
import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicInteger;

public final class Systrace {
    public static final ThreadLocal<C04360hU> A00 = new C04350hT();
    public static final AtomicInteger A01 = new AtomicInteger();
    public static final String[] A02 = {"dalvik.system.VMStack", "java.lang.Thread", "com.facebook.systrace.Systrace", "com.facebook.systrace.SystraceMessage", "com.facebook.litho.FbComponentsSystrace", "com.facebook.litho.ComponentsSystrace", "com.facebook.debug.tracer.Tracer"};
    public static final String[][] A03 = {new String[]{"com.facebook.common.fury.FBSystraceReqContextLifecycleCallbacks.onActivate", "com.facebook.common.fury.FBSystraceReqContextLifecycleCallbacks.onDeactivate"}, new String[]{"com.facebook.common.plugins.fblogging.FbPluginsLogger.pluginMarkerStart", "com.facebook.common.plugins.fblogging.FbPluginsLogger.pluginMarkerEnd"}, new String[]{"com.facebook.common.plugins.fblogging.FbPluginsLogger.onSocketGetPluginsStart", "com.facebook.common.plugins.fblogging.FbPluginsLogger.onSocketGetPluginsEnd"}};

    static {
        if (AnonymousClass0I3.A03) {
            Method method = AnonymousClass0I3.A02;
            AnonymousClass0P5.A00(method);
            AnonymousClass0I3.A00(method, true);
        }
        C04430hf.A00(false);
    }

    public static void A02(String str, String str2, int i) {
        if (!A03(64)) {
            return;
        }
        if (TraceDirect.checkNative()) {
            TraceDirect.nativeTraceMetadata(str, str2, i);
            return;
        }
        C04440hj r3 = new C04440hj('M');
        int myPid = Process.myPid();
        StringBuilder sb = r3.A00;
        sb.append('|');
        sb.append(myPid);
        r3.A00(str);
        sb.append('|');
        sb.append(i);
        r3.A00(str2);
        C04450hk.A00(r3.toString());
    }

    public static boolean A03(long j) {
        if ((j & C04430hf.A01) == 0 && (j & 0) == 0) {
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
            C04450hk.A00("E");
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
        C04440hj r3 = new C04440hj('B');
        int myPid = Process.myPid();
        StringBuilder sb = r3.A00;
        sb.append('|');
        sb.append(myPid);
        r3.A00(str);
        C04450hk.A00(r3.toString());
    }
}
