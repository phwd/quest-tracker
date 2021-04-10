package com.facebook.crudolib.prefs;

import android.os.Handler;
import androidx.annotation.Nullable;
import com.facebook.infer.annotation.NullsafeStrict;
import com.facebook.infer.annotation.PropagatesNullable;
import java.util.Map;
import java.util.Set;
import javax.annotation.concurrent.ThreadSafe;

@NullsafeStrict
@ThreadSafe
public interface LightSharedPreferences {

    @NullsafeStrict
    public interface Editor {
        void apply();

        Editor clear();

        boolean commit();

        boolean commit(int i);

        Editor putBoolean(String str, boolean z);

        Editor putDouble(String str, double d);

        Editor putFloat(String str, float f);

        Editor putInt(String str, int i);

        Editor putLong(String str, long j);

        Editor putString(String str, @Nullable String str2);

        Editor putStringSet(String str, @Nullable Set<String> set);

        Editor remove(String str);
    }

    public interface OnSharedPreferenceChangeListener {
        void onSharedPreferenceChanged(LightSharedPreferences lightSharedPreferences, String str);
    }

    boolean contains(String str);

    Editor edit();

    Map<String, ?> getAll();

    boolean getBoolean(String str, boolean z);

    double getDouble(String str, double d);

    float getFloat(String str, float f);

    int getInt(String str, int i);

    @Nullable
    Throwable getLastCommitCallStack();

    long getLong(String str, long j);

    int getSize();

    String getString(String str, @PropagatesNullable String str2);

    Set<String> getStringSet(String str, @PropagatesNullable Set<String> set);

    void registerOnSharedPreferenceChangeListener(String str, Handler handler, OnSharedPreferenceChangeListener onSharedPreferenceChangeListener);

    void registerOnSharedPreferenceChangeListener(String str, OnSharedPreferenceChangeListener onSharedPreferenceChangeListener);

    void unregisterOnSharedPreferenceChangeListener(String str, OnSharedPreferenceChangeListener onSharedPreferenceChangeListener);
}
