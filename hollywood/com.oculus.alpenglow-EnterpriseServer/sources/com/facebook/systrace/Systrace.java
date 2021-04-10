package com.facebook.systrace;

import X.AnonymousClass0IJ;
import X.AnonymousClass0Q1;
import X.AnonymousClass0jS;
import X.AnonymousClass0jT;
import X.AnonymousClass0ji;
import X.AnonymousClass0jj;
import X.C05500je;
import android.os.Process;
import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicInteger;

public final class Systrace {
    public static final ThreadLocal<AnonymousClass0jT> A00 = new AnonymousClass0jS();
    public static final AtomicInteger A01 = new AtomicInteger();
    public static final String[] A02 = {"dalvik.system.VMStack", "java.lang.Thread", "com.facebook.systrace.Systrace", "com.facebook.systrace.SystraceMessage", "com.facebook.litho.FbComponentsSystrace", "com.facebook.litho.ComponentsSystrace", "com.facebook.debug.tracer.Tracer"};
    public static final String[][] A03 = {new String[]{"com.facebook.common.fury.FBSystraceReqContextLifecycleCallbacks.onActivate", "com.facebook.common.fury.FBSystraceReqContextLifecycleCallbacks.onDeactivate"}, new String[]{"com.facebook.common.plugins.fblogging.FbPluginsLogger.pluginMarkerStart", "com.facebook.common.plugins.fblogging.FbPluginsLogger.pluginMarkerEnd"}, new String[]{"com.facebook.common.plugins.fblogging.FbPluginsLogger.onSocketGetPluginsStart", "com.facebook.common.plugins.fblogging.FbPluginsLogger.onSocketGetPluginsEnd"}};

    static {
        if (AnonymousClass0IJ.A03) {
            Method method = AnonymousClass0IJ.A02;
            AnonymousClass0Q1.A00(method);
            AnonymousClass0IJ.A00(method, true);
        }
        C05500je.A00(false);
    }

    public static void A02(String str, String str2, int i) {
        if (!A03(64)) {
            return;
        }
        if (TraceDirect.checkNative()) {
            TraceDirect.nativeTraceMetadata(str, str2, i);
            return;
        }
        AnonymousClass0ji r3 = new AnonymousClass0ji('M');
        int myPid = Process.myPid();
        StringBuilder sb = r3.A00;
        sb.append('|');
        sb.append(myPid);
        r3.A00(str);
        sb.append('|');
        sb.append(i);
        r3.A00(str2);
        AnonymousClass0jj.A00(r3.toString());
    }

    public static boolean A03(long j) {
        if ((j & C05500je.A01) == 0 && (j & 0) == 0) {
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
            AnonymousClass0jj.A00("E");
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
        AnonymousClass0ji r3 = new AnonymousClass0ji('B');
        int myPid = Process.myPid();
        StringBuilder sb = r3.A00;
        sb.append('|');
        sb.append(myPid);
        r3.A00(str);
        AnonymousClass0jj.A00(r3.toString());
    }
}
