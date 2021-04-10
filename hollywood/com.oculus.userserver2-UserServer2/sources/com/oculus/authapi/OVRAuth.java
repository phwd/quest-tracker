package com.oculus.authapi;

import X.Mi;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.ResultReceiver;
import com.oculus.auth.service.contract.ServiceContract;
import com.oculus.userserver.managerservice.DsatFetcherForUser;
import com.oculus.userserver.os.FrameworkWrapper;

public class OVRAuth {
    public static final String EXTRA_RESULT = "result";
    public static final int RESULT_ERROR = 1;
    public static final int RESULT_OK = -1;
    public static final String TAG = "OVRAuth";
    public final CallerInfoProvider mCallerInfoProvider;
    public final Context mContext;
    public final boolean mIsUnderTest;

    /* renamed from: com.oculus.authapi.OVRAuth$1  reason: invalid class name */
    public class AnonymousClass1 extends AuthTaskReceiver<Void, AuthLoginError> {
        public final /* synthetic */ OVRAuth this$0;
    }

    /* renamed from: com.oculus.authapi.OVRAuth$11  reason: invalid class name */
    public class AnonymousClass11 extends ResultReceiver {
        public final /* synthetic */ OVRAuth this$0;
        public final /* synthetic */ ResultReceiver val$receiver;
    }

    /* renamed from: com.oculus.authapi.OVRAuth$12  reason: invalid class name */
    public class AnonymousClass12 extends AuthTaskReceiver<Void, AuthError> {
        public final /* synthetic */ OVRAuth this$0;
    }

    /* renamed from: com.oculus.authapi.OVRAuth$13  reason: invalid class name */
    public class AnonymousClass13 extends AuthTaskReceiver<Void, AuthError> {
        public final /* synthetic */ OVRAuth this$0;
    }

    /* renamed from: com.oculus.authapi.OVRAuth$14  reason: invalid class name */
    public class AnonymousClass14 extends AuthTaskReceiver<Void, AuthError> {
        public final /* synthetic */ OVRAuth this$0;
    }

    /* renamed from: com.oculus.authapi.OVRAuth$15  reason: invalid class name */
    public class AnonymousClass15 extends AuthTaskReceiver<AuthVerifyLoginForAccountLinkingResult, AuthLoginError> {
        public final /* synthetic */ OVRAuth this$0;
    }

    /* renamed from: com.oculus.authapi.OVRAuth$16  reason: invalid class name */
    public class AnonymousClass16 extends AuthTaskReceiver<AuthFbLoginResult, AuthFbLoginError> {
        public final /* synthetic */ OVRAuth this$0;
    }

    /* renamed from: com.oculus.authapi.OVRAuth$17  reason: invalid class name */
    public class AnonymousClass17 extends AuthTaskReceiver<Void, AuthFbLoginError> {
        public final /* synthetic */ OVRAuth this$0;
    }

    /* renamed from: com.oculus.authapi.OVRAuth$18  reason: invalid class name */
    public class AnonymousClass18 extends AuthTaskReceiver<Boolean, AuthError> {
        public final /* synthetic */ OVRAuth this$0;
    }

    /* renamed from: com.oculus.authapi.OVRAuth$2  reason: invalid class name */
    public class AnonymousClass2 extends AuthTaskReceiver<Void, AuthLoginError> {
        public final /* synthetic */ OVRAuth this$0;
    }

    /* renamed from: com.oculus.authapi.OVRAuth$3  reason: invalid class name */
    public class AnonymousClass3 extends AuthTaskReceiver<Void, AuthLoginError> {
        public final /* synthetic */ OVRAuth this$0;
    }

    /* renamed from: com.oculus.authapi.OVRAuth$4  reason: invalid class name */
    public class AnonymousClass4 extends AuthTaskReceiver<AuthAuthWithOculusEmailAndPasswordForAccountLinkingResult, AuthLoginError> {
        public final /* synthetic */ OVRAuth this$0;
    }

    /* renamed from: com.oculus.authapi.OVRAuth$5  reason: invalid class name */
    public class AnonymousClass5 extends AuthTaskReceiver<Void, AuthLoginError> {
        public final /* synthetic */ OVRAuth this$0;
    }

