package X;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import com.facebook.rti.common.sharedprefs.IRtiSharedPreferences;
import java.util.WeakHashMap;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
@SuppressLint({"SharedPreferencesUse"})
/* renamed from: X.1PC  reason: invalid class name */
public final class AnonymousClass1PC {
    public final SharedPreferences A00;
    public final Object A01 = new Object();
    @GuardedBy("mLock")
    public final WeakHashMap<IRtiSharedPreferences.OnSharedPreferenceChangeListener, SharedPreferences.OnSharedPreferenceChangeListener> A02 = new WeakHashMap<>();

    public final AnonymousClass1PB A00() {
        return new AnonymousClass1PB(this, this.A00.edit());
    }

    public AnonymousClass1PC(SharedPreferences sharedPreferences) {
        this.A00 = sharedPreferences;
    }
}
