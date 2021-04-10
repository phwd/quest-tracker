package com.oculus.deviceconfigclient;

import android.text.TextUtils;
import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

/* access modifiers changed from: package-private */
@Nullsafe(Nullsafe.Mode.LOCAL)
public class ValueInfo<Type> {
    private boolean mExposureLogged = false;
    @Nullable
    private String mLoggingId = null;
    private boolean mSessionless = false;
    @Nullable
    private Type mValueForSerialization = null;
    private boolean mValueReturned = false;
    private ValueSetFlags mValueSetFlag = ValueSetFlags.NotSet;
    @Nullable
    private Type mValueToOverride = null;
    @Nullable
    private Type mValueToReturn = null;

    public enum ValueSetFlags {
        NotSet(0),
        FromCache(1),
        FromService(2);
        
        final int mPriority;

        private ValueSetFlags(int priority) {
            this.mPriority = priority;
        }

        public int getPriority() {
            return this.mPriority;
        }
    }

    public ValueInfo() {
    }

    public ValueInfo(Type value, ValueSetFlags valueSetFlag) {
        this.mValueToReturn = value;
        this.mValueForSerialization = value;
        this.mValueSetFlag = valueSetFlag;
    }

    @Nullable
    public Type getValue() {
        Type type = this.mValueToOverride;
        if (type != null) {
            return type;
        }
        this.mValueReturned = true;
        return this.mValueToReturn;
    }

    @Nullable
    public Type getValueToOverride() {
        return this.mValueToOverride;
    }

    public boolean isValueEqual(Type newValue) {
        return Objects_equals(this.mValueToReturn, newValue);
    }

    @Nullable
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

    public void setValueToOverride(@Nullable Type value) {
        this.mValueToOverride = value;
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

    public void setLoggingId(@Nullable String loggingId, boolean sessionless) {
        if ((!TextUtils.equals(this.mLoggingId, loggingId) || this.mSessionless != sessionless) && !this.mValueReturned) {
            this.mExposureLogged = false;
        }
        this.mLoggingId = loggingId;
        this.mSessionless = sessionless;
    }

    public boolean isSessionless() {
        return this.mSessionless;
    }

    @Nullable
    public String getLoggingId() {
        return this.mLoggingId;
    }

    public String debugOnlyMemorySnapshot() {
        return String.format("ValueToReturn: %s - ValueForSerialization: %s - ValueReturned: %s - ValueSetFlag: %s - ExposureLogged: %s - Sessionless: %s - LoggingId: %s", this.mValueToReturn, this.mValueForSerialization, Boolean.valueOf(this.mValueReturned), this.mValueSetFlag, Boolean.valueOf(this.mExposureLogged), Boolean.valueOf(this.mSessionless), this.mLoggingId);
    }

    @Nullable
    public Type debugOnlyGetValue() {
        return this.mValueToReturn;
    }

    private static <Type> boolean Objects_equals(@Nullable Type left, @Nullable Type right) {
        if (left == null) {
            return right == null;
        }
        return left.equals(right);
    }
}
