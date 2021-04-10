package com.oculus.horizon.cloudstorage2;

public class ConflictData {
    public final long localTimestamp;
    public final long remoteTimestamp;

    public ConflictData(long j, long j2) {
        this.localTimestamp = j;
        this.remoteTimestamp = j2;
    }
}
