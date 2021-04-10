package com.oculus.vrshell.panels;

public enum PanelFlags {
    GAZE_VALID(1),
    GAZE_INSIDE(2),
    REMOTE_ACTIVE(4),
    REMOTE_CONNECTED(8),
    REMOTE_RECENTERED(16),
    HAND_ACTIVE(32);
    
    public final long flagValue;

    public long getFlagValue() {
        return this.flagValue;
    }

    public boolean isEnabled(long j) {
        if ((j & this.flagValue) != 0) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: public */
    PanelFlags(long j) {
        this.flagValue = j;
    }
}
