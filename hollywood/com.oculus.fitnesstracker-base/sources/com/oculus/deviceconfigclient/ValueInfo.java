package com.oculus.deviceconfigclient;

import android.text.TextUtils;
import com.facebook.infer.annotation.Nullsafe;

/* access modifiers changed from: package-private */
@Nullsafe(Nullsafe.Mode.LOCAL)
public final class ValueInfo<Type> {
    boolean mExposureLogged = false;
    String mLoggingId = null;
    boolean mSessionless = false;
    Type mValueForSerialization = null;
    boolean mValueReturned = false;
    ValueSetFlags mValueSetFlag = ValueSetFlags.NotSet;
    Type mValueToOverride = null;
    private Type mValueToReturn = null;

    public enum ValueSetFlags {
        NotSet(0),
        FromCache(1),
        FromService(2);
        
        final int mPriority;

        private ValueSetFlags(int i) {
            this.mPriority = i;
        }
    }

    public ValueInfo() {
    }

    public ValueInfo(Type type, ValueSetFlags valueSetFlags) {
        this.mValueToReturn = type;
        this.mValueForSerialization = type;
        this.mValueSetFlag = valueSetFlags;
    }

    public final Type getValue() {
        Type type = this.mValueToOverride;
        if (type != null) {
            return type;
        }
        this.mValueReturned = true;
        return this.mValueToReturn;
    }

    private boolean isValueForSerializationEqual(Type type) {
        Type type2 = this.mValueForSerialization;
        if (type2 == null) {
            return type == null;
        }
        return type2.equals(type);
    }

    public final boolean setValueFromService(Type type, boolean z) {
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

    public final boolean setValueForSerialization(Type type) {
        boolean z = !isValueForSerializationEqual(type);
        this.mValueForSerialization = type;
        return z;
    }

    public final void setLoggingId(String str, boolean z) {
        if ((!TextUtils.equals(this.mLoggingId, str) || this.mSessionless != z) && !this.mValueReturned) {
            this.mExposureLogged = false;
        }
        this.mLoggingId = str;
        this.mSessionless = z;
    }
}
