package com.facebook.netlite.certificatepinning.okhttp;

import android.annotation.SuppressLint;
import com.facebook.netlite.certificatepinning.internal.CertificatePinningData;
import okhttp3.CertificatePinner;

public class FbCertificatePinnerFactory {
    private static final String[] FB_PINNED_DOMAINS = {"facebook-hardware.com", "facebook.com", "facebookvirtualassistant.com", "facebookstudy.com", "fbcdn.net", "fbsbx.com", "freebasics.com", "internet.org", "instagram.com", "oculus.com", "viewpointsfromfacebook.com", "whatsapp.com"};

    @SuppressLint({"BadMethodUse-java.lang.System.currentTimeMillis"})
    public static CertificatePinner create(long appBuildTimeInMs) {
        if (appBuildTimeInMs < System.currentTimeMillis() - 31536000000L) {
            return CertificatePinner.DEFAULT;
        }
        return createPinnerWithoutFallback();
    }

    public static CertificatePinner createPinnerWithoutFallback() {
        CertificatePinner.Builder builder = new CertificatePinner.Builder();
        addPinsToBuilder(builder, CertificatePinningData.FB_CERT_SHA256_PINS);
        return builder.build();
    }

    private static void addPinsToBuilder(CertificatePinner.Builder builder, String[] pins) {
        for (String pin : pins) {
            String[] strArr = FB_PINNED_DOMAINS;
            for (String domain : strArr) {
                builder.add(domain, "sha256/" + pin);
                builder.add("*." + domain, "sha256/" + pin);
            }
        }
    }
}
