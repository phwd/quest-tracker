package X;

import android.content.Context;
import android.content.SharedPreferences;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0WJ  reason: invalid class name */
public final class AnonymousClass0WJ {
    public static final AnonymousClass0WI A00 = new AnonymousClass0WI("rti.mqtt.oxygen_fbns_config");

    public static SharedPreferences A00(Context context, AnonymousClass0WE r4) {
        return AnonymousClass0WG.A00.A00(context, AnonymousClass006.A05(AnonymousClass0WE.RTI_PREFIX, r4.getName()), r4.isMultiProc());
    }
}
