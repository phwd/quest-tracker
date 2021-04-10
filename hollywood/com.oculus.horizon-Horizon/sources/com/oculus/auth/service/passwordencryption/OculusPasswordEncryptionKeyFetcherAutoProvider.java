package com.oculus.auth.service.passwordencryption;

import X.AnonymousClass0J3;
import X.AnonymousClass117;
import com.facebook.annotations.Generated;
import com.oculus.http.core.interceptor.AuthorizationInterceptor;
import retrofit.RestAdapter;

@Generated({"By: InjectorProcessor"})
public class OculusPasswordEncryptionKeyFetcherAutoProvider extends AnonymousClass0J3<OculusPasswordEncryptionKeyFetcher> {
    public OculusPasswordEncryptionKeyFetcher get() {
        return new OculusPasswordEncryptionKeyFetcher(this, (RestAdapter) AnonymousClass117.A00(AuthorizationInterceptor.HTTP_STATUS_UNAUTHORIZED, this));
    }
}
