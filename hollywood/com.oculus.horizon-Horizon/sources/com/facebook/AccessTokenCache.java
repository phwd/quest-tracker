package com.facebook;

import android.content.SharedPreferences;
import android.os.Bundle;
import com.facebook.internal.Validate;
import org.json.JSONException;
import org.json.JSONObject;

public class AccessTokenCache {
    public static final String CACHED_ACCESS_TOKEN_KEY = "com.facebook.AccessTokenManager.CachedAccessToken";
    public final SharedPreferences sharedPreferences;
    public LegacyTokenHelper tokenCachingStrategy;
    public final SharedPreferencesTokenCachingStrategyFactory tokenCachingStrategyFactory;

    public static class SharedPreferencesTokenCachingStrategyFactory {
        public LegacyTokenHelper create() {
            Validate.sdkInitialized();
            return new LegacyTokenHelper(FacebookSdk.applicationContext, null);
        }
    }

    private AccessToken getCachedAccessToken() {
        String string = this.sharedPreferences.getString(CACHED_ACCESS_TOKEN_KEY, null);
        if (string != null) {
            try {
                return AccessToken.createFromJSONObject(new JSONObject(string));
            } catch (JSONException unused) {
            }
        }
        return null;
    }

    private LegacyTokenHelper getTokenCachingStrategy() {
        if (this.tokenCachingStrategy == null) {
            synchronized (this) {
                if (this.tokenCachingStrategy == null) {
                    this.tokenCachingStrategy = this.tokenCachingStrategyFactory.create();
                }
            }
        }
        return this.tokenCachingStrategy;
    }

    private boolean hasCachedAccessToken() {
        return this.sharedPreferences.contains(CACHED_ACCESS_TOKEN_KEY);
    }

    public void clear() {
        this.sharedPreferences.edit().remove(CACHED_ACCESS_TOKEN_KEY).apply();
        if (FacebookSdk.isLegacyTokenUpgradeSupported) {
            getTokenCachingStrategy().clear();
        }
    }

    public void save(AccessToken accessToken) {
        Validate.notNull(accessToken, "accessToken");
        try {
            this.sharedPreferences.edit().putString(CACHED_ACCESS_TOKEN_KEY, accessToken.toJSONObject().toString()).apply();
        } catch (JSONException unused) {
        }
    }

    private AccessToken getLegacyAccessToken() {
        Bundle load = getTokenCachingStrategy().load();
        if (load == null || !LegacyTokenHelper.hasTokenInformation(load)) {
            return null;
        }
        return AccessToken.createFromLegacyCache(load);
    }

    private boolean shouldCheckLegacyToken() {
        return FacebookSdk.isLegacyTokenUpgradeSupported;
    }

    public AccessToken load() {
        if (hasCachedAccessToken()) {
            return getCachedAccessToken();
        }
        if (!FacebookSdk.isLegacyTokenUpgradeSupported) {
            return null;
        }
        AccessToken legacyAccessToken = getLegacyAccessToken();
        if (legacyAccessToken == null) {
            return legacyAccessToken;
        }
        save(legacyAccessToken);
        getTokenCachingStrategy().clear();
        return legacyAccessToken;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public AccessTokenCache() {
        this(FacebookSdk.applicationContext.getSharedPreferences("com.facebook.AccessTokenManager.SharedPreferences", 0), new SharedPreferencesTokenCachingStrategyFactory());
        Validate.sdkInitialized();
    }

    public AccessTokenCache(SharedPreferences sharedPreferences2, SharedPreferencesTokenCachingStrategyFactory sharedPreferencesTokenCachingStrategyFactory) {
        this.sharedPreferences = sharedPreferences2;
        this.tokenCachingStrategyFactory = sharedPreferencesTokenCachingStrategyFactory;
    }
}
