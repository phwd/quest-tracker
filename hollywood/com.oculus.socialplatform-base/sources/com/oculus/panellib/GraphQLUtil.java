package com.oculus.panellib;

import X.AnonymousClass006;
import com.oculus.graphql.fb.FacebookGraphQLUtil;
import com.oculus.http.common.graphql.GraphQLQueryConstants;
import java.io.IOException;
import java.util.ArrayList;
import okhttp3.Call;
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
    public static final HttpUrl REQUEST_URL;

    public interface Callback {
        void onFailure(String str);

        void onSuccess(JSONObject jSONObject);
    }

    static {
        HttpUrl.Builder newBuilder = HttpUrl.parse("https://graph.oculus.com").newBuilder();
        newBuilder.addPathSegment("graphql");
        REQUEST_URL = newBuilder.build();
    }

    public static void post(OkHttpClient okHttpClient, FormBody formBody, final Callback callback) {
        Request.Builder builder = new Request.Builder();
        builder.url(REQUEST_URL);
        builder.post(formBody);
        okHttpClient.newCall(builder.build()).enqueue(new okhttp3.Callback() {
            /* class com.oculus.panellib.GraphQLUtil.AnonymousClass1 */

            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
                callback.onFailure(iOException.toString());
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    Callback callback = callback;
                    StringBuilder sb = new StringBuilder("Unexpected code: ");
                    sb.append(response);
                    sb.append(" - ");
                    sb.append(response.body.string());
                    callback.onFailure(sb.toString());
                    return;
                }
                try {
                    callback.onSuccess(new JSONObject(response.body.string()));
                } catch (JSONException e) {
                    callback.onFailure(AnonymousClass006.A07("Json error: ", e.getMessage()));
                }
            }
        });
    }

    public static void query(OkHttpClient okHttpClient, long j, JSONObject jSONObject, String str, Callback callback) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        arrayList.add(HttpUrl.canonicalize("access_token", " \"':;<=>@[]^`{}|/\\?#&!$(),~", false, false, true, true));
        arrayList2.add(HttpUrl.canonicalize(str, " \"':;<=>@[]^`{}|/\\?#&!$(),~", false, false, true, true));
        String valueOf = String.valueOf(j);
        arrayList.add(HttpUrl.canonicalize(FacebookGraphQLUtil.QUERY_PARAM_DOC_ID, " \"':;<=>@[]^`{}|/\\?#&!$(),~", false, false, true, true));
        arrayList2.add(HttpUrl.canonicalize(valueOf, " \"':;<=>@[]^`{}|/\\?#&!$(),~", false, false, true, true));
        String obj = jSONObject.toString();
        arrayList.add(HttpUrl.canonicalize("variables", " \"':;<=>@[]^`{}|/\\?#&!$(),~", false, false, true, true));
        arrayList2.add(HttpUrl.canonicalize(obj, " \"':;<=>@[]^`{}|/\\?#&!$(),~", false, false, true, true));
        post(okHttpClient, new FormBody(arrayList, arrayList2), callback);
    }

    public static void query(OkHttpClient okHttpClient, String str, String str2, JSONObject jSONObject, Callback callback) {
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
        post(okHttpClient, new FormBody(arrayList, arrayList2), callback);
    }
}
