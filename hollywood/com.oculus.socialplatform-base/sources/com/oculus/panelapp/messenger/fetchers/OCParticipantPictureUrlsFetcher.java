package com.oculus.panelapp.messenger.fetchers;

import X.AnonymousClass006;
import android.util.Log;
import android.util.Pair;
import com.oculus.graphql.oc.GraphQLUtil;
import com.oculus.messengervr.oc.OcUserPictureUrlsQueryHandler;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import okhttp3.OkHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OCParticipantPictureUrlsFetcher implements OcUserPictureUrlsQueryHandler {
    public static final String TAG = "OCParticipantPictureUrlsFetcher";
    public final String mAccessToken;
    public final OkHttpClient mOkHttpClient;

    public static /* synthetic */ String access$000() {
        return TAG;
    }

    public static JSONObject getParamsForFetchOCUsersProfilePics(List<String> list) {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONArray jSONArray = new JSONArray();
            for (int i = 0; i < list.size(); i++) {
                jSONArray.put(list.get(i));
            }
            jSONObject.put("user_ids", jSONArray);
            return jSONObject;
        } catch (JSONException e) {
            Log.e(TAG, "Json error on query params", e);
            return jSONObject;
        }
    }

    public static /* synthetic */ void lambda$queryOCUsersNamesAndProfilePics$1(Consumer consumer, Consumer consumer2, JSONObject jSONObject) {
        try {
            JSONObject optJSONObject = jSONObject.optJSONObject("data");
            JSONArray optJSONArray = optJSONObject != null ? optJSONObject.optJSONArray("nodes") : null;
            HashMap hashMap = new HashMap();
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                    String string = jSONObject2.getString("id");
                    JSONObject optJSONObject2 = jSONObject2.optJSONObject("profile_photo");
                    hashMap.putIfAbsent(string, new Pair(jSONObject2.getString("display_name"), optJSONObject2 != null ? optJSONObject2.getString("uri") : null));
                }
            }
            consumer.accept(hashMap);
        } catch (JSONException e) {
            consumer2.accept(e);
        }
    }

    public static /* synthetic */ void lambda$queryOCUsersProfilePics$0(Consumer consumer, Consumer consumer2, JSONObject jSONObject) {
        try {
            JSONObject optJSONObject = jSONObject.optJSONObject("data");
            JSONArray optJSONArray = optJSONObject != null ? optJSONObject.optJSONArray("nodes") : null;
            HashMap hashMap = new HashMap();
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                    String string = jSONObject2.getString("id");
                    JSONObject optJSONObject2 = jSONObject2.optJSONObject("profile_photo");
                    hashMap.putIfAbsent(string, optJSONObject2 != null ? optJSONObject2.getString("uri") : null);
                }
            }
            consumer.accept(hashMap);
        } catch (JSONException e) {
            consumer2.accept(e);
        }
    }

    @Override // com.oculus.messengervr.oc.OcUserPictureUrlsQueryHandler
    public void queryOCUsersNamesAndProfilePics(List<String> list, Consumer<Map<String, Pair<String, String>>> consumer, Consumer<Throwable> consumer2) {
        queryGraphQLForOCUsers(this.mOkHttpClient, "query($user_ids: [ID!]!) {  nodes(node_id: $user_ids) {    id,    ... on User {      display_name      profile_photo {        uri      }    }  }}", list, this.mAccessToken, consumer2, new Consumer(consumer, consumer2) {
            /* class com.oculus.panelapp.messenger.fetchers.$$Lambda$OCParticipantPictureUrlsFetcher$5ZhLskNjyPEKKag6oJErKSEaUo2 */
            public final /* synthetic */ Consumer f$0;
            public final /* synthetic */ Consumer f$1;

            {
                this.f$0 = r1;
                this.f$1 = r2;
            }

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                OCParticipantPictureUrlsFetcher.lambda$queryOCUsersNamesAndProfilePics$1(this.f$0, this.f$1, (JSONObject) obj);
            }
        });
    }

    @Override // com.oculus.messengervr.oc.OcUserPictureUrlsQueryHandler
    public void queryOCUsersProfilePics(List<String> list, Consumer<Map<String, String>> consumer, Consumer<Throwable> consumer2) {
        queryGraphQLForOCUsers(this.mOkHttpClient, "query($user_ids: [ID!]!) {  nodes(node_id: $user_ids) {    id,    ... on User {      profile_photo {        uri      }    }  }}", list, this.mAccessToken, consumer2, new Consumer(consumer, consumer2) {
            /* class com.oculus.panelapp.messenger.fetchers.$$Lambda$OCParticipantPictureUrlsFetcher$s6NvkwnAZ9ftgAWpImm3Mzg07Ro2 */
            public final /* synthetic */ Consumer f$0;
            public final /* synthetic */ Consumer f$1;

            {
                this.f$0 = r1;
                this.f$1 = r2;
            }

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                OCParticipantPictureUrlsFetcher.lambda$queryOCUsersProfilePics$0(this.f$0, this.f$1, (JSONObject) obj);
            }
        });
    }

    public OCParticipantPictureUrlsFetcher(String str, OkHttpClient okHttpClient) {
        this.mAccessToken = str;
        this.mOkHttpClient = okHttpClient;
    }

    public static void queryGraphQLForOCUsers(OkHttpClient okHttpClient, String str, List<String> list, String str2, final Consumer<Throwable> consumer, final Consumer<JSONObject> consumer2) {
        GraphQLUtil.queryDoc(okHttpClient, str, getParamsForFetchOCUsersProfilePics(list), str2, new GraphQLUtil.Result() {
            /* class com.oculus.panelapp.messenger.fetchers.OCParticipantPictureUrlsFetcher.AnonymousClass1 */

            @Override // com.oculus.graphql.oc.GraphQLUtil.Result
            public void onFailure(String str) {
                Log.e(OCParticipantPictureUrlsFetcher.TAG, AnonymousClass006.A07("onFailure: ", str));
                consumer.accept(new Throwable(str));
            }

            @Override // com.oculus.graphql.oc.GraphQLUtil.Result
            public void onSuccess(JSONObject jSONObject) {
                consumer2.accept(jSONObject);
            }
        });
    }
}
