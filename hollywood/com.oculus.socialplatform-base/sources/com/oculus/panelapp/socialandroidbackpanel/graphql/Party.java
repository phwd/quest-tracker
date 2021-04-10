package com.oculus.panelapp.socialandroidbackpanel.graphql;

import androidx.annotation.Nullable;
import com.oculus.provider.OculusContent;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Party {
    public static final String PARTY_FIELDS = "    ... on Party {\n      id\n      party_type\n      party_leader {\nid\nalias\nprofile_photo(size:\"80x80\") {\n  uri\n}\n      }\n      invited_by {\nid\nalias\nprofile_photo(size:\"80x80\") {\n  uri\n}\n      }\n      invited_users {\n          nodes {\nid\nalias\nprofile_photo(size:\"80x80\") {\n  uri\n}\n          }\n      }\n      party_users {\n        nodes {\nid\nalias\nprofile_photo(size:\"80x80\") {\n  uri\n}\n          }\n      }\n      party_blocked_users {\n        nodes {\n             id\n             alias\n          }\n      }\n      party_blocked_invited_users {\n        nodes {\n             id\n             alias\n          }\n      }\n      party_group_launch {\nid\ndestination {\nid\ndisplay_name\nimage(size_tag: \"80x80\")\napplication {\nid\ndisplay_name\nicon_image(size: \"80x80\") {\n  uri\n}\n}\n}\n      }\n    }\n";
    public final String id;
    @Nullable
    public final User invitedBy;
    public final List<User> invitedUsers;
    public final List<User> partyBlockedInvitedUsers;
    public final List<User> partyBlockedUsers;
    @Nullable
    public final GroupLaunch partyGroupLaunch;
    @Nullable
    public final User partyLeader;
    public final String partyType;
    public final List<User> partyUsers;

    public static Party fromJSONObject(JSONObject jSONObject) throws JSONException {
        User user;
        User user2;
        JSONObject optJSONObject = jSONObject.optJSONObject("party_leader");
        JSONObject optJSONObject2 = jSONObject.optJSONObject("invited_by");
        JSONObject optJSONObject3 = jSONObject.optJSONObject("party_group_launch");
        String string = jSONObject.getString("id");
        String string2 = jSONObject.getString("party_type");
        GroupLaunch groupLaunch = null;
        if (optJSONObject != null) {
            user = User.fromJSONObject(optJSONObject);
        } else {
            user = null;
        }
        if (optJSONObject2 != null) {
            user2 = User.fromJSONObject(optJSONObject2);
        } else {
            user2 = null;
        }
        List<User> userListFromJSONArray = userListFromJSONArray(jSONObject.getJSONObject("invited_users").getJSONArray("nodes"));
        List<User> userListFromJSONArray2 = userListFromJSONArray(jSONObject.getJSONObject(OculusContent.FriendList.PARTY_USERS_COLUMN).getJSONArray("nodes"));
        List<User> userListFromJSONArray3 = userListFromJSONArray(jSONObject.getJSONObject("party_blocked_users").getJSONArray("nodes"));
        List<User> userListFromJSONArray4 = userListFromJSONArray(jSONObject.getJSONObject("party_blocked_invited_users").getJSONArray("nodes"));
        if (optJSONObject3 != null) {
            groupLaunch = GroupLaunch.fromJSONObject(optJSONObject3);
        }
        return new Party(string, string2, user, user2, userListFromJSONArray, userListFromJSONArray2, userListFromJSONArray3, userListFromJSONArray4, groupLaunch);
    }

    public static List<User> userListFromJSONArray(JSONArray jSONArray) throws JSONException {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(User.fromJSONObject(jSONArray.getJSONObject(i)));
        }
        return arrayList;
    }

    public Party(String str, String str2, User user, User user2, List<User> list, List<User> list2, List<User> list3, List<User> list4, GroupLaunch groupLaunch) {
        this.id = str;
        this.partyType = str2;
        this.partyLeader = user;
        this.invitedBy = user2;
        this.invitedUsers = list;
        this.partyUsers = list2;
        this.partyBlockedUsers = list3;
        this.partyBlockedInvitedUsers = list4;
        this.partyGroupLaunch = groupLaunch;
    }
}
