package com.facebook.tigon.iface;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class TigonBackupHostServiceRequestInfo {
    public final String identifier;
    public final boolean isPrimary;
    public final long seqID;

    public TigonBackupHostServiceRequestInfo(long j, boolean z, String str) {
        this.seqID = j;
        this.isPrimary = z;
        this.identifier = str;
    }

    public long seqID() {
        return this.seqID;
    }

    public boolean isPrimary() {
        return this.isPrimary;
    }

    public String identifier() {
        return this.identifier;
    }
}
