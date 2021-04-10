package X;

import android.content.Context;
import android.content.SharedPreferences;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0vm  reason: invalid class name and case insensitive filesystem */
public final class C07680vm {
    public static final C07570vV A00 = new C07570vV("rti.mqtt.oxygen_fbns_config");

    public static SharedPreferences A00(Context context, EnumC07690vn r4) {
        String A05 = AnonymousClass006.A05(EnumC07690vn.RTI_PREFIX, r4.getName());
        int i = 0;
        if (r4.isMultiProc()) {
            i = 4;
        }
        return context.getSharedPreferences(A05, i);
    }
}
