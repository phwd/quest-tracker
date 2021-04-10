package com.oculus.vrshell.panels;

public enum PanelFlags {
    GAZE_VALID(1),
    GAZE_INSIDE(2),
    REMOTE_ACTIVE(4),
    REMOTE_CONNECTED(8),
    REMOTE_RECENTERED(16),
    HAND_ACTIVE(32);
    
    private final long flagValue;

    private PanelFlags(long flagValue2) {
        this.flagValue = flagValue2;
    }

    public long getFlagValue() {
        return this.flagValue;
    }

    public boolean isEnabled(long bits) {
        return (this.flagValue & bits) != 0;
    }
}
