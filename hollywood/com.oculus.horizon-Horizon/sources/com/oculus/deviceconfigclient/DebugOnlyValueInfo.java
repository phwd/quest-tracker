package com.oculus.deviceconfigclient;

import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class DebugOnlyValueInfo {
    public final String mConfigParamName;
    @Nullable
    public final Object mDeviceWideCachedValue;
    public final boolean mIsSessionless;
    public final String mLoggingId;
    public final String mMCValueSource;
    @Nullable
    public final Object mMobileConfigServiceValue;
    @Nullable
    public Object mOverriddenValue;
    public int mValueType;

    public static class Builder {
        public String mConfigParamName = "";
        @Nullable
        public Object mDeviceWideCachedValue = null;
        public boolean mIsSessionless = false;
        public String mLoggingId = "";
        public String mMCValueSource = "";
        @Nullable
        public Object mMobileConfigServiceValue = null;
        @Nullable
        public Object mOverriddenValue = null;
        public int mValueType = 0;
    }
}
