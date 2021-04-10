package com.oculus.deviceconfigclient;

import android.text.TextUtils;

/* access modifiers changed from: package-private */
public class ValueInfo<Type> {
    private boolean mExposureLogged = false;
    private String mLoggingId = null;
    private boolean mSessionless = false;
    private Type mValueForSerialization = null;
    private boolean mValueReturned = false;
    private ValueSetFlags mValueSetFlag = ValueSetFlags.NotSet;
    private Type mValueToOverride = null;
    private Type mValueToReturn = null;

    public enum ValueSetFlags {
        NotSet(0),
        FromCache(1),
        FromService(2);
        
        final int mPriority;

        private ValueSetFlags(int priority) {
            this.mPriority = priority;
        }
    }

    public ValueInfo() {
    }

    public ValueInfo(Type value, ValueSetFlags valueSetFlag) {
        this.mValueToReturn = value;
        this.mValueForSerialization = value;
        this.mValueSetFlag = valueSetFlag;
    }

    public Type getValue() {
        if (this.mValueToOverride != null) {
            return this.mValueToOverride;
        }
        this.mValueReturned = true;
        return this.mValueToReturn;
    }

    public Type getValueToOverride() {
        return this.mValueToOverride;
    }

    public Type getValueForSerialization() {
        return this.mValueForSerialization;
    }

    public boolean isValueForSerializationEqual(Type newValue) {
        return Objects_equals(this.mValueForSerialization, newValue);
    }

    public boolean setValueFromService(Type value, boolean setAsReturned) {
        boolean serializationNeeded = false;
        this.mValueToReturn = value;
        this.mValueReturned |= setAsReturned;
        if (!isValueForSerializationEqual(value)) {
            this.mValueForSerialization = value;
            serializationNeeded = true;
        }
        this.mValueSetFlag = ValueSetFlags.FromService;
        return serializationNeeded;
    }

    public boolean setValueForSerialization(Type value) {
        boolean serializationNeeded = !isValueForSerializationEqual(value);
        this.mValueForSerialization = value;
        return serializationNeeded;
    }

    public boolean wasValueReturned() {
        return this.mValueReturned;
    }

    public boolean hasExposureLogged() {
        return this.mExposureLogged;
    }

    public void setExposureLogged() {
        this.mExposureLogged = true;
    }

    public boolean hasValueSetFromService() {
        return this.mValueSetFlag == ValueSetFlags.FromService;
    }

    public void setLoggingId(String loggingId, boolean sessionless) {
        if ((!TextUtils.equals(this.mLoggingId, loggingId) || this.mSessionless != sessionless) && !this.mValueReturned) {
            this.mExposureLogged = false;
        }
        this.mLoggingId = loggingId;
        this.mSessionless = sessionless;
    }

    public boolean isSessionless() {
        return this.mSessionless;
    }

    public String getLoggingId() {
        return this.mLoggingId;
    }

    private static <Type> boolean Objects_equals(Type left, Type right) {
        if (left == null) {
            return right == null;
        }
        return left.equals(right);
    }
}
