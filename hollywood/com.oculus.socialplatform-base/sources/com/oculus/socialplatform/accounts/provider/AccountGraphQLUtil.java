package com.oculus.socialplatform.accounts.provider;

import X.AnonymousClass0MD;
import androidx.annotation.Nullable;
import com.oculus.mediaupload.model.FacebookShareRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;
import java.util.function.BiConsumer;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.json.JSONException;
import org.json.JSONObject;

public class AccountGraphQLUtil {
    public static final String GRAPH_API_ENDPOINT = "https://graph.facebook.com";
    public static final String PATH_GRAPHQL = "graphql";
    public static final String TAG = "AccountGraphQLUtil";

    public static final class Result {
        public String mUserName;
        @Nullable
        public String mUserProfilePictureUrl;

        public Optional<String> getUserProfilePictureUrl() {
            return Optional.ofNullable(this.mUserProfilePictureUrl);
        }

        public Result(String str, @Nullable String str2) {
            this.mUserName = str;
            this.mUserProfilePictureUrl = str2;
        }

        public String getUserName() {
            return this.mUserName;
        }
    }

    public static Optional<Result> fetchUserData(OkHttpClient okHttpClient, String str) {
        Optional<Result> optional;
        HttpUrl.Builder newBuilder = HttpUrl.parse("https://graph.facebook.com").newBuilder();
        newBuilder.addPathSegment("graphql");
        newBuilder.addQueryParameter("method", "post");
        HashMap hashMap = new HashMap();
        hashMap.put("access_token", str);
        hashMap.put("doc", "query {\n  me {\n    ... on User {\n      name\n      profile_picture(width: 40, height: 40, scale: 2) {\n        uri\n      }\n    }\n  }\n}");
        hashMap.forEach(new BiConsumer() {
            /* class com.oculus.socialplatform.accounts.provider.$$Lambda$2NmDXJdp3b2vI4IInY8XQgcSQ */

            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                HttpUrl.Builder.this.addQueryParameter((String) obj, (String) obj2);
            }
        });
        Request.Builder builder = new Request.Builder();
        builder.url(newBuilder.build());
        try {
            Response execute = okHttpClient.newCall(builder.build()).execute();
            ResponseBody responseBody = execute.body;
            try {
                if (!execute.isSuccessful()) {
                    AnonymousClass0MD.A04(TAG, "Fetching account data failed. Response is not successful.");
                    optional = Optional.empty();
                    if (responseBody == null) {
                        return optional;
                    }
                } else {
                    JSONObject jSONObject = new JSONObject(responseBody.string()).getJSONObject("data").getJSONObject(FacebookShareRequest.ME);
                    String optString = jSONObject.optString("name");
                    JSONObject optJSONObject = jSONObject.optJSONObject("profile_picture");
                    String str2 = null;
                    if (optJSONObject != null && !optJSONObject.optString("uri").isEmpty()) {
                        str2 = optJSONObject.getString("uri");
                    }
                    optional = Optional.of(new Result(optString, str2));
                }
                responseBody.close();
                return optional;
            } catch (Throwable unused) {
            }
            throw th;
        } catch (IOException e) {
            AnonymousClass0MD.A0C(TAG, e, "Request failed.");
            return Optional.empty();
        } catch (JSONException e2) {
            AnonymousClass0MD.A0C(TAG, e2, "Decoding JSON failed.");
            return Optional.empty();
        }
    }
}
