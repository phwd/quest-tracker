package X;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

/* renamed from: X.0Yn  reason: invalid class name and case insensitive filesystem */
public class C02000Yn extends BroadcastReceiver {
    public final /* synthetic */ AbstractC02010Yp A00;

    public C02000Yn(AbstractC02010Yp r1) {
        this.A00 = r1;
    }

    public final void onReceive(Context context, Intent intent) {
        if (intent != null && AnonymousClass0W7.A00(intent.getAction(), "com.facebook.rti.intent.ACTION_NOTIFICATION_ACK")) {
            AbstractC02010Yp r3 = this.A00;
            C01890Xx r0 = r3.A04;
            if (AnonymousClass0WZ.A01(r0.A00, C01890Xx.A00(intent))) {
                String stringExtra = intent.getStringExtra("extra_notification_id");
                if (!TextUtils.isEmpty(stringExtra)) {
                    r3.A02(stringExtra, C01890Xx.A00(intent), intent.getBooleanExtra("extra_processor_completed", true));
                }
            }
        }
    }
}
