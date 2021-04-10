package com.oculus.modules;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.oculus.modules.codegen.Resolver;
import com.oculus.modules.codegen.SettingsManagerModule;
import com.oculus.os.SettingsManager;
import com.oculus.os.SettingsObserverCallback;
import java.util.HashMap;

public class SettingsManagerModuleImpl extends SettingsManagerModule implements SettingsObserverCallback {
    private static final String TAG = "SettingsManagerModuleImpl";
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private long mNextSubscriptionKey = 0;
    private final SettingsManager mSettingsManager = new SettingsManager();
    private final HashMap<String, Long> mSubscriptionCounts = new HashMap<>();

    @Override // com.oculus.modules.codegen.SettingsManagerModule
    public void shutdownModule() {
        for (String str : this.mSubscriptionCounts.keySet()) {
            this.mSettingsManager.unregisterSettingsObserver(str, this);
        }
    }

    @Override // com.oculus.modules.codegen.SettingsManagerModule
    public void getBooleanImpl(String str, boolean z, Resolver<Boolean> resolver) {
        boolean z2 = this.mSettingsManager.getBoolean(str, z);
        String str2 = TAG;
        Log.d(str2, "Got boolean value " + z2 + " for key \"" + str + "\"");
        resolver.resolve(Boolean.valueOf(z2));
    }

    @Override // com.oculus.modules.codegen.SettingsManagerModule
    public void getDoubleImpl(String str, double d, Resolver<Double> resolver) {
        double d2 = this.mSettingsManager.getDouble(str, d);
        String str2 = TAG;
        Log.d(str2, "Got double value " + d2 + " for key \"" + str + "\"");
        resolver.resolve(Double.valueOf(d2));
    }

    @Override // com.oculus.modules.codegen.SettingsManagerModule
    public void getFloatImpl(String str, double d, Resolver<Double> resolver) {
        float f = this.mSettingsManager.getFloat(str, (float) d);
        String str2 = TAG;
        Log.d(str2, "Got float value " + f + " for key \"" + str + "\"");
        resolver.resolve(new Double((double) f));
    }

    @Override // com.oculus.modules.codegen.SettingsManagerModule
    public void getIntImpl(String str, double d, Resolver<Double> resolver) {
        int i = this.mSettingsManager.getInt(str, (int) d);
        String str2 = TAG;
        Log.d(str2, "Got int value " + i + " for key \"" + str + "\"");
        resolver.resolve(new Double((double) i));
    }

    @Override // com.oculus.modules.codegen.SettingsManagerModule
    public void getLongImpl(String str, double d, Resolver<Double> resolver) {
        long j = this.mSettingsManager.getLong(str, (long) d);
        String str2 = TAG;
        Log.d(str2, "Got long value " + j + " for key \"" + str + "\"");
        resolver.resolve(new Double((double) j));
    }

    @Override // com.oculus.modules.codegen.SettingsManagerModule
    public void getStringImpl(String str, String str2, Resolver<String> resolver) {
        String string = this.mSettingsManager.getString(str, str2);
        String str3 = TAG;
        Log.d(str3, "Got String value " + string + " for key \"" + str + "\"");
        resolver.resolve(string);
    }

    @Override // com.oculus.modules.codegen.SettingsManagerModule
    public void hasDefinedValueImpl(String str, Resolver<Boolean> resolver) {
        resolver.resolve(Boolean.valueOf(this.mSettingsManager.getString(str, null) != null));
    }

    @Override // com.oculus.modules.codegen.SettingsManagerModule
    public void setBooleanImpl(String str, boolean z, Resolver<Void> resolver) {
        if (!this.mSettingsManager.setBoolean(str, z)) {
            String str2 = "Failed to set boolean value " + z + " for key \"" + str + "\"";
            Log.e(TAG, str2);
            resolver.reject(new Exception(str2));
            return;
        }
        resolver.resolve(null);
    }

    @Override // com.oculus.modules.codegen.SettingsManagerModule
    public void setDoubleImpl(String str, double d, Resolver<Void> resolver) {
        if (!this.mSettingsManager.setDouble(str, d)) {
            String str2 = "Failed to set double value " + d + " for key \"" + str + "\"";
            Log.e(TAG, str2);
            resolver.reject(new Exception(str2));
            return;
        }
        resolver.resolve(null);
    }

    @Override // com.oculus.modules.codegen.SettingsManagerModule
    public void setFloatImpl(String str, double d, Resolver<Void> resolver) {
        if (!this.mSettingsManager.setFloat(str, (float) d)) {
            String str2 = "Failed to set boolean value " + d + " for key \"" + str + "\"";
            Log.e(TAG, str2);
            resolver.reject(new Exception(str2));
            return;
        }
        resolver.resolve(null);
    }

    @Override // com.oculus.modules.codegen.SettingsManagerModule
    public void setIntImpl(String str, double d, Resolver<Void> resolver) {
        if (!this.mSettingsManager.setInt(str, (int) d)) {
            String str2 = "Failed to set int value " + d + " for key \"" + str + "\"";
            Log.e(TAG, str2);
            resolver.reject(new Exception(str2));
            return;
        }
        resolver.resolve(null);
    }

    @Override // com.oculus.modules.codegen.SettingsManagerModule
    public void setLongImpl(String str, double d, Resolver<Void> resolver) {
        if (!this.mSettingsManager.setLong(str, (long) d)) {
            String str2 = "Failed to set long value " + d + " for key \"" + str + "\"";
            Log.e(TAG, str2);
            resolver.reject(new Exception(str2));
            return;
        }
        resolver.resolve(null);
    }

    @Override // com.oculus.modules.codegen.SettingsManagerModule
    public void setStringImpl(String str, String str2, Resolver<Void> resolver) {
        if (!this.mSettingsManager.setString(str, str2)) {
            String str3 = "Failed to set String value " + str2 + " for key \"" + str + "\"";
            Log.e(TAG, str3);
            resolver.reject(new Exception(str3));
            return;
        }
        resolver.resolve(null);
    }

    public void onSettingChange(String str) {
        String str2 = TAG;
        Log.d(str2, "Observed change for key \"" + str + "\"");
        SettingsManagerModule.SettingUpdate settingUpdate = new SettingsManagerModule.SettingUpdate();
        settingUpdate.name = str;
        emitOnUpdated(settingUpdate);
    }

    @Override // com.oculus.modules.codegen.SettingsManagerModule
    public void subscribeImpl(String str, Resolver<Void> resolver) {
        Long l = this.mSubscriptionCounts.get(str);
        if (l == null) {
            String str2 = TAG;
            Log.d(str2, "Registering observer for key \"" + str + "\"");
            this.mSettingsManager.registerSettingsObserver(str, this, this.mHandler);
            l = 0L;
        }
        this.mSubscriptionCounts.put(str, Long.valueOf(l.longValue() + 1));
        resolver.resolve(null);
    }

    @Override // com.oculus.modules.codegen.SettingsManagerModule
    public void unsubscribeImpl(String str, Resolver<Void> resolver) {
        Long l = this.mSubscriptionCounts.get(str);
        if (l == null) {
            String str2 = TAG;
            Log.e(str2, "No observer to unregister for key \"" + str + "\"");
        } else if (l.longValue() <= 1) {
            String str3 = TAG;
            Log.d(str3, "Unregistering observer for key \"" + str + "\" with count \"" + l + "\"");
            this.mSettingsManager.unregisterSettingsObserver(str, this);
            this.mSubscriptionCounts.remove(str);
        } else {
            this.mSubscriptionCounts.put(str, Long.valueOf(l.longValue() - 1));
        }
        resolver.resolve(null);
    }
}
