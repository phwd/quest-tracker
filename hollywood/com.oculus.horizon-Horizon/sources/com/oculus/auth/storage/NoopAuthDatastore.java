package com.oculus.auth.storage;

import com.oculus.auth.credentials.Credentials;
import javax.annotation.Nullable;

public class NoopAuthDatastore implements AuthDatastore {
    @Override // com.oculus.auth.storage.AuthDatastore
    public void clear() {
    }

    @Override // com.oculus.auth.storage.AuthDatastore
    public void clearCredentials() {
    }

    @Override // com.oculus.auth.storage.AuthDatastore
    @Nullable
    public Credentials getCredentials() {
        return null;
    }

    @Override // com.oculus.auth.storage.AuthDatastore
    public long getCredentialsUpdateTimeMillis() {
        return 0;
    }

    @Override // com.oculus.auth.storage.AuthDatastore
    @Nullable
    public String getUserId() {
        return null;
    }

    @Override // com.oculus.auth.storage.AuthDatastore
    public void storeCredentials(Credentials credentials) {
    }
}
