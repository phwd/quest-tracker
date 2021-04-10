package com.facebook;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.facebook.internal.ImageRequest;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;
import org.json.JSONException;
import org.json.JSONObject;

public final class Profile implements Parcelable {
    public static final Parcelable.Creator<Profile> CREATOR = new Parcelable.Creator() {
        /* class com.facebook.Profile.AnonymousClass2 */

        @Override // android.os.Parcelable.Creator
        public Profile createFromParcel(Parcel parcel) {
            return new Profile(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public Profile[] newArray(int i) {
            return new Profile[i];
        }
    };
    public static final String FIRST_NAME_KEY = "first_name";
    public static final String ID_KEY = "id";
    public static final String LAST_NAME_KEY = "last_name";
    public static final String LINK_URI_KEY = "link_uri";
    public static final String MIDDLE_NAME_KEY = "middle_name";
    public static final String NAME_KEY = "name";
    public final String firstName;
    public final String id;
    public final String lastName;
    public final Uri linkUri;
    public final String middleName;
    public final String name;

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Profile)) {
            return false;
        }
        Profile profile = (Profile) obj;
        if (this.id.equals(profile.id) && this.firstName == null) {
            return profile.firstName == null;
        }
        if (this.firstName.equals(profile.firstName) && this.middleName == null) {
            return profile.middleName == null;
        }
        if (this.middleName.equals(profile.middleName) && this.lastName == null) {
            return profile.lastName == null;
        }
        if (this.lastName.equals(profile.lastName) && this.name == null) {
            return profile.name == null;
        }
        if (!this.name.equals(profile.name) || this.linkUri != null) {
            return this.linkUri.equals(profile.linkUri);
        }
        return profile.linkUri == null;
    }

    public Uri getProfilePictureUri(int i, int i2) {
        return ImageRequest.getProfilePictureUri(this.id, i, i2);
    }

    public int hashCode() {
        int hashCode = 527 + this.id.hashCode();
        String str = this.firstName;
        if (str != null) {
            hashCode = (hashCode * 31) + str.hashCode();
        }
        String str2 = this.middleName;
        if (str2 != null) {
            hashCode = (hashCode * 31) + str2.hashCode();
        }
        String str3 = this.lastName;
        if (str3 != null) {
            hashCode = (hashCode * 31) + str3.hashCode();
        }
        String str4 = this.name;
        if (str4 != null) {
            hashCode = (hashCode * 31) + str4.hashCode();
        }
        Uri uri = this.linkUri;
        if (uri != null) {
            return (hashCode * 31) + uri.hashCode();
        }
        return hashCode;
    }

    public JSONObject toJSONObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", this.id);
            jSONObject.put(FIRST_NAME_KEY, this.firstName);
            jSONObject.put(MIDDLE_NAME_KEY, this.middleName);
            jSONObject.put(LAST_NAME_KEY, this.lastName);
            jSONObject.put("name", this.name);
            Uri uri = this.linkUri;
            if (uri == null) {
                return jSONObject;
            }
            jSONObject.put(LINK_URI_KEY, uri.toString());
            return jSONObject;
        } catch (JSONException unused) {
            return null;
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        String obj;
        parcel.writeString(this.id);
        parcel.writeString(this.firstName);
        parcel.writeString(this.middleName);
        parcel.writeString(this.lastName);
        parcel.writeString(this.name);
        Uri uri = this.linkUri;
        if (uri == null) {
            obj = null;
        } else {
            obj = uri.toString();
        }
        parcel.writeString(obj);
    }

    public static void fetchProfileForCurrentAccessToken() {
        AccessToken accessToken = AccessTokenManager.getInstance().currentAccessToken;
        if (accessToken == null) {
            setCurrentProfile(null);
        } else {
            Utility.getGraphMeRequestWithCacheAsync(accessToken.token, new Utility.GraphMeRequestWithCacheCallback() {
                /* class com.facebook.Profile.AnonymousClass1 */

                @Override // com.facebook.internal.Utility.GraphMeRequestWithCacheCallback
                public void onFailure(FacebookException facebookException) {
                }

                @Override // com.facebook.internal.Utility.GraphMeRequestWithCacheCallback
                public void onSuccess(JSONObject jSONObject) {
                    Uri uri;
                    String optString = jSONObject.optString("id");
                    if (optString != null) {
                        String optString2 = jSONObject.optString("link");
                        String optString3 = jSONObject.optString(Profile.FIRST_NAME_KEY);
                        String optString4 = jSONObject.optString(Profile.MIDDLE_NAME_KEY);
                        String optString5 = jSONObject.optString(Profile.LAST_NAME_KEY);
                        String optString6 = jSONObject.optString("name");
                        if (optString2 != null) {
                            uri = Uri.parse(optString2);
                        } else {
                            uri = null;
                        }
                        Profile.setCurrentProfile(new Profile(optString, optString3, optString4, optString5, optString6, uri));
                    }
                }
            });
        }
    }

    public static Profile getCurrentProfile() {
        return ProfileManager.getInstance().currentProfile;
    }

    public static void setCurrentProfile(Profile profile) {
        ProfileManager.getInstance().setCurrentProfile(profile);
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getId() {
        return this.id;
    }

    public String getLastName() {
        return this.lastName;
    }

    public Uri getLinkUri() {
        return this.linkUri;
    }

    public String getMiddleName() {
        return this.middleName;
    }

    public String getName() {
        return this.name;
    }

    public Profile(Parcel parcel) {
        Uri parse;
        this.id = parcel.readString();
        this.firstName = parcel.readString();
        this.middleName = parcel.readString();
        this.lastName = parcel.readString();
        this.name = parcel.readString();
        String readString = parcel.readString();
        if (readString == null) {
            parse = null;
        } else {
            parse = Uri.parse(readString);
        }
        this.linkUri = parse;
    }

    public Profile(String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable Uri uri) {
        Validate.notNullOrEmpty(str, "id");
        this.id = str;
        this.firstName = str2;
        this.middleName = str3;
        this.lastName = str4;
        this.name = str5;
        this.linkUri = uri;
    }

    public Profile(JSONObject jSONObject) {
        Uri uri = null;
        this.id = jSONObject.optString("id", null);
        this.firstName = jSONObject.optString(FIRST_NAME_KEY, null);
        this.middleName = jSONObject.optString(MIDDLE_NAME_KEY, null);
        this.lastName = jSONObject.optString(LAST_NAME_KEY, null);
        this.name = jSONObject.optString("name", null);
        String optString = jSONObject.optString(LINK_URI_KEY, null);
        this.linkUri = optString != null ? Uri.parse(optString) : uri;
    }
}
