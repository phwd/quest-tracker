package com.facebook.papaya.client.platform;

import X.C0909ob;
import X.C0910oc;
import X.C0911od;
import X.C1192ut;
import X.CU;
import X.CV;
import X.KJ;
import X.V3;
import android.content.ComponentName;
import android.content.Context;
import android.os.Process;
import com.google.common.base.Preconditions;

public final class Platform {
    public static final Class TAG = Platform.class;
    public static PlatformJobScheduler$SchedulingGuard sGuard;

    static {
        KJ.A05("papaya", 0);
    }

    public static void cancelExecution(Context context) {
        C0910oc ocVar = new C0910oc(context);
        PlatformJobScheduler$SchedulingGuard A00 = PlatformJobScheduler$SchedulingGuard.A00();
        C1192ut.A00(ocVar.A39(A00), new C0911od(A00), V3.INSTANCE);
    }

    public static synchronized void onRunFinished() {
        synchronized (Platform.class) {
            if (sGuard != null) {
                PlatformLog.A00(Platform.class, "Finished Papaya execution", new Object[0]);
                sGuard.A01();
                sGuard = null;
            } else {
                throw null;
            }
        }
    }

    public static synchronized void onRunStarted() {
        synchronized (Platform.class) {
            boolean z = false;
            if (sGuard == null) {
                z = true;
            }
            Preconditions.checkState(z);
            PlatformLog.A00(Platform.class, "Performing Papaya execution", new Object[0]);
            sGuard = PlatformJobScheduler$SchedulingGuard.A00();
        }
    }

    public static boolean scheduleExecution(Context context, ComponentName componentName, long j, boolean z, long j2, boolean z2, boolean z3) {
        C0909ob obVar = new C0909ob(context, componentName, j, z, j2, z2, z3);
        PlatformJobScheduler$SchedulingGuard A00 = PlatformJobScheduler$SchedulingGuard.A00();
        C1192ut.A00(obVar.A39(A00), new C0911od(A00), V3.INSTANCE);
        return true;
    }

    public static void killProcess() {
        if (!CU.A01.equals(CV.A00().A00)) {
            Process.killProcess(Process.myPid());
        }
    }
}
