package com.oculus.companion.server;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SecureStorage {
    private static final char[] HEX_TABLE = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static final Set<String> secureData = new HashSet(Arrays.asList("secret_key", "access_token"));
    private final Context context;

    private static String bytesToHex(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (byte b : bArr) {
            sb.append(HEX_TABLE[(b >> 4) & 15]);
            sb.append(HEX_TABLE[b & 15]);
        }
        return sb.toString();
    }

    public SecureStorage(Context context2) {
        this.context = context2;
    }

    private SharedPreferences getSharedPreferences(String str) {
        if (secureData.contains(str)) {
            return this.context.getSharedPreferences("com.oculus.companion.identity_secure", 4);
        }
        return this.context.createDeviceProtectedStorageContext().getSharedPreferences("com.oculus.companion.identity", 0);
    }

    public void storeValue(String str, String str2) {
        SharedPreferences.Editor edit = getSharedPreferences(str).edit();
        edit.putString(str, str2);
        edit.commit();
    }

    public void storeValue(String str, boolean z) {
        SharedPreferences.Editor edit = getSharedPreferences(str).edit();
        edit.putBoolean(str, z);
        edit.commit();
    }

    public void storeValue(String str, byte[] bArr) {
        storeValue(str, bytesToHex(bArr));
    }

    public void storeValue(String str, int i) {
        SharedPreferences.Editor edit = getSharedPreferences(str).edit();
        edit.putInt(str, i);
        edit.commit();
    }

    public byte[] getValue(String str) {
        return CompanionUtil.hexToBytes(getSharedPreferences(str).getString(str, ""));
    }

    public int getIntValue(String str) {
        return getSharedPreferences(str).getInt(str, 0);
    }

    public String getStringValue(String str) {
        return getSharedPreferences(str).getString(str, "");
    }

    public boolean getBooleanValue(String str) {
        return getSharedPreferences(str).getBoolean(str, false);
    }

    public boolean hasKey(String str) {
        return getSharedPreferences(str).contains(str);
    }
}
