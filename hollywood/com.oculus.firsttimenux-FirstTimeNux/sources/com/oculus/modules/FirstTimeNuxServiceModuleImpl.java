package com.oculus.modules;

import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.oculus.modules.codegen.FirstTimeNuxServiceModule;
import com.oculus.modules.codegen.Resolver;
import com.oculus.os.PreferencesManager;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.MessageDigest;

public class FirstTimeNuxServiceModuleImpl extends FirstTimeNuxServiceModule {
    private static final String FIRST_TIME_NUX_SERVICE_TYPE = "FIRST_TIME_NUX";
    private static final String LOCAL_ACCOUNT_MODE_ENABLED_KEY = "local_account_mode_enabled";
    private static final String PAIRING_CODE_PREF_KEY = "pairing_code";
    private static final String TAG = FirstTimeNUXSettingsModuleImpl.class.getSimpleName();

    public FirstTimeNuxServiceModuleImpl() {
        calculateAndSavePairingCode();
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.FirstTimeNuxServiceModule
    public String getPairingCodeImpl() {
        Pair<Boolean, String> pref = new PreferencesManager().getString(PAIRING_CODE_PREF_KEY);
        if (((Boolean) pref.first).booleanValue() && !TextUtils.isEmpty((CharSequence) pref.second)) {
            return (String) pref.second;
        }
        String pairingCode = calculatePairingCode();
        savePairingCode(pairingCode);
        return pairingCode;
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.FirstTimeNuxServiceModule
    public boolean isLocalAccountModeEnabledImpl() {
        Pair<Boolean, Boolean> pref = new PreferencesManager().getBoolean(LOCAL_ACCOUNT_MODE_ENABLED_KEY);
        return ((Boolean) pref.first).booleanValue() && ((Boolean) pref.second).booleanValue();
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.FirstTimeNuxServiceModule
    public void onAccessTokenImpl(String accessToken, Resolver<Void> resolver) {
        resolver.resolve(null);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.FirstTimeNuxServiceModule
    public String marshallJSConstantServiceType() {
        return FIRST_TIME_NUX_SERVICE_TYPE;
    }

    private String calculatePairingCode() {
        try {
            byte[] hashSum = MessageDigest.getInstance("SHA-256").digest(Build.SERIAL.getBytes());
            ByteBuffer buffer = ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN);
            buffer.put(hashSum, 0, 4);
            return String.format("%05d", Integer.valueOf((int) ((long) (((double) buffer.getLong(0)) % Math.pow(10.0d, 5.0d)))));
        } catch (Exception e) {
            Log.e(TAG, "Error calculating the headset code", e);
            return "";
        }
    }

    private void savePairingCode(String pairingCode) {
        PreferencesManager pm = new PreferencesManager();
        Pair<Boolean, String> pref = pm.getString(PAIRING_CODE_PREF_KEY);
        if (((Boolean) pref.first).booleanValue() && TextUtils.isEmpty((CharSequence) pref.second)) {
            pm.set(PAIRING_CODE_PREF_KEY, pairingCode);
        }
    }

    private void calculateAndSavePairingCode() {
        String pairingCode = calculatePairingCode();
        Log.d("MY_TEST_TAG", pairingCode);
        savePairingCode(pairingCode);
    }
}
