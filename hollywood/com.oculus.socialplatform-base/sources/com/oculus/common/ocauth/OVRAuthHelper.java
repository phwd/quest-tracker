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
import com.oculus.vrshell.util.CallerInfoHelper;
import java.util.LinkedList;
import java.util.List;

public class OVRAuthHelper {
    public static final String TAG = LoggingUtil.tag(OVRAuthHelper.class);
    public final List<OvrAuthTokenCallback> mAccessTokenListeners = new LinkedList();
    public final OVRAuth mOVRAuth;
    public String mUserAccessToken;

    public interface OvrAuthDeviceOwnershipCallback {
        void onError(AuthError authError);

        void onSuccess();
    }

    public interface OvrAuthTokenCallback {
        void onAccessTokenReceived(String str);
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

    public void ensureDeviceOwnership(final OvrAuthDeviceOwnershipCallback ovrAuthDeviceOwnershipCallback) {
        this.mOVRAuth.ensureDeviceOwnership(new AuthResultCallback<Void, AuthError>() {
            /* class com.oculus.common.ocauth.OVRAuthHelper.AnonymousClass1 */

            @Override // com.oculus.authapi.AuthResultCallback
            public void onError(AuthError authError) {
                Log.e(OVRAuthHelper.TAG, "ensureDeviceOwnership failed", authError);
                ovrAuthDeviceOwnershipCallback.onError(authError);
            }

            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
            @Override // com.oculus.authapi.AuthResultCallback
            public /* bridge */ /* synthetic */ void onResult(Void r2) {
                onResult((Void) null);
            }

            public void onResult(Void r2) {
                ovrAuthDeviceOwnershipCallback.onSuccess();
            }
        });
    }

    public void fetchAccessToken() {
        this.mOVRAuth.status(new ResultReceiver(new Handler(Looper.getMainLooper())) {
            /* class com.oculus.common.ocauth.OVRAuthHelper.AnonymousClass2 */

            public void onReceiveResult(int i, Bundle bundle) {
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

    public OVRAuthHelper(Application application) {
        this.mOVRAuth = new OVRAuth(application, new OVRAuth.CallerInfoProvider(application) {
            /* class com.oculus.common.ocauth.$$Lambda$OVRAuthHelper$1dRTTed_ERmYhJKjmefAWPRJPvM2 */
            public final /* synthetic */ Application f$0;

            {
                this.f$0 = r1;
            }

            @Override // com.oculus.authapi.OVRAuth.CallerInfoProvider
            public final Intent attachCallerInfo(Intent intent) {
                CallerInfoHelper.attachCallerInfo(intent, this.f$0, "SystemUX:AuthHelper");
                return intent;
            }
        });
    }
}
