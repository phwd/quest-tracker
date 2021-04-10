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
/* renamed from: X.0vr  reason: invalid class name and case insensitive filesystem */
public final class C07730vr implements AnonymousClass0ux {
    public final SharedPreferences A00;
    public final Object A01 = new Object();
    @GuardedBy("mLock")
    public final WeakHashMap<IRtiSharedPreferences.OnSharedPreferenceChangeListener, SharedPreferences.OnSharedPreferenceChangeListener> A02 = new WeakHashMap<>();

    @Override // X.AnonymousClass0ux
    public final synchronized Map<String, ?> A2u() {
        return this.A00.getAll();
    }

    @Override // X.AnonymousClass0ux
    public final boolean A1p(String str) {
        return this.A00.contains(str);
    }

    @Override // X.AnonymousClass0ux
    public final C07720vq A2E() {
        return new C07720vq(this, this.A00.edit());
    }

    @Override // X.AnonymousClass0ux
    public final boolean A37(String str, boolean z) {
        return this.A00.getBoolean(str, z);
    }

    @Override // X.AnonymousClass0ux
    public final int A3n(String str, int i) {
        return this.A00.getInt(str, i);
    }

    @Override // X.AnonymousClass0ux
    public final long A3x(String str, long j) {
        return this.A00.getLong(str, j);
    }

    @Override // X.AnonymousClass0ux
    public final String A4Z(String str, @Nullable String str2) {
        return this.A00.getString(str, str2);
    }

    public C07730vr(SharedPreferences sharedPreferences) {
        this.A00 = sharedPreferences;
    }
}
