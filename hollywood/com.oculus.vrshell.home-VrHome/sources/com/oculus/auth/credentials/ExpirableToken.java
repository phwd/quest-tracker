package com.oculus.auth.credentials;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class ExpirableToken {
    public final BootCountedRealtime mExpirationTime;
    public final String mValue;

    private ExpirableToken(String value, BootCountedRealtime expirationTime) {
        this.mValue = value;
        this.mExpirationTime = expirationTime;
    }

    public static ExpirableToken of(String value, BootCountedRealtime expirationTime) {
        return new ExpirableToken(value, expirationTime);
    }
}
