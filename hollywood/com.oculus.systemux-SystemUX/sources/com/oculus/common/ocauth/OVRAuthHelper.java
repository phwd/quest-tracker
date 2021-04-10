package com.oculus.common.ocauth;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ResultReceiver;
import android.text.TextUtils;
import android.util.Log;
import com.oculus.authapi.AuthError;
import com.oculus.authapi.AuthResultCallback;
import com.oculus.authapi.OVRAuth;
import com.oculus.common.logutilities.LoggingUtil;
import java.util.LinkedList;
import java.util.List;

public class OVRAuthHelper {
    private static final String TAG = LoggingUtil.tag(OVRAuthHelper.class);
    private final List<OvrAuthTokenCallback> mAccessTokenListeners = new LinkedList();
    private final OVRAuth mOVRAuth;
    private String mUserAccessToken;

    public interface OvrAuthDeviceOwnershipCallback {
        void onError(AuthError authError);

        void onSuccess();
    }

    public interface OvrAuthTokenCallback {
        void onAccessTokenReceived(String str);
    }

    public OVRAuthHelper(Application application) {
        this.mOVRAuth = new OVRAuth(application, new OVRAuth.CallerInfoProvider(application) {
            /* class com.oculus.common.ocauth.$$Lambda$OVRAuthHelper$1dRTTed_ERmYhJKjmefAWPRJPvM */
            private final /* synthetic */ Application f$0;

            {
                this.f$0 = r1;
            }

            @Override // com.oculus.authapi.OVRAuth.CallerInfoProvider
            public final Intent attachCallerInfo(Intent intent) {
                return OVRAuthHelper.lambda$new$0(this.f$0, intent);
            }
        });
    }

    public void ensureDeviceOwnership(final OvrAuthDeviceOwnershipCallback ovrAuthDeviceOwnershipCallback) {
        this.mOVRAuth.ensureDeviceOwnership(new AuthResultCallback<Void, AuthError>() {
            /* class com.oculus.common.ocauth.OVRAuthHelper.AnonymousClass1 */

            public void onResult(Void r2) {
                Log.d(OVRAuthHelper.TAG, "ensureDeviceOwnership succeeded");
                ovrAuthDeviceOwnershipCallback.onSuccess();
            }

            @Override // com.oculus.authapi.AuthResultCallback
            public void onError(AuthError authError) {
                Log.e(OVRAuthHelper.TAG, "ensureDeviceOwnership failed", authError);
                ovrAuthDeviceOwnershipCallback.onError(authError);
            }
        });
    }

    public void fetchAccessToken() {
        Log.i(TAG, "fetchAuthToken - start");
        this.mOVRAuth.status(new ResultReceiver(new Handler(Looper.getMainLooper())) {
            /* class com.oculus.common.ocauth.OVRAuthHelper.AnonymousClass2 */

            /* access modifiers changed from: protected */
            public void onReceiveResult(int i, Bundle bundle) {
                String str = OVRAuthHelper.TAG;
                Log.i(str, "fetchAuthToken - completed, result: " + i);
                if (i == -1) {
                    synchronized (OVRAuthHelper.this) {
                        OVRAuthHelper.this.mUserAccessToken = bundle.getString("access_token", null);
                        if (!TextUtils.isEmpty(OVRAuthHelper.this.mUserAccessToken)) {
                            OVRAuthHelper.this.notifyAccessTokenListeners();
                        }
                    }
                    return;
                }
                Log.e(OVRAuthHelper.TAG, String.format("Error retrieving auth token from OVRAuth: %s", bundle.getString("error_message")));
            }
        });
    }

    public synchronized void registerAccessTokenListener(OvrAuthTokenCallback ovrAuthTokenCallback) {
        if (!TextUtils.isEmpty(this.mUserAccessToken)) {
            ovrAuthTokenCallback.onAccessTokenReceived(this.mUserAccessToken);
        } else {
            this.mAccessTokenListeners.add(ovrAuthTokenCallback);
        }
    }

    public synchronized void removeAccessTokenListener(OvrAuthTokenCallback ovrAuthTokenCallback) {
        this.mAccessTokenListeners.remove(ovrAuthTokenCallback);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void notifyAccessTokenListeners() {
        for (OvrAuthTokenCallback ovrAuthTokenCallback : this.mAccessTokenListeners) {
            ovrAuthTokenCallback.onAccessTokenReceived(this.mUserAccessToken);
        }
    }
}
