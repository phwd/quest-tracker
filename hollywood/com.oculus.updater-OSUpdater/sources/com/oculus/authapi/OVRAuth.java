package com.oculus.authapi;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.ResultReceiver;
import com.facebook.debug.log.BLog;
import com.oculus.common.build.BuildConstants;

public class OVRAuth {
    private static final String TAG = "OVRAuth";
    private final CallerInfoProvider mCallerInfoProvider;
    private final Context mContext;
    private boolean mIsUnderTest;

    public interface CallerInfoProvider {
        Intent attachCallerInfo(Intent intent);
    }

    /* access modifiers changed from: protected */
    public ResultReceiver getReceiverForSending(ResultReceiver resultReceiver) {
        if (this.mIsUnderTest) {
            return new TestReceiverForSending(resultReceiver);
        }
        Parcel obtain = Parcel.obtain();
        resultReceiver.writeToParcel(obtain, 0);
        obtain.setDataPosition(0);
        ResultReceiver resultReceiver2 = (ResultReceiver) ResultReceiver.CREATOR.createFromParcel(obtain);
        obtain.recycle();
        return resultReceiver2;
    }

    /* access modifiers changed from: package-private */
    public static final class TestReceiverForSending extends ResultReceiver {
        final ResultReceiver mActualReceiver;

        TestReceiverForSending(ResultReceiver resultReceiver) {
            super(null);
            this.mActualReceiver = resultReceiver;
        }

        public void send(int i, Bundle bundle) {
            this.mActualReceiver.send(i, bundle);
        }
    }

    public OVRAuth(Context context, CallerInfoProvider callerInfoProvider) {
        this.mContext = context;
        this.mCallerInfoProvider = callerInfoProvider;
    }

    private Intent attachCallerInfo(Intent intent) {
        CallerInfoProvider callerInfoProvider = this.mCallerInfoProvider;
        return callerInfoProvider != null ? callerInfoProvider.attachCallerInfo(intent) : intent;
    }

    public void fetchHorizonDeviceScopedCredentials(AuthResultCallback<AuthCredentials, AuthError> authResultCallback) {
        AnonymousClass10 r0 = new AuthTaskReceiver<AuthCredentials, AuthError>(authResultCallback) {
            /* class com.oculus.authapi.OVRAuth.AnonymousClass10 */

            /* access modifiers changed from: protected */
            @Override // com.oculus.authapi.AuthTaskReceiver
            public AuthCredentials createResultFromBundle(Bundle bundle) {
                return new AuthCredentials(bundle);
            }
        };
        Intent newHorizonIntent = newHorizonIntent("com.oculus.auth.ACTION_FETCH_HORIZON_DEVICE_SCOPED_CREDENTIALS");
        newHorizonIntent.putExtra("receiver", getReceiverForSending(r0));
        startAuthService(newHorizonIntent);
    }

    @SuppressLint({"BadMethodUse-android.content.Context.startService"})
    public void startAuthService(Intent intent) {
        Bundle extras;
        ResultReceiver resultReceiver;
        boolean z = true;
        try {
            if (this.mContext.startService(intent) == null) {
                BLog.e(TAG, "Horizon Service not installed...");
            } else {
                z = false;
            }
        } catch (Exception e) {
            BLog.e(TAG, e, "Failed to communicate with Horizon service");
        }
        if (z && (extras = intent.getExtras()) != null && (resultReceiver = (ResultReceiver) extras.getParcelable("receiver")) != null) {
            Bundle bundle = new Bundle();
            bundle.putInt("error_code", -7);
            resultReceiver.send(-7, bundle);
        }
    }

    /* access modifiers changed from: protected */
    public Intent newHorizonIntent(String str) {
        Intent intent = new Intent(str);
        intent.setPackage(BuildConstants.PACKAGE_NAME_HORIZON);
        return attachCallerInfo(intent);
    }
}
