package com.facebook.acra.util;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class ACRAResponse {
    public int mStatus;

    public int getStatusCode() {
        return this.mStatus;
    }

    public void setStatusCode(int i) {
        this.mStatus = i;
    }
}
