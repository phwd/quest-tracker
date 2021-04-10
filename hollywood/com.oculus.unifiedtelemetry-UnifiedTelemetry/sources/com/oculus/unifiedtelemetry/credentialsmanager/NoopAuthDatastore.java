package com.oculus.unifiedtelemetry.credentialsmanager;

import com.oculus.auth.credentials.Credentials;
import com.oculus.auth.storage.AuthDatastore;
import javax.annotation.Nullable;

public class NoopAuthDatastore implements AuthDatastore {
    @Override // com.oculus.auth.storage.AuthDatastore
    public final void clear() {
    }

    @Override // com.oculus.auth.storage.AuthDatastore
    public final void clearCredentials() {
    }

    @Override // com.oculus.auth.storage.AuthDatastore
    @Nullable
    public final Credentials getCredentials() {
        return null;
    }

    @Override // com.oculus.auth.storage.AuthDatastore
    public final long getCredentialsUpdateTimeMillis() {
        return 0;
    }

    @Override // com.oculus.auth.storage.AuthDatastore
    @Nullable
    public final String getUserId() {
        return null;
    }

    @Override // com.oculus.auth.storage.AuthDatastore
    public final void storeCredentials(Credentials credentials) {
    }
}
