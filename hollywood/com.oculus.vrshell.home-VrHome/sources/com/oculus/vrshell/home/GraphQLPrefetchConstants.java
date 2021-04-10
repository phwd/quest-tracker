package com.oculus.vrshell.home;

import android.util.Log;
import com.oculus.provider.OculusContent;
import com.oculus.utils.GraphQLPrefetchQuery;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GraphQLPrefetchConstants {
    public static GraphQLPrefetchQuery[] getQueries() {
        try {
            return new GraphQLPrefetchQuery[]{new GraphQLPrefetchQuery(5130087203668383L, new JSONObject().put("alternativeSource", JSONObject.NULL).put(OculusContent.Paging.CURSOR, JSONObject.NULL).put("count", 4).put("feedSource", JSONObject.NULL).put("nodeId", JSONObject.NULL).put("section", "DEFAULT").put("imageScale", 1), new JSONObject().put("services", new JSONArray().put("HOME").put("EXPLORE"))), new GraphQLPrefetchQuery(2678002792329403L, new JSONObject().put(OculusContent.Paging.FIRST, 6), new JSONObject().put("services", new JSONArray().put("HOME").put("EXPLORE"))), new GraphQLPrefetchQuery(2886345294781084L, new JSONObject(), new JSONObject().put("services", new JSONArray().put("HOME").put("EXPLORE"))), new GraphQLPrefetchQuery(3581296875273019L, new JSONObject().put("hmdType", "MONTEREY").put("shouldOnlyReturnFirstView", true).put("storeIDOverride", JSONObject.NULL).put("storeViewID", JSONObject.NULL).put("storeViewTypes", new JSONArray().put("LANDING_MENU_3D")).put("useTestStore", false).put(OculusContent.Paging.CURSOR, JSONObject.NULL).put("count", 3).put("imageScale", 1), new JSONObject().put("services", new JSONArray().put("STORE"))), new GraphQLPrefetchQuery(3843850595667167L, new JSONObject().put("hmdType", "MONTEREY").put("storeIDOverride", JSONObject.NULL).put("storeViewTypes", new JSONArray().put("LANDING_MENU_3D")).put("useTestStore", false), new JSONObject().put("services", new JSONArray().put("STORE")))};
        } catch (JSONException e) {
            Log.e("GraphQLPrefetch", "Failed to create JSON");
            return null;
        }
    }
}
