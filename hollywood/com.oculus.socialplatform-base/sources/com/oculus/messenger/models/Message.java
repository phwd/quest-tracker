package com.oculus.messenger.models;

import androidx.core.app.NotificationCompat$CarExtender;
import com.facebook.common.json.AutoGenJsonSerializer;
import com.facebook.proguard.annotations.DoNotStripAny;
import com.fasterxml.jackson.annotation.JsonProperty;

@AutoGenJsonSerializer
@DoNotStripAny
public class Message {
    @JsonProperty("senderId")
    public final long mActorId;
    @JsonProperty("attachments")
    public final Attachment[] mAttachments;
    @JsonProperty("body")
    public final String mBody;
    @JsonProperty("isSending")
    public final boolean mIsSending;
    @JsonProperty("messageId")
    public final String mMessageId;
    @JsonProperty("offlineThreadingId")
    public final long mOfflineThreadingId;
    @JsonProperty("status")
    public final String mStatus;
    @JsonProperty(NotificationCompat$CarExtender.KEY_TIMESTAMP)
    public final long mTimestamp;
    @JsonProperty("type")
    public final String mType;

    public Message(String str, String str2, long j, long j2, String str3, String str4, long j3, Attachment[] attachmentArr) {
        this.mBody = str;
        this.mMessageId = str2;
        this.mActorId = j;
        this.mTimestamp = j2;
        this.mStatus = str3;
        this.mIsSending = "SENDING".equals(str3);
        this.mType = str4;
        this.mOfflineThreadingId = j3;
        this.mAttachments = attachmentArr;
    }

    public long getActorId() {
        return this.mActorId;
    }

    public Attachment[] getAttachments() {
        return this.mAttachments;
    }

    public String getBody() {
        return this.mBody;
    }

    public boolean getIsSending() {
        return this.mIsSending;
    }

    public String getMessageId() {
        return this.mMessageId;
    }

    public long getOfflineThreadingId() {
        return this.mOfflineThreadingId;
    }

    public String getStatus() {
        return this.mStatus;
    }

    public long getTimestamp() {
        return this.mTimestamp;
    }

    public String getType() {
        return this.mType;
    }
}
