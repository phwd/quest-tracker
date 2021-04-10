package com.oculus.messengervr.fb;

import X.AbstractC06511aN;
import X.AbstractC13231zC;
import X.AnonymousClass0MD;
import X.AnonymousClass1vA;
import X.AnonymousClass1vF;
import X.C12481xf;
import android.util.Pair;
import androidx.core.app.NotificationCompat$CarExtender;
import com.oculus.common.sociallogger.LoggingConstants;
import com.oculus.horizon.api.rating.ReviewsRequest;
import com.oculus.messengervr.fbshared.models.FBMessengerMessage;
import com.oculus.messengervr.fbshared.models.FBMessengerThread;
import com.oculus.messengervr.interfaces.MessengerMessage;
import com.oculus.messengervr.interfaces.MessengerThread;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.function.BiConsumer;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class GraphQLUtil {
    public static final String GRAPH_API_ENDPOINT;
    public static final String PATH_GRAPHQL;

    public static AbstractC13231zC<MessengerMessage[]> fetchMessageList(OkHttpClient okHttpClient, String str, long j, int i) {
        return new C12481xf(new AnonymousClass1vF(str, j, i, okHttpClient) {
            /* class com.oculus.messengervr.fb.$$Lambda$GraphQLUtil$dLwvv4yJ_vc6IC4vwEUWBZfMCU2 */
            public final /* synthetic */ String f$0;
            public final /* synthetic */ long f$1;
            public final /* synthetic */ int f$2;
            public final /* synthetic */ OkHttpClient f$3;

            {
                this.f$0 = r1;
                this.f$1 = r2;
                this.f$2 = r4;
                this.f$3 = r5;
            }

            @Override // X.AnonymousClass1vF
            public final void subscribe(AnonymousClass1vA r7) {
                GraphQLUtil.lambda$fetchMessageList$3(this.f$0, this.f$1, this.f$2, this.f$3, r7);
            }
        });
    }

    public static AbstractC13231zC<MessengerThread> fetchThreadData(OkHttpClient okHttpClient, String str, long j) {
        return new C12481xf(new AnonymousClass1vF(str, j, okHttpClient) {
            /* class com.oculus.messengervr.fb.$$Lambda$GraphQLUtil$mSEWMSC9W9IXgToEAMhVKdpQluU2 */
            public final /* synthetic */ String f$0;
            public final /* synthetic */ long f$1;
            public final /* synthetic */ OkHttpClient f$2;

            {
                this.f$0 = r1;
                this.f$1 = r2;
                this.f$2 = r4;
            }

            @Override // X.AnonymousClass1vF
            public final void subscribe(AnonymousClass1vA r5) {
                GraphQLUtil.lambda$fetchThreadData$1(this.f$0, this.f$1, this.f$2, r5);
            }
        });
    }

    public static /* synthetic */ void lambda$fetchMessageList$3(String str, final long j, int i, OkHttpClient okHttpClient, final AnonymousClass1vA r9) {
        HttpUrl.Builder newBuilder = HttpUrl.parse("https://graph.facebook.com").newBuilder();
        newBuilder.addPathSegment("graphql");
        newBuilder.addQueryParameter("method", "post");
        HashMap hashMap = new HashMap();
        hashMap.put("access_token", str);
        hashMap.put("doc", "query VrMessengerFetchMessageList ($thread_key: ID!, $count: Int) {\n  vr_messenger_thread(thread_key: $thread_key) {\n    messages(first: $count) {\n      nodes {\n        message_id\n        text\n        sender_id_string\n        sender_name\n        sender_profile_picture(width: 28, height: 28) {\n          uri        }        timestamp_ms\n        is_admin_message\n        attachment_type\n        attachment_image_urls\n      }\n    }\n  }\n}");
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("thread_key", j);
        jSONObject.put(ReviewsRequest.KEY_COUNT, i);
        hashMap.put("variables", jSONObject.toString());
        hashMap.forEach(new BiConsumer() {
            /* class com.oculus.messengervr.fb.$$Lambda$2NmDXJdp3b2vI4IInY8XQgcSQ2 */

            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                HttpUrl.Builder.this.addQueryParameter((String) obj, (String) obj2);
            }
        });
        Request.Builder builder = new Request.Builder();
        builder.url(newBuilder.build());
        Call newCall = okHttpClient.newCall(builder.build());
        r9.A9i(new AbstractC06511aN(j, newCall) {
            /* class com.oculus.messengervr.fb.$$Lambda$GraphQLUtil$U1qtMNvdxYyGAPFRmQHcBzbg5k2 */
            public final /* synthetic */ long f$0;
            public final /* synthetic */ Call f$1;

            {
                this.f$0 = r1;
                this.f$1 = r3;
            }

            @Override // X.AbstractC06511aN
            public final void cancel() {
                this.f$1.cancel();
            }
        });
        newCall.enqueue(new Callback() {
            /* class com.oculus.messengervr.fb.GraphQLUtil.AnonymousClass2 */

            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
                AnonymousClass0MD.A0E("VR_MESSENGER_API", iOException, "Fetch message list failed: threadKey = %d", Long.valueOf(j));
                r9.onComplete();
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) {
                ResponseBody responseBody = response.body;
                try {
                    if (!response.isSuccessful()) {
                        r9.onError(new RuntimeException("Request message list data failed."));
                        if (responseBody == null) {
                            return;
                        }
                    } else {
                        JSONObject optJSONObject = new JSONObject(responseBody.string()).optJSONObject("data");
                        if (optJSONObject == null) {
                            r9.onError(new JSONException("Cannot read 'data' field."));
                        } else {
                            r9.onSuccess(GraphQLUtil.parseMessengerMessageList(optJSONObject.getJSONObject("vr_messenger_thread")));
                        }
                    }
                    try {
                        responseBody.close();
                        return;
                    } catch (IOException | JSONException e) {
                        r9.onError(e);
                        return;
                    }
                } catch (Throwable unused) {
                }
                throw th;
            }
        });
    }

    public static /* synthetic */ void lambda$fetchThreadData$1(String str, final long j, OkHttpClient okHttpClient, final AnonymousClass1vA r8) {
        HttpUrl.Builder newBuilder = HttpUrl.parse("https://graph.facebook.com").newBuilder();
        newBuilder.addPathSegment("graphql");
        newBuilder.addQueryParameter("method", "post");
        HashMap hashMap = new HashMap();
        hashMap.put("access_token", str);
        hashMap.put("doc", "query VrMessengerFetchThreadData ($thread_key: ID!) {\n  vr_messenger_thread(thread_key: $thread_key) {\n    is_thread_present\n    thread_key {\n      other_user_id\n      thread_fbid\n    }\n    thread_name {\n      text\n    }\n    last_activity_timestamp_ms\n    snippet\n    thread_picture {\n      uri\n    }\n    participant_profile_picture_urls_for_thread_picture {\n      uri\n    }\n    is_muted\n    is_unread\n    last_read_watermark_timestamp_ms\n  }\n}");
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("thread_key", j);
        hashMap.put("variables", jSONObject.toString());
        hashMap.forEach(new BiConsumer() {
            /* class com.oculus.messengervr.fb.$$Lambda$2NmDXJdp3b2vI4IInY8XQgcSQ2 */

            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                HttpUrl.Builder.this.addQueryParameter((String) obj, (String) obj2);
            }
        });
        Request.Builder builder = new Request.Builder();
        builder.url(newBuilder.build());
        Call newCall = okHttpClient.newCall(builder.build());
        r8.A9i(new AbstractC06511aN(j, newCall) {
            /* class com.oculus.messengervr.fb.$$Lambda$GraphQLUtil$Fpypk3KNsfkj3Eew0qDcZnoME2 */
            public final /* synthetic */ long f$0;
            public final /* synthetic */ Call f$1;

            {
                this.f$0 = r1;
                this.f$1 = r3;
            }

            @Override // X.AbstractC06511aN
            public final void cancel() {
                this.f$1.cancel();
            }
        });
        newCall.enqueue(new Callback() {
            /* class com.oculus.messengervr.fb.GraphQLUtil.AnonymousClass1 */

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) {
                ResponseBody responseBody = response.body;
                try {
                    if (!response.isSuccessful()) {
                        r8.onError(new RuntimeException("Request thread data failed."));
                        if (responseBody == null) {
                            return;
                        }
                    } else {
                        JSONObject optJSONObject = new JSONObject(responseBody.string()).optJSONObject("data");
                        if (optJSONObject == null) {
                            r8.onError(new JSONException("Cannot read 'data' field."));
                        } else {
                            r8.onSuccess(GraphQLUtil.parseMessengerThread(optJSONObject.getJSONObject("vr_messenger_thread")));
                        }
                    }
                    try {
                        responseBody.close();
                        return;
                    } catch (IOException | JSONException e) {
                        r8.onError(e);
                        return;
                    }
                } catch (Throwable unused) {
                }
                throw th;
            }

            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
                AnonymousClass0MD.A0E("VR_MESSENGER_API", iOException, "Fetching thread failed: %s (threadKey=%d)", iOException.getMessage(), Long.valueOf(j));
                r8.onComplete();
            }
        });
    }

    public static MessengerMessage[] parseMessengerMessageList(JSONObject jSONObject) {
        JSONArray jSONArray = jSONObject.getJSONObject(NotificationCompat$CarExtender.KEY_MESSAGES).getJSONArray("nodes");
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject2 = jSONArray.getJSONObject(i);
            String string = jSONObject2.getString(LoggingConstants.MESSAGE_ID);
            String optString = optString(jSONObject2, "text");
            String str = null;
            if (optString.isEmpty()) {
                optString = null;
            }
            Long valueOf = Long.valueOf(Long.parseLong(jSONObject2.getString("sender_id_string")));
            String optString2 = optString(jSONObject2, "sender_name");
            if (optString2.isEmpty()) {
                optString2 = null;
            }
            JSONObject optJSONObject = jSONObject2.optJSONObject("sender_profile_picture");
            if (optJSONObject != null) {
                String optString3 = optString(optJSONObject, "uri");
                if (!optString3.isEmpty()) {
                    str = optString3;
                }
            }
            Long valueOf2 = Long.valueOf(jSONObject2.getLong("timestamp_ms"));
            Boolean valueOf3 = Boolean.valueOf(jSONObject2.getBoolean("is_admin_message"));
            Integer valueOf4 = Integer.valueOf(jSONObject2.optInt("attachment_type"));
            ArrayList arrayList2 = new ArrayList();
            JSONArray optJSONArray = jSONObject2.optJSONArray("attachment_image_urls");
            if (optJSONArray != null) {
                for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                    arrayList2.add(optJSONArray.getString(i2));
                }
            }
            FBMessengerMessage.Builder builder = new FBMessengerMessage.Builder();
            builder.mMessageId = string;
            builder.mText = optString;
            builder.mSenderId = valueOf;
            builder.mSenderName = optString2;
            builder.mSenderProfilePictureUrl = str;
            builder.mTimestampMs = valueOf2;
            builder.mIsAdminMessage = valueOf3;
            builder.mAttachmentType = valueOf4.intValue();
            builder.mAttachmentImageUrls = (String[]) arrayList2.toArray(new String[0]);
            arrayList.add(0, builder.build());
        }
        return (MessengerMessage[]) arrayList.toArray(new MessengerMessage[0]);
    }

    public static FBMessengerThread parseMessengerThread(JSONObject jSONObject) {
        String str;
        String string;
        String str2;
        Pair<String, String> pair;
        String string2;
        String string3;
        jSONObject.optBoolean("is_thread_present");
        JSONObject jSONObject2 = jSONObject.getJSONObject("thread_key");
        String optString = optString(jSONObject2, "other_user_id");
        if (optString.isEmpty()) {
            if (!jSONObject2.isNull("thread_fbid")) {
                optString = jSONObject2.getString("thread_fbid");
            } else {
                throw new JSONException("thread_fbid is null");
            }
        }
        long parseLong = Long.parseLong(optString);
        JSONObject optJSONObject = jSONObject.optJSONObject("thread_name");
        Long l = null;
        if (optJSONObject == null || optString(optJSONObject, "text").isEmpty()) {
            str = null;
        } else {
            str = optJSONObject.getString("text");
        }
        long optLong = jSONObject.optLong("last_activity_timestamp_ms");
        if (optString(jSONObject, "snippet").isEmpty()) {
            string = null;
        } else {
            string = jSONObject.getString("snippet");
        }
        JSONObject optJSONObject2 = jSONObject.optJSONObject("thread_picture");
        if (optJSONObject2 == null || optString(optJSONObject2, "uri").isEmpty()) {
            str2 = null;
        } else {
            str2 = optJSONObject2.getString("uri");
        }
        if (str2 == null) {
            JSONArray jSONArray = jSONObject.getJSONArray("participant_profile_picture_urls_for_thread_picture");
            String optString2 = optString(jSONArray, 0);
            String optString3 = optString(jSONArray, 1);
            if (optString2 == null || !optString2.isEmpty()) {
                string2 = jSONArray.getString(0);
            } else {
                string2 = null;
            }
            if (optString3 == null || !optString3.isEmpty()) {
                string3 = jSONArray.getString(1);
            } else {
                string3 = null;
            }
            pair = new Pair<>(string2, string3);
        } else {
            pair = null;
        }
        if (jSONObject.optLong("last_read_watermark_timestamp_ms") != 0) {
            l = Long.valueOf(jSONObject.getLong("last_read_watermark_timestamp_ms"));
        }
        FBMessengerThread.Builder builder = new FBMessengerThread.Builder();
        builder.mThreadKey = Long.valueOf(parseLong);
        builder.mThreadName = str;
        builder.mLastActivityTimestampMs = Long.valueOf(optLong);
        builder.mSnippet = string;
        builder.mThreadPictureUrl = str2;
        builder.mParticipantProfilePictureUrlsForThreadPicture = pair;
        builder.mIsUnread = Boolean.valueOf(jSONObject.optBoolean("is_unread", false));
        builder.mIsMuted = Boolean.valueOf(jSONObject.optBoolean("is_muted", false));
        builder.mLastReadWatermarkTimestampMs = l;
        return builder.build();
    }

    public static String optString(JSONArray jSONArray, int i) {
        if (!jSONArray.isNull(i)) {
            return (String) Objects.requireNonNull(jSONArray.optString(i, ""), "Make Nullsafe happy.");
        }
        return "";
    }

    public static String optString(JSONObject jSONObject, String str) {
        return !jSONObject.isNull(str) ? jSONObject.optString(str, "") : "";
    }
}
