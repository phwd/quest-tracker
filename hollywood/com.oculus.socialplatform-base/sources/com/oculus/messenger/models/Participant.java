package com.oculus.messenger.models;

import com.facebook.common.json.AutoGenJsonSerializer;
import com.facebook.proguard.annotations.DoNotStripAny;
import com.fasterxml.jackson.annotation.JsonProperty;

@AutoGenJsonSerializer
@DoNotStripAny
public class Participant {
    @JsonProperty("actionTimestamp")
    public final long mActionTimestamp;
    @JsonProperty("alias")
    public final String mAlias;
    @JsonProperty("participantId")
    public final long mParticipantId;
    @JsonProperty("watermarkTimestamp")
    public final long mWatermarkTimestamp;

    public Participant(long j, String str, long j2, long j3) {
        this.mParticipantId = j;
        this.mAlias = str;
        this.mWatermarkTimestamp = j2;
        this.mActionTimestamp = j3;
    }

    public long getActionTimestamp() {
        return this.mActionTimestamp;
    }

    public String getAlias() {
        return this.mAlias;
    }

    public long getParticipantId() {
        return this.mParticipantId;
    }

    public long getWatermarkTimestamp() {
        return this.mWatermarkTimestamp;
    }
}
