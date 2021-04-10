package com.oculus.auth.storage;

import com.oculus.auth.credentials.Credentials;
import javax.annotation.Nullable;

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
