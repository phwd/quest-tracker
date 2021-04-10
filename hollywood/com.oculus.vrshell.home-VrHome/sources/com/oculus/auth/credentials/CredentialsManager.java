package com.oculus.auth.credentials;

import javax.annotation.Nullable;

public interface CredentialsManager {
    @Nullable
    Credentials getCredentials();
}
