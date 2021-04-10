package com.oculus.common.auth;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.oculus.aidl.OVRServiceInterface;
import com.oculus.common.logutilities.LoggingUtil;

public class AuthManager {
    private static final String ACCESS_TOKEN_KEY = "access_token";
    private static final String HORIZON_OVRSERVICE_NAME = "com.oculus.horizon.service.OVRService";
    private static final String HORIZON_PKG_NAME = "com.oculus.horizon";
    private static final String PLATFORM_SDK_BUNDLE_KEY_ERROR_CODE = "error_code";
    private static final String PLATFORM_SDK_BUNDLE_KEY_ERROR_MESSAGE = "error_message";
    private static final String TAG = LoggingUtil.tag(AuthManager.class);
    private final String mAppId;
    private final AuthListener mAuthListener;
    private final Handler mBgHandler;
    private final HandlerThread mBgThread;
    private final ServiceConnection mConnection = new ServiceConnection() {
        /* class com.oculus.common.auth.AuthManager.AnonymousClass1 */

        public void onServiceDisconnected(ComponentName componentName) {
        }

        public void onServiceConnected(ComponentName componentName, final IBinder iBinder) {
            AuthManager.this.mBgHandler.post(new Runnable() {
                /* class com.oculus.common.auth.AuthManager.AnonymousClass1.AnonymousClass1 */

                public void run() {
                    OVRServiceInterface asInterface = OVRServiceInterface.Stub.asInterface(iBinder);
                    Bundle bundle = new Bundle();
                    bundle.putInt("sdk_major_version", 1);
                    bundle.putInt("sdk_minor_version", 1);
                    bundle.putString("app_id", AuthManager.this.mAppId);
                    try {
                        Bundle appScopedAccessToken = asInterface.getAppScopedAccessToken(bundle);
                        if (appScopedAccessToken.containsKey("error_code")) {
                            AuthManager.this.mAuthListener.onUserAccessTokenError(appScopedAccessToken.getInt("error_code"), appScopedAccessToken.getString("error_message"));
                        } else {
                            AuthManager.this.mAuthListener.onUserAccessToken(appScopedAccessToken.getString("access_token"));
                        }
                    } catch (RemoteException e) {
                        AuthManager.this.mAuthListener.onUserAccessTokenError(-1, e.toString());
                    }
                    AuthManager.this.mContext.unbindService(AuthManager.this.mConnection);
                }
            });
        }
    };
    private final Context mContext;

    public interface AuthListener {
        void onUserAccessToken(String str);

        void onUserAccessTokenError(int i, String str);
    }

    public AuthManager(Context context, String str, AuthListener authListener) {
        this.mContext = context;
        this.mAppId = str;
        this.mAuthListener = authListener;
        this.mBgThread = new HandlerThread("AuthBackground");
        this.mBgThread.start();
        this.mBgHandler = new Handler(this.mBgThread.getLooper());
    }

    public void requestUserAccessToken() {
        try {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.oculus.horizon", HORIZON_OVRSERVICE_NAME));
            this.mContext.bindService(intent, this.mConnection, 1);
        } catch (SecurityException e) {
            Log.e(TAG, "Failed to bind to OVRService", e);
        }
    }
}
