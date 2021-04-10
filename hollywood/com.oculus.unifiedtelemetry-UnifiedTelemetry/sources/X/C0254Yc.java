package X;

import android.content.Context;
import android.content.SharedPreferences;
import com.facebook.infer.annotation.Nullsafe;
import java.util.UUID;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.Yc  reason: case insensitive filesystem */
public final class C0254Yc implements SU {
    public final Context A00;

    @Override // X.SU
    public final String A2F() {
        SharedPreferences sharedPreferences = this.A00.getSharedPreferences("UniqueDeviceIdPrefs", 0);
        String string = sharedPreferences.getString("device_id", null);
        if (string != null) {
            return string;
        }
        String obj = UUID.randomUUID().toString();
        sharedPreferences.edit().putString("device_id", obj).apply();
        return obj;
    }

    public C0254Yc(Context context) {
        this.A00 = context;
    }
}
