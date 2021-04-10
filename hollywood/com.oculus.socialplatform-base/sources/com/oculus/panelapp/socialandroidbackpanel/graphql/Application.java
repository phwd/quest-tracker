package com.oculus.panelapp.socialandroidbackpanel.graphql;

import org.json.JSONException;
import org.json.JSONObject;

public class Application {
    public static final String APPLICATION_FIELDS = "id\ndisplay_name\nicon_image(size: \"80x80\") {\n  uri\n}\n";
    public final String displayName;
    public final String id;
    public final String imageUri;

    public static Application fromJSONObject(JSONObject jSONObject) throws JSONException {
        return new Application(jSONObject.getString("id"), jSONObject.getString("display_name"), jSONObject.getJSONObject("icon_image").getString("uri"));
    }

    public Application(String str, String str2, String str3) {
        this.id = str;
        this.displayName = str2;
        this.imageUri = str3;
    }
}
