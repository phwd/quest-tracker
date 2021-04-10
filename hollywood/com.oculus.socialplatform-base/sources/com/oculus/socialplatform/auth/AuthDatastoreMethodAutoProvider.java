package com.oculus.socialplatform.auth;

import X.AnonymousClass0VG;
import com.facebook.annotations.Generated;
import com.oculus.auth.storage.AuthDatastore;

@Generated({"By: InjectorProcessor"})
public class AuthDatastoreMethodAutoProvider extends AnonymousClass0VG<AuthDatastore> {
    public AuthDatastore get() {
        return new SocialPlatformAuthDatastore();
    }
}
