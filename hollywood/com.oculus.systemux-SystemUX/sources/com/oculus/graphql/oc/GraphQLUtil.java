package com.oculus.graphql.oc;

import com.oculus.graphql.fb.FacebookGraphQLUtil;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;

public class GraphQLUtil {
    private static final String OCULUS_GRAPHAPI_ENDPOINT = "https://graph.oculus.com";
    private static final String OCULUS_GRAPHAPI_GRAPHQL_PATH = "graphql";

    public interface Result {
        void onFailure(String str);

        void onSuccess(JSONObject jSONObject);
    }

    public static void query(OkHttpClient okHttpClient, String str, String str2, String str3, Result result) {
        try {
            HttpUrl.Builder newBuilder = HttpUrl.parse(OCULUS_GRAPHAPI_ENDPOINT).newBuilder();
            newBuilder.addPathSegment(OCULUS_GRAPHAPI_GRAPHQL_PATH);
            newBuilder.addQueryParameter("access_token", str3);
            newBuilder.addQueryParameter(FacebookGraphQLUtil.QUERY_PARAM_DOC_ID, str);
            newBuilder.addQueryParameter(FacebookGraphQLUtil.QUERY_PARAM_VARIABLES, str2);
            query(okHttpClient, newBuilder.build().toString(), result);
        } catch (Exception e) {
            result.onFailure("GraphQLUtil.query failed: " + e.toString());
        }
    }

    public static void queryDoc(OkHttpClient okHttpClient, String str, JSONObject jSONObject, String str2, Result result) {
        try {
            HttpUrl.Builder newBuilder = HttpUrl.parse(OCULUS_GRAPHAPI_ENDPOINT).newBuilder();
            newBuilder.addPathSegment(OCULUS_GRAPHAPI_GRAPHQL_PATH);
            newBuilder.addQueryParameter("access_token", str2);
            newBuilder.addQueryParameter(FacebookGraphQLUtil.QUERY_PARAM_DOC, str);
            newBuilder.addQueryParameter(FacebookGraphQLUtil.QUERY_PARAM_VARIABLES, jSONObject.toString());
            query(okHttpClient, newBuilder.build().toString(), result);
        } catch (Exception e) {
            result.onFailure("GraphQLUtil.query failed: " + e.toString());
        }
    }

    public static void query(OkHttpClient okHttpClient, String str, String str2, JSONObject jSONObject, Result result) {
        try {
            HttpUrl.Builder newBuilder = HttpUrl.parse(OCULUS_GRAPHAPI_ENDPOINT).newBuilder();
            newBuilder.addPathSegment(OCULUS_GRAPHAPI_GRAPHQL_PATH);
            newBuilder.addQueryParameter("q", str);
            newBuilder.addQueryParameter("access_token", str2);
            if (jSONObject != null) {
                newBuilder.addQueryParameter("query_params", jSONObject.toString());
            }
            query(okHttpClient, newBuilder.build().toString(), result);
        } catch (Exception e) {
            result.onFailure("GraphQLUtil.query failed: " + e.toString());
        }
    }

    private static void query(OkHttpClient okHttpClient, String str, final Result result) throws Exception {
        okHttpClient.newCall(new Request.Builder().url(str).build()).enqueue(new Callback() {
            /* class com.oculus.graphql.oc.GraphQLUtil.AnonymousClass1 */

            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
                Result.this.onFailure(iOException.toString());
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    Result result = Result.this;
                    result.onFailure("Unexpected code: " + response);
                    return;
                }
                try {
                    Result.this.onSuccess(new JSONObject(response.body().string()));
                } catch (JSONException e) {
                    Result result2 = Result.this;
                    result2.onFailure("Json error: " + e.getMessage());
                }
            }
        });
    }

    public static void post(OkHttpClient okHttpClient, String str, String str2, JSONObject jSONObject, final Result result) {
        try {
            HttpUrl.Builder newBuilder = HttpUrl.parse(OCULUS_GRAPHAPI_ENDPOINT).newBuilder();
            newBuilder.addPathSegment(OCULUS_GRAPHAPI_GRAPHQL_PATH);
            FormBody.Builder add = new FormBody.Builder().add("access_token", str2).add("q", str);
            if (jSONObject != null) {
                add.add("query_params", jSONObject.toString());
            }
            okHttpClient.newCall(new Request.Builder().url(newBuilder.build().toString()).post(add.build()).build()).enqueue(new Callback() {
                /* class com.oculus.graphql.oc.GraphQLUtil.AnonymousClass2 */

                @Override // okhttp3.Callback
                public void onFailure(Call call, IOException iOException) {
                    Result.this.onFailure(iOException.toString());
                }

                @Override // okhttp3.Callback
                public void onResponse(Call call, Response response) throws IOException {
                    if (!response.isSuccessful()) {
                        Result result = Result.this;
                        result.onFailure("Unexpected code: " + response + " - " + response.body().string());
                        return;
                    }
                    try {
                        Result.this.onSuccess(new JSONObject(response.body().string()));
                    } catch (JSONException e) {
                        Result result2 = Result.this;
                        result2.onFailure("Json error: " + e.getMessage());
                    }
                }
            });
        } catch (Exception e) {
            result.onFailure("GraphQLUtil.post failed: " + e.toString());
        }
    }
}
