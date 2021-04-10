package com.oculus.modules;

import X.AnonymousClass006;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.oculus.modules.codegen.Resolver;
import com.oculus.modules.codegen.SettingsManagerModule;
import com.oculus.os.SettingsManager;
import com.oculus.os.SettingsObserverCallback;
import java.util.HashMap;

public class SettingsManagerModuleImpl extends SettingsManagerModule implements SettingsObserverCallback {
    public static final String TAG = "SettingsManagerModuleImpl";
    public final Handler mHandler = new Handler(Looper.getMainLooper());
    public long mNextSubscriptionKey = 0;
    public final SettingsManager mSettingsManager = new SettingsManager();
    public final HashMap<String, Long> mSubscriptionCounts = new HashMap<>();

    @Override // com.oculus.modules.codegen.SettingsManagerModule
    public void getBooleanImpl(String str, boolean z, Resolver<Boolean> resolver) {
        resolver.resolve(Boolean.valueOf(this.mSettingsManager.getBoolean(str, z)));
    }

    @Override // com.oculus.modules.codegen.SettingsManagerModule
    public void getDoubleImpl(String str, double d, Resolver<Double> resolver) {
        resolver.resolve(Double.valueOf(this.mSettingsManager.getDouble(str, d)));
    }

    @Override // com.oculus.modules.codegen.SettingsManagerModule
    public void getFloatImpl(String str, double d, Resolver<Double> resolver) {
        resolver.resolve(new Double((double) this.mSettingsManager.getFloat(str, (float) d)));
    }

    @Override // com.oculus.modules.codegen.SettingsManagerModule
    public void getIntImpl(String str, double d, Resolver<Double> resolver) {
        resolver.resolve(new Double((double) this.mSettingsManager.getInt(str, (int) d)));
    }

    @Override // com.oculus.modules.codegen.SettingsManagerModule
    public void getLongImpl(String str, double d, Resolver<Double> resolver) {
        resolver.resolve(new Double((double) this.mSettingsManager.getLong(str, (long) d)));
    }

    @Override // com.oculus.modules.codegen.SettingsManagerModule
    public void getStringImpl(String str, String str2, Resolver<String> resolver) {
        resolver.resolve(this.mSettingsManager.getString(str, str2));
    }

    @Override // com.oculus.modules.codegen.SettingsManagerModule
    public void hasDefinedValueImpl(String str, Resolver<Boolean> resolver) {
        boolean z = false;
        if (this.mSettingsManager.getString(str, (String) null) != null) {
            z = true;
        }
        resolver.resolve(Boolean.valueOf(z));
    }

    public void onSettingChange(String str) {
        SettingsManagerModule.SettingUpdate settingUpdate = new SettingsManagerModule.SettingUpdate();
        settingUpdate.name = str;
        emitOnUpdated(settingUpdate);
    }

    @Override // com.oculus.modules.codegen.SettingsManagerModule
    public void setBooleanImpl(String str, boolean z, Resolver<Void> resolver) {
        if (!this.mSettingsManager.setBoolean(str, z)) {
            StringBuilder sb = new StringBuilder("Failed to set boolean value ");
            sb.append(z);
            sb.append(" for key \"");
            sb.append(str);
            sb.append("\"");
            String obj = sb.toString();
            Log.e(TAG, obj);
            resolver.reject(new Exception(obj));
            return;
        }
        resolver.resolve(null);
    }

    @Override // com.oculus.modules.codegen.SettingsManagerModule
    public void setDoubleImpl(String str, double d, Resolver<Void> resolver) {
        if (!this.mSettingsManager.setDouble(str, d)) {
            StringBuilder sb = new StringBuilder("Failed to set double value ");
            sb.append(d);
            sb.append(" for key \"");
            sb.append(str);
            sb.append("\"");
            String obj = sb.toString();
            Log.e(TAG, obj);
            resolver.reject(new Exception(obj));
            return;
        }
        resolver.resolve(null);
    }

    @Override // com.oculus.modules.codegen.SettingsManagerModule
    public void setFloatImpl(String str, double d, Resolver<Void> resolver) {
        if (!this.mSettingsManager.setFloat(str, (float) d)) {
            StringBuilder sb = new StringBuilder("Failed to set boolean value ");
            sb.append(d);
            sb.append(" for key \"");
            sb.append(str);
            sb.append("\"");
            String obj = sb.toString();
            Log.e(TAG, obj);
            resolver.reject(new Exception(obj));
            return;
        }
        resolver.resolve(null);
    }

    @Override // com.oculus.modules.codegen.SettingsManagerModule
    public void setIntImpl(String str, double d, Resolver<Void> resolver) {
        if (!this.mSettingsManager.setInt(str, (int) d)) {
            StringBuilder sb = new StringBuilder("Failed to set int value ");
            sb.append(d);
            sb.append(" for key \"");
            sb.append(str);
            sb.append("\"");
            String obj = sb.toString();
            Log.e(TAG, obj);
            resolver.reject(new Exception(obj));
            return;
        }
        resolver.resolve(null);
    }

    @Override // com.oculus.modules.codegen.SettingsManagerModule
    public void setLongImpl(String str, double d, Resolver<Void> resolver) {
        if (!this.mSettingsManager.setLong(str, (long) d)) {
            StringBuilder sb = new StringBuilder("Failed to set long value ");
            sb.append(d);
            sb.append(" for key \"");
            sb.append(str);
            sb.append("\"");
            String obj = sb.toString();
            Log.e(TAG, obj);
            resolver.reject(new Exception(obj));
            return;
        }
        resolver.resolve(null);
    }

    @Override // com.oculus.modules.codegen.SettingsManagerModule
    public void setStringImpl(String str, String str2, Resolver<Void> resolver) {
        if (!this.mSettingsManager.setString(str, str2)) {
            String A0C = AnonymousClass006.A0C("Failed to set String value ", str2, " for key \"", str, "\"");
            Log.e(TAG, A0C);
            resolver.reject(new Exception(A0C));
            return;
        }
        resolver.resolve(null);
    }

    @Override // com.oculus.modules.codegen.SettingsManagerModule
    public void shutdownModule() {
        for (String str : this.mSubscriptionCounts.keySet()) {
            this.mSettingsManager.unregisterSettingsObserver(str, this);
        }
    }

    @Override // com.oculus.modules.codegen.SettingsManagerModule
    public void subscribeImpl(String str, Resolver<Void> resolver) {
        Long l = this.mSubscriptionCounts.get(str);
        if (l == null) {
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
            Log.e(TAG, AnonymousClass006.A09("No observer to unregister for key \"", str, "\""));
        } else {
            long longValue = l.longValue();
            if (longValue <= 1) {
                this.mSettingsManager.unregisterSettingsObserver(str, this);
                this.mSubscriptionCounts.remove(str);
            } else {
                this.mSubscriptionCounts.put(str, Long.valueOf(longValue - 1));
            }
        }
        resolver.resolve(null);
    }
}
