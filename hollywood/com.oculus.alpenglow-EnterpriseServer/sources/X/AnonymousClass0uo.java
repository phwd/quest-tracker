package X;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

/* renamed from: X.0uo  reason: invalid class name */
public class AnonymousClass0uo extends BroadcastReceiver {
    public final /* synthetic */ AnonymousClass0ul A00;

    public AnonymousClass0uo(AnonymousClass0ul r1) {
        this.A00 = r1;
    }

    public final void onReceive(Context context, Intent intent) {
        if (intent != null && C07950wI.A00(intent.getAction(), "com.facebook.rti.intent.ACTION_NOTIFICATION_ACK")) {
            AnonymousClass0ul r3 = this.A00;
            if (r3.A04.A03(C07800w2.A00(intent))) {
                String stringExtra = intent.getStringExtra("extra_notification_id");
                if (!TextUtils.isEmpty(stringExtra)) {
                    r3.A03(stringExtra, C07800w2.A00(intent), intent.getBooleanExtra("extra_processor_completed", true));
                }
            }
        }
    }
}
