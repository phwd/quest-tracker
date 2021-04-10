package com.oculus.deviceauthserver;

import android.content.Context;
import android.content.Intent;
import android.os.SystemProperties;
import android.os.UserHandle;
import android.text.TextUtils;
import android.util.Log;
import com.android.volley.NetworkError;
import com.android.volley.ServerError;
import com.oculus.deviceauthserver.DeviceAuthenticator;
import com.oculus.devicecertservice.DeviceAuth;
import com.oculus.devicecertservice.DeviceAuthErrorCallback;
import com.oculus.devicecertservice.DeviceIdentityException;
import com.oculus.os.SettingsManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.util.NoSuchElementException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* access modifiers changed from: package-private */
public final class DeviceAuthenticator {
    private static final long EXPIRATION_BUFFER_MILLIS = TimeUnit.MINUTES.toMillis(10);
    private static final String NOTIFICATION_NEW_TOKEN_AVAILABLE = "com.oculus.deviceauthservice.NEW_TOKEN_AVAILABLE";
    private static final String TAG = DeviceAuthenticator.class.getSimpleName();
    private static final long TIMEOUT_MILLIS = TimeUnit.SECONDS.toMillis(15);
    private static DeviceAuthenticator sInstance;
    private final Context mContext;
    private final DeviceAuth mDeviceAuth = initDeviceAuth(this.mContext);
    private final ExecutorService mExecutorService = Executors.newSingleThreadExecutor();
    private CompletableFuture<DeviceCredentials> mFuture;
    private final DeviceAuthStore mStore = new DeviceAuthStore(this.mContext);

    private DeviceAuthenticator(Context context) {
        this.mContext = context.getApplicationContext().createDeviceProtectedStorageContext();
    }

    private static DeviceAuth initDeviceAuth(Context context) {
        String sandbox = null;
        if (Constants.IS_DEBUG_BUILD) {
            sandbox = SystemProperties.get("ovr.deviceauth.sandbox");
            if (sandbox.isEmpty()) {
                sandbox = null;
            }
            if (sandbox != null) {
                Log.d(TAG, String.format("Using sandbox: [%s]", sandbox));
            }
        }
        try {
            return new DeviceAuth(context, sandbox);
        } catch (NoSuchElementException e) {
            Log.e(TAG, "Error initializing DeviceAuth", e);
            return null;
        }
    }

    static synchronized DeviceAuthenticator getInstance(Context context) {
        DeviceAuthenticator deviceAuthenticator;
        synchronized (DeviceAuthenticator.class) {
            if (sInstance == null) {
                sInstance = new DeviceAuthenticator(context);
            }
            deviceAuthenticator = sInstance;
        }
        return deviceAuthenticator;
    }

    /* access modifiers changed from: package-private */
    public DeviceCredentials getCachedCredentials() {
        return this.mStore.getCredentials();
    }

    /* access modifiers changed from: package-private */
    public DeviceCredentials fetchCredentials() throws DeviceIdentityException, NetworkError, ServerError, InterruptedException {
        Log.d(TAG, "Fetching credentials");
        DeviceCredentials credentials = this.mStore.getCredentials();
        if (areCredentialsValid(credentials)) {
            Log.d(TAG, "Returning stored credentials");
            return credentials;
        } else if (isOculusAddressResolvable()) {
            Log.d(TAG, "Fetching new credentials");
            try {
                DeviceCredentials newCredentials = fetchNewCredentialsAsync().get(TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);
                Log.d(TAG, "New credentials fetched successfully");
                return newCredentials;
            } catch (ExecutionException ee) {
                Throwable cause = ee.getCause();
                Log.e(TAG, "Error fetching new credentials", cause);
                throw cause;
            } catch (TimeoutException e) {
                Log.e(TAG, "Timeout fetching new credentials", e);
                throw new NetworkError(e);
            } catch (NetworkError | ServerError | DeviceIdentityException e2) {
                throw e2;
            } catch (Throwable e3) {
                throw new DeviceIdentityException(e3);
            }
        } else {
            throw new NetworkError(new Exception("Unable to resolve graph.facebook-hardware.com"));
        }
    }

    /* access modifiers changed from: package-private */
    public void refreshCredentialsAsync() {
        fetchNewCredentialsAsync();
    }

    /* access modifiers changed from: package-private */
    public ExecutorService getExecutorService() {
        return this.mExecutorService;
    }

    /* access modifiers changed from: package-private */
    public void dump(PrintWriter writer) {
        DeviceCredentials credentials = this.mStore.getCredentials();
        Object[] objArr = new Object[2];
        objArr[0] = Boolean.valueOf(credentials != null);
        objArr[1] = Boolean.valueOf(areCredentialsValid(credentials));
        writer.format("Credentials available: %b, valid: %b\n", objArr);
    }

    private boolean areCredentialsValid(DeviceCredentials credentials) {
        if (credentials != null) {
            return BootCountedRealtime.current(this.mContext).sameBootCountAndEarlierThan(credentials.mToken.mExpirationTime, EXPIRATION_BUFFER_MILLIS);
        }
        return false;
    }

