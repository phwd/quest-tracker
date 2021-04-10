package com.oculus.updater.core.os.contract;

public class OSUpdateException extends Exception {
    public boolean mNeedTelemForCheckFailed;

    public OSUpdateException(String str, boolean z) {
        super(str);
        this.mNeedTelemForCheckFailed = z;
    }
}
