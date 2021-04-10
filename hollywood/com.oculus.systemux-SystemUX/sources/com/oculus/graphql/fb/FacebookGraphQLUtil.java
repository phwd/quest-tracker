package com.oculus.graphql.fb;

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
    private static final String FB_GRAPHQL_ENDPOINT = "https://graph.facebook.com";
    private static final String FB_GRAPHQL_PATH = "graphql";
    public static final String QUERY_PARAM_ACCESS_TOKEN = "access_token";
    public static final String QUERY_PARAM_DOC = "doc";
    public static final String QUERY_PARAM_DOC_ID = "doc_id";
    public static final String QUERY_PARAM_VARIABLES = "variables";
    private static String TAG = LoggingUtil.tag(FacebookGraphQLUtil.class);

    public interface FailureCallback {
        void callback(Throwable th);
    }

    public interface SuccessCallback {
        void callback(JSONObject jSONObject);
    }

    public static void queryDocID(OkHttpClient okHttpClient, String str, @Nullable JSONObject jSONObject, String str2, SuccessCallback successCallback, FailureCallback failureCallback) {
        HashMap hashMap = new HashMap();
        hashMap.put(QUERY_PARAM_DOC_ID, str);
        if (jSONObject != null) {
            hashMap.put(QUERY_PARAM_VARIABLES, jSONObject.toString());
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

    private static void query(OkHttpClient okHttpClient, String str, final SuccessCallback successCallback, final FailureCallback failureCallback) {
        okHttpClient.newCall(new Request.Builder().url(str).build()).enqueue(new Callback() {
            /* class com.oculus.graphql.fb.FacebookGraphQLUtil.AnonymousClass1 */

            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
                Log.e(FacebookGraphQLUtil.TAG, "Query failed");
                FailureCallback.this.callback(iOException);
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) {
                try {
                    ResponseBody body = response.body();
                    try {
                        if (response.isSuccessful()) {
                            Log.d(FacebookGraphQLUtil.TAG, "Query successful");
                            try {
                                JSONObject jSONObject = new JSONObject(body.string());
                                Log.d(FacebookGraphQLUtil.TAG, "Successfully parsed response to JSON");
                                JSONObject optJSONObject = jSONObject.optJSONObject("data");
                                if (optJSONObject == null) {
                                    String str = FacebookGraphQLUtil.TAG;
                                    Log.e(str, "Failed to extract data from JSON. JSON: " + jSONObject.toString());
                                    FailureCallback.this.callback(new IOException("Failed to extract data from JSON"));
                                    if (body != null) {
                                        body.close();
                                        return;
                                    }
                                    return;
                                }
                                Log.d(FacebookGraphQLUtil.TAG, "Successfully extracted data from JSON");
                                successCallback.callback(optJSONObject);
                            } catch (JSONException e) {
                                Log.e(FacebookGraphQLUtil.TAG, "Failed to parse JSON: ", e);
                                FailureCallback.this.callback(e);
                                if (body != null) {
                                    body.close();
                                    return;
                                }
                                return;
                            }
                        } else {
                            Log.e(FacebookGraphQLUtil.TAG, "Response is not successful");
                            FailureCallback.this.callback(new RuntimeException("Response is not successful"));
                        }
                        if (body != null) {
                            body.close();
                            return;
                        }
                        return;
                    } catch (Throwable th) {
                        th.addSuppressed(th);
                    }
                    throw th;
                } catch (IOException e2) {
                    Log.e(FacebookGraphQLUtil.TAG, "Failed to get response: ", e2);
                    FailureCallback.this.callback(e2);
                }
            }
        });
    }

    private static HttpUrl.Builder getDefaultUrlBuilder(Map<String, String> map) {
        HttpUrl.Builder newBuilder = HttpUrl.parse(FB_GRAPHQL_ENDPOINT).newBuilder();
        newBuilder.addPathSegment(FB_GRAPHQL_PATH);
        newBuilder.addQueryParameter("method", "post");
        map.forEach(new BiConsumer() {
            /* class com.oculus.graphql.fb.$$Lambda$FacebookGraphQLUtil$IKaJV8Q0hhpy3O3mwSVE9eX7d7Y */

            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                FacebookGraphQLUtil.lambda$getDefaultUrlBuilder$0(HttpUrl.Builder.this, (String) obj, (String) obj2);
            }
        });
        return newBuilder;
    }
}
