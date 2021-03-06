package com.oculus.auth.components;

import X.AbstractC06640p5;
import X.AnonymousClass0DC;
import X.AnonymousClass0DD;
import X.AnonymousClass0J2;
import X.AnonymousClass0QC;
import X.AnonymousClass0p1;
import X.AnonymousClass117;
import X.C003008y;
import X.C01020Iw;
import com.facebook.AccessToken;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.auth.api.CheckApprovedMachineResponse;
import com.oculus.auth.api.FBAuthMethods;
import com.oculus.auth.api.FBAuthRequest;
import com.oculus.auth.api.FBLoginResponse;
import com.oculus.horizon.api.ApiTaskCallback;
import com.oculus.horizon.api.fbconnect.FBFriendPolicy;
import com.oculus.horizon.fbconnect.FBConnectHelper;
import com.oculus.horizon.fbconnect.FacebookData;
import com.oculus.http.core.base.ApiError;
import com.oculus.http.core.base.ApiException;
import com.oculus.util.constants.OculusConstants;
import javax.annotation.Nullable;
import javax.inject.Provider;

@Dependencies({"_UL__ULSEP_com_oculus_horizon_fbconnect_FBConnectHelper_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_auth_api_FBAuthMethods_ULSEP_BINDING_ID"})
public class FBLoginComponents {
    public AnonymousClass0QC _UL_mInjectionContext;

    @AutoGeneratedAccessMethod
    public static final AnonymousClass0p1 _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_auth_components_FBLoginComponents_ULGT__ULSEP_ACCESS_METHOD(AbstractC06640p5 r2) {
        return new C003008y(216, r2);
    }

    @AutoGeneratedAccessMethod
    public static final FBLoginComponents _UL__ULSEP_com_oculus_auth_components_FBLoginComponents_ULSEP_ACCESS_METHOD(AbstractC06640p5 r1) {
        return (FBLoginComponents) AnonymousClass117.A00(216, r1);
    }

    @AutoGeneratedFactoryMethod
    public static final FBLoginComponents _UL__ULSEP_com_oculus_auth_components_FBLoginComponents_ULSEP_FACTORY_METHOD(AbstractC06640p5 r1) {
        return new FBLoginComponents(r1);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_auth_components_FBLoginComponents_ULGT__ULSEP_ACCESS_METHOD(AbstractC06640p5 r2) {
        return new C01020Iw(216, r2);
    }

    public AnonymousClass0DC<CheckApprovedMachineResponse> checkApprovedAsync(FBAuthRequest fBAuthRequest) {
        ApiTaskCallback apiTaskCallback = new ApiTaskCallback();
        try {
            ((FBAuthMethods) AnonymousClass0J2.A03(1, 180, this._UL_mInjectionContext)).checkApproved(fBAuthRequest, apiTaskCallback);
            return apiTaskCallback.mCompletionSource.A00;
        } catch (ApiException e) {
            return AnonymousClass0DC.A03(e);
        }
    }

    public AnonymousClass0DC<FBLoginResponse> linkAccountAsync(final FBLoginResponse fBLoginResponse, @Nullable String str, @Nullable String str2) {
        final AnonymousClass0DD r3 = new AnonymousClass0DD();
        AccessToken accessToken = new AccessToken(fBLoginResponse.accessToken, OculusConstants.OCULUS_APP_ID, fBLoginResponse.uid, null, null, null, null, null);
        FBConnectHelper fBConnectHelper = (FBConnectHelper) AnonymousClass0J2.A03(0, 486, this._UL_mInjectionContext);
        fBConnectHelper.mFBConnectDelegate = new FBConnectHelper.FBConnectDelegate() {
            /* class com.oculus.auth.components.FBLoginComponents.AnonymousClass1 */

            @Override // com.oculus.horizon.fbconnect.FBConnectHelper.FBConnectDelegate
            public void onFBAccountUnlinkError(@Nullable ApiError apiError) {
            }

            @Override // com.oculus.horizon.fbconnect.FBConnectHelper.FBConnectDelegate
            public void onFBAccountUnlinkSuccess() {
            }

            @Override // com.oculus.horizon.fbconnect.FBConnectHelper.FBConnectDelegate
            public void onFBAuthCancelled() {
            }

            @Override // com.oculus.horizon.fbconnect.FBConnectHelper.FBConnectDelegate
            public void onFBAuthSuccess(AccessToken accessToken) {
            }

            @Override // com.oculus.horizon.fbconnect.FBConnectHelper.FBConnectDelegate
            public void onFBDataFetchComplete(@Nullable FacebookData facebookData) {
            }

            @Override // com.oculus.horizon.fbconnect.FBConnectHelper.FBConnectDelegate
            public void onFBSyncFriendsError(@Nullable ApiError apiError) {
            }

            @Override // com.oculus.horizon.fbconnect.FBConnectHelper.FBConnectDelegate
            public void onFBSyncFriendsSuccess() {
            }

            @Override // com.oculus.horizon.fbconnect.FBConnectHelper.FBConnectDelegate
            public void onFBUnsyncFriendsError(@Nullable ApiError apiError) {
            }

            @Override // com.oculus.horizon.fbconnect.FBConnectHelper.FBConnectDelegate
            public void onFBUnsyncFriendsSuccess() {
            }

            @Override // com.oculus.horizon.fbconnect.FBConnectHelper.FBConnectDelegate
            public void onFBAccountLinkError(@Nullable ApiError apiError) {
                r3.A01(apiError);
            }

            @Override // com.oculus.horizon.fbconnect.FBConnectHelper.FBConnectDelegate
            public void onFBAccountLinkSuccess() {
                r3.A02(fBLoginResponse);
            }

            @Override // com.oculus.horizon.fbconnect.FBConnectHelper.FBConnectDelegate
            public void onFBAuthError(@Nullable ApiError apiError) {
                onFBAccountLinkError(apiError);
            }
        };
        fBConnectHelper.registerFBConnectAccount(accessToken, FBFriendPolicy.IGNORE_FRIEND, str, str2);
        return r3.A00;
    }

    public AnonymousClass0DC<FBLoginResponse> loginRequestAsync(FBAuthRequest fBAuthRequest) {
        ApiTaskCallback apiTaskCallback = new ApiTaskCallback();
        try {
            ((FBAuthMethods) AnonymousClass0J2.A03(1, 180, this._UL_mInjectionContext)).loginRequest(fBAuthRequest, apiTaskCallback);
            return apiTaskCallback.mCompletionSource.A00;
        } catch (ApiException e) {
            return AnonymousClass0DC.A03(e);
        }
    }

    @Inject
    public FBLoginComponents(AbstractC06640p5 r3) {
        this._UL_mInjectionContext = new AnonymousClass0QC(2, r3);
    }
}
