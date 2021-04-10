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

        public static Builder createDebugOnlyValueInfo() {
            return new Builder();
        }

        public DebugOnlyValueInfo build() {
            return new DebugOnlyValueInfo(this);
        }

        public Builder setConfigParamName(String str) {
            this.mConfigParamName = str;
            return this;
        }

        public Builder setDeviceWideCachedValue(@Nullable Object obj) {
            this.mDeviceWideCachedValue = obj;
            return this;
        }

        public Builder setIsSessionless(boolean z) {
            this.mIsSessionless = z;
            return this;
        }

        public Builder setLoggingId(String str) {
            this.mLoggingId = str;
            return this;
        }

        public Builder setMCValueSource(String str) {
            this.mMCValueSource = str;
            return this;
        }

        public Builder setMobileConfigServiceValue(@Nullable Object obj) {
            this.mMobileConfigServiceValue = obj;
            return this;
        }

        public Builder setOverriddenValue(@Nullable Object obj) {
            this.mOverriddenValue = obj;
            return this;
        }

        public Builder setValueType(int i) {
            this.mValueType = i;
            return this;
        }
    }

    public String getConfigParamName() {
        return this.mConfigParamName;
    }

    @Nullable
    public Object getDeviceWideCachedValue() {
        return this.mDeviceWideCachedValue;
    }

    public boolean getIsSessionless() {
        return this.mIsSessionless;
    }

    public String getLoggingId() {
        return this.mLoggingId;
    }

    public String getMCValueSource() {
        return this.mMCValueSource;
    }

    @Nullable
    public Object getMobileConfigServiceValue() {
        return this.mMobileConfigServiceValue;
    }

    @Nullable
    public Object getOverriddenValue() {
        return this.mOverriddenValue;
    }

    public int getValueType() {
        return this.mValueType;
    }

    public DebugOnlyValueInfo(Builder builder) {
        this.mConfigParamName = builder.mConfigParamName;
        this.mDeviceWideCachedValue = builder.mDeviceWideCachedValue;
        this.mMobileConfigServiceValue = builder.mMobileConfigServiceValue;
        this.mOverriddenValue = builder.mOverriddenValue;
        this.mLoggingId = builder.mLoggingId;
        this.mIsSessionless = builder.mIsSessionless;
        this.mMCValueSource = builder.mMCValueSource;
        this.mValueType = builder.mValueType;
    }
}
