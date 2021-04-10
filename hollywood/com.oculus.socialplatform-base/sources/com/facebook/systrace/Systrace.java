package com.facebook.systrace;

import X.AnonymousClass0GT;
import X.AnonymousClass0ly;
import X.AnonymousClass0mF;
import X.C03290lz;
import X.C03340mA;
import X.C03350mE;
import android.os.Process;
import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicInteger;

public final class Systrace {
    public static final ThreadLocal<C03290lz> A00 = new AnonymousClass0ly();
    public static final AtomicInteger A01 = new AtomicInteger();
    public static final String[] A02 = {"dalvik.system.VMStack", "java.lang.Thread", "com.facebook.systrace.Systrace", "com.facebook.systrace.SystraceMessage", "com.facebook.litho.FbComponentsSystrace", "com.facebook.litho.ComponentsSystrace", "com.facebook.debug.tracer.Tracer"};
    public static final String[][] A03 = {new String[]{"com.facebook.common.fury.FBSystraceReqContextLifecycleCallbacks.onActivate", "com.facebook.common.fury.FBSystraceReqContextLifecycleCallbacks.onDeactivate"}, new String[]{"com.facebook.common.plugins.fblogging.FbPluginsLogger.pluginMarkerStart", "com.facebook.common.plugins.fblogging.FbPluginsLogger.pluginMarkerEnd"}, new String[]{"com.facebook.common.plugins.fblogging.FbPluginsLogger.onSocketGetPluginsStart", "com.facebook.common.plugins.fblogging.FbPluginsLogger.onSocketGetPluginsEnd"}};

    static {
        if (AnonymousClass0GT.A03) {
            Method method = AnonymousClass0GT.A02;
            if (method != null) {
                AnonymousClass0GT.A00(method, true);
            } else {
                throw new AssertionError();
            }
        }
        C03340mA.A00(false);
    }

    public static void A02(String str, String str2, int i) {
        if (!A03(64)) {
            return;
        }
        if (TraceDirect.checkNative()) {
            TraceDirect.nativeTraceMetadata(str, str2, i);
            return;
        }
        C03350mE r3 = new C03350mE('M');
        int myPid = Process.myPid();
        StringBuilder sb = r3.A00;
        sb.append('|');
        sb.append(myPid);
        r3.A00(str);
        sb.append('|');
        sb.append(i);
        r3.A00(str2);
        AnonymousClass0mF.A00(r3.toString());
    }

    public static boolean A03(long j) {
        if ((j & C03340mA.A01) == 0 && (j & 0) == 0) {
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
            AnonymousClass0mF.A00("E");
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
        C03350mE r3 = new C03350mE('B');
        int myPid = Process.myPid();
        StringBuilder sb = r3.A00;
        sb.append('|');
        sb.append(myPid);
        r3.A00(str);
        AnonymousClass0mF.A00(r3.toString());
    }
}
