package com.oculus.authapi;

import X.AnonymousClass0NO;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.ResultReceiver;
import com.oculus.auth.service.contract.ServiceContract;

public class OVRAuth {
    public static final String EXTRA_RESULT = "result";
    public static final int RESULT_ERROR = 1;
    public static final int RESULT_OK = -1;
    public static final String TAG = "OVRAuth";
    public final CallerInfoProvider mCallerInfoProvider;
    public final Context mContext;
    public final boolean mIsUnderTest;

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

    /* renamed from: com.oculus.authapi.OVRAuth$7  reason: invalid class name */
    public class AnonymousClass7 extends AuthTaskReceiver<AuthFetchFbInfoForAccountLinkingResult, AuthFetchFbInfoForAccountLinkingError> {
        public final /* synthetic */ OVRAuth this$0;
    }

    public interface CallerInfoProvider {
        Intent attachCallerInfo(Intent intent);
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

    public static final Intent A00(OVRAuth oVRAuth, String str) {
        Intent intent = new Intent(str);
        intent.setPackage("com.oculus.horizon");
        CallerInfoProvider callerInfoProvider = oVRAuth.mCallerInfoProvider;
        if (callerInfoProvider != null) {
            callerInfoProvider.attachCallerInfo(intent);
        }
        return intent;
    }

    public static final ResultReceiver A01(OVRAuth oVRAuth, ResultReceiver resultReceiver) {
        if (oVRAuth.mIsUnderTest) {
            return new TestReceiverForSending(resultReceiver);
        }
        Parcel obtain = Parcel.obtain();
        resultReceiver.writeToParcel(obtain, 0);
        obtain.setDataPosition(0);
        ResultReceiver resultReceiver2 = (ResultReceiver) ResultReceiver.CREATOR.createFromParcel(obtain);
        obtain.recycle();
        return resultReceiver2;
    }

    @SuppressLint({"BadMethodUse-android.content.Context.startService"})
    public static final void A02(OVRAuth oVRAuth, Intent intent) {
        ResultReceiver resultReceiver;
        try {
            if (oVRAuth.mContext.startService(intent) == null) {
                AnonymousClass0NO.A08(TAG, "Horizon Service not installed...");
                Bundle extras = intent.getExtras();
                if (extras != null && (resultReceiver = (ResultReceiver) extras.getParcelable("receiver")) != null) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("error_code", -7);
                    resultReceiver.send(-7, bundle);
                }
            }
        } catch (Exception e) {
            AnonymousClass0NO.A0H(TAG, e, "Failed to communicate with Horizon service");
        }
    }

    public final void A03(AuthResultCallback<AuthCredentials, AuthError> authResultCallback) {
        AnonymousClass10 r1 = new AuthTaskReceiver<AuthCredentials, AuthError>(authResultCallback) {
            /* class com.oculus.authapi.OVRAuth.AnonymousClass10 */

            /* Return type fixed from 'java.lang.Object' to match base method */
            @Override // com.oculus.authapi.AuthTaskReceiver
            public final AuthCredentials A02(Bundle bundle) throws AuthError {
                return new AuthCredentials(bundle);
            }
        };
        Intent A00 = A00(this, ServiceContract.ACTION_FETCH_HORIZON_DEVICE_SCOPED_CREDENTIALS);
        A00.putExtra("receiver", A01(this, r1));
        A02(this, A00);
    }

    public OVRAuth(Context context, CallerInfoProvider callerInfoProvider) {
        this.mContext = context;
        this.mCallerInfoProvider = callerInfoProvider;
    }
}
