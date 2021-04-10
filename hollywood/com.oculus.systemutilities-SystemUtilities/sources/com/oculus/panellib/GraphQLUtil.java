package com.oculus.panellib;

import java.io.IOException;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;

public class GraphQLUtil {
    private static final HttpUrl REQUEST_URL = HttpUrl.parse("https://graph.oculus.com").newBuilder().addPathSegment("graphql").build();

    public interface Callback {
        void onFailure(String str);

        void onSuccess(JSONObject jSONObject);
    }

    public static void query(OkHttpClient client, String query, String accessToken, JSONObject params, Callback callback) {
        FormBody.Builder formBuilder = new FormBody.Builder().add("access_token", accessToken).add("q", query);
        if (params != null) {
            formBuilder.add("query_params", params.toString());
        }
        post(client, formBuilder.build(), callback);
    }

    private static void post(OkHttpClient client, FormBody postBody, final Callback callback) {
        client.newCall(new Request.Builder().url(REQUEST_URL).post(postBody).build()).enqueue(new okhttp3.Callback() {
            /* class com.oculus.panellib.GraphQLUtil.AnonymousClass1 */

            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException e) {
                callback.onFailure(e.toString());
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    callback.onFailure("Unexpected code: " + response + " - " + response.body().string());
                    return;
                }
                try {
                    callback.onSuccess(new JSONObject(response.body().string()));
                } catch (JSONException e) {
                    callback.onFailure("Json error: " + e.getMessage());
                }
            }
        });
    }
}
