package com.oculus.deviceauthserver;

import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.ConnectivityManager;
import android.net.Network;
import android.os.Handler;
import android.os.Looper;
import android.os.ServiceSpecificException;
import android.util.Log;
import com.android.volley.NetworkError;
import com.android.volley.ServerError;
import com.google.common.collect.ImmutableMultimap;
import com.oculus.aidl.IDeviceAuthService;
import com.oculus.devicecertservice.DeviceIdentityException;
import com.oculus.os.DeviceAuthToken;
import com.oculus.trustedcallerverifier.SignatureHelper;
import com.oculus.trustedcallerverifier.TrustedCallerVerifier;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class DeviceAuthService extends Service {
    public static final String ACTION_REFRESH_TOKEN = "REFRESH_TOKEN";
    private static final long FETCH_TOKEN_RETRY_INTERVAL = TimeUnit.SECONDS.toMillis(5);
    private static final String TAG = DeviceAuthService.class.getSimpleName();
    private DeviceAuthenticator mAuthenticator;
    private DeviceAuthTokenBinder mBinder;
    private ConnectivityManager mConnectivityManager;
    private Handler mHandler;
    private AtomicBoolean mIsNetworkAvailable;
    private ConnectivityManager.NetworkCallback mNetworkCallback;

    public void onCreate() {
        super.onCreate();
        this.mIsNetworkAvailable = new AtomicBoolean(false);
        this.mAuthenticator = DeviceAuthenticator.getInstance(this);
        this.mHandler = new Handler(Looper.getMainLooper());
        this.mBinder = new DeviceAuthTokenBinder();
        this.mConnectivityManager = (ConnectivityManager) getSystemService("connectivity");
        if (this.mConnectivityManager != null) {
            this.mNetworkCallback = new ConnectivityManager.NetworkCallback() {
                /* class com.oculus.deviceauthserver.DeviceAuthService.AnonymousClass1 */

                public void onAvailable(Network network) {
                    super.onAvailable(network);
                    Log.d(DeviceAuthService.TAG, "Network connected");
                    DeviceAuthService.this.mIsNetworkAvailable.set(true);
                    DeviceAuthService.this.fetchAuthTokenWrapper();
                }

                public void onLost(Network network) {
                    super.onLost(network);
                    Log.d(DeviceAuthService.TAG, "Network lost");
                    DeviceAuthService.this.mIsNetworkAvailable.set(false);
                }
            };
            this.mConnectivityManager.registerDefaultNetworkCallback(this.mNetworkCallback);
        }
    }

    public void onStart(Intent intent, int startId) {
        if (intent != null) {
            handleIntent(intent);
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.oculus.deviceauthserver.DeviceAuthService$DeviceAuthTokenBinder, android.os.IBinder] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.os.IBinder onBind(android.content.Intent r2) {
        /*
            r1 = this;
            com.oculus.deviceauthserver.DeviceAuthService$DeviceAuthTokenBinder r0 = r1.mBinder
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.deviceauthserver.DeviceAuthService.onBind(android.content.Intent):android.os.IBinder");
    }

    public void onDestroy() {
        ConnectivityManager.NetworkCallback networkCallback;
        super.onDestroy();
        ConnectivityManager connectivityManager = this.mConnectivityManager;
        if (connectivityManager != null && (networkCallback = this.mNetworkCallback) != null) {
            connectivityManager.unregisterNetworkCallback(networkCallback);
        }
    }

    private void handleIntent(Intent intent) {
        if (ACTION_REFRESH_TOKEN.equals(intent.getAction())) {
            fetchAuthTokenWrapper();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void fetchAuthTokenWrapper() {
        Log.d(TAG, "fetchAuthTokenWrapper called");
        this.mAuthenticator.getExecutorService().execute(new Runnable() {
            /* class com.oculus.deviceauthserver.DeviceAuthService.AnonymousClass2 */

            public void run() {
                if (DeviceAuthenticator.isOculusAddressResolvable()) {
                    Log.d(DeviceAuthService.TAG, "isOculusAddressResolvable returned true");
                    DeviceAuthService.this.mAuthenticator.refreshCredentialsAsync();
                    return;
                }
                Log.d(DeviceAuthService.TAG, "isOculusAddressResolvable returned false");
                if (DeviceAuthService.this.mIsNetworkAvailable.get()) {
                    Log.d(DeviceAuthService.TAG, "network is still available, run handler");
                    DeviceAuthService.this.mHandler.postDelayed(new Runnable() {
                        /* class com.oculus.deviceauthserver.DeviceAuthService.AnonymousClass2.AnonymousClass1 */

                        public void run() {
                            Log.d(DeviceAuthService.TAG, "handler will call wrappper again");
                            DeviceAuthService.this.fetchAuthTokenWrapper();
                        }
                    }, DeviceAuthService.FETCH_TOKEN_RETRY_INTERVAL);
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void dump(FileDescriptor fd, PrintWriter writer, String[] args) {
        this.mAuthenticator.dump(writer);
    }

    private class DeviceAuthTokenBinder extends IDeviceAuthService.Stub {
        private final TrustedCallerVerifier trustedCallerVerifier;

        public DeviceAuthTokenBinder() {
            ImmutableMultimap.Builder<Signature, String> signatures = ImmutableMultimap.builder();
            signatures.put(SignatureHelper.OCULUS_CORE_APPS_RELEASE_SIGNATURE, Constants.CALIBRATION_SYNC_PACKAGE);
            signatures.put(SignatureHelper.OCULUS_PRIV_APPS_RELEASE_SIG, Constants.ENTERPRISE_SERVER_PACKAGE);
            signatures.put(SignatureHelper.OCULUS_CORE_APPS_RELEASE_SIGNATURE, Constants.HORIZON_PACKAGE);
            signatures.put(SignatureHelper.OCULUS_CORE_APPS_RELEASE_SIGNATURE, Constants.OCMS_PACKAGE);
            signatures.put(SignatureHelper.OCULUS_CORE_APPS_RELEASE_SIGNATURE, Constants.UPDATER_PACKAGE);
            if (Constants.IS_DEBUG_BUILD) {
                signatures.put(SignatureHelper.FBANDROID_FIRST_PARTY_DEBUG_SIG, Constants.CALIBRATION_SYNC_PACKAGE);
                signatures.put(SignatureHelper.FBANDROID_FIRST_PARTY_DEBUG_SIG, Constants.ENTERPRISE_SERVER_PACKAGE);
                signatures.put(SignatureHelper.FBANDROID_FIRST_PARTY_DEBUG_SIG, Constants.HORIZON_PACKAGE);
                signatures.put(SignatureHelper.FBANDROID_FIRST_PARTY_DEBUG_SIG, Constants.OCMS_PACKAGE);
                signatures.put(SignatureHelper.FBANDROID_FIRST_PARTY_DEBUG_SIG, Constants.UPDATER_PACKAGE);
            }
            PackageManager pm = DeviceAuthService.this.getPackageManager();
            try {
                signatures.put(pm.getPackageInfo("android", 64).signatures[0], TrustedCallerVerifier.ALL_PACKAGES_MARKER);
            } catch (PackageManager.NameNotFoundException e) {
                Log.wtf(DeviceAuthService.TAG, "Platform package signature is missing");
            }
            this.trustedCallerVerifier = new TrustedCallerVerifier(signatures.build(), DeviceAuthService.this, pm);
        }

        public String getDeviceAuthToken(String applicationClientToken) {
            Log.d(DeviceAuthService.TAG, "getDeviceAuthToken called");
            this.trustedCallerVerifier.enforceTrustedCaller();
            DeviceCredentials credentials = DeviceAuthService.this.mAuthenticator.getCachedCredentials();
            if (credentials != null) {
                return credentials.mToken.mValue;
            }
            return null;
        }

        public DeviceAuthToken fetchDeviceAuthToken(String applicationClientToken) {
            Log.d(DeviceAuthService.TAG, "fetchDeviceAuthToken called");
            this.trustedCallerVerifier.enforceTrustedCaller();
            try {
                DeviceToken token = DeviceAuthService.this.mAuthenticator.fetchCredentials().mToken;
                return DeviceAuthToken.of(token.mValue, token.mExpirationTime.mElapsedRealtime);
            } catch (DeviceIdentityException e) {
                Log.e(DeviceAuthService.TAG, "Error fetching credentials", e);
                throw new ServiceSpecificException(1, e.getMessage());
            } catch (NetworkError e2) {
                Log.e(DeviceAuthService.TAG, "Error fetching credentials", e2);
                throw new ServiceSpecificException(2, e2.getMessage());
            } catch (ServerError e3) {
                Log.e(DeviceAuthService.TAG, "Error fetching credentials", e3);
                throw new ServiceSpecificException(3, e3.getMessage());
            } catch (InterruptedException | RuntimeException e4) {
                Log.e(DeviceAuthService.TAG, "Error fetching credentials", e4);
                throw new IllegalStateException(e4);
            }
        }
    }
}
