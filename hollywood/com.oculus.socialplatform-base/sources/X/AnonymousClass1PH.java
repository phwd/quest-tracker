package X;

import android.content.Context;
import android.content.SharedPreferences;
import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1PH  reason: invalid class name */
public final class AnonymousClass1PH extends AnonymousClass1Q1 {
    public AnonymousClass1PH(Context context, AnonymousClass1YF r5, Integer num, @Nullable AnonymousClass1PM r7) {
        super(context, r5, num, r7);
        SharedPreferences sharedPreferences = this.A02.A00(AnonymousClass1PL.LAST_HOST).A00;
        this.A06 = sharedPreferences.getString("work_last_host", null);
        this.A05 = sharedPreferences.getString("work_last_analytics_endpoint", null);
    }
}