    /* renamed from: com.oculus.authapi.OVRAuth$6  reason: invalid class name */
    public class AnonymousClass6 extends AuthTaskReceiver<Void, AuthLoginWithFbAuthError> {
        public final /* synthetic */ OVRAuth this$0;
    }

    /* renamed from: com.oculus.authapi.OVRAuth$7  reason: invalid class name */
    public class AnonymousClass7 extends AuthTaskReceiver<AuthFetchFbInfoForAccountLinkingResult, AuthFetchFbInfoForAccountLinkingError> {
        public final /* synthetic */ OVRAuth this$0;
    }

    /* renamed from: com.oculus.authapi.OVRAuth$8  reason: invalid class name */
    public class AnonymousClass8 extends ResultReceiver {
        public final /* synthetic */ OVRAuth this$0;
        public final /* synthetic */ ResultReceiver val$receiver;
    }

    /* renamed from: com.oculus.authapi.OVRAuth$9  reason: invalid class name */
    public class AnonymousClass9 extends AuthTaskReceiver<AuthCredentials, AuthError> {
        public final /* synthetic */ OVRAuth this$0;
    }

    public interface CallerInfoProvider {
        Intent A0t(Intent intent);
    }

    public class ProxyAuthCallback implements AuthResultCallback<Void, AuthLoginError> {
        public final AuthResultCallback<Void, AuthLoginError> mCallback;
        public final String mFbAccessToken;
        public final String mFbUserId;
        public final /* synthetic */ OVRAuth this$0;
    }

    public static final class TestReceiverForSending extends ResultReceiver {
        public final ResultReceiver mActualReceiver;

        public TestReceiverForSending(ResultReceiver resultReceiver) {
            super(null);
            this.mActualReceiver = resultReceiver;
        }

        public final void send(int i, Bundle bundle) {
            this.mActualReceiver.send(i, bundle);
        }
    }

    public final void A00(AuthResultCallback<AuthCredentials, AuthError> authResultCallback) {
        Parcelable parcelable;
        ResultReceiver resultReceiver;
        AnonymousClass10 r2 = new AuthTaskReceiver<AuthCredentials, AuthError>(authResultCallback) {
            /* class com.oculus.authapi.OVRAuth.AnonymousClass10 */
        };
        Intent intent = new Intent(ServiceContract.ACTION_FETCH_HORIZON_DEVICE_SCOPED_CREDENTIALS);
        intent.setPackage("com.oculus.horizon");
        CallerInfoProvider callerInfoProvider = this.mCallerInfoProvider;
        if (callerInfoProvider != null) {
            callerInfoProvider.A0t(intent);
        }
        if (this.mIsUnderTest) {
            parcelable = new TestReceiverForSending(r2);
        } else {
            Parcel obtain = Parcel.obtain();
            r2.writeToParcel(obtain, 0);
            obtain.setDataPosition(0);
            parcelable = (ResultReceiver) ResultReceiver.CREATOR.createFromParcel(obtain);
            obtain.recycle();
        }
        intent.putExtra("receiver", parcelable);
        if (!(this instanceof DsatFetcherForUser.OVRAuthForUser)) {
            try {
                if (this.mContext.startService(intent) == null) {
                    Mi.A00(TAG, "Horizon Service not installed...");
                    Bundle extras = intent.getExtras();
                    if (extras != null && (resultReceiver = (ResultReceiver) extras.getParcelable("receiver")) != null) {
                        Bundle bundle = new Bundle();
                        bundle.putInt("error_code", -7);
                        resultReceiver.send(-7, bundle);
                    }
                }
            } catch (Exception e) {
                Mi.A06(TAG, e, "Failed to communicate with Horizon service");
            }
        } else {
            DsatFetcherForUser.OVRAuthForUser oVRAuthForUser = (DsatFetcherForUser.OVRAuthForUser) this;
            FrameworkWrapper.A04(oVRAuthForUser.mContext, intent, oVRAuthForUser.mUserHandle);
        }
    }

    public OVRAuth(Context context, CallerInfoProvider callerInfoProvider) {
        this.mContext = context;
        this.mCallerInfoProvider = callerInfoProvider;
    }
}
