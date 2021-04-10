package com.oculus.modules;

import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.oculus.modules.codegen.HmdPairingCodeModule;
import com.oculus.os.PreferencesManager;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.MessageDigest;

public class HmdPairingCodeModuleImpl extends HmdPairingCodeModule {
    public static final String HMD_PAIRING_CODE_PREF_KEY = "hmd_pairing_code";
    public static final String TAG = "HmdPairingCodeModuleImpl";

    private String calculateHmdPairingCode() {
        try {
            byte[] digest = MessageDigest.getInstance("SHA-256").digest(Build.SERIAL.getBytes());
            ByteBuffer order = ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN);
            order.put(digest, 0, 4);
            return String.format("%05d", Integer.valueOf((int) ((long) (((double) order.getLong(0)) % Math.pow(10.0d, 5.0d)))));
        } catch (Exception e) {
            Log.e(TAG, "Error calculating the headset code", e);
            return "";
        }
    }

    private void saveHmdPairingCode(String str) {
        PreferencesManager preferencesManager = new PreferencesManager();
        Pair string = preferencesManager.getString(HMD_PAIRING_CODE_PREF_KEY);
        if (((Boolean) string.first).booleanValue() && TextUtils.isEmpty((CharSequence) string.second)) {
            preferencesManager.set(HMD_PAIRING_CODE_PREF_KEY, str);
        }
    }

    @Override // com.oculus.modules.codegen.HmdPairingCodeModule
    public String getHmdPairingCodeImpl() {
        Pair string = new PreferencesManager().getString(HMD_PAIRING_CODE_PREF_KEY);
        if (((Boolean) string.first).booleanValue() && !TextUtils.isEmpty((CharSequence) string.second)) {
            return (String) string.second;
        }
        String calculateHmdPairingCode = calculateHmdPairingCode();
        saveHmdPairingCode(calculateHmdPairingCode);
        return calculateHmdPairingCode;
    }

    public HmdPairingCodeModuleImpl() {
        calculateAndSaveHmdPairingCode();
    }

    private void calculateAndSaveHmdPairingCode() {
        saveHmdPairingCode(calculateHmdPairingCode());
    }
}
