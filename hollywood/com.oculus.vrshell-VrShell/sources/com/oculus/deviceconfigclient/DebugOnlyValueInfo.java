package com.oculus.deviceconfigclient;

import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class DebugOnlyValueInfo {
    private final String mConfigParamName;
    @Nullable
    private final Object mDeviceWideCachedValue;
    private final boolean mIsSessionless;
    private final String mLoggingId;
    @Nullable
    private final Object mMobileConfigServiceValue;
    @Nullable
    private Object mOverriddenValue;
    private int mValueType;

    private DebugOnlyValueInfo(Builder builder) {
        this.mConfigParamName = builder.mConfigParamName;
        this.mDeviceWideCachedValue = builder.mDeviceWideCachedValue;
        this.mMobileConfigServiceValue = builder.mMobileConfigServiceValue;
        this.mOverriddenValue = builder.mOverriddenValue;
        this.mLoggingId = builder.mLoggingId;
        this.mIsSessionless = builder.mIsSessionless;
        this.mValueType = builder.mValueType;
    }

    public String getConfigParamName() {
        return this.mConfigParamName;
    }

    @Nullable
    public Object getDeviceWideCachedValue() {
        return this.mDeviceWideCachedValue;
    }

    @Nullable
    public Object getMobileConfigServiceValue() {
        return this.mMobileConfigServiceValue;
    }

    @Nullable
    public Object getOverriddenValue() {
        return this.mOverriddenValue;
    }

    public String getLoggingId() {
        return this.mLoggingId;
    }

    public boolean getIsSessionless() {
        return this.mIsSessionless;
    }

    public int getValueType() {
        return this.mValueType;
    }

    public static class Builder {
        private String mConfigParamName = "";
        @Nullable
        private Object mDeviceWideCachedValue = null;
        private boolean mIsSessionless = false;
        private String mLoggingId = "";
        @Nullable
        private Object mMobileConfigServiceValue = null;
        @Nullable
        private Object mOverriddenValue = null;
        private int mValueType = 0;

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

        public Builder setMobileConfigServiceValue(@Nullable Object obj) {
            this.mMobileConfigServiceValue = obj;
            return this;
        }

        public Builder setOverriddenValue(@Nullable Object obj) {
            this.mOverriddenValue = obj;
            return this;
        }

        public Builder setLoggingId(String str) {
            this.mLoggingId = str;
            return this;
        }

        public Builder setIsSessionless(boolean z) {
            this.mIsSessionless = z;
            return this;
        }

        public Builder setValueType(int i) {
            this.mValueType = i;
            return this;
        }
    }
}
