package com.oculus.certificatepinning;

import X.AnonymousClass006;
import android.annotation.SuppressLint;
import okhttp3.CertificatePinner;

public class FbCertificatePinnerFactory {
    public static final String[] FB_PINNED_DOMAINS = {"facebook-hardware.com", "facebook.com", "fbcdn.net", "fbsbx.com", "freebasics.com", "internet.org", "instagram.com", "oculus.com", "whatsapp.com"};
    public static final String SHA_256_PIN_PREFIX = "sha256/";

    public static void addPinsToBuilder(CertificatePinner.Builder builder, String[] strArr) {
        for (String str : strArr) {
            String[] strArr2 = FB_PINNED_DOMAINS;
            for (String str2 : strArr2) {
                builder.add(str2, AnonymousClass006.A07(SHA_256_PIN_PREFIX, str));
                builder.add(AnonymousClass006.A07(CertificatePinner.Pin.WILDCARD, str2), AnonymousClass006.A07(SHA_256_PIN_PREFIX, str));
            }
        }
    }

    public static CertificatePinner createPinnerWithoutFallback() {
        CertificatePinner.Builder builder = new CertificatePinner.Builder();
        addPinsToBuilder(builder, CertificatePinningData.FB_CERT_SHA256_PINS);
        return builder.build();
    }

    @SuppressLint({"BadMethodUse-java.lang.System.currentTimeMillis"})
    public static CertificatePinner createWithRampart(long j) {
        CertificatePinner.Builder builder = new CertificatePinner.Builder();
        if (j >= System.currentTimeMillis() - CertificatePinningData.PINNING_EXPIRATION_MS) {
            addPinsToBuilder(builder, CertificatePinningData.FB_CERT_SHA256_PINS);
        }
        addPinsToBuilder(builder, CertificatePinningData.FB_INTERNAL_CERT_SHA256_PINS);
        return builder.build();
    }

    @SuppressLint({"BadMethodUse-java.lang.System.currentTimeMillis"})
    public static CertificatePinner create(long j) {
        if (j < System.currentTimeMillis() - CertificatePinningData.PINNING_EXPIRATION_MS) {
            return CertificatePinner.DEFAULT;
        }
        return createPinnerWithoutFallback();
    }
}
