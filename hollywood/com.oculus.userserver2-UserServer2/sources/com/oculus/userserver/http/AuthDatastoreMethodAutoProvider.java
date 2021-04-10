package com.oculus.userserver.http;

import X.AbstractC0029Ba;
import com.facebook.annotations.Generated;
import com.oculus.auth.credentials.Credentials;
import com.oculus.auth.storage.AuthDatastore;
import javax.annotation.Nullable;

@Generated({"By: InjectorProcessor"})
public class AuthDatastoreMethodAutoProvider extends AbstractC0029Ba<AuthDatastore> {
    public final Object get() {
        return new AuthDatastore() {
            /* class com.oculus.userserver.http.UserServerHttpModule.AnonymousClass2 */

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
        };
    }
}
