package com.oculus.deviceconfigclient;

public class DebugOnlyValueInfo {
    private final String mConfigParamName;
    private final Object mDeviceWideCachedValue;
    private final boolean mIsSessionless;
    private final String mLoggingId;
    private final Object mMobileConfigServiceValue;
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

    public Object getDeviceWideCachedValue() {
        return this.mDeviceWideCachedValue;
    }

    public Object getMobileConfigServiceValue() {
        return this.mMobileConfigServiceValue;
    }

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
        private Object mDeviceWideCachedValue = null;
        private boolean mIsSessionless = false;
        private String mLoggingId = "";
        private Object mMobileConfigServiceValue = null;
        private Object mOverriddenValue = null;
        private int mValueType = 0;

        public static Builder createDebugOnlyValueInfo() {
            return new Builder();
        }

        public DebugOnlyValueInfo build() {
            return new DebugOnlyValueInfo(this);
        }

        public Builder setConfigParamName(String configParamName) {
            this.mConfigParamName = configParamName;
            return this;
        }

        public Builder setDeviceWideCachedValue(Object value) {
            this.mDeviceWideCachedValue = value;
            return this;
        }

        public Builder setMobileConfigServiceValue(Object value) {
            this.mMobileConfigServiceValue = value;
            return this;
        }

        public Builder setOverriddenValue(Object value) {
            this.mOverriddenValue = value;
            return this;
        }

        public Builder setLoggingId(String loggingId) {
            this.mLoggingId = loggingId;
            return this;
        }

        public Builder setIsSessionless(boolean isSessionless) {
            this.mIsSessionless = isSessionless;
            return this;
        }

        public Builder setValueType(int valueType) {
            this.mValueType = valueType;
            return this;
        }
    }
}
