package X;

import android.content.Context;
import android.content.SharedPreferences;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1PN  reason: invalid class name */
public final class AnonymousClass1PN {
    public static final AnonymousClass1PO A00 = new AnonymousClass1PO("rti.mqtt.oxygen_fbns_config");

    public static SharedPreferences A00(Context context, AnonymousClass1PL r4) {
        String A07 = AnonymousClass006.A07(AnonymousClass1PL.RTI_PREFIX, r4.getName());
        int i = 0;
        if (r4.isMultiProc()) {
            i = 4;
        }
        return context.getSharedPreferences(A07, i);
    }
}
