package com.oculus.explore;

import android.util.Log;
import com.oculus.utils.GraphQLPrefetchQuery;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GraphQLPrefetchConstants {
    public static GraphQLPrefetchQuery[] getQueries() {
        try {
            return new GraphQLPrefetchQuery[]{new GraphQLPrefetchQuery(5201004316639113L, new JSONObject().put("alternativeSource", JSONObject.NULL).put("cursor", JSONObject.NULL).put("count", 4).put("feedSource", JSONObject.NULL).put("nodeId", JSONObject.NULL).put("section", "DEFAULT").put("imageScale", 1), new JSONObject().put("services", new JSONArray().put("HOME").put("EXPLORE"))), new GraphQLPrefetchQuery(2678002792329403L, new JSONObject().put("first", 6), new JSONObject().put("services", new JSONArray().put("HOME").put("EXPLORE"))), new GraphQLPrefetchQuery(2886345294781084L, new JSONObject(), new JSONObject().put("services", new JSONArray().put("HOME").put("EXPLORE")))};
        } catch (JSONException e) {
            Log.e("GraphQLPrefetch", "Failed to create JSON");
            return null;
        }
    }
}
