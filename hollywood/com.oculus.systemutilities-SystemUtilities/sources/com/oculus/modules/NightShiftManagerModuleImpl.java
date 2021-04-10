package com.oculus.modules;

import android.content.Context;
import android.util.Log;
import com.oculus.modules.codegen.NightShiftManagerModule;
import com.oculus.modules.codegen.Resolver;
import com.oculus.os.NightShiftController;
import com.oculus.panellib.SystraceBlock;

public class NightShiftManagerModuleImpl extends NightShiftManagerModule {
    private static final String TAG = NightShiftManagerModuleImpl.class.getSimpleName();
    private final NightShiftController mNightShiftController;

    public NightShiftManagerModuleImpl(Context context) {
        this.mNightShiftController = new NightShiftController(context);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.NightShiftManagerModule
    public void getNightShiftSettingsImpl(Resolver<NightShiftManagerModule.NightShiftData> resolver) {
        SystraceBlock block = new SystraceBlock(TAG, "getNightShiftSettingsImpl");
        Throwable th = null;
        try {
            NightShiftManagerModule.NightShiftData nightShiftSettings = new NightShiftManagerModule.NightShiftData();
            nightShiftSettings.active = this.mNightShiftController.isActivated();
            nightShiftSettings.autoMode = (double) this.mNightShiftController.getAutoMode();
            nightShiftSettings.customStartTimeMinute = (double) this.mNightShiftController.getCustomStartTime();
            nightShiftSettings.customEndTimeMinute = (double) this.mNightShiftController.getCustomEndTime();
            resolver.resolve(nightShiftSettings);
        } catch (Exception e) {
            Log.e(TAG, "Failed to get Night Shift settings from the OS: " + e.getMessage());
            resolver.reject(e);
        }
        if (block == null) {
            return;
        }
        if (0 != 0) {
            try {
                block.close();
                return;
            } catch (Throwable th2) {
                th.addSuppressed(th2);
                return;
            }
        } else {
            block.close();
            return;
        }
        if (block != null) {
            if (th != null) {
                try {
                    block.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
            } else {
                block.close();
            }
        }
        throw th;
        throw th;
        try {
        } catch (Throwable th4) {
            th = r3;
            th = th4;
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.NightShiftManagerModule
    public void setNightShiftActiveImpl(boolean active, Resolver<Boolean> resolver) {
        boolean success = this.mNightShiftController.setActivated(active);
        Log.d(TAG, "Setting Night Shift active state: " + String.valueOf(active));
        if (!success) {
            Log.e(TAG, "Failed to activate Night Shift. Controller transaction was unsuccessful.");
        }
        resolver.resolve(Boolean.valueOf(success));
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.NightShiftManagerModule
    public void setNightShiftAutoModeImpl(double mode, Resolver<Boolean> resolver) {
        boolean success = this.mNightShiftController.setAutoMode((int) mode);
        Log.d(TAG, "Setting Night Shift auto mode: " + String.valueOf((int) mode));
        if (!success) {
            Log.e(TAG, "Failed to set Night Shift Auto Mode. Controller transaction was unsuccessful.");
        }
        resolver.resolve(Boolean.valueOf(success));
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.NightShiftManagerModule
    public void setNightShiftStartTimeImpl(double time, Resolver<Boolean> resolver) {
        int startInt = (int) time;
        boolean success = this.mNightShiftController.setCustomStartTime(startInt);
        if (!success) {
            Log.e(TAG, "Failed to set Night Shift Start Time. Attempted to set : " + String.valueOf(startInt) + " minutes. " + "Controller transaction was unsuccessful.");
        }
        resolver.resolve(Boolean.valueOf(success));
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.NightShiftManagerModule
    public void setNightShiftEndTimeImpl(double time, Resolver<Boolean> resolver) {
        int endInt = (int) time;
        boolean success = this.mNightShiftController.setCustomEndTime(endInt);
        if (!success) {
            Log.e(TAG, "Failed to set Night Shift End Time. Attempted to set : " + String.valueOf(endInt) + " minutes. " + "Controller transaction was unsuccessful.");
        }
        resolver.resolve(Boolean.valueOf(success));
    }
}
