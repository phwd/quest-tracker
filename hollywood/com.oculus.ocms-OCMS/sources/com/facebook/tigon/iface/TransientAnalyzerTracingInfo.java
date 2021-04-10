package com.facebook.tigon.iface;

import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class TransientAnalyzerTracingInfo {
    private final boolean mEndToEndTracingRequested;
    private final boolean mLegacyTracingRequested;
    private final String mSessionId;
    private final boolean mTriggerMobileHttpRequestLogging;

    public TransientAnalyzerTracingInfo(String str, boolean z, boolean z2, boolean z3) {
        if (str != null) {
            this.mSessionId = str;
        } else {
            this.mSessionId = new String("<NULL>");
        }
        this.mEndToEndTracingRequested = z;
        this.mLegacyTracingRequested = z2;
        this.mTriggerMobileHttpRequestLogging = z3;
    }

    public boolean endToEndTracingRequested() {
        return this.mEndToEndTracingRequested;
    }

    public boolean legacyTracingRequested() {
        return this.mLegacyTracingRequested;
    }

    public boolean triggerMobileHttpRequestLogging() {
        return this.mTriggerMobileHttpRequestLogging;
    }

    public String sessionId() {
        return this.mSessionId;
    }

    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof TransientAnalyzerTracingInfo)) {
            return false;
        }
        TransientAnalyzerTracingInfo transientAnalyzerTracingInfo = (TransientAnalyzerTracingInfo) obj;
        if (this.mSessionId.equals(transientAnalyzerTracingInfo.sessionId()) && this.mEndToEndTracingRequested == transientAnalyzerTracingInfo.endToEndTracingRequested() && this.mLegacyTracingRequested == transientAnalyzerTracingInfo.legacyTracingRequested() && this.mTriggerMobileHttpRequestLogging == transientAnalyzerTracingInfo.triggerMobileHttpRequestLogging()) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.mSessionId.hashCode() + (this.mEndToEndTracingRequested ? 1 : 0) + (this.mLegacyTracingRequested ? 1 : 0) + (this.mTriggerMobileHttpRequestLogging ? 1 : 0);
    }
}
