package com.oculus.modules;

import X.AnonymousClass006;
import android.util.Log;
import android.util.Pair;
import com.facebook.tigon.iface.TigonRequest;
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
    public static final String MODULE_NAME = "LivestreamingCommentsModule";
    public static final String TAG = "LivestreamingCommentsModule";

    public void shutdownModule() {
    }

    public static List<Pair<String, String>> describe() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Pair("getLivestreamingComments", "sssi"));
        return arrayList;
    }

    public void getLivestreamingComments(String str, String str2, String str3, int i) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("broadcast_id", str);
            jSONObject.put("start_cursor", str3);
            invokeCallback(i, AnonymousClass006.A09("[", jsonStringFromURL(new URL(AnonymousClass006.A0B("https://graph.facebook.com/graphql?doc=", URLEncoder.encode("query GetLivestreamingCommentsQuery($broadcast_id: ID!, $start_cursor: ID) {node(id: $broadcast_id) { ... on Video { feedback { comment_count_reduced reaction_count_reduced top_level_comments(orderby: chronological, before: $start_cursor, first: 2) { count nodes { id author { id name profile_picture(width: 40, height: 40) { uri } }body { text } } page_info { start_cursor } }top_reactions(first: 3) { nodes { face_image(width: 24, height: 24) { uri } } }  } live_viewer_count_read_only }  } }"), "&variables=", URLEncoder.encode(jSONObject.toString()))), TigonRequest.POST, str2), "]"));
        } catch (Exception e) {
            Log.e("RCTVR", "", e);
            invokeCallback(i, "[]");
        }
    }

    public void invokeCallback(int i, String str) {
        RCTBaseJavaModule.nativeInvokeCallback(this.RVRCtxTag, i, str);
    }

    private String jsonStringFromURL(URL url, String str, String str2) throws Exception {
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod(str);
        httpURLConnection.setRequestProperty("Authorization", AnonymousClass006.A07("OAuth ", str2));
        httpURLConnection.setDoOutput(true);
        httpURLConnection.connect();
        StringBuffer stringBuffer = new StringBuffer();
        InputStream inputStream = httpURLConnection.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine != null) {
                stringBuffer.append(AnonymousClass006.A07(readLine, "\n"));
            } else {
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuffer.toString();
            }
        }
    }

    public String getModuleName() {
        return "LivestreamingCommentsModule";
    }
}
