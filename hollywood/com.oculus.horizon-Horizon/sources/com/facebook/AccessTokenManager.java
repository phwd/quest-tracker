package com.facebook;

import X.AnonymousClass006;
import X.AnonymousClass0B0;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphRequestBatch;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;
import com.oculus.horizon.abuse_prevention.VideoUploaderCleanerServiceManager;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;
import org.json.JSONObject;

public final class AccessTokenManager {
    public static final String ACTION_CURRENT_ACCESS_TOKEN_CHANGED = "com.facebook.sdk.ACTION_CURRENT_ACCESS_TOKEN_CHANGED";
    public static final String EXTRA_NEW_ACCESS_TOKEN = "com.facebook.sdk.EXTRA_NEW_ACCESS_TOKEN";
    public static final String EXTRA_OLD_ACCESS_TOKEN = "com.facebook.sdk.EXTRA_OLD_ACCESS_TOKEN";
    public static final String ME_PERMISSIONS_GRAPH_PATH = "me/permissions";
    public static final String SHARED_PREFERENCES_NAME = "com.facebook.AccessTokenManager.SharedPreferences";
    public static final String TAG = "AccessTokenManager";
    public static final String TOKEN_EXTEND_GRAPH_PATH = "oauth/access_token";
    public static final int TOKEN_EXTEND_RETRY_SECONDS = 3600;
    public static final int TOKEN_EXTEND_THRESHOLD_SECONDS = 86400;
    public static volatile AccessTokenManager instance;
    public final AccessTokenCache accessTokenCache;
    public AccessToken currentAccessToken;
    public Date lastAttemptedTokenExtendDate = new Date(0);
    public final AnonymousClass0B0 localBroadcastManager;
    public AtomicBoolean tokenRefreshInProgress = new AtomicBoolean(false);

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void refreshCurrentAccessTokenImpl(final AccessToken.AccessTokenRefreshCallback accessTokenRefreshCallback) {
        String str;
        final AccessToken accessToken = this.currentAccessToken;
        if (accessToken == null) {
            if (accessTokenRefreshCallback != null) {
                str = "No current access token to refresh";
            } else {
                return;
            }
        } else if (this.tokenRefreshInProgress.compareAndSet(false, true)) {
            this.lastAttemptedTokenExtendDate = new Date();
            final HashSet hashSet = new HashSet();
            final HashSet hashSet2 = new HashSet();
            final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
            final RefreshResult refreshResult = new RefreshResult();
            GraphRequestBatch graphRequestBatch = new GraphRequestBatch(createGrantedPermissionsRequest(accessToken, new GraphRequest.Callback() {
                /* class com.facebook.AccessTokenManager.AnonymousClass2 */

                @Override // com.facebook.GraphRequest.Callback
                public void onCompleted(GraphResponse graphResponse) {
                    JSONArray optJSONArray;
                    Set set;
                    JSONObject jSONObject = graphResponse.graphObject;
                    if (!(jSONObject == null || (optJSONArray = jSONObject.optJSONArray("data")) == null)) {
                        atomicBoolean.set(true);
                        for (int i = 0; i < optJSONArray.length(); i++) {
                            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                            if (optJSONObject != null) {
                                String optString = optJSONObject.optString("permission");
                                String optString2 = optJSONObject.optString("status");
                                if (!Utility.isNullOrEmpty(optString) && !Utility.isNullOrEmpty(optString2)) {
                                    String lowerCase = optString2.toLowerCase(Locale.US);
                                    if (lowerCase.equals("granted")) {
                                        set = hashSet;
                                    } else if (lowerCase.equals("declined")) {
                                        set = hashSet2;
                                    } else {
                                        Log.w(AccessTokenManager.TAG, AnonymousClass006.A05("Unexpected status: ", lowerCase));
                                    }
                                    set.add(optString);
                                }
                            }
                        }
                    }
                }
            }), createExtendAccessTokenRequest(accessToken, new GraphRequest.Callback() {
                /* class com.facebook.AccessTokenManager.AnonymousClass3 */

                @Override // com.facebook.GraphRequest.Callback
                public void onCompleted(GraphResponse graphResponse) {
                    JSONObject jSONObject = graphResponse.graphObject;
                    if (jSONObject != null) {
                        refreshResult.accessToken = jSONObject.optString("access_token");
                        refreshResult.expiresAt = jSONObject.optInt(AccessToken.EXPIRES_AT_KEY);
                    }
                }
            }));
            graphRequestBatch.addCallback(new GraphRequestBatch.Callback() {
                /* class com.facebook.AccessTokenManager.AnonymousClass4 */

                @Override // com.facebook.GraphRequestBatch.Callback
                public void onBatchCompleted(GraphRequestBatch graphRequestBatch) {
                    Throwable th;
                    AccessToken.AccessTokenRefreshCallback accessTokenRefreshCallback;
                    AccessToken.AccessTokenRefreshCallback accessTokenRefreshCallback2;
                    FacebookException facebookException;
                    Set<String> set;
                    Set<String> set2;
                    Date date;
                    AccessToken accessToken = null;
                    try {
                        if (AccessTokenManager.getInstance().currentAccessToken == null || AccessTokenManager.getInstance().currentAccessToken.userId != accessToken.userId) {
                            accessTokenRefreshCallback2 = accessTokenRefreshCallback;
                            if (accessTokenRefreshCallback2 != null) {
                                facebookException = new FacebookException("No current access token to refresh");
                            }
                            AccessTokenManager.this.tokenRefreshInProgress.set(false);
                        }
                        if (!atomicBoolean.get()) {
                            RefreshResult refreshResult = refreshResult;
                            if (refreshResult.accessToken == null && refreshResult.expiresAt == 0) {
                                accessTokenRefreshCallback2 = accessTokenRefreshCallback;
                                if (accessTokenRefreshCallback2 != null) {
                                    facebookException = new FacebookException("Failed to refresh access token");
                                }
                                AccessTokenManager.this.tokenRefreshInProgress.set(false);
                            }
                        }
                        String str = refreshResult.accessToken;
                        if (str == null) {
                            str = accessToken.token;
                        }
                        AccessToken accessToken2 = accessToken;
                        String str2 = accessToken2.applicationId;
                        String str3 = accessToken2.userId;
                        if (atomicBoolean.get()) {
                            set = hashSet;
                        } else {
                            set = accessToken.permissions;
                        }
                        if (atomicBoolean.get()) {
                            set2 = hashSet2;
                        } else {
                            set2 = accessToken.declinedPermissions;
                        }
                        AccessToken accessToken3 = accessToken;
                        AccessTokenSource accessTokenSource = accessToken3.source;
                        int i = refreshResult.expiresAt;
                        if (i != 0) {
                            date = new Date(((long) i) * 1000);
                        } else {
                            date = accessToken3.expires;
                        }
                        AccessToken accessToken4 = new AccessToken(str, str2, str3, set, set2, accessTokenSource, date, new Date());
                        try {
                            AccessTokenManager.getInstance().setCurrentAccessToken(accessToken4);
                            AccessTokenManager.this.tokenRefreshInProgress.set(false);
                            AccessToken.AccessTokenRefreshCallback accessTokenRefreshCallback3 = accessTokenRefreshCallback;
                            if (accessTokenRefreshCallback3 != null) {
                                accessTokenRefreshCallback3.OnTokenRefreshed(accessToken4);
                                return;
                            }
                            return;
                        } catch (Throwable th2) {
                            th = th2;
                            accessToken = accessToken4;
                            AccessTokenManager.this.tokenRefreshInProgress.set(false);
                            accessTokenRefreshCallback = accessTokenRefreshCallback;
                            accessTokenRefreshCallback.OnTokenRefreshed(accessToken);
                            throw th;
                        }
                        accessTokenRefreshCallback2.OnTokenRefreshFailed(facebookException);
                        AccessTokenManager.this.tokenRefreshInProgress.set(false);
                    } catch (Throwable th3) {
                        th = th3;
                        AccessTokenManager.this.tokenRefreshInProgress.set(false);
                        accessTokenRefreshCallback = accessTokenRefreshCallback;
                        if (!(accessTokenRefreshCallback == null || accessToken == null)) {
                            accessTokenRefreshCallback.OnTokenRefreshed(accessToken);
                        }
                        throw th;
                    }
                }
            });
            GraphRequest.executeBatchAsync(graphRequestBatch);
            return;
        } else if (accessTokenRefreshCallback != null) {
            str = "Refresh already in progress";
        } else {
            return;
        }
        accessTokenRefreshCallback.OnTokenRefreshFailed(new FacebookException(str));
    }

