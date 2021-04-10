package com.oculus.deviceauthserver;

import com.android.internal.annotations.Immutable;

/* access modifiers changed from: package-private */
@Immutable
public final class DeviceCredentials {
    final String mEntId;
    final DeviceToken mToken;

    DeviceCredentials(DeviceToken token, String entId) {
        this.mToken = token;
        this.mEntId = entId;
    }
}
