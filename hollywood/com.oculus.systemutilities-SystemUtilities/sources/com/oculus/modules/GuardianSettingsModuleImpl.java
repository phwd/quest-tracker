package com.oculus.modules;

import android.content.Context;
import android.util.Log;
import com.oculus.modules.codegen.GuardianSettingsModule;
import com.oculus.os.SettingsManager;

public class GuardianSettingsModuleImpl extends GuardianSettingsModule {
    private static final String CAMERA_LINE_FREQUENCY_DETECTION = "oculus.software.camera.line_frequency_detection";
    private static final int DEFAULT_ELECTRIC_GRID_LINE_FREQUENCY = 0;
    private static final String TAG = GuardianSettingsModuleImpl.class.getSimpleName();
    private Context mContext = null;
    private final SettingsManager mSettingsManager = new SettingsManager();

    public GuardianSettingsModuleImpl(Context context) {
        this.mContext = context;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0055, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0056, code lost:
        r3 = r2;
        r2 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x006a, code lost:
        r2 = th;
     */
    @Override // com.oculus.modules.codegen.GuardianSettingsModule
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void getElectricalGridValueImpl(com.oculus.modules.codegen.Resolver<java.lang.Double> r8) {
        /*
        // Method dump skipped, instructions count: 108
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.modules.GuardianSettingsModuleImpl.getElectricalGridValueImpl(com.oculus.modules.codegen.Resolver):void");
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.GuardianSettingsModule
    public void setElectricalGridValueImpl(double frequency) {
        if (!this.mContext.getPackageManager().hasSystemFeature(CAMERA_LINE_FREQUENCY_DETECTION)) {
            Log.d(TAG, "Error: Line frequency detection feature not supported, but setElectricalGridValueImpl was called!");
            return;
        }
        int frequencyInt = (int) frequency;
        if (frequencyInt == -1 || frequencyInt == 50 || frequencyInt == 60) {
            this.mSettingsManager.setInt("electric_grid_line_frequency", frequencyInt);
            Log.d(TAG, "setElectricalGridFrequency value: " + frequencyInt);
            return;
        }
        Log.d(TAG, "setElectricalGridValue got bad value! " + frequency);
    }
}
