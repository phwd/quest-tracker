package com.facebook.oxygen.common.verification;

import android.annotation.SuppressLint;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class ExtSigVerificationResult {
    public final String reason;
    public final boolean verified;

    private ExtSigVerificationResult(boolean z, String str) {
        this.verified = z;
        this.reason = str;
    }

    public static ExtSigVerificationResult verified() {
        return new ExtSigVerificationResult(true, "Verified.");
    }

    public static ExtSigVerificationResult unverified(String str) {
        return new ExtSigVerificationResult(false, str);
    }

    @SuppressLint({"StringFormatUse"})
    public static ExtSigVerificationResult unverifiedFormat(String str, Object... objArr) {
        return new ExtSigVerificationResult(false, String.format(null, str, objArr));
    }
}
