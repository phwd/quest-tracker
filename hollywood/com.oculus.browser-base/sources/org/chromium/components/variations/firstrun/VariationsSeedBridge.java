package org.chromium.components.variations.firstrun;

import android.util.Base64;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class VariationsSeedBridge {
    public static String a(String str) {
        return AbstractC3983nz.f10523a.getString(str, "");
    }

    public static void b(int i) {
        AbstractC3364kK0.g("Variations.FirstRunPrefsDebug", i, 5);
    }

    public static void clearFirstRunPrefs() {
        if (!AbstractC3983nz.f10523a.getString("variations_seed_base64", "").isEmpty()) {
            b(4);
        }
        AbstractC3983nz.f10523a.edit().remove("variations_seed_base64").remove("variations_seed_signature").remove("variations_seed_country").remove("variations_seed_date_ms").remove("variations_seed_is_gzip_compressed").apply();
        b(1);
    }

    public static String getVariationsFirstRunSeedCountry() {
        return a("variations_seed_country");
    }

    public static byte[] getVariationsFirstRunSeedData() {
        int i = 2;
        byte[] decode = Base64.decode(a("variations_seed_base64"), 2);
        if (decode.length != 0) {
            i = 3;
        }
        b(i);
        return decode;
    }

    public static long getVariationsFirstRunSeedDate() {
        return AbstractC3983nz.f10523a.getLong("variations_seed_date_ms", 0);
    }

    public static boolean getVariationsFirstRunSeedIsGzipCompressed() {
        return AbstractC3983nz.f10523a.getBoolean("variations_seed_is_gzip_compressed", false);
    }

    public static String getVariationsFirstRunSeedSignature() {
        return a("variations_seed_signature");
    }

    public static boolean hasNativePref() {
        return AbstractC3983nz.f10523a.getBoolean("variations_seed_native_stored", false);
    }

    public static void markVariationsSeedAsStored() {
        AbstractC3983nz.f10523a.edit().putBoolean("variations_seed_native_stored", true).apply();
    }

    public static void setVariationsFirstRunSeed(byte[] bArr, String str, String str2, long j, boolean z) {
        AbstractC3983nz.f10523a.edit().putString("variations_seed_base64", Base64.encodeToString(bArr, 2)).putString("variations_seed_signature", str).putString("variations_seed_country", str2).putLong("variations_seed_date_ms", j).putBoolean("variations_seed_is_gzip_compressed", z).apply();
        b(0);
    }
}
