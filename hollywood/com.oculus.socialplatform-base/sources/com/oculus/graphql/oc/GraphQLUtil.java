package com.oculus.graphql.oc;

import X.AnonymousClass006;
import com.oculus.graphql.fb.FacebookGraphQLUtil;
import com.oculus.http.common.graphql.GraphQLQueryConstants;
import java.io.IOException;
import java.util.ArrayList;
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
    public static final String OCULUS_GRAPHAPI_ENDPOINT = "https://graph.oculus.com";
    public static final String OCULUS_GRAPHAPI_GRAPHQL_PATH = "graphql";

    public interface Result {
        void onFailure(String str);

        void onSuccess(JSONObject jSONObject);
    }

    public static void post(OkHttpClient okHttpClient, String str, String str2, JSONObject jSONObject, final Result result) {
        try {
            HttpUrl.Builder newBuilder = HttpUrl.parse("https://graph.oculus.com").newBuilder();
            newBuilder.addPathSegment("graphql");
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            arrayList.add(HttpUrl.canonicalize("access_token", " \"':;<=>@[]^`{}|/\\?#&!$(),~", false, false, true, true));
            arrayList2.add(HttpUrl.canonicalize(str2, " \"':;<=>@[]^`{}|/\\?#&!$(),~", false, false, true, true));
            arrayList.add(HttpUrl.canonicalize(GraphQLQueryConstants.GRAPHQL_QUERY_KEY, " \"':;<=>@[]^`{}|/\\?#&!$(),~", false, false, true, true));
            arrayList2.add(HttpUrl.canonicalize(str, " \"':;<=>@[]^`{}|/\\?#&!$(),~", false, false, true, true));
            if (jSONObject != null) {
                String obj = jSONObject.toString();
                arrayList.add(HttpUrl.canonicalize(GraphQLQueryConstants.GRAPHQL_QUERY_PARAMS_KEY, " \"':;<=>@[]^`{}|/\\?#&!$(),~", false, false, true, true));
                arrayList2.add(HttpUrl.canonicalize(obj, " \"':;<=>@[]^`{}|/\\?#&!$(),~", false, false, true, true));
            }
            Request.Builder builder = new Request.Builder();
            builder.url(newBuilder.build().toString());
            builder.post(new FormBody(arrayList, arrayList2));
            okHttpClient.newCall(builder.build()).enqueue(new Callback() {
                /* class com.oculus.graphql.oc.GraphQLUtil.AnonymousClass2 */

                @Override // okhttp3.Callback
                public void onFailure(Call call, IOException iOException) {
                    Result.this.onFailure(iOException.toString());
                }

                @Override // okhttp3.Callback
                public void onResponse(Call call, Response response) throws IOException {
                    if (!response.isSuccessful()) {
                        Result result = Result.this;
                        StringBuilder sb = new StringBuilder("Unexpected code: ");
                        sb.append(response);
                        sb.append(" - ");
                        sb.append(response.body.string());
                        result.onFailure(sb.toString());
                        return;
                    }
                    try {
                        Result.this.onSuccess(new JSONObject(response.body.string()));
                    } catch (JSONException e) {
                        Result.this.onFailure(AnonymousClass006.A07("Json error: ", e.getMessage()));
                    }
                }
            });
        } catch (Exception e) {
            result.onFailure(AnonymousClass006.A07("GraphQLUtil.post failed: ", e.toString()));
        }
    }

    public static void queryDoc(OkHttpClient okHttpClient, String str, JSONObject jSONObject, String str2, Result result) {
        try {
            HttpUrl.Builder newBuilder = HttpUrl.parse("https://graph.oculus.com").newBuilder();
            newBuilder.addPathSegment("graphql");
            newBuilder.addQueryParameter("access_token", str2);
            newBuilder.addQueryParameter("doc", str);
            newBuilder.addQueryParameter("variables", jSONObject.toString());
            query(okHttpClient, newBuilder.build().toString(), result);
        } catch (Exception e) {
            result.onFailure(AnonymousClass006.A07("GraphQLUtil.query failed: ", e.toString()));
        }
    }

    public static void query(OkHttpClient okHttpClient, String str, final Result result) throws Exception {
        Request.Builder builder = new Request.Builder();
        builder.url(str);
        okHttpClient.newCall(builder.build()).enqueue(new Callback() {
            /* class com.oculus.graphql.oc.GraphQLUtil.AnonymousClass1 */

            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
                Result.this.onFailure(iOException.toString());
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    Result result = Result.this;
                    StringBuilder sb = new StringBuilder("Unexpected code: ");
                    sb.append(response);
                    result.onFailure(sb.toString());
                    return;
                }
                try {
                    Result.this.onSuccess(new JSONObject(response.body.string()));
                } catch (JSONException e) {
                    Result.this.onFailure(AnonymousClass006.A07("Json error: ", e.getMessage()));
                }
            }
        });
    }

    public static void query(OkHttpClient okHttpClient, String str, String str2, String str3, Result result) {
        try {
            HttpUrl.Builder newBuilder = HttpUrl.parse("https://graph.oculus.com").newBuilder();
            newBuilder.addPathSegment("graphql");
            newBuilder.addQueryParameter("access_token", str3);
            newBuilder.addQueryParameter(FacebookGraphQLUtil.QUERY_PARAM_DOC_ID, str);
            newBuilder.addQueryParameter("variables", str2);
            query(okHttpClient, newBuilder.build().toString(), result);
        } catch (Exception e) {
            result.onFailure(AnonymousClass006.A07("GraphQLUtil.query failed: ", e.toString()));
        }
    }

    public static void query(OkHttpClient okHttpClient, String str, String str2, JSONObject jSONObject, Result result) {
        try {
            HttpUrl.Builder newBuilder = HttpUrl.parse("https://graph.oculus.com").newBuilder();
            newBuilder.addPathSegment("graphql");
            newBuilder.addQueryParameter(GraphQLQueryConstants.GRAPHQL_QUERY_KEY, str);
            newBuilder.addQueryParameter("access_token", str2);
            if (jSONObject != null) {
                newBuilder.addQueryParameter(GraphQLQueryConstants.GRAPHQL_QUERY_PARAMS_KEY, jSONObject.toString());
            }
            query(okHttpClient, newBuilder.build().toString(), result);
        } catch (Exception e) {
            result.onFailure(AnonymousClass006.A07("GraphQLUtil.query failed: ", e.toString()));
        }
    }
}
