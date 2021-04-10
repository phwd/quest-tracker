package com.oculus.authapi;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class AuthFbLoginResult {
    public final String mAccessToken;
    public final String mUid;
}
