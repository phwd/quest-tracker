package com.facebook;

import android.content.SharedPreferences;
import com.facebook.internal.Validate;
import com.oculus.horizon.fbconnect.FBConnectHelper;
import org.json.JSONException;
import org.json.JSONObject;

public final class ProfileCache {
    public static final String CACHED_PROFILE_KEY = "com.facebook.ProfileManager.CachedProfile";
    public static final String SHARED_PREFERENCES_NAME = "com.facebook.AccessTokenManager.SharedPreferences";
    public final SharedPreferences sharedPreferences = FacebookSdk.applicationContext.getSharedPreferences("com.facebook.AccessTokenManager.SharedPreferences", 0);

    public void clear() {
        this.sharedPreferences.edit().remove(CACHED_PROFILE_KEY).apply();
    }

    public Profile load() {
        String string = this.sharedPreferences.getString(CACHED_PROFILE_KEY, null);
        if (string != null) {
            try {
                return new Profile(new JSONObject(string));
            } catch (JSONException unused) {
            }
        }
        return null;
    }

    public void save(Profile profile) {
        Validate.notNull(profile, FBConnectHelper.FACEBOOK_DATA_PROFILE_KEY);
        JSONObject jSONObject = profile.toJSONObject();
        if (jSONObject != null) {
            this.sharedPreferences.edit().putString(CACHED_PROFILE_KEY, jSONObject.toString()).apply();
        }
    }

    public ProfileCache() {
        Validate.sdkInitialized();
    }
}
