package X;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;

/* renamed from: X.yq  reason: case insensitive filesystem */
public final class C1404yq implements AnonymousClass8U {
    @Override // X.AnonymousClass8U
    public final void A1I(AnonymousClass8F r5, AnonymousClass8H r6) {
        Intent intent = new Intent("com.oculus.assistant.ACTION_TIMER");
        intent.putExtra("timer_extra_key", "from server");
        intent.putExtra("display_message_extra_key", "Time Up Message");
        Object systemService = BX.A00().getSystemService("alarm");
        if (systemService != null) {
            ((AlarmManager) systemService).cancel(PendingIntent.getBroadcast(BX.A00(), 0, intent, 0));
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type android.app.AlarmManager");
    }
}
