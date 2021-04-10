package X;

import android.content.Context;
import android.content.SharedPreferences;
import com.squareup.okhttp.ConnectionPool;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
/* renamed from: X.0mI  reason: invalid class name and case insensitive filesystem */
public final class C05990mI {
    public static final AnonymousClass0Vq A03 = AnonymousClass0Vq.A00();
    public AtomicLong A00 = new AtomicLong(ConnectionPool.DEFAULT_KEEP_ALIVE_DURATION_MS);
    public final SharedPreferences A01;
    public final AnonymousClass0nN A02;

    public final synchronized long A00(String str) {
        long j;
        j = -1;
        try {
            String string = this.A01.getString(str, null);
            if (string != null) {
                AnonymousClass0mJ A002 = AnonymousClass0mJ.A00(string);
                if (A002 != null) {
                    j = A002.A00 - A002.A01;
                }
            }
        } catch (ClassCastException e) {
            AnonymousClass0NO.A0K("NotificationDeliveryStoreSharedPreferences", e, "fail to read notifId %s", str);
        }
        this.A01.edit().remove(str).apply();
        return j;
    }

    public C05990mI(Context context, String str, AnonymousClass0nN r6) {
        this.A01 = AnonymousClass0WG.A00.A00(context, new AnonymousClass0WI(AnonymousClass006.A05("rti.mqtt.fbns_notification_store_", str)).A00, false);
        this.A02 = r6;
    }
}
