package com.facebook.tigon.iface;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class TigonNetworkStatusInfo {
    private final long mBandwidthEstimateKbps;
    private final long mBandwidthStdDev;
    private final long mBandwidthWeight;
    private final long mRttMilliseconds;
    private final long mRttStdDev;
    private final long mThroughputCorrectedKbps;
    private final long mThroughputKbps;
    private final long mTtfbEstimateMs;
    private final long mTtfbStdDev;
    private final long mTtfbWeight;

    public TigonNetworkStatusInfo(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10) {
        this.mThroughputKbps = j;
        this.mRttMilliseconds = j2;
        this.mThroughputCorrectedKbps = j3;
        this.mRttStdDev = j4;
        this.mBandwidthEstimateKbps = j5;
        this.mTtfbEstimateMs = j6;
        this.mBandwidthWeight = j7;
        this.mTtfbWeight = j8;
        this.mBandwidthStdDev = j9;
        this.mTtfbStdDev = j10;
    }

    public long throughputKbps() {
        return this.mThroughputKbps;
    }

    public long rttMilliseconds() {
        return this.mRttMilliseconds;
    }

    public long throughputCorrectedKbps() {
        return this.mThroughputCorrectedKbps;
    }

    public long rttStdDev() {
        return this.mRttStdDev;
    }

    public long bandwidthEstimateKbps() {
        return this.mBandwidthEstimateKbps;
    }

    public long ttfbEstimateMs() {
        return this.mTtfbEstimateMs;
    }

    public long bandwidthWeight() {
        return this.mBandwidthWeight;
    }

    public long ttfbWeight() {
        return this.mTtfbWeight;
    }

    public long bandwidthStdDev() {
        return this.mBandwidthStdDev;
    }

    public long ttfbStdDev() {
        return this.mTtfbStdDev;
    }

    public long expectedLatency() {
        if (rttMilliseconds() <= 0) {
            return -1;
        }
        if (rttStdDev() > 0) {
            return rttMilliseconds() + rttStdDev();
        }
        return rttMilliseconds();
    }
}
