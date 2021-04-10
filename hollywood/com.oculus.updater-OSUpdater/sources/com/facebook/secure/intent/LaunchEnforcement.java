package com.facebook.secure.intent;

public class LaunchEnforcement {
    public static final EnforceMode DEFAULT_ENFORCE_MODE = EnforceMode.OPEN_EXCEPTION_ONLY;
    private EnforceMode mEnforceMode = DEFAULT_ENFORCE_MODE;

    public enum EnforceMode {
        OPEN_EVERYWHERE,
        OPEN_NONFB_AND_EXCEPTION_ONLY,
        OPEN_EXCEPTION_ONLY,
        ENFORCE_EVERYWHERE
    }

    public synchronized EnforceMode getEnforceMode() {
        return this.mEnforceMode;
    }

    public synchronized void setEnforceMode(EnforceMode enforceMode) {
        this.mEnforceMode = enforceMode;
    }
}
