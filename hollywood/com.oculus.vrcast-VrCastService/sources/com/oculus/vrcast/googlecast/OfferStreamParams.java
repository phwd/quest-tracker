package com.oculus.vrcast.googlecast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OfferStreamParams {
    public final String aesIvMask;
    public final String aesKey;
    public final String codecName;
    public final GoogleCastParams googleCastParams;
    public final long ssrc;

    private OfferStreamParams(Builder builder) {
        this.googleCastParams = builder.googleCastParams;
        this.codecName = builder.codecName;
        this.aesKey = builder.aesKey;
        this.aesIvMask = builder.aesIvMask;
        this.ssrc = builder.ssrc;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        public String aesIvMask;
        public String aesKey;
        public String codecName = "vp8";
        public GoogleCastParams googleCastParams;
        public long ssrc;

        public OfferStreamParams build() {
            return new OfferStreamParams(this);
        }

        public Builder setGoogleCastParams(GoogleCastParams googleCastParams2) {
            this.googleCastParams = googleCastParams2;
            return this;
        }

        public Builder setAesKey(String str) {
            this.aesKey = str;
            return this;
        }

        public Builder setAesIvMask(String str) {
            this.aesIvMask = str;
            return this;
        }

        public Builder setSsrc(long j) {
            this.ssrc = j;
            return this;
        }

        public Builder setCodecName(String str) {
            this.codecName = str;
            return this;
        }
    }

    /* access modifiers changed from: package-private */
    public JSONObject toJSONObject() throws JSONException {
        return new JSONObject().put("index", 0).put("codecName", this.codecName).put("rtpProfile", "cast").put("rtpPayloadType", 96).put("ssrc", this.ssrc).put("targetDelay", this.googleCastParams.targetPlayoutDelay).put("aesKey", this.aesKey).put("aesIvMask", this.aesIvMask).put("timeBase", "1/90000").put("receiverRtcpEventLog", false).put("type", "video_source").put("renderMode", "video").put("maxFrameRate", String.format("%d/1000", Integer.valueOf(this.googleCastParams.fps * 1000))).put("maxBitRate", this.googleCastParams.bitrate * 1000).put("resolutions", new JSONArray().put(new JSONObject().put("width", this.googleCastParams.width).put("height", this.googleCastParams.height)));
    }
}
