package org.chromium.chrome.browser.offlinepages;

import android.content.Intent;
import android.os.Bundle;
import org.chromium.base.ContextUtils;
import org.chromium.components.background_task_scheduler.TaskInfo;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class BackgroundSchedulerBridge {
    public static void backupSchedule(TriggerConditions triggerConditions, long j) {
        Bundle bundle = new Bundle();
        bundle.putLong("ScheduleTime", System.currentTimeMillis());
        bundle.putBoolean("PowerConnected", triggerConditions.f10721a);
        bundle.putInt("BatteryPercentage", triggerConditions.b);
        bundle.putBoolean("UnmeteredNetwork", triggerConditions.c);
        C1111Se1 a2 = TaskInfo.a(77, j * 1000, 604800000);
        a2.c = triggerConditions.c ? 2 : 1;
        a2.f = false;
        a2.e = true;
        a2.b = bundle;
        a2.d = triggerConditions.f10721a;
        AbstractC2898hf.b().b(ContextUtils.getApplicationContext(), a2.a());
    }

    public static TriggerConditions createTriggerConditions(boolean z, int i, boolean z2) {
        return new TriggerConditions(z, i, z2);
    }

    public static int getBatteryConditions() {
        Intent a2 = C5222vE.a(ContextUtils.getApplicationContext());
        if (a2 == null) {
            return 0;
        }
        return C5222vE.c(a2);
    }

    public static int getNetworkConditions() {
        return C5222vE.d(ContextUtils.getApplicationContext());
    }

    public static boolean getPowerConditions() {
        Intent a2 = C5222vE.a(ContextUtils.getApplicationContext());
        if (a2 == null) {
            return false;
        }
        return C5222vE.e(a2);
    }

    public static void schedule(TriggerConditions triggerConditions) {
        Bundle bundle = new Bundle();
        bundle.putLong("ScheduleTime", System.currentTimeMillis());
        bundle.putBoolean("PowerConnected", triggerConditions.f10721a);
        bundle.putInt("BatteryPercentage", triggerConditions.b);
        bundle.putBoolean("UnmeteredNetwork", triggerConditions.c);
        C1111Se1 a2 = TaskInfo.a(77, 0, 604800000);
        a2.c = triggerConditions.c ? 2 : 1;
        a2.f = true;
        a2.e = true;
        a2.b = bundle;
        a2.d = triggerConditions.f10721a;
        AbstractC2898hf.b().b(ContextUtils.getApplicationContext(), a2.a());
    }

    public static void unschedule() {
        AbstractC2898hf.b().a(ContextUtils.getApplicationContext(), 77);
    }
}
