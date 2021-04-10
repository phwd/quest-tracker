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

        private ValueSetFlags(int i) {
            this.mPriority = i;
        }

        public int getPriority() {
            return this.mPriority;
        }
    }

    public ValueInfo() {
    }

    public ValueInfo(Type type, ValueSetFlags valueSetFlags) {
        this.mValueToReturn = type;
        this.mValueForSerialization = type;
        this.mValueSetFlag = valueSetFlags;
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

    public boolean isValueEqual(Type type) {
        return Objects_equals(this.mValueToReturn, type);
    }

    @Nullable
    public Type getValueForSerialization() {
        return this.mValueForSerialization;
    }

    public boolean isValueForSerializationEqual(Type type) {
        return Objects_equals(this.mValueForSerialization, type);
    }

    public boolean setValueFromService(Type type, boolean z) {
        boolean z2;
        this.mValueToReturn = type;
        this.mValueReturned = z | this.mValueReturned;
        if (!isValueForSerializationEqual(type)) {
            this.mValueForSerialization = type;
            z2 = true;
        } else {
            z2 = false;
        }
        this.mValueSetFlag = ValueSetFlags.FromService;
        return z2;
    }

    public boolean setValueForSerialization(Type type) {
        boolean z = !isValueForSerializationEqual(type);
        this.mValueForSerialization = type;
        return z;
    }

    public void setValueToOverride(@Nullable Type type) {
        this.mValueToOverride = type;
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

    public void setLoggingId(@Nullable String str, boolean z) {
        if ((!TextUtils.equals(this.mLoggingId, str) || this.mSessionless != z) && !this.mValueReturned) {
            this.mExposureLogged = false;
        }
        this.mLoggingId = str;
        this.mSessionless = z;
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

    private static <Type> boolean Objects_equals(@Nullable Type type, @Nullable Type type2) {
        if (type == null) {
            return type2 == null;
        }
        return type.equals(type2);
    }
}
