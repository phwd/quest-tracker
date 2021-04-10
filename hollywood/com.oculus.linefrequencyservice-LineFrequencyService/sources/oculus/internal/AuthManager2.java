package oculus.internal;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.oculus.aidl.IAuthService2;
import com.oculus.aidl.IAuthenticationCallback;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class AuthManager2 {
    private static final String ACTION_ACCESS_TOKEN_CHANGED = "com.oculus.horizon.ACCESS_TOKEN_CHANGED";
    private static final String ACTION_DSAT_ACCESS_TOKEN_CHANGED = "com.oculus.horizon.DEVICE_SCOPED_ACCESS_TOKEN_CHANGED";
    private static final boolean DEBUG = false;
    private static final String EXTRA_ACCESS_TOKEN = "access_token";
    private static final String EXTRA_USER_ID = "user_id";
    private static Long HORIZON_APP_ID = new Long(826037204154824L);
    private static final String HORIZON_AUTH_PERMISSION = "com.oculus.horizon.auth";
    private static final String TAG = "AuthManager2";
    private static AuthManager2 sInstance;
    private final Context mApplicationContext;
    Map<Long, Credentials> mCachedCredentials;
    Map<Long, Credentials> mCachedDeviceScopedCredentials;

    public static class Credentials {
        public final String accessToken;
        public final boolean deviceScoped;
        public final String userId;

        Credentials(String accessToken2, String userId2, boolean deviceScoped2) {
            this.accessToken = accessToken2;
            this.userId = userId2;
            this.deviceScoped = deviceScoped2;
        }

        public String getAccessToken() {
            return this.accessToken;
        }

        public String getUserId() {
            return this.userId;
        }
    }

    /* access modifiers changed from: package-private */
    public class BlockingAuthServiceConnection implements ServiceConnection {
        IAuthService2 authService;
        CountDownLatch connection = new CountDownLatch(1);

        public BlockingAuthServiceConnection() {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(Constants.HORIZON_PACKAGE, "com.oculus.auth.service.AuthService2"));
            AuthManager2.this.mApplicationContext.bindService(intent, this, 1);
        }

        public void onServiceConnected(ComponentName name, IBinder service) {
            this.authService = IAuthService2.Stub.asInterface(service);
            this.connection.countDown();
        }

        public void onServiceDisconnected(ComponentName name) {
            this.authService = null;
            this.connection.countDown();
        }

        public <T> T connectAndRun(Function<IAuthService2, T> f) {
            if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
                T result = null;
                try {
                    if (this.authService == null) {
                        Log.d(AuthManager2.TAG, "authService was null, awaiting connection");
                        this.connection.await(5, TimeUnit.SECONDS);
                    }
                    if (this.authService == null) {
                        Log.d(AuthManager2.TAG, "authService still null");
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                try {
                    result = this.authService != null ? f.apply(this.authService) : null;
                } catch (Exception e2) {
                    Log.e(AuthManager2.TAG, "Exception in connection to Authentication service", e2);
                } catch (Throwable th) {
                    AuthManager2.this.mApplicationContext.unbindService(this);
                    this.authService = null;
                    throw th;
                }
                AuthManager2.this.mApplicationContext.unbindService(this);
                this.authService = null;
                return result;
            }
            throw new IllegalStateException("Cannot call blocking auth function from main looper.");
        }
    }

    /* access modifiers changed from: private */
    public class CachingAuthenticationFetcher extends IAuthenticationCallback.Stub {
        private final Long mAppId;
        private final CompletableFuture<Credentials> mFuture;
        private final boolean mIsDeviceScoped;
        private final ServiceConnection mServiceConnection;

        private CachingAuthenticationFetcher(final Long appId, boolean isDeviceScoped, final boolean forceRefresh) {
            this.mFuture = new CompletableFuture<>();
            this.mAppId = appId;
            this.mIsDeviceScoped = isDeviceScoped;
            this.mServiceConnection = new ServiceConnection(AuthManager2.this) {
                /* class oculus.internal.AuthManager2.CachingAuthenticationFetcher.AnonymousClass1 */

                public void onServiceConnected(ComponentName name, IBinder service) {
                    IAuthService2 authService = IAuthService2.Stub.asInterface(service);
                    try {
                        if (CachingAuthenticationFetcher.this.mIsDeviceScoped) {
                            authService.fetchDeviceScopedCredentials(AuthManager2.this.mApplicationContext.getPackageName(), CachingAuthenticationFetcher.this.mAppId.longValue(), forceRefresh, CachingAuthenticationFetcher.this);
                        } else {
                            authService.fetchCredentials(AuthManager2.this.mApplicationContext.getPackageName(), appId.longValue(), forceRefresh, CachingAuthenticationFetcher.this);
                        }
                    } catch (RemoteException e) {
                        CachingAuthenticationFetcher.this.mFuture.completeExceptionally(e);
                    }
                }

                public void onServiceDisconnected(ComponentName name) {
                    CachingAuthenticationFetcher.this.mFuture.completeExceptionally(new RuntimeException("Service disconnected"));
                }
            };
        }

        @Override // com.oculus.aidl.IAuthenticationCallback
        public void onSuccess(Bundle result) {
            Credentials creds = new Credentials(result.getString(AuthManager2.EXTRA_ACCESS_TOKEN), result.getString(AuthManager2.EXTRA_USER_ID), this.mIsDeviceScoped);
            if (this.mIsDeviceScoped) {
                AuthManager2.this.mCachedDeviceScopedCredentials.put(this.mAppId, creds);
            } else {
                AuthManager2.this.mCachedCredentials.put(this.mAppId, creds);
            }
            this.mFuture.complete(creds);
            AuthManager2.this.mApplicationContext.unbindService(this.mServiceConnection);
        }

        @Override // com.oculus.aidl.IAuthenticationCallback
        public void onFailure(int errorCode, Bundle extras) {
            this.mFuture.completeExceptionally(new RuntimeException(String.format("Failed to authenticate with error code: %d", Integer.valueOf(errorCode))));
            AuthManager2.this.mApplicationContext.unbindService(this.mServiceConnection);
        }
    }

    private CompletableFuture<Credentials> internalFetchCredentials(long appId, boolean isDeviceScoped, boolean forceRefresh) {
        CachingAuthenticationFetcher fetcher = new CachingAuthenticationFetcher(Long.valueOf(appId), isDeviceScoped, forceRefresh);
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(Constants.HORIZON_PACKAGE, "com.oculus.auth.service.AuthService2"));
        if (this.mApplicationContext.bindService(intent, fetcher.mServiceConnection, 1)) {
            return fetcher.mFuture;
        }
        throw new SecurityException("Unable to bind to service");
    }

    private AuthManager2(Context context) {
        if (context != null) {
            this.mApplicationContext = context.getApplicationContext();
            this.mCachedCredentials = new HashMap();
            this.mCachedDeviceScopedCredentials = new HashMap();
            IntentFilter filter = new IntentFilter();
            filter.addAction(ACTION_ACCESS_TOKEN_CHANGED);
            filter.addAction(ACTION_DSAT_ACCESS_TOKEN_CHANGED);
            this.mApplicationContext.registerReceiver(new BroadcastReceiver() {
                /* class oculus.internal.AuthManager2.AnonymousClass1 */

                public void onReceive(Context context, Intent intent) {
                    if (intent != null) {
                        synchronized (AuthManager2.sInstance) {
                            String action = intent.getAction();
                            if (TextUtils.equals(AuthManager2.ACTION_ACCESS_TOKEN_CHANGED, action)) {
                                AuthManager2.this.mCachedCredentials.clear();
                            } else if (TextUtils.equals(AuthManager2.ACTION_DSAT_ACCESS_TOKEN_CHANGED, action)) {
                                AuthManager2.this.mCachedDeviceScopedCredentials.clear();
                            }
                        }
                    }
                }
            }, filter, HORIZON_AUTH_PERMISSION, null);
            return;
        }
        throw new RuntimeException("context cannot be null");
    }

    public static synchronized AuthManager2 getInstance(Context context) {
        AuthManager2 authManager2;
        synchronized (AuthManager2.class) {
            if (sInstance == null) {
                sInstance = new AuthManager2(context);
            }
            authManager2 = sInstance;
        }
        return authManager2;
    }

    public String getAccessTokenBlocking(boolean forceUpdateCache) {
        Credentials creds = getCredentialsBlocking(forceUpdateCache);
        if (creds == null) {
            return null;
        }
        return creds.getAccessToken();
    }

    public String getDeviceScopedAccessTokenBlocking(boolean forceUpdateCache) {
        Credentials creds = getDeviceScopedCredentialsBlocking(forceUpdateCache);
        if (creds == null) {
            return null;
        }
        return creds.accessToken;
    }

    public Credentials getCredentialsBlocking(boolean forceUpdateCache) {
        synchronized (sInstance) {
            if (!forceUpdateCache) {
                if (this.mCachedCredentials.get(HORIZON_APP_ID) != null) {
                    return this.mCachedCredentials.get(HORIZON_APP_ID);
                }
            }
            return (Credentials) new BlockingAuthServiceConnection().connectAndRun(new Function() {
                /* class oculus.internal.$$Lambda$AuthManager2$jjsJrqYQWyx0bEtDfmMiuUTb0cs */

                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return AuthManager2.this.lambda$getCredentialsBlocking$0$AuthManager2((IAuthService2) obj);
                }
            });
        }
    }

    public /* synthetic */ Credentials lambda$getCredentialsBlocking$0$AuthManager2(IAuthService2 authService) {
        Credentials creds;
        try {
            Bundle bundle = authService.getCredentials();
            if (bundle == null) {
                return null;
            }
            synchronized (sInstance) {
                creds = new Credentials(bundle.getString(EXTRA_ACCESS_TOKEN), bundle.getString(EXTRA_USER_ID), DEBUG);
                this.mCachedCredentials.put(HORIZON_APP_ID, creds);
            }
            return creds;
        } catch (RemoteException e) {
            return null;
        }
    }

    public Credentials getDeviceScopedCredentialsBlocking(boolean forceUpdateCache) {
        synchronized (sInstance) {
            if (!forceUpdateCache) {
                if (this.mCachedDeviceScopedCredentials.get(HORIZON_APP_ID) != null) {
                    return this.mCachedDeviceScopedCredentials.get(HORIZON_APP_ID);
                }
            }
            return (Credentials) new BlockingAuthServiceConnection().connectAndRun(new Function() {
                /* class oculus.internal.$$Lambda$AuthManager2$YkZONo2sOP1_I5FghL1BjQtqhqo */

                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return AuthManager2.this.lambda$getDeviceScopedCredentialsBlocking$1$AuthManager2((IAuthService2) obj);
                }
            });
        }
    }

    public /* synthetic */ Credentials lambda$getDeviceScopedCredentialsBlocking$1$AuthManager2(IAuthService2 authService) {
        Credentials creds;
        try {
            Bundle bundle = authService.getDeviceScopedCredentials();
            if (bundle == null) {
                return null;
            }
            synchronized (sInstance) {
                creds = new Credentials(bundle.getString(EXTRA_ACCESS_TOKEN), bundle.getString(EXTRA_USER_ID), true);
                this.mCachedDeviceScopedCredentials.put(HORIZON_APP_ID, creds);
            }
            return creds;
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to get user credentials");
            return null;
        }
    }

    public CompletableFuture<Credentials> fetchCredentials(Long appId, boolean forceRefresh) {
        synchronized (sInstance) {
            if (!forceRefresh) {
                if (this.mCachedCredentials.get(appId) != null) {
                    return CompletableFuture.completedFuture(this.mCachedCredentials.get(appId));
                }
            }
            return internalFetchCredentials(appId.longValue(), DEBUG, forceRefresh);
        }
    }

    public CompletableFuture<Credentials> fetchDeviceScopedCredentials(Long appId, boolean forceRefresh) {
        synchronized (sInstance) {
            if (!forceRefresh) {
                if (this.mCachedDeviceScopedCredentials.get(appId) != null) {
                    return CompletableFuture.completedFuture(this.mCachedDeviceScopedCredentials.get(appId));
                }
            }
            return internalFetchCredentials(appId.longValue(), true, forceRefresh);
        }
    }
}
