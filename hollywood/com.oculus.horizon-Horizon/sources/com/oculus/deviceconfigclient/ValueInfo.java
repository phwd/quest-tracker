package com.oculus.deviceconfigclient;

import android.text.TextUtils;
import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class ValueInfo<Type> {
    public boolean mExposureLogged = false;
    @Nullable
    public String mLoggingId = null;
    public boolean mSessionless = false;
    @Nullable
    public Type mValueForSerialization = null;
    public boolean mValueReturned = false;
    public ValueSetFlags mValueSetFlag = ValueSetFlags.NotSet;
    @Nullable
    public Type mValueToOverride = null;
    @Nullable
    public Type mValueToReturn = null;

    public enum ValueSetFlags {
        NotSet(0),
        FromCache(1),
        FromService(2);
        
        public final int mPriority;

        /* access modifiers changed from: public */
        ValueSetFlags(int i) {
            this.mPriority = i;
        }

        public int getPriority() {
            return this.mPriority;
        }
    }

    public final void A00(@Nullable String str, boolean z) {
        if ((!TextUtils.equals(this.mLoggingId, str) || this.mSessionless != z) && !this.mValueReturned) {
            this.mExposureLogged = false;
        }
        this.mLoggingId = str;
        this.mSessionless = z;
    }

    public final boolean A01(Type type, boolean z) {
        boolean z2;
        this.mValueToReturn = type;
        this.mValueReturned = z | this.mValueReturned;
        Type type2 = this.mValueForSerialization;
        if (type2 != null ? type2.equals(type) : type == null) {
            z2 = false;
        } else {
            this.mValueForSerialization = type;
            z2 = true;
        }
        this.mValueSetFlag = ValueSetFlags.FromService;
        return z2;
    }

    public ValueInfo() {
    }

    public ValueInfo(Type type, ValueSetFlags valueSetFlags) {
        this.mValueToReturn = type;
        this.mValueForSerialization = type;
        this.mValueSetFlag = valueSetFlags;
    }
}
