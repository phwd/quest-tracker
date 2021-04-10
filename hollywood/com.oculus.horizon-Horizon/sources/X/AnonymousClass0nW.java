package X;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import com.facebook.rti.common.sharedprefs.IRtiSharedPreferences;
import java.util.Map;
import java.util.WeakHashMap;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
@SuppressLint({"SharedPreferencesUse"})
/* renamed from: X.0nW  reason: invalid class name */
public final class AnonymousClass0nW implements AnonymousClass0WD {
    public final SharedPreferences A00;
    public final Object A01 = new Object();
    @GuardedBy("mLock")
    public final WeakHashMap<IRtiSharedPreferences.OnSharedPreferenceChangeListener, SharedPreferences.OnSharedPreferenceChangeListener> A02 = new WeakHashMap<>();

    @Override // X.AnonymousClass0WD
    public final synchronized Map<String, ?> A2x() {
        return this.A00.getAll();
    }

    @Override // X.AnonymousClass0WD
    public final boolean A1r(String str) {
        return this.A00.contains(str);
    }

    @Override // X.AnonymousClass0WD
    public final C06520nY A2L() {
        return new C06520nY(this, this.A00.edit());
    }

    @Override // X.AnonymousClass0WD
    public final boolean A38(String str, boolean z) {
        return this.A00.getBoolean(str, z);
    }

    @Override // X.AnonymousClass0WD
    public final int A3e(String str, int i) {
        return this.A00.getInt(str, i);
    }

    @Override // X.AnonymousClass0WD
    public final long A3l(String str, long j) {
        return this.A00.getLong(str, j);
    }

    @Override // X.AnonymousClass0WD
    public final String A4R(String str, @Nullable String str2) {
        return this.A00.getString(str, str2);
    }

    public AnonymousClass0nW(SharedPreferences sharedPreferences) {
        this.A00 = sharedPreferences;
    }
}
