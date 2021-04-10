package com.oculus.panelapp.socialandroidbackpanel.graphql;

import org.json.JSONException;
import org.json.JSONObject;

public class GroupLaunch {
    public static final String GROUP_LAUNCH_FIELDS = "id\ndestination {\nid\ndisplay_name\nimage(size_tag: \"80x80\")\napplication {\nid\ndisplay_name\nicon_image(size: \"80x80\") {\n  uri\n}\n}\n}\n";
    public final Destination destination;
    public final String id;

    public static GroupLaunch fromJSONObject(JSONObject jSONObject) throws JSONException {
        return new GroupLaunch(jSONObject.getString("id"), Destination.fromJSONObject(jSONObject.getJSONObject("destination")));
    }

    public GroupLaunch(String str, Destination destination2) {
        this.id = str;
        this.destination = destination2;
    }
}
