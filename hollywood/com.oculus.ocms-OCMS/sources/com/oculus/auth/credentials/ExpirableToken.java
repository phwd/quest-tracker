package com.oculus.auth.credentials;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class ExpirableToken {
    public final BootCountedRealtime mExpirationTime;
    public final String mValue;

    private ExpirableToken(String str, BootCountedRealtime bootCountedRealtime) {
        this.mValue = str;
        this.mExpirationTime = bootCountedRealtime;
    }

    public static ExpirableToken of(String str, BootCountedRealtime bootCountedRealtime) {
        return new ExpirableToken(str, bootCountedRealtime);
    }
}
