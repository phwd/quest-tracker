package defpackage;

import android.content.SharedPreferences;
import android.util.Base64;
import org.chromium.base.ContextUtils;
import org.chromium.base.TraceEvent;
import org.chromium.components.background_task_scheduler.TaskInfo;

/* renamed from: sf  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC4776sf {
    public static void a(TaskInfo taskInfo) {
        TraceEvent k0 = TraceEvent.k0("BackgroundTaskSchedulerPrefs.addScheduledTask", Integer.toString(taskInfo.f10811a));
        try {
            C4606rf rfVar = new C4606rf(taskInfo.b, taskInfo.c, taskInfo.d);
            taskInfo.g.a(rfVar);
            c().edit().putString(String.valueOf(taskInfo.f10811a), rfVar.f11212a).apply();
            if (k0 != null) {
                k0.close();
                return;
            }
            return;
        } catch (Throwable th) {
            AbstractC0754Mh1.f8495a.a(th, th);
        }
        throw th;
    }

    public static TO0 b(int i) {
        String string = c().getString(String.valueOf(i), null);
        if (string == null) {
            AbstractC1220Ua0.a("BTSPrefs", AbstractC2531fV.w("No data found for task id: ", i), new Object[0]);
            return null;
        }
        try {
            return (TO0) AbstractC2360eV.k(TO0.e, Base64.decode(string, 0));
        } catch (L30 e) {
            AbstractC1220Ua0.a("BTSPrefs", "Invalid protocol buffer: " + e, new Object[0]);
            e(i);
            return null;
        }
    }

    public static SharedPreferences c() {
        return ContextUtils.getApplicationContext().getSharedPreferences("org.chromium.components.background_task_scheduler", 0);
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x006b  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0075  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void d() {
        /*
        // Method dump skipped, instructions count: 168
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.AbstractC4776sf.d():void");
    }

    public static void e(int i) {
        TraceEvent k0 = TraceEvent.k0("BackgroundTaskSchedulerPrefs.removeScheduledTask", Integer.toString(i));
        try {
            c().edit().remove(String.valueOf(i)).apply();
            if (k0 != null) {
                k0.close();
                return;
            }
            return;
        } catch (Throwable th) {
            AbstractC0754Mh1.f8495a.a(th, th);
        }
        throw th;
    }
}
