package com.oculus.auth.credentials;

public interface CredentialsChangedHandler {
    void onCredentialsChanged();

    void onDeviceScopedCredentialsChanged();
}
