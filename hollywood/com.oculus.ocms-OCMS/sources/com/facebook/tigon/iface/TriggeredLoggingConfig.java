package com.facebook.tigon.iface;

import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class TriggeredLoggingConfig {
    private static final String FIELDMAP = "ELT";
    private boolean mEndToEndTracingRequested;
    private boolean mLegacyTracingRequested;
    private final String mLoggingId;
    private final String mSessionId;
    private boolean mTriggerFlowTimeData;
    private final String mUseCaseId;

    public TriggeredLoggingConfig(String str, String str2, String str3) {
        this.mUseCaseId = str;
        this.mLoggingId = str2;
        this.mSessionId = str3;
    }

    public TriggeredLoggingConfig(String str, String str2) {
        this(str, str2, "");
    }

    private static boolean getBitField(String str, int i) {
        return str.charAt(i) == FIELDMAP.charAt(i);
    }

    @Nullable
    public static TriggeredLoggingConfig deserialize(String str) {
        String[] split = str.split(":");
        TriggeredLoggingConfig triggeredLoggingConfig = null;
        if (split.length >= 3 && split.length <= 4 && split[0].length() == 3 && split[1].length() != 0 && split[2].length() != 0) {
            triggeredLoggingConfig = new TriggeredLoggingConfig(split[1], split[2], split.length > 3 ? split[3] : "");
            triggeredLoggingConfig.setEndToEndTracingRequested(getBitField(split[0], 0));
            triggeredLoggingConfig.setLegacyTracingRequested(getBitField(split[0], 1));
            triggeredLoggingConfig.setTriggerFlowTimeData(getBitField(split[0], 2));
        }
        return triggeredLoggingConfig;
    }

    public String useCaseId() {
        return this.mUseCaseId;
    }

    public String loggingId() {
        return this.mLoggingId;
    }

    public String sessionId() {
        return this.mSessionId;
    }

    public boolean getEndToEndTracingRequested() {
        return this.mEndToEndTracingRequested;
    }

    public boolean getLegacyTracingRequested() {
        return this.mLegacyTracingRequested;
    }

    public boolean getTriggerFlowTimeData() {
        return this.mTriggerFlowTimeData;
    }

    public void setEndToEndTracingRequested(boolean z) {
        this.mEndToEndTracingRequested = z;
    }

    public void setLegacyTracingRequested(boolean z) {
        this.mLegacyTracingRequested = z;
    }

    public void setTriggerFlowTimeData(boolean z) {
        this.mTriggerFlowTimeData = z;
    }

    public String serialize() {
        StringBuilder sb = new StringBuilder();
        char c = '.';
        sb.append(this.mEndToEndTracingRequested ? 'E' : '.');
        sb.append(this.mLegacyTracingRequested ? 'L' : '.');
        if (this.mTriggerFlowTimeData) {
            c = 'T';
        }
        sb.append(c);
        sb.append(':');
        sb.append(this.mUseCaseId);
        sb.append(':');
        sb.append(this.mLoggingId);
        sb.append(':');
        sb.append(this.mSessionId);
        return sb.toString();
    }
}
