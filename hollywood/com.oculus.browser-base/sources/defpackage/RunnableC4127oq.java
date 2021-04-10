package defpackage;

import android.content.Context;
import android.os.Bundle;
import java.util.Objects;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.background_sync.BackgroundSyncBackgroundTaskScheduler;
import org.chromium.components.background_task_scheduler.TaskInfo;

/* renamed from: oq  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC4127oq implements Runnable {
    public final C4298pq F;
    public final String G;
    public final Context H;

    public RunnableC4127oq(C4298pq pqVar, String str, Context context) {
        this.F = pqVar;
        this.G = str;
        this.H = context;
    }

    public void run() {
        C4298pq pqVar = this.F;
        String str = this.G;
        Objects.requireNonNull(pqVar);
        str.hashCode();
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case 694178979:
                if (str.equals("BackgroundSync Event")) {
                    c = 0;
                    break;
                }
                break;
            case 694350810:
                if (str.equals("Servicification Startup Task")) {
                    c = 1;
                    break;
                }
                break;
            case 902055135:
                if (str.equals("OfflinePageUtils")) {
                    c = 2;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                BackgroundSyncBackgroundTaskScheduler.getInstance().scheduleOneOffTask(0, 360000);
                return;
            case 1:
                pqVar.a();
                return;
            case 2:
                Bundle bundle = new Bundle();
                bundle.putLong("ScheduleTime", System.currentTimeMillis());
                bundle.putBoolean("PowerConnected", false);
                bundle.putInt("BatteryPercentage", 0);
                bundle.putBoolean("UnmeteredNetwork", false);
                C1111Se1 a2 = TaskInfo.a(77, 300000, 604800000);
                a2.c = 1;
                a2.f = false;
                a2.e = true;
                a2.b = bundle;
                a2.d = false;
                AbstractC2898hf.b().b(ContextUtils.getApplicationContext(), a2.a());
                return;
            default:
                AbstractC1220Ua0.d("BackgroundService", AbstractC2531fV.f("Unknown task tag ", str), new Object[0]);
                return;
        }
    }
}
