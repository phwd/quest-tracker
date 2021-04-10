package com.oculus.dsatauth;

import bolts.Task;
import bolts.TaskCompletionSource;
import com.facebook.inject.ApplicationScopeClassInit;
import com.facebook.inject.ApplicationScoped;
import com.facebook.inject.FbInjector;
import com.facebook.inject.InjectionContext;
import com.facebook.inject.InjectorLike;
import com.facebook.inject.Lazy;
import com.facebook.inject.UltralightSingletonProvider;
import com.facebook.ultralight.UL;
import com.oculus.auth.credentials.Credentials;
import com.oculus.auth.credentials.CredentialsModule;
import com.oculus.authapi.AuthCredentials;
import com.oculus.authapi.AuthError;
import com.oculus.authapi.AuthResultCallback;
import com.oculus.authapi.OVRAuth;
import com.oculus.authapi.inject.OVRAuthModule;
import com.oculus.dsatauth.DsatauthModule;
import javax.inject.Provider;

@ApplicationScoped
public class DsatFetcher {
    private static volatile DsatFetcher $ul_$xXXcom_oculus_dsatauth_DsatFetcher$xXXINSTANCE;
    private InjectionContext $ul_mInjectionContext;
    private final Provider<Credentials> mCredentialsProvider;
    private volatile boolean mIsDeviceMissingIdentity;

    public static final Provider $ul_$xXXjavax_inject_Provider$x3Ccom_oculus_dsatauth_DsatFetcher$x3E$xXXACCESS_METHOD(InjectorLike $ul_injector) {
        return UltralightSingletonProvider.get(DsatauthModule.UL_id.$ul_$xXXcom_oculus_dsatauth_DsatFetcher$xXXBINDING_ID, $ul_injector);
    }

    public static final Lazy $ul_$xXXcom_facebook_inject_Lazy$x3Ccom_oculus_dsatauth_DsatFetcher$x3E$xXXACCESS_METHOD(InjectorLike $ul_injector) {
        return UltralightSingletonProvider.get(DsatauthModule.UL_id.$ul_$xXXcom_oculus_dsatauth_DsatFetcher$xXXBINDING_ID, $ul_injector);
    }

    public static final DsatFetcher $ul_$xXXcom_oculus_dsatauth_DsatFetcher$xXXACCESS_METHOD(InjectorLike $ul_injector) {
        return (DsatFetcher) UL.factorymap.get(DsatauthModule.UL_id.$ul_$xXXcom_oculus_dsatauth_DsatFetcher$xXXBINDING_ID, $ul_injector);
    }

    public static final DsatFetcher $ul_$xXXcom_oculus_dsatauth_DsatFetcher$xXXFACTORY_METHOD(InjectorLike $ul_injector) {
        if ($ul_$xXXcom_oculus_dsatauth_DsatFetcher$xXXINSTANCE == null) {
            synchronized (DsatFetcher.class) {
                ApplicationScopeClassInit state = ApplicationScopeClassInit.start($ul_$xXXcom_oculus_dsatauth_DsatFetcher$xXXINSTANCE, $ul_injector);
                if (state != null) {
                    try {
                        $ul_$xXXcom_oculus_dsatauth_DsatFetcher$xXXINSTANCE = new DsatFetcher($ul_injector.getApplicationInjector());
                    } finally {
                        state.finish();
                    }
                }
            }
        }
        return $ul_$xXXcom_oculus_dsatauth_DsatFetcher$xXXINSTANCE;
    }

    public DsatFetcher(InjectorLike $ul_injector) {
        this.$ul_mInjectionContext = new InjectionContext(1, $ul_injector);
        this.mCredentialsProvider = CredentialsModule.$ul_$xXXjavax_inject_Provider$x3Ccom_oculus_auth_credentials_Credentials$x3E$xXXACCESS_METHOD($ul_injector);
    }

    public Task<String> fetchDsatElseRegularTokenAsync() {
        final TaskCompletionSource<String> completion = new TaskCompletionSource<>();
        if (this.mIsDeviceMissingIdentity) {
            onNoDeviceIdentity(completion);
        } else {
            ((OVRAuth) FbInjector.lazyInstance(0, OVRAuthModule.UL_id.$ul_$xXXcom_oculus_authapi_OVRAuth$xXXBINDING_ID, this.$ul_mInjectionContext)).fetchHorizonDeviceScopedCredentials(new AuthResultCallback<AuthCredentials, AuthError>() {
                /* class com.oculus.dsatauth.DsatFetcher.AnonymousClass1 */

                public void onResult(AuthCredentials result) {
                    completion.setResult(result.getAccessToken());
                }

                @Override // com.oculus.authapi.AuthResultCallback
                public void onError(AuthError error) {
                    if (error.getErrorCode() == -16) {
                        DsatFetcher.this.mIsDeviceMissingIdentity = true;
                        DsatFetcher.this.onNoDeviceIdentity(completion);
                        return;
                    }
                    completion.setError(error);
                }
            });
        }
        return completion.getTask();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void onNoDeviceIdentity(TaskCompletionSource<String> completion) {
        Credentials credentials = this.mCredentialsProvider.get();
        if (credentials == null) {
            completion.setResult(null);
        } else {
            completion.setResult(credentials.getAccessToken());
        }
    }
}