    public static GraphRequest createExtendAccessTokenRequest(AccessToken accessToken, GraphRequest.Callback callback) {
        Bundle bundle = new Bundle();
        bundle.putString("grant_type", "fb_extend_sso_token");
        return new GraphRequest(accessToken, TOKEN_EXTEND_GRAPH_PATH, bundle, HttpMethod.GET, callback);
    }

    public static GraphRequest createGrantedPermissionsRequest(AccessToken accessToken, GraphRequest.Callback callback) {
        return new GraphRequest(accessToken, ME_PERMISSIONS_GRAPH_PATH, new Bundle(), HttpMethod.GET, callback);
    }

    public static AccessTokenManager getInstance() {
        if (instance == null) {
            synchronized (AccessTokenManager.class) {
                if (instance == null) {
                    Validate.sdkInitialized();
                    instance = new AccessTokenManager(AnonymousClass0B0.A00(FacebookSdk.applicationContext), new AccessTokenCache());
                }
            }
        }
        return instance;
    }

    private void sendCurrentAccessTokenChangedBroadcast(AccessToken accessToken, AccessToken accessToken2) {
        Intent intent = new Intent(ACTION_CURRENT_ACCESS_TOKEN_CHANGED);
        intent.putExtra(EXTRA_OLD_ACCESS_TOKEN, accessToken);
        intent.putExtra(EXTRA_NEW_ACCESS_TOKEN, accessToken2);
        this.localBroadcastManager.A03(intent);
    }

