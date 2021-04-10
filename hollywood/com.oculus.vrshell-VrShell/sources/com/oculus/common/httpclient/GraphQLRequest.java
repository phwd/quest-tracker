package com.oculus.common.httpclient;

import java.io.IOException;
import java.util.Locale;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;

public class GraphQLRequest {
    private static final String OCULUS_GRAPHAPI_ENDPOINT = "https://graph.oculus.com";
    private static final String OCULUS_GRAPHAPI_GRAPHQL_PATH = "graphql";

    public interface GraphQLResponse {
        void onFailure(String str);

        void onSuccess(JSONObject jSONObject);
    }

    public static void get(OkHttpClient okHttpClient, String str, String str2, GraphQLResponse graphQLResponse) {
        try {
            executeRequest(okHttpClient, constructBaseRequest(str, str2).build(), graphQLResponse);
        } catch (Exception e) {
            graphQLResponse.onFailure("GraphQLRequest.get failed: " + e.toString());
        }
    }

    public static void post(OkHttpClient okHttpClient, String str, String str2, FormBody formBody, GraphQLResponse graphQLResponse) {
        try {
            executeRequest(okHttpClient, constructBaseRequest(str, str2).post(formBody).build(), graphQLResponse);
        } catch (Exception e) {
            graphQLResponse.onFailure("GraphQLRequest.post failed: " + e.toString());
        }
    }

    private static Request.Builder constructBaseRequest(String str, String str2) {
        Request.Builder url = new Request.Builder().url(HttpUrl.parse(String.format(Locale.ROOT, "%s/%s", OCULUS_GRAPHAPI_ENDPOINT, str2)).newBuilder().build().toString());
        return url.addHeader("Authorization", "Bearer " + str);
    }

    private static void executeRequest(OkHttpClient okHttpClient, Request request, final GraphQLResponse graphQLResponse) {
        okHttpClient.newCall(request).enqueue(new Callback() {
            /* class com.oculus.common.httpclient.GraphQLRequest.AnonymousClass1 */

            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
                GraphQLResponse.this.onFailure(iOException.toString());
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    GraphQLResponse graphQLResponse = GraphQLResponse.this;
                    graphQLResponse.onFailure("Unexpected code: " + response);
                    return;
                }
                try {
                    GraphQLResponse.this.onSuccess(new JSONObject(response.body().string()));
                } catch (JSONException e) {
                    GraphQLResponse graphQLResponse2 = GraphQLResponse.this;
                    graphQLResponse2.onFailure("Json error: " + e.getMessage());
                }
            }
        });
    }
}
