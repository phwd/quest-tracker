package com.oculus.modules;

import android.util.Log;
import android.util.Pair;
import com.google.common.net.HttpHeaders;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

public class LivestreamingCommentsModule extends RCTBaseJavaModule {
    private static String MODULE_NAME = LivestreamingCommentsModule.class.getSimpleName();
    private static final String TAG = MODULE_NAME;

    public String getModuleName() {
        return MODULE_NAME;
    }

    private String jsonStringFromURL(URL url, String requestMethod, String accessToken) throws Exception {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod(requestMethod);
        urlConnection.setRequestProperty(HttpHeaders.AUTHORIZATION, "OAuth " + accessToken);
        urlConnection.setDoOutput(true);
        urlConnection.connect();
        StringBuffer buffer = new StringBuffer();
        InputStream input = urlConnection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        for (String line = reader.readLine(); line != null; line = reader.readLine()) {
            buffer.append(line + "\n");
        }
        input.close();
        urlConnection.disconnect();
        return buffer.toString();
    }

    public void getLivestreamingComments(String broadcastID, String accessToken, String commentCursor, int callbackID) {
        try {
            JSONObject variablesJson = new JSONObject();
            variablesJson.put("broadcast_id", broadcastID);
            variablesJson.put("start_cursor", commentCursor);
            invokeCallback(callbackID, "[" + jsonStringFromURL(new URL("https://graph.facebook.com/graphql?doc=" + URLEncoder.encode("query GetLivestreamingCommentsQuery($broadcast_id: ID!, $start_cursor: ID) {node(id: $broadcast_id) { ... on Video { feedback { comment_count_reduced reaction_count_reduced top_level_comments(orderby: chronological, before: $start_cursor, first: 2) { count nodes { id author { id name profile_picture(width: 40, height: 40) { uri } }body { text } } page_info { start_cursor } }top_reactions(first: 3) { nodes { face_image(width: 24, height: 24) { uri } } }  } live_viewer_count_read_only }  } }") + "&variables=" + URLEncoder.encode(variablesJson.toString())), "POST", accessToken) + "]");
        } catch (Exception e) {
            Log.e("RCTVR", "", e);
            invokeCallback(callbackID, "[]");
        }
    }

    public static List<Pair<String, String>> describe() {
        List<Pair<String, String>> list = new ArrayList<>();
        list.add(new Pair<>("getLivestreamingComments", "sssi"));
        return list;
    }

    public void shutdownModule() {
    }

    public void invokeCallback(int callbackID, String jsonString) {
        nativeInvokeCallback(this.RVRCtxTag, callbackID, jsonString);
    }
}
