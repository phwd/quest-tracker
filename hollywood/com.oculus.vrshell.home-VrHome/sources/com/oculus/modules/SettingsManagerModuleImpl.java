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
    private static final String TAG = SettingsManagerModuleImpl.class.getSimpleName();
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private long mNextSubscriptionKey = 0;
    private final SettingsManager mSettingsManager = new SettingsManager();
    private final HashMap<String, Long> mSubscriptionCounts = new HashMap<>();

    @Override // com.oculus.modules.codegen.SettingsManagerModule
    public void shutdownModule() {
        for (String settingName : this.mSubscriptionCounts.keySet()) {
            this.mSettingsManager.unregisterSettingsObserver(settingName, this);
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.SettingsManagerModule
    public void getBooleanImpl(String name, boolean defaultValue, Resolver<Boolean> resolver) {
        boolean value = this.mSettingsManager.getBoolean(name, defaultValue);
        Log.d(TAG, "Got boolean value " + value + " for key \"" + name + "\"");
        resolver.resolve(Boolean.valueOf(value));
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.SettingsManagerModule
    public void getDoubleImpl(String name, double defaultValue, Resolver<Double> resolver) {
        double value = this.mSettingsManager.getDouble(name, defaultValue);
        Log.d(TAG, "Got double value " + value + " for key \"" + name + "\"");
        resolver.resolve(Double.valueOf(value));
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.SettingsManagerModule
    public void getFloatImpl(String name, double defaultValue, Resolver<Double> resolver) {
        float value = this.mSettingsManager.getFloat(name, (float) defaultValue);
        Log.d(TAG, "Got float value " + value + " for key \"" + name + "\"");
        resolver.resolve(new Double((double) value));
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.SettingsManagerModule
    public void getIntImpl(String name, double defaultValue, Resolver<Double> resolver) {
        int value = this.mSettingsManager.getInt(name, (int) defaultValue);
        Log.d(TAG, "Got int value " + value + " for key \"" + name + "\"");
        resolver.resolve(new Double((double) value));
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.SettingsManagerModule
    public void getLongImpl(String name, double defaultValue, Resolver<Double> resolver) {
        long value = this.mSettingsManager.getLong(name, (long) defaultValue);
        Log.d(TAG, "Got long value " + value + " for key \"" + name + "\"");
        resolver.resolve(new Double((double) value));
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.SettingsManagerModule
    public void getStringImpl(String name, String defaultValue, Resolver<String> resolver) {
        String value = this.mSettingsManager.getString(name, defaultValue);
        Log.d(TAG, "Got String value " + value + " for key \"" + name + "\"");
        resolver.resolve(value);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.SettingsManagerModule
    public void hasDefinedValueImpl(String name, Resolver<Boolean> resolver) {
        resolver.resolve(Boolean.valueOf(this.mSettingsManager.getString(name, null) != null));
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.SettingsManagerModule
    public void setBooleanImpl(String name, boolean value, Resolver<Void> resolver) {
        if (!this.mSettingsManager.setBoolean(name, value)) {
            String errorMessage = "Failed to set boolean value " + value + " for key \"" + name + "\"";
            Log.e(TAG, errorMessage);
            resolver.reject(new Exception(errorMessage));
            return;
        }
        resolver.resolve(null);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.SettingsManagerModule
    public void setDoubleImpl(String name, double value, Resolver<Void> resolver) {
        if (!this.mSettingsManager.setDouble(name, value)) {
            String errorMessage = "Failed to set double value " + value + " for key \"" + name + "\"";
            Log.e(TAG, errorMessage);
            resolver.reject(new Exception(errorMessage));
            return;
        }
        resolver.resolve(null);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.SettingsManagerModule
    public void setFloatImpl(String name, double value, Resolver<Void> resolver) {
        if (!this.mSettingsManager.setFloat(name, (float) value)) {
            String errorMessage = "Failed to set boolean value " + value + " for key \"" + name + "\"";
            Log.e(TAG, errorMessage);
            resolver.reject(new Exception(errorMessage));
            return;
        }
        resolver.resolve(null);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.SettingsManagerModule
    public void setIntImpl(String name, double value, Resolver<Void> resolver) {
        if (!this.mSettingsManager.setInt(name, (int) value)) {
            String errorMessage = "Failed to set int value " + value + " for key \"" + name + "\"";
            Log.e(TAG, errorMessage);
            resolver.reject(new Exception(errorMessage));
            return;
        }
        resolver.resolve(null);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.SettingsManagerModule
    public void setLongImpl(String name, double value, Resolver<Void> resolver) {
        if (!this.mSettingsManager.setLong(name, (long) value)) {
            String errorMessage = "Failed to set long value " + value + " for key \"" + name + "\"";
            Log.e(TAG, errorMessage);
            resolver.reject(new Exception(errorMessage));
            return;
        }
        resolver.resolve(null);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.SettingsManagerModule
    public void setStringImpl(String name, String value, Resolver<Void> resolver) {
        if (!this.mSettingsManager.setString(name, value)) {
            String errorMessage = "Failed to set String value " + value + " for key \"" + name + "\"";
            Log.e(TAG, errorMessage);
            resolver.reject(new Exception(errorMessage));
            return;
        }
        resolver.resolve(null);
    }

    @Override // com.oculus.os.SettingsObserverCallback
    public void onSettingChange(String settingName) {
        Log.d(TAG, "Observed change for key \"" + settingName + "\"");
        SettingsManagerModule.SettingUpdate settingUpdate = new SettingsManagerModule.SettingUpdate();
        settingUpdate.name = settingName;
        emitOnUpdated(settingUpdate);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.SettingsManagerModule
    public void subscribeImpl(String name, Resolver<Void> resolver) {
        Long subscriptionCount = this.mSubscriptionCounts.get(name);
        if (subscriptionCount == null) {
            Log.d(TAG, "Registering observer for key \"" + name + "\"");
            this.mSettingsManager.registerSettingsObserver(name, this, this.mHandler);
            subscriptionCount = 0L;
        }
        this.mSubscriptionCounts.put(name, Long.valueOf(subscriptionCount.longValue() + 1));
        resolver.resolve(null);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.SettingsManagerModule
    public void unsubscribeImpl(String name, Resolver<Void> resolver) {
        Long subscriptionCount = this.mSubscriptionCounts.get(name);
        if (subscriptionCount == null) {
            Log.e(TAG, "No observer to unregister for key \"" + name + "\"");
        } else if (subscriptionCount.longValue() <= 1) {
            Log.d(TAG, "Unregistering observer for key \"" + name + "\" with count \"" + subscriptionCount + "\"");
            this.mSettingsManager.unregisterSettingsObserver(name, this);
            this.mSubscriptionCounts.remove(name);
        } else {
            this.mSubscriptionCounts.put(name, Long.valueOf(subscriptionCount.longValue() - 1));
        }
        resolver.resolve(null);
    }
}
