package com.oculus.auth.storage;

import com.facebook.inject.RequiresBinding;
import com.oculus.auth.credentials.Credentials;
import javax.annotation.Nullable;

@RequiresBinding
public interface AuthDatastore {
    void clear();

    void clearCredentials();

    @Nullable
    Credentials getCredentials();

    long getCredentialsUpdateTimeMillis();

    @Nullable
    String getUserId();

    void storeCredentials(Credentials credentials);
}
