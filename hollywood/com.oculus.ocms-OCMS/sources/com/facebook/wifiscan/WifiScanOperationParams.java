package com.facebook.wifiscan;

import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.concurrent.Immutable;

@Nullsafe(Nullsafe.Mode.LOCAL)
@Immutable
public class WifiScanOperationParams {
    private static final long DEFAULT_AGE_LIMIT_MS = 10000;
    public final long ageLimitMs;
    public boolean forceActiveScan;
    public long forceTimestampFixWindowMs;
    public final boolean returnAllResults;
    public final long timeoutMs;

    public static WifiScanOperationParams withTimeout(long j) {
        return new WifiScanOperationParams(j, 10000);
    }

    public WifiScanOperationParams(long j, long j2) {
        this(j, j2, false);
    }

    public WifiScanOperationParams(long j, long j2, boolean z) {
        this.timeoutMs = j;
        this.ageLimitMs = j2;
        this.returnAllResults = z;
    }

    public WifiScanOperationParams(long j, long j2, boolean z, long j3, boolean z2) {
        this.timeoutMs = j;
        this.ageLimitMs = j2;
        this.returnAllResults = z;
        this.forceTimestampFixWindowMs = j3;
        this.forceActiveScan = z2;
    }

    public String toString() {
        return "WifiScanOperationParams{timeoutMs=" + this.timeoutMs + ", ageLimitMs=" + this.ageLimitMs + ", returnAllResults=" + this.returnAllResults + '}';
    }
}
