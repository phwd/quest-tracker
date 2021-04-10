package com.oculus.messenger.models;

import com.facebook.common.json.AutoGenJsonSerializer;
import com.facebook.proguard.annotations.DoNotStripAny;
import com.fasterxml.jackson.annotation.JsonProperty;

@AutoGenJsonSerializer
@DoNotStripAny
public class Attachment {
    @JsonProperty("attachmentId")
    public final long mAttachmentId;
    @JsonProperty("attachmentType")
    public final String mAttachmentType;

    public Attachment(long j, String str) {
        this.mAttachmentId = j;
        this.mAttachmentType = str;
    }

    public long getAttachmentId() {
        return this.mAttachmentId;
    }

    public String getAttachmentType() {
        return this.mAttachmentType;
    }
}
