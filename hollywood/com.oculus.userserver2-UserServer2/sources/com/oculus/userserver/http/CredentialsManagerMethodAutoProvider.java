package com.oculus.userserver.http;

import X.AbstractC0029Ba;
import com.facebook.annotations.Generated;
import com.oculus.auth.credentials.Credentials;
import com.oculus.auth.credentials.CredentialsManager;
import javax.annotation.Nullable;

@Generated({"By: InjectorProcessor"})
public class CredentialsManagerMethodAutoProvider extends AbstractC0029Ba<CredentialsManager> {
    public final Object get() {
        return new CredentialsManager() {
            /* class com.oculus.userserver.http.UserServerHttpModule.AnonymousClass1 */

            @Override // com.oculus.auth.credentials.CredentialsManager
            @Nullable
            public final Credentials getCredentials() {
                return null;
            }
        };
    }
}
