package com.oculus.dsatauth;

import X.AbstractC03180ld;
import X.AnonymousClass0Qj;
import X.AnonymousClass0RE;
import X.AnonymousClass0VB;
import X.AnonymousClass0VF;
import X.AnonymousClass0lg;
import X.AnonymousClass1TK;
import bolts.Task;
import bolts.TaskCompletionSource;
import com.facebook.inject.ApplicationScoped;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.auth.credentials.Credentials;
import com.oculus.auth.credentials.CredentialsModule;
import com.oculus.authapi.AuthCredentials;
import com.oculus.authapi.AuthError;
import com.oculus.authapi.AuthResultCallback;
import com.oculus.authapi.OVRAuth;
import javax.inject.Provider;

@Dependencies({"_UL__ULSEP_com_oculus_authapi_OVRAuth_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_auth_credentials_Credentials_ULSEP_BINDING_ID"})
@ApplicationScoped
public class DsatFetcher {
    public static volatile DsatFetcher _UL__ULSEP_com_oculus_dsatauth_DsatFetcher_ULSEP_INSTANCE;
    public AnonymousClass0RE _UL_mInjectionContext;
    @Inject
    public final Provider<Credentials> mCredentialsProvider;
    public volatile boolean mIsDeviceMissingIdentity;

    @AutoGeneratedAccessMethod
    public static final AbstractC03180ld _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_dsatauth_DsatFetcher_ULGT__ULSEP_ACCESS_METHOD(AnonymousClass0lg r2) {
        return new AnonymousClass0VB(103, r2);
    }

    @AutoGeneratedAccessMethod
    public static final DsatFetcher _UL__ULSEP_com_oculus_dsatauth_DsatFetcher_ULSEP_ACCESS_METHOD(AnonymousClass0lg r2) {
        return (DsatFetcher) AnonymousClass1TK.A00(103, r2, null);
    }

    @AutoGeneratedFactoryMethod
    public static final DsatFetcher _UL__ULSEP_com_oculus_dsatauth_DsatFetcher_ULSEP_FACTORY_METHOD(AnonymousClass0lg r4, Object obj) {
        if (_UL__ULSEP_com_oculus_dsatauth_DsatFetcher_ULSEP_INSTANCE == null) {
            synchronized (DsatFetcher.class) {
                AnonymousClass0Qj A00 = AnonymousClass0Qj.A00(_UL__ULSEP_com_oculus_dsatauth_DsatFetcher_ULSEP_INSTANCE, r4);
                if (A00 != null) {
                    try {
                        _UL__ULSEP_com_oculus_dsatauth_DsatFetcher_ULSEP_INSTANCE = new DsatFetcher(r4.getApplicationInjector());
                    } finally {
                        A00.A01();
                    }
                }
            }
        }
        return _UL__ULSEP_com_oculus_dsatauth_DsatFetcher_ULSEP_INSTANCE;
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_dsatauth_DsatFetcher_ULGT__ULSEP_ACCESS_METHOD(AnonymousClass0lg r2) {
        return new AnonymousClass0VB(103, r2);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void onNoDeviceIdentity(TaskCompletionSource<String> taskCompletionSource) {
        String str;
        Credentials credentials = this.mCredentialsProvider.get();
        if (credentials == null) {
            str = null;
        } else {
            str = credentials.mAccessToken;
        }
        taskCompletionSource.setResult(str);
    }

    public Task<String> fetchDsatElseRegularTokenAsync() {
        final TaskCompletionSource<String> taskCompletionSource = new TaskCompletionSource<>();
        if (this.mIsDeviceMissingIdentity) {
            onNoDeviceIdentity(taskCompletionSource);
        } else {
            ((OVRAuth) AnonymousClass0VF.A03(0, 63, this._UL_mInjectionContext)).fetchHorizonDeviceScopedCredentials(new AuthResultCallback<AuthCredentials, AuthError>() {
                /* class com.oculus.dsatauth.DsatFetcher.AnonymousClass1 */

                @Override // com.oculus.authapi.AuthResultCallback
                public void onError(AuthError authError) {
                    if (authError.mErrorCode == -16) {
                        DsatFetcher.this.mIsDeviceMissingIdentity = true;
                        DsatFetcher.this.onNoDeviceIdentity(taskCompletionSource);
                        return;
                    }
                    taskCompletionSource.setError(authError);
                }

                public void onResult(AuthCredentials authCredentials) {
                    taskCompletionSource.setResult(authCredentials.mAccessToken);
                }
            });
        }
        return taskCompletionSource.task;
    }

    @Inject
    public DsatFetcher(AnonymousClass0lg r3) {
        this._UL_mInjectionContext = new AnonymousClass0RE(1, r3);
        this.mCredentialsProvider = CredentialsModule._UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_auth_credentials_Credentials_ULGT__ULSEP_ACCESS_METHOD(r3);
    }
}