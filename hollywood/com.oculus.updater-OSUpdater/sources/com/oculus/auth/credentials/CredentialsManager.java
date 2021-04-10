package com.oculus.auth.credentials;

import com.facebook.inject.RequiresBinding;
import javax.annotation.Nullable;

@RequiresBinding
public interface CredentialsManager {
    @Nullable
    Credentials getCredentials();
}
