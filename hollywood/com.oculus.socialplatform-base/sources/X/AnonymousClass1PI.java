package X;

import android.content.Context;
import android.content.SharedPreferences;
import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1PI  reason: invalid class name */
public final class AnonymousClass1PI extends AnonymousClass1Q1 {
    public final AnonymousClass1PJ A00;

    public AnonymousClass1PI(Context context, AnonymousClass1YF r8, Integer num, @Nullable AnonymousClass1PM r10, AnonymousClass1PJ r11) {
        super(context, r8, num, r10);
        this.A00 = r11;
        SharedPreferences sharedPreferences = this.A02.A00(AnonymousClass1PL.LAST_HOST).A00;
        if (System.currentTimeMillis() - sharedPreferences.getLong("zero_rating_last_host_timestamp", 0) < 86400000) {
            this.A06 = sharedPreferences.getString("zero_rating_last_host", null);
        }
    }
}
