package com.oculus.deviceauthserver;

import com.android.internal.annotations.Immutable;

/* access modifiers changed from: package-private */
@Immutable
public final class DeviceToken {
    final BootCountedRealtime mExpirationTime;
    final String mValue;

    DeviceToken(String value, BootCountedRealtime expirationTime) {
        this.mValue = value;
        this.mExpirationTime = expirationTime;
    }
}
