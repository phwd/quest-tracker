package com.oculus.mediaupload.request;

import android.text.TextUtils;
import androidx.core.app.NotificationCompat$CarExtender;
import com.google.common.collect.ImmutableMap;
import com.oculus.http.common.graphql.GraphQLParamsHelper;
import javax.annotation.Nullable;

public class OculusShareToMessengerMessageSendData {
    @Nullable
    public final String text;
    public final String threadId;
    public final String videoId;

    public OculusShareToMessengerMessageSendData(String str, String str2, @Nullable String str3) {
        this.threadId = str;
        this.videoId = str2;
        this.text = str3;
    }

    public final String toString() {
        ImmutableMap.Builder A01 = ImmutableMap.A01();
        A01.put("thread_or_other_user_id", this.threadId);
        A01.put("ent_video_id", this.videoId);
        String str = this.text;
        if (str != null && !TextUtils.isEmpty(str)) {
            A01.put(NotificationCompat$CarExtender.KEY_TEXT, this.text);
        }
        return GraphQLParamsHelper.A01(A01.build());
    }
}
