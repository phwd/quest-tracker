package com.facebook.tigon.iface;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class TigonBackupHostServiceInfo {
    public final boolean isBackupHostServiceEnabled;

    public TigonBackupHostServiceInfo(boolean z) {
        this.isBackupHostServiceEnabled = z;
    }

    public boolean isBackupHostServiceEnabled() {
        return this.isBackupHostServiceEnabled;
    }
}
