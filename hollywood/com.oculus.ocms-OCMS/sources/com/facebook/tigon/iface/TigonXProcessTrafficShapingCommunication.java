package com.facebook.tigon.iface;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class TigonXProcessTrafficShapingCommunication {
    private final long mBandwidthKbps;
    private final long mExpirationTimeMS;

    public TigonXProcessTrafficShapingCommunication(long j, long j2) {
        this.mBandwidthKbps = j;
        this.mExpirationTimeMS = j2;
    }

    public long bandwidthKbps() {
        return this.mBandwidthKbps;
    }

    public long expirationTimeMS() {
        return this.mExpirationTimeMS;
    }
}
