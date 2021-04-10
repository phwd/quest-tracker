package com.facebook.privacy.datacollection;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class PrivacyDecisionMatchResult {
    public final String message;
    public final boolean status;

    public PrivacyDecisionMatchResult(boolean z, String str) {
        this.status = z;
        this.message = str;
    }
}
