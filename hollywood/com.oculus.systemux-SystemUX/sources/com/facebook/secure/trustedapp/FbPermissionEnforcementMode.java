package com.facebook.secure.trustedapp;

public enum FbPermissionEnforcementMode {
    DISABLED(0),
    LOG_ONLY(1),
    ENFORCED(2);
    
    private int mode;

    public static FbPermissionEnforcementMode fromInt(int i) {
        FbPermissionEnforcementMode[] values = values();
        for (FbPermissionEnforcementMode fbPermissionEnforcementMode : values) {
            if (fbPermissionEnforcementMode.getMode() == i) {
                return fbPermissionEnforcementMode;
            }
        }
        return DISABLED;
    }

    private int getMode() {
        return this.mode;
    }

    private FbPermissionEnforcementMode(int i) {
        this.mode = i;
    }
}
