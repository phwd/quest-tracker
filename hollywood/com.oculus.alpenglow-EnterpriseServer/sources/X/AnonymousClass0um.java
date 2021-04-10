package X;

import android.content.Context;
import android.content.SharedPreferences;
import com.squareup.okhttp.ConnectionPool;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
/* renamed from: X.0um  reason: invalid class name */
public final class AnonymousClass0um {
    public static final AnonymousClass0wM A03 = AnonymousClass0wM.A00();
    public AtomicLong A00 = new AtomicLong(ConnectionPool.DEFAULT_KEEP_ALIVE_DURATION_MS);
    public final SharedPreferences A01;
    public final C07640vh A02;

    public final synchronized long A00(String str) {
        long j;
        j = -1;
        try {
            String string = this.A01.getString(str, null);
            if (string != null) {
                AnonymousClass0up A002 = AnonymousClass0up.A00(string);
                if (A002 != null) {
                    j = A002.A00 - A002.A01;
                }
            }
        } catch (ClassCastException e) {
            AnonymousClass0NK.A0C("NotificationDeliveryStoreSharedPreferences", e, "fail to read notifId %s", str);
        }
        this.A01.edit().remove(str).apply();
        return j;
    }

    public AnonymousClass0um(Context context, String str, C07640vh r6) {
        this.A01 = context.getSharedPreferences(new C07570vV(AnonymousClass006.A05("rti.mqtt.fbns_notification_store_", str)).A00, 0);
        this.A02 = r6;
    }
}
