package defpackage;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import org.chromium.base.ThreadUtils;
import org.chromium.components.background_task_scheduler.TaskInfo;
import org.chromium.components.background_task_scheduler.internal.BackgroundTaskBroadcastReceiver;

/* renamed from: df  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2215df implements AbstractC2385ef {
    @Override // defpackage.AbstractC2385ef
    public void a(Context context, int i) {
        Object obj = ThreadUtils.f10596a;
        ((AlarmManager) context.getSystemService("alarm")).cancel(PendingIntent.getBroadcast(context, i, new Intent(context, BackgroundTaskBroadcastReceiver.class).putExtra("_background_task_id", i), 268435456));
    }

    @Override // defpackage.AbstractC2385ef
    public boolean b(Context context, TaskInfo taskInfo) {
        Object obj = ThreadUtils.f10596a;
        int i = taskInfo.f10811a;
        taskInfo.g.a(new C2044cf((AlarmManager) context.getSystemService("alarm"), PendingIntent.getBroadcast(context, i, new Intent(context, BackgroundTaskBroadcastReceiver.class).putExtra("_background_task_id", i), 268435456)));
        return true;
    }
}
