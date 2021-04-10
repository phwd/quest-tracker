package com.facebook.proxygen;

public class ConnQuality {
    public final long mCwnd;
    public final long mMss;
    public final long mRtt;
    public final long mRtx;
    public final long mTotalBytesWritten;
    public final long mUpstreamLatency;

    public String toString() {
        StringBuilder sb = new StringBuilder("{\"rtt\":");
        sb.append(this.mRtt);
        sb.append(",\"rtx\":");
        sb.append(this.mRtx);
        sb.append(",\"cwnd\":");
        sb.append(this.mCwnd);
        sb.append(",\"mss\":");
        sb.append(this.mMss);
        sb.append(",\"tbw\":");
        sb.append(this.mTotalBytesWritten);
        sb.append("}");
        return sb.toString();
    }

    public ConnQuality(long j, long j2, long j3, long j4, long j5, long j6) {
        this.mRtt = j;
        this.mRtx = j2;
        this.mCwnd = j3;
        this.mMss = j4;
        this.mTotalBytesWritten = j5;
        this.mUpstreamLatency = j6;
    }

    public long getCwnd() {
        return this.mCwnd;
    }

    public long getMss() {
        return this.mMss;
    }

    public long getRtt() {
        return this.mRtt;
    }

    public long getRtx() {
        return this.mRtx;
    }

    public long getTotalBytesWritten() {
        return this.mTotalBytesWritten;
    }

    public long getUpstreamLatency() {
        return this.mUpstreamLatency;
    }
}
