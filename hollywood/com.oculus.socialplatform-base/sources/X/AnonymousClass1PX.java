package X;

import android.content.Context;
import android.content.SharedPreferences;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.msys.mci.Settings;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1PX  reason: invalid class name */
public final class AnonymousClass1PX implements Settings {
    public final SharedPreferences A00;

    @Override // com.facebook.msys.mci.Settings
    public final boolean readBooleanSetting(String str, boolean z) {
        return this.A00.getBoolean(str, z);
    }

    @Override // com.facebook.msys.mci.Settings
    public final long readLongSetting(String str, long j) {
        return this.A00.getLong(str, j);
    }

    @Override // com.facebook.msys.mci.Settings
    @Nullable
    public final String readStringSetting(String str, @Nullable String str2) {
        return this.A00.getString(str, str2);
    }

    @Override // com.facebook.msys.mci.Settings
    public final void writeBooleanSetting(String str, boolean z) {
        this.A00.edit().putBoolean(str, z).apply();
    }

    @Override // com.facebook.msys.mci.Settings
    public final void writeLongSetting(String str, long j) {
        this.A00.edit().putLong(str, j).apply();
    }

    @Override // com.facebook.msys.mci.Settings
    public final void writeStringSetting(String str, @Nullable String str2) {
        this.A00.edit().putString(str, str2).apply();
    }

    public AnonymousClass1PX(Context context) {
        this.A00 = context.getSharedPreferences("msys-preferences", 0);
    }
}
