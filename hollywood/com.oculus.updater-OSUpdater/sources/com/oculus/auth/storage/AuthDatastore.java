package com.oculus.auth.storage;

import com.facebook.inject.RequiresBinding;

@RequiresBinding
public interface AuthDatastore {
    void clearCredentials();

    long getCredentialsUpdateTimeMillis();
}
