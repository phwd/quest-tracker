package com.oculus.store;

import android.util.Log;
import com.oculus.utils.GraphQLPrefetchQuery;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GraphQLPrefetchConstants {
    public static GraphQLPrefetchQuery[] getQueries() {
        try {
            return new GraphQLPrefetchQuery[]{new GraphQLPrefetchQuery(3581296875273019L, new JSONObject().put("hmdType", "MONTEREY").put("shouldOnlyReturnFirstView", true).put("storeIDOverride", JSONObject.NULL).put("storeViewID", JSONObject.NULL).put("storeViewTypes", new JSONArray().put("LANDING_MENU_3D")).put("useTestStore", false).put("cursor", JSONObject.NULL).put("count", 3).put("imageScale", 1), new JSONObject().put("services", new JSONArray().put("STORE"))), new GraphQLPrefetchQuery(3843850595667167L, new JSONObject().put("hmdType", "MONTEREY").put("storeIDOverride", JSONObject.NULL).put("storeViewTypes", new JSONArray().put("LANDING_MENU_3D")).put("useTestStore", false), new JSONObject().put("services", new JSONArray().put("STORE")))};
        } catch (JSONException e) {
            Log.e("GraphQLPrefetch", "Failed to create JSON");
            return null;
        }
    }
}
