package X;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;

/* renamed from: X.yt  reason: case insensitive filesystem */
public final class C1407yt implements AnonymousClass8U {
    public static final Yh A00 = new Yh();

    @Override // X.AnonymousClass8U
    public final void A1I(AnonymousClass8F r6, AnonymousClass8H r7) {
        AlarmManager.AlarmClockInfo alarmClockInfo = new AlarmManager.AlarmClockInfo(System.currentTimeMillis() + ((long) 20000), null);
        Intent intent = new Intent("com.oculus.assistant.ACTION_TIMER");
        intent.putExtra("timer_extra_key", "from server");
        intent.putExtra("display_message_extra_key", "Time Up Message");
        Object systemService = BX.A00().getSystemService("alarm");
        if (systemService != null) {
            ((AlarmManager) systemService).setAlarmClock(alarmClockInfo, PendingIntent.getBroadcast(BX.A00(), 0, intent, 0));
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type android.app.AlarmManager");
    }
}
