package com.facebook;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;
import com.oculus.security.basecomponent.OculusIntentLogger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class AccessToken implements Parcelable {
    public static final String ACCESS_TOKEN_KEY = "access_token";
    public static final String APPLICATION_ID_KEY = "application_id";
    public static final Parcelable.Creator<AccessToken> CREATOR = new Parcelable.Creator() {
        /* class com.facebook.AccessToken.AnonymousClass2 */

        @Override // android.os.Parcelable.Creator
        public AccessToken createFromParcel(Parcel parcel) {
            return new AccessToken(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public AccessToken[] newArray(int i) {
            return new AccessToken[i];
        }
    };
    public static final int CURRENT_JSON_FORMAT = 1;
    public static final String DECLINED_PERMISSIONS_KEY = "declined_permissions";
    public static final AccessTokenSource DEFAULT_ACCESS_TOKEN_SOURCE = AccessTokenSource.FACEBOOK_APPLICATION_WEB;
    public static final Date DEFAULT_EXPIRATION_TIME;
    public static final Date DEFAULT_LAST_REFRESH_TIME = new Date();
    public static final String EXPIRES_AT_KEY = "expires_at";
    public static final String EXPIRES_IN_KEY = "expires_in";
    public static final String LAST_REFRESH_KEY = "last_refresh";
    public static final Date MAX_DATE;
    public static final String PERMISSIONS_KEY = "permissions";
    public static final String SOURCE_KEY = "source";
    public static final String TOKEN_KEY = "token";
    public static final String USER_ID_KEY = "user_id";
    public static final String VERSION_KEY = "version";
    public final String applicationId;
    public final Set<String> declinedPermissions;
    public final Date expires;
    public final Date lastRefresh;
    public final Set<String> permissions;
    public final AccessTokenSource source;
    public final String token;
    public final String userId;

    public interface AccessTokenCreationCallback {
        void onError(FacebookException facebookException);

        void onSuccess(AccessToken accessToken);
    }

    public interface AccessTokenRefreshCallback {
        void OnTokenRefreshFailed(FacebookException facebookException);

        void OnTokenRefreshed(AccessToken accessToken);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        String str;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AccessToken)) {
            return false;
        }
        AccessToken accessToken = (AccessToken) obj;
        return this.expires.equals(accessToken.expires) && this.permissions.equals(accessToken.permissions) && this.declinedPermissions.equals(accessToken.declinedPermissions) && this.token.equals(accessToken.token) && this.source == accessToken.source && this.lastRefresh.equals(accessToken.lastRefresh) && ((str = this.applicationId) != null ? str.equals(accessToken.applicationId) : accessToken.applicationId == null) && this.userId.equals(accessToken.userId);
    }

    private void appendPermissions(StringBuilder sb) {
        String str;
        sb.append(" permissions:");
        Set<String> set = this.permissions;
        if (set == null) {
            str = "null";
        } else {
            sb.append("[");
            sb.append(TextUtils.join(", ", set));
            str = "]";
        }
        sb.append(str);
    }

    public static AccessToken createFromBundle(List<String> list, Bundle bundle, AccessTokenSource accessTokenSource, Date date, String str) {
        String string = bundle.getString("access_token");
        Date bundleLongAsDate = Utility.getBundleLongAsDate(bundle, EXPIRES_IN_KEY, date);
        String string2 = bundle.getString("user_id");
        if (Utility.isNullOrEmpty(string) || bundleLongAsDate == null) {
            return null;
        }
        return new AccessToken(string, str, string2, list, null, accessTokenSource, bundleLongAsDate, new Date());
    }

    public static AccessToken createFromJSONObject(JSONObject jSONObject) throws JSONException {
        if (jSONObject.getInt("version") <= 1) {
            String string = jSONObject.getString("token");
            Date date = new Date(jSONObject.getLong(EXPIRES_AT_KEY));
            JSONArray jSONArray = jSONObject.getJSONArray("permissions");
            JSONArray jSONArray2 = jSONObject.getJSONArray(DECLINED_PERMISSIONS_KEY);
            Date date2 = new Date(jSONObject.getLong(LAST_REFRESH_KEY));
            return new AccessToken(string, jSONObject.getString("application_id"), jSONObject.getString("user_id"), Utility.jsonArrayToStringList(jSONArray), Utility.jsonArrayToStringList(jSONArray2), AccessTokenSource.valueOf(jSONObject.getString("source")), date, date2);
        }
        throw new FacebookException("Unknown AccessToken serialization format.");
    }

    public static AccessToken createFromLegacyCache(Bundle bundle) {
        List<String> permissionsFromBundle = getPermissionsFromBundle(bundle, LegacyTokenHelper.PERMISSIONS_KEY);
        List<String> permissionsFromBundle2 = getPermissionsFromBundle(bundle, LegacyTokenHelper.DECLINED_PERMISSIONS_KEY);
        String applicationId2 = LegacyTokenHelper.getApplicationId(bundle);
        if (Utility.isNullOrEmpty(applicationId2)) {
            Validate.sdkInitialized();
            applicationId2 = FacebookSdk.applicationId;
        }
        String token2 = LegacyTokenHelper.getToken(bundle);
        try {
            return new AccessToken(token2, applicationId2, Utility.awaitGetGraphMeRequestWithCache(token2).getString("id"), permissionsFromBundle, permissionsFromBundle2, LegacyTokenHelper.getSource(bundle), LegacyTokenHelper.getDate(bundle, LegacyTokenHelper.EXPIRATION_DATE_KEY), LegacyTokenHelper.getDate(bundle, LegacyTokenHelper.LAST_REFRESH_DATE_KEY));
        } catch (JSONException unused) {
            return null;
        }
    }

    public static void createFromNativeLinkingIntent(Intent intent, final String str, final AccessTokenCreationCallback accessTokenCreationCallback) {
        String str2;
        Validate.notNull(intent, OculusIntentLogger.KEY_INTENT);
        if (intent.getExtras() == null) {
            str2 = "No extras found on intent";
        } else {
            final Bundle bundle = new Bundle(intent.getExtras());
            String string = bundle.getString("access_token");
            if (string == null || string.isEmpty()) {
                str2 = "No access token found on intent";
            } else {
                String string2 = bundle.getString("user_id");
                if (string2 == null || string2.isEmpty()) {
                    Utility.getGraphMeRequestWithCacheAsync(string, new Utility.GraphMeRequestWithCacheCallback() {
                        /* class com.facebook.AccessToken.AnonymousClass1 */

                        @Override // com.facebook.internal.Utility.GraphMeRequestWithCacheCallback
                        public void onFailure(FacebookException facebookException) {
                            accessTokenCreationCallback.onError(facebookException);
                        }

                        @Override // com.facebook.internal.Utility.GraphMeRequestWithCacheCallback
                        public void onSuccess(JSONObject jSONObject) {
                            try {
                                bundle.putString("user_id", jSONObject.getString("id"));
                                accessTokenCreationCallback.onSuccess(AccessToken.createFromBundle(null, bundle, AccessTokenSource.FACEBOOK_APPLICATION_WEB, new Date(), str));
                            } catch (JSONException unused) {
                                accessTokenCreationCallback.onError(new FacebookException("Unable to generate access token due to missing user id"));
                            }
                        }
                    });
                    return;
                } else {
                    accessTokenCreationCallback.onSuccess(createFromBundle(null, bundle, AccessTokenSource.FACEBOOK_APPLICATION_WEB, new Date(), str));
                    return;
                }
            }
        }
        accessTokenCreationCallback.onError(new FacebookException(str2));
    }

    @SuppressLint({"FieldGetter"})
    public static AccessToken createFromRefresh(AccessToken accessToken, Bundle bundle) {
        AccessTokenSource accessTokenSource = accessToken.source;
        if (accessTokenSource == AccessTokenSource.FACEBOOK_APPLICATION_WEB || accessTokenSource == AccessTokenSource.FACEBOOK_APPLICATION_NATIVE || accessTokenSource == AccessTokenSource.FACEBOOK_APPLICATION_SERVICE) {
            Date bundleLongAsDate = Utility.getBundleLongAsDate(bundle, EXPIRES_IN_KEY, new Date(0));
            String string = bundle.getString("access_token");
            if (Utility.isNullOrEmpty(string)) {
                return null;
            }
            return new AccessToken(string, accessToken.applicationId, accessToken.userId, accessToken.permissions, accessToken.declinedPermissions, accessToken.source, bundleLongAsDate, new Date());
        }
        StringBuilder sb = new StringBuilder("Invalid token source: ");
        sb.append(accessTokenSource);
        throw new FacebookException(sb.toString());
    }

    private String tokenToString() {
        if (this.token == null) {
            return "null";
        }
        if (FacebookSdk.isLoggingBehaviorEnabled(LoggingBehavior.INCLUDE_ACCESS_TOKENS)) {
            return this.token;
        }
        return "ACCESS_TOKEN_REMOVED";
    }

    public int hashCode() {
        int hashCode;
        int hashCode2 = (((((((((((527 + this.expires.hashCode()) * 31) + this.permissions.hashCode()) * 31) + this.declinedPermissions.hashCode()) * 31) + this.token.hashCode()) * 31) + this.source.hashCode()) * 31) + this.lastRefresh.hashCode()) * 31;
        String str = this.applicationId;
        if (str == null) {
            hashCode = 0;
        } else {
            hashCode = str.hashCode();
        }
        return ((hashCode2 + hashCode) * 31) + this.userId.hashCode();
    }

    public boolean isExpired() {
        return new Date().after(this.expires);
    }

    public JSONObject toJSONObject() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("version", 1);
        jSONObject.put("token", this.token);
        jSONObject.put(EXPIRES_AT_KEY, this.expires.getTime());
        jSONObject.put("permissions", new JSONArray((Collection) this.permissions));
        jSONObject.put(DECLINED_PERMISSIONS_KEY, new JSONArray((Collection) this.declinedPermissions));
        jSONObject.put(LAST_REFRESH_KEY, this.lastRefresh.getTime());
        jSONObject.put("source", this.source.name());
        jSONObject.put("application_id", this.applicationId);
        jSONObject.put("user_id", this.userId);
        return jSONObject;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("{AccessToken");
        sb.append(" token:");
        sb.append(tokenToString());
        appendPermissions(sb);
        sb.append("}");
        return sb.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.expires.getTime());
        parcel.writeStringList(new ArrayList(this.permissions));
        parcel.writeStringList(new ArrayList(this.declinedPermissions));
        parcel.writeString(this.token);
        parcel.writeString(this.source.name());
        parcel.writeLong(this.lastRefresh.getTime());
        parcel.writeString(this.applicationId);
        parcel.writeString(this.userId);
    }

    public static AccessToken getCurrentAccessToken() {
        return AccessTokenManager.getInstance().currentAccessToken;
    }

    public static List<String> getPermissionsFromBundle(Bundle bundle, String str) {
        ArrayList<String> stringArrayList = bundle.getStringArrayList(str);
        if (stringArrayList == null) {
            return Collections.emptyList();
        }
        return Collections.unmodifiableList(new ArrayList(stringArrayList));
    }

    public static void setCurrentAccessToken(AccessToken accessToken) {
        AccessTokenManager.getInstance().setCurrentAccessToken(accessToken);
    }

    public String getApplicationId() {
        return this.applicationId;
    }

    public Set<String> getDeclinedPermissions() {
        return this.declinedPermissions;
    }

    public Date getExpires() {
        return this.expires;
    }

    public Date getLastRefresh() {
        return this.lastRefresh;
    }

    public Set<String> getPermissions() {
        return this.permissions;
    }

    public AccessTokenSource getSource() {
        return this.source;
    }

    public String getToken() {
        return this.token;
    }

    public String getUserId() {
        return this.userId;
    }

    static {
        Date date = new Date(Long.MAX_VALUE);
        MAX_DATE = date;
        DEFAULT_EXPIRATION_TIME = date;
    }

    public AccessToken(Parcel parcel) {
        this.expires = new Date(parcel.readLong());
        ArrayList arrayList = new ArrayList();
        parcel.readStringList(arrayList);
        this.permissions = Collections.unmodifiableSet(new HashSet(arrayList));
        arrayList.clear();
        parcel.readStringList(arrayList);
        this.declinedPermissions = Collections.unmodifiableSet(new HashSet(arrayList));
        this.token = parcel.readString();
        this.source = AccessTokenSource.valueOf(parcel.readString());
        this.lastRefresh = new Date(parcel.readLong());
        this.applicationId = parcel.readString();
        this.userId = parcel.readString();
    }

    public AccessToken(String str, String str2, String str3, @Nullable Collection<String> collection, @Nullable Collection<String> collection2, @Nullable AccessTokenSource accessTokenSource, @Nullable Date date, @Nullable Date date2) {
        HashSet hashSet;
        HashSet hashSet2;
        Validate.notNullOrEmpty(str, "accessToken");
        Validate.notNullOrEmpty(str2, "applicationId");
        Validate.notNullOrEmpty(str3, "userId");
        this.expires = date == null ? DEFAULT_EXPIRATION_TIME : date;
        if (collection != null) {
            hashSet = new HashSet(collection);
        } else {
            hashSet = new HashSet();
        }
        this.permissions = Collections.unmodifiableSet(hashSet);
        if (collection2 != null) {
            hashSet2 = new HashSet(collection2);
        } else {
            hashSet2 = new HashSet();
        }
        this.declinedPermissions = Collections.unmodifiableSet(hashSet2);
        this.token = str;
        this.source = accessTokenSource == null ? DEFAULT_ACCESS_TOKEN_SOURCE : accessTokenSource;
        this.lastRefresh = date2 == null ? DEFAULT_LAST_REFRESH_TIME : date2;
        this.applicationId = str2;
        this.userId = str3;
    }

    public static void refreshCurrentAccessTokenAsync() {
        AccessTokenManager.getInstance().refreshCurrentAccessToken(null);
    }

    public static void refreshCurrentAccessTokenAsync(AccessTokenRefreshCallback accessTokenRefreshCallback) {
        AccessTokenManager.getInstance().refreshCurrentAccessToken(accessTokenRefreshCallback);
    }
}
