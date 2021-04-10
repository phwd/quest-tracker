package com.oculus.authapi;

import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
public final class AuthAuthWithOculusEmailAndPasswordForAccountLinkingResult {
    @Nullable
    public final String mAccessToken;
    @Nullable
    public final String mUserId;
}
