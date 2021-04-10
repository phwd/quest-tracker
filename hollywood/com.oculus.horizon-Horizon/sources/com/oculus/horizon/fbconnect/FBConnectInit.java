package com.oculus.horizon.fbconnect;

import X.AbstractC06640p5;
import X.AnonymousClass0DC;
import X.AnonymousClass0J2;
import X.AnonymousClass0QC;
import X.AnonymousClass0b9;
import X.AnonymousClass0p1;
import X.AnonymousClass117;
import X.C003008y;
import X.C01020Iw;
import android.content.Context;
import android.content.Intent;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.oculus.auth.credentials.CredentialsManager;
import com.oculus.auth.credentials.CredentialsModule;
import com.oculus.common.init.INeedInit;
import com.oculus.horizon.fbconnect.contract.FBConnectContent;
import com.oculus.horizon.linkedaccounts.database.LinkedAccountsSQLiteHelper;
import com.oculus.horizon.linkedaccounts.model.ServiceProvider;
import com.oculus.security.basecomponent.OculusPublicBroadcastReceiver;
import com.oculus.util.device.DeviceUtils;
import java.util.concurrent.Callable;
import javax.inject.Inject;
import javax.inject.Provider;

@Dependencies({"_UL__ULSEP_com_oculus_util_device_DeviceUtils_ULSEP_BINDING_ID", "_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_fbconnect_FBConnectHelper_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_auth_credentials_CredentialsManager_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_linkedaccounts_database_LinkedAccountsSQLiteHelper_ULSEP_BINDING_ID"})
public class FBConnectInit implements INeedInit {
    public static final String TAG = "FBConnectInit";
    public AnonymousClass0QC _UL_mInjectionContext;
    @Inject
    @Eager
    public final CredentialsManager mCredentialsManager;
    @Inject
    @Eager
    public final DeviceUtils mDeviceUtils;
    @Inject
    @Eager
    public final FBConnectHelper mFBConnectHelper;
    @Inject
    @Eager
    public final LinkedAccountsSQLiteHelper mLinkedAccountsSQLiteHelper;

    public static class FBConnectAccessTokenTracker extends AccessTokenTracker {
        public final LinkedAccountsSQLiteHelper mLinkedAccountsSQLiteHelper;

        @Override // com.facebook.AccessTokenTracker
        public void onCurrentAccessTokenChanged(AccessToken accessToken, final AccessToken accessToken2) {
            if (accessToken2 == null) {
                AnonymousClass0DC.A06(new Callable<Void>() {
                    /* class com.oculus.horizon.fbconnect.FBConnectInit.FBConnectAccessTokenTracker.AnonymousClass1 */

                    @Override // java.util.concurrent.Callable
                    public Void call() throws Exception {
                        FBConnectAccessTokenTracker.this.mLinkedAccountsSQLiteHelper.A00(ServiceProvider.FACEBOOK);
                        return null;
                    }
                });
                return;
            }
            Profile.fetchProfileForCurrentAccessToken();
            AnonymousClass0DC.A06(new Callable<Void>() {
                /* class com.oculus.horizon.fbconnect.FBConnectInit.FBConnectAccessTokenTracker.AnonymousClass2 */

                @Override // java.util.concurrent.Callable
                public Void call() throws Exception {
                    LinkedAccountsSQLiteHelper linkedAccountsSQLiteHelper = FBConnectAccessTokenTracker.this.mLinkedAccountsSQLiteHelper;
                    AccessToken accessToken = accessToken2;
                    linkedAccountsSQLiteHelper.A01(accessToken.userId, accessToken.token, ServiceProvider.FACEBOOK);
                    return null;
                }
            });
        }

        public FBConnectAccessTokenTracker(LinkedAccountsSQLiteHelper linkedAccountsSQLiteHelper) {
            this.mLinkedAccountsSQLiteHelper = linkedAccountsSQLiteHelper;
        }
    }

    public static class FBConnectProfileTracker extends ProfileTracker {
        public final Context mContext;

        @Override // com.facebook.ProfileTracker
        public void onCurrentProfileChanged(Profile profile, Profile profile2) {
            this.mContext.getContentResolver().notifyChange(FBConnectContent.Account.CONTENT_URI, null);
        }

        public FBConnectProfileTracker(Context context) {
            this.mContext = context;
        }
    }

    @AutoGeneratedAccessMethod
    public static final AnonymousClass0p1 _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_horizon_fbconnect_FBConnectInit_ULGT__ULSEP_ACCESS_METHOD(AbstractC06640p5 r2) {
        return new C003008y(278, r2);
    }

    @AutoGeneratedAccessMethod
    public static final FBConnectInit _UL__ULSEP_com_oculus_horizon_fbconnect_FBConnectInit_ULSEP_ACCESS_METHOD(AbstractC06640p5 r1) {
        return (FBConnectInit) AnonymousClass117.A00(278, r1);
    }

    @AutoGeneratedFactoryMethod
    public static final FBConnectInit _UL__ULSEP_com_oculus_horizon_fbconnect_FBConnectInit_ULSEP_FACTORY_METHOD(AbstractC06640p5 r1) {
        return new FBConnectInit(r1);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_horizon_fbconnect_FBConnectInit_ULGT__ULSEP_ACCESS_METHOD(AbstractC06640p5 r2) {
        return new C01020Iw(278, r2);
    }

    @Override // com.oculus.common.init.INeedInit
    public void init() {
        FacebookSdk.sdkInitialize((Context) AnonymousClass0J2.A03(0, 294, this._UL_mInjectionContext));
        new FBConnectAccessTokenTracker(this.mLinkedAccountsSQLiteHelper);
        new FBConnectProfileTracker((Context) AnonymousClass0J2.A03(0, 294, this._UL_mInjectionContext));
        if (this.mDeviceUtils.A04()) {
            new OculusPublicBroadcastReceiver("android.intent.action.SCREEN_ON") {
                /* class com.oculus.horizon.fbconnect.FBConnectInit.AnonymousClass1 */

                @Override // com.oculus.security.basecomponent.OculusSecureBroadcastReceiverBase
                public void onReceive(Context context, Intent intent, AnonymousClass0b9 r5) {
                    if (FBConnectInit.this.mCredentialsManager.getCredentials() != null && intent.getAction().equals("android.intent.action.SCREEN_ON")) {
                        FBConnectInit.this.mFBConnectHelper.updateFBConnectAccountAsync(FBConnectInit.TAG);
                    }
                }
            }.registerReceiver((Context) AnonymousClass0J2.A03(0, 294, this._UL_mInjectionContext));
        }
    }

    @Inject
    public FBConnectInit(AbstractC06640p5 r3) {
        this._UL_mInjectionContext = new AnonymousClass0QC(1, r3);
        this.mDeviceUtils = DeviceUtils.A00(r3);
        this.mFBConnectHelper = FBConnectHelper._UL__ULSEP_com_oculus_horizon_fbconnect_FBConnectHelper_ULSEP_ACCESS_METHOD(r3);
        this.mCredentialsManager = CredentialsModule._UL__ULSEP_com_oculus_auth_credentials_CredentialsManager_ULSEP_ACCESS_METHOD(r3);
        this.mLinkedAccountsSQLiteHelper = (LinkedAccountsSQLiteHelper) AnonymousClass117.A00(293, r3);
    }

    public static /* synthetic */ String access$100() {
        return TAG;
    }
}