package com.oculus.graphql.fb;

import X.AnonymousClass006;
import android.util.Log;
import androidx.annotation.Nullable;
import com.oculus.common.logutilities.LoggingUtil;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.json.JSONException;
import org.json.JSONObject;

public class FacebookGraphQLUtil {
    public static final String FB_GRAPHQL_ENDPOINT = "https://graph.facebook.com";
    public static final String FB_GRAPHQL_PATH = "graphql";
    public static final String QUERY_PARAM_ACCESS_TOKEN = "access_token";
    public static final String QUERY_PARAM_DOC = "doc";
    public static final String QUERY_PARAM_DOC_ID = "doc_id";
    public static final String QUERY_PARAM_VARIABLES = "variables";
    public static String TAG = LoggingUtil.tag(FacebookGraphQLUtil.class);

    public interface FailureCallback {
        void callback(Throwable th);
    }

    public interface SuccessCallback {
        void callback(JSONObject jSONObject);
    }

    public static HttpUrl.Builder getDefaultUrlBuilder(Map<String, String> map) {
        HttpUrl.Builder newBuilder = HttpUrl.parse("https://graph.facebook.com").newBuilder();
        newBuilder.addPathSegment("graphql");
        newBuilder.addQueryParameter("method", "post");
        map.forEach(new BiConsumer() {
            /* class com.oculus.graphql.fb.$$Lambda$FacebookGraphQLUtil$IKaJV8Q0hhpy3O3mwSVE9eX7d7Y2 */

            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                HttpUrl.Builder.this.addQueryParameter((String) obj, (String) obj2);
            }
        });
        return newBuilder;
    }

    public static void query(OkHttpClient okHttpClient, String str, final SuccessCallback successCallback, final FailureCallback failureCallback) {
        Request.Builder builder = new Request.Builder();
        builder.url(str);
        okHttpClient.newCall(builder.build()).enqueue(new Callback() {
            /* class com.oculus.graphql.fb.FacebookGraphQLUtil.AnonymousClass1 */

            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
                Log.e(FacebookGraphQLUtil.TAG, "Query failed");
                FailureCallback.this.callback(iOException);
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) {
                try {
                    ResponseBody responseBody = response.body;
                    try {
                        if (response.isSuccessful()) {
                            try {
                                JSONObject jSONObject = new JSONObject(responseBody.string());
                                JSONObject optJSONObject = jSONObject.optJSONObject("data");
                                if (optJSONObject == null) {
                                    Log.e(FacebookGraphQLUtil.TAG, AnonymousClass006.A07("Failed to extract data from JSON. JSON: ", jSONObject.toString()));
                                    FailureCallback.this.callback(new IOException("Failed to extract data from JSON"));
                                } else {
                                    successCallback.callback(optJSONObject);
                                }
                            } catch (JSONException e) {
                                Log.e(FacebookGraphQLUtil.TAG, "Failed to parse JSON: ", e);
                                FailureCallback.this.callback(e);
                            }
                        } else {
                            Log.e(FacebookGraphQLUtil.TAG, "Response is not successful");
                            FailureCallback.this.callback(new RuntimeException("Response is not successful"));
                            if (responseBody == null) {
                                return;
                            }
                        }
                        responseBody.close();
                        return;
                    } catch (Throwable unused) {
                    }
                    throw th;
                } catch (IOException e2) {
                    Log.e(FacebookGraphQLUtil.TAG, "Failed to get response: ", e2);
                    FailureCallback.this.callback(e2);
                }
            }
        });
    }

    public static void queryDocID(OkHttpClient okHttpClient, String str, @Nullable JSONObject jSONObject, String str2, SuccessCallback successCallback, FailureCallback failureCallback) {
        HashMap hashMap = new HashMap();
        hashMap.put(QUERY_PARAM_DOC_ID, str);
        if (jSONObject != null) {
            hashMap.put("variables", jSONObject.toString());
        }
        hashMap.put("access_token", str2);
        query(okHttpClient, getDefaultUrlBuilder(hashMap).build().toString(), successCallback, failureCallback);
    }

    public static JSONObject parseField(JSONObject jSONObject, String str) throws JSONException {
        JSONObject optJSONObject = jSONObject.optJSONObject(str);
        if (optJSONObject != null) {
            return optJSONObject;
        }
        throw new JSONException(String.format("Failed to extract %s from JSON: %s ", str, jSONObject.toString()));
    }
}
