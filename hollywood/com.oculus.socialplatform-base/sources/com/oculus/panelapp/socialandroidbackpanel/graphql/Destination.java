package com.oculus.panelapp.socialandroidbackpanel.graphql;

import com.facebook.acra.AppComponentStats;
import org.json.JSONException;
import org.json.JSONObject;

public class Destination {
    public static final String DESTINATION_FIELDS = "id\ndisplay_name\nimage(size_tag: \"80x80\")\napplication {\nid\ndisplay_name\nicon_image(size: \"80x80\") {\n  uri\n}\n}\n";
    public final Application application;
    public final String displayName;
    public final String id;
    public final String imageUri;

    public static Destination fromJSONObject(JSONObject jSONObject) throws JSONException {
        return new Destination(jSONObject.getString("id"), jSONObject.getString("display_name"), jSONObject.getString("image"), Application.fromJSONObject(jSONObject.getJSONObject(AppComponentStats.TAG_APPLICATION)));
    }

    public Destination(String str, String str2, String str3, Application application2) {
        this.id = str;
        this.displayName = str2;
        this.imageUri = str3;
        this.application = application2;
    }
}
