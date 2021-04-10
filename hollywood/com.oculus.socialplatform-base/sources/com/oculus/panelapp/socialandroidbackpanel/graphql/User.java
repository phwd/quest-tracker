package com.oculus.panelapp.socialandroidbackpanel.graphql;

import androidx.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

public class User {
    public static final String USER_FIELDS = "id\nalias\nprofile_photo(size:\"80x80\") {\n  uri\n}\n";
    public final String alias;
    public final String id;
    @Nullable
    public final String profilePhotoURL;

    public static User fromJSONObject(JSONObject jSONObject) throws JSONException {
        String str;
        JSONObject optJSONObject = jSONObject.optJSONObject("profile_photo");
        if (optJSONObject != null) {
            str = optJSONObject.optString("uri");
        } else {
            str = null;
        }
        return new User(jSONObject.getString("id"), jSONObject.getString("alias"), str);
    }

    public User(String str, String str2, String str3) {
        this.id = str;
        this.alias = str2;
        this.profilePhotoURL = str3;
    }
}