    static boolean isOculusAddressResolvable() {
        try {
            InetAddress ipAddr = InetAddress.getByName("graph.facebook-hardware.com");
            if (ipAddr == null || ipAddr.getAddress() == null || ipAddr.getAddress().length <= 0) {
                return false;
            }
            Log.d(TAG, "Successfully resolved graph.facebook-hardware.com");
            return true;
        } catch (IOException e) {
            Log.e(TAG, "Failed to resolve graph.facebook-hardware.com", e);
            return false;
        }
    }

    private synchronized CompletableFuture<DeviceCredentials> fetchNewCredentialsAsync() {
        Log.d(TAG, "Fetching new credentials asynchronously");
        if (this.mFuture == null || this.mFuture.isDone()) {
            this.mFuture = new CompletableFuture<>();
            Request request = new Request(this.mContext, this.mFuture);
            this.mExecutorService.execute(new Runnable(this.mStore.getCredentials(), request) {
                /* class com.oculus.deviceauthserver.$$Lambda$DeviceAuthenticator$CHbyH19vMfqKydDV_9BmhxMm0M0 */
                private final /* synthetic */ DeviceCredentials f$1;
                private final /* synthetic */ DeviceAuthenticator.Request f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void run() {
                    DeviceAuthenticator.this.lambda$fetchNewCredentialsAsync$2$DeviceAuthenticator(this.f$1, this.f$2);
                }
            });
            return this.mFuture;
        }
        Log.d(TAG, "Returning ongoing future");
        return this.mFuture;
    }

    public /* synthetic */ void lambda$fetchNewCredentialsAsync$2$DeviceAuthenticator(DeviceCredentials credentials, Request request) {
        if (this.mDeviceAuth != null) {
            Log.d(TAG, "Calling DeviceAuth.authenticate()");
            this.mDeviceAuth.authenticate(Constants.DEVICE_AUTH_SERVICE_ACCESS_TOKEN, credentials != null ? credentials.mEntId : null, new DeviceAuth.AccessTokenCallback(request) {
                /* class com.oculus.deviceauthserver.$$Lambda$DeviceAuthenticator$vgn9TI7yg1whfpcIxDSJuuFp10 */
                private final /* synthetic */ DeviceAuthenticator.Request f$1;

                {
                    this.f$1 = r2;
                }

                @Override // com.oculus.devicecertservice.DeviceAuth.AccessTokenCallback
                public final void call(String str, int i, String str2) {
                    DeviceAuthenticator.this.lambda$fetchNewCredentialsAsync$0$DeviceAuthenticator(this.f$1, str, i, str2);
                }
            }, new DeviceAuthErrorCallback(request) {
                /* class com.oculus.deviceauthserver.$$Lambda$DeviceAuthenticator$q1qZtoeDcn3Ys7GpzHdmnKa_O9E */
                private final /* synthetic */ DeviceAuthenticator.Request f$1;

                {
                    this.f$1 = r2;
                }

                @Override // com.oculus.devicecertservice.DeviceAuthErrorCallback
                public final void call(String str, Exception exc) {
                    DeviceAuthenticator.this.lambda$fetchNewCredentialsAsync$1$DeviceAuthenticator(this.f$1, str, exc);
                }
            });
            return;
        }
        this.mFuture.completeExceptionally(new DeviceIdentityException("DeviceAuth not initialized"));
    }

    /* access modifiers changed from: private */
    /* renamed from: handleResponse */
    public void lambda$fetchNewCredentialsAsync$0$DeviceAuthenticator(String token, String entId, int sessionTtl, Request request) {
        Log.d(TAG, "Received new credentials");
        DeviceCredentials credentials = new DeviceCredentials(new DeviceToken(token, request.mStartTime.plus(TimeUnit.SECONDS.toMillis((long) sessionTtl))), entId);
        this.mStore.setCredentials(credentials);
        if (!TextUtils.isEmpty(entId)) {
            new SettingsManager().setString("device_ent_id", entId);
        } else {
            Log.e(TAG, "entId is null or empty");
        }
        request.mFuture.complete(credentials);
        notifyClients();
    }

    /* access modifiers changed from: private */
    /* renamed from: handleError */
    public void lambda$fetchNewCredentialsAsync$1$DeviceAuthenticator(String errorMessage, Exception ex, Request request) {
        String str = TAG;
        Log.e(str, "\n\nERROR: " + errorMessage + "\n\n" + ex.toString() + "\n\n");
        request.mFuture.completeExceptionally(ex);
    }

    private void notifyClients() {
        Intent intent = new Intent();
        intent.setAction(NOTIFICATION_NEW_TOKEN_AVAILABLE);
        this.mContext.sendBroadcastAsUser(intent, UserHandle.ALL);
    }

    /* access modifiers changed from: private */
    public static final class Request {
        final CompletableFuture<DeviceCredentials> mFuture;
        final BootCountedRealtime mStartTime;

        Request(Context context, CompletableFuture<DeviceCredentials> future) {
            this.mFuture = future;
            this.mStartTime = BootCountedRealtime.current(context);
        }
    }
}