    private boolean shouldExtendAccessToken() {
        if (this.currentAccessToken == null) {
            return false;
        }
        Long valueOf = Long.valueOf(new Date().getTime());
        if (!this.currentAccessToken.source.canExtendToken()) {
            return false;
        }
        long longValue = valueOf.longValue();
        if (longValue - this.lastAttemptedTokenExtendDate.getTime() <= 3600000 || longValue - this.currentAccessToken.lastRefresh.getTime() <= VideoUploaderCleanerServiceManager.STALE_THRESHOLD_MILLIS) {
            return false;
        }
        return true;
    }

    public boolean loadCurrentAccessToken() {
        AccessToken load = this.accessTokenCache.load();
        if (load == null) {
            return false;
        }
        setCurrentAccessToken(load, false);
        return true;
    }

    public AccessTokenManager(AnonymousClass0B0 r4, AccessTokenCache accessTokenCache2) {
        Validate.notNull(r4, "localBroadcastManager");
        Validate.notNull(accessTokenCache2, "accessTokenCache");
        this.localBroadcastManager = r4;
        this.accessTokenCache = accessTokenCache2;
    }

    public void extendAccessTokenIfNeeded() {
        if (shouldExtendAccessToken()) {
            refreshCurrentAccessToken(null);
        }
    }

    public AccessToken getCurrentAccessToken() {
        return this.currentAccessToken;
    }

    public void refreshCurrentAccessToken(final AccessToken.AccessTokenRefreshCallback accessTokenRefreshCallback) {
        if (Looper.getMainLooper().equals(Looper.myLooper())) {
            refreshCurrentAccessTokenImpl(accessTokenRefreshCallback);
        } else {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                /* class com.facebook.AccessTokenManager.AnonymousClass1 */

                public void run() {
                    AccessTokenManager.this.refreshCurrentAccessTokenImpl(accessTokenRefreshCallback);
                }
            });
        }
    }

    private void setCurrentAccessToken(AccessToken accessToken, boolean z) {
        AccessToken accessToken2 = this.currentAccessToken;
        this.currentAccessToken = accessToken;
        this.tokenRefreshInProgress.set(false);
        this.lastAttemptedTokenExtendDate = new Date(0);
        if (z) {
            AccessTokenCache accessTokenCache2 = this.accessTokenCache;
            if (accessToken != null) {
                accessTokenCache2.save(accessToken);
            } else {
                accessTokenCache2.clear();
                Validate.sdkInitialized();
                Utility.clearFacebookCookies(FacebookSdk.applicationContext);
            }
        }
        if (!Utility.areObjectsEqual(accessToken2, accessToken)) {
            sendCurrentAccessTokenChangedBroadcast(accessToken2, accessToken);
        }
    }

    public void setCurrentAccessToken(AccessToken accessToken) {
        setCurrentAccessToken(accessToken, true);
    }

    public static class RefreshResult {
        public String accessToken;
        public int expiresAt;

        public RefreshResult() {
        }

        public /* synthetic */ RefreshResult(AnonymousClass1 r1) {
        }
    }
}
