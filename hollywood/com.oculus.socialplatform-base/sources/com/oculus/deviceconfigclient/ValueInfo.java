package com.oculus.deviceconfigclient;

import X.AnonymousClass006;
import android.text.TextUtils;
import com.facebook.infer.annotation.Nullsafe;
import java.util.ArrayList;
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

    public void setExposureLogged() {
        this.mExposureLogged = true;
    }

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

    public static <Type> boolean Objects_equals(@Nullable Type type, @Nullable Type type2) {
        if (type != null) {
            return type.equals(type2);
        }
        if (type2 == null) {
            return true;
        }
        return false;
    }

    public String debugOnlyMemorySnapshot(SnapshotQuery snapshotQuery) {
        ArrayList arrayList = new ArrayList();
        if (snapshotQuery.ValueToReturn) {
            StringBuilder sb = new StringBuilder("ValueToReturn: ");
            sb.append((Object) this.mValueToReturn);
            arrayList.add(sb.toString());
        }
        if (snapshotQuery.ValueForSerialization) {
            StringBuilder sb2 = new StringBuilder("ValueForSerialization: ");
            sb2.append((Object) this.mValueForSerialization);
            arrayList.add(sb2.toString());
        }
        if (snapshotQuery.ValueReturned) {
            arrayList.add(AnonymousClass006.A0E("ValueReturned: ", this.mValueReturned));
        }
        if (snapshotQuery.ValueSetFlag) {
            StringBuilder sb3 = new StringBuilder("ValueSetFlag: ");
            sb3.append(this.mValueSetFlag);
            arrayList.add(sb3.toString());
        }
        if (snapshotQuery.ExposureLogged) {
            arrayList.add(AnonymousClass006.A0E("ExposureLogged: ", this.mExposureLogged));
        }
        if (snapshotQuery.Sessionless) {
            arrayList.add(AnonymousClass006.A0E("Sessionless: ", this.mSessionless));
        }
        if (snapshotQuery.LoggingId) {
            arrayList.add(AnonymousClass006.A07("LoggingId: ", this.mLoggingId));
        }
        return String.join(" - ", arrayList);
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

    public boolean hasValueSetFromService() {
        if (this.mValueSetFlag == ValueSetFlags.FromService) {
            return true;
        }
        return false;
    }

    public boolean isValueEqual(Type type) {
        return Objects_equals(this.mValueToReturn, type);
    }

    public boolean isValueForSerializationEqual(Type type) {
        return Objects_equals(this.mValueForSerialization, type);
    }

    public void setLoggingId(@Nullable String str, boolean z) {
        if ((!TextUtils.equals(this.mLoggingId, str) || this.mSessionless != z) && !this.mValueReturned) {
            this.mExposureLogged = false;
        }
        this.mLoggingId = str;
        this.mSessionless = z;
    }

    public boolean setValueForSerialization(Type type) {
        boolean z = !Objects_equals(this.mValueForSerialization, type);
        this.mValueForSerialization = type;
        return z;
    }

    public boolean setValueFromService(Type type, boolean z) {
        boolean z2;
        this.mValueToReturn = type;
        this.mValueReturned = z | this.mValueReturned;
        if (!Objects_equals(this.mValueForSerialization, type)) {
            this.mValueForSerialization = type;
            z2 = true;
        } else {
            z2 = false;
        }
        this.mValueSetFlag = ValueSetFlags.FromService;
        return z2;
    }

    @Nullable
    public Type debugOnlyGetValue() {
        return this.mValueToReturn;
    }

    @Nullable
    public String getLoggingId() {
        return this.mLoggingId;
    }

    @Nullable
    public Type getValueForSerialization() {
        return this.mValueForSerialization;
    }

    @Nullable
    public Type getValueToOverride() {
        return this.mValueToOverride;
    }

    public boolean hasExposureLogged() {
        return this.mExposureLogged;
    }

    public boolean isSessionless() {
        return this.mSessionless;
    }

    public boolean wasValueReturned() {
        return this.mValueReturned;
    }

    public void setValueToOverride(@Nullable Type type) {
        this.mValueToOverride = type;
    }

    public ValueInfo() {
    }

    public ValueInfo(Type type, ValueSetFlags valueSetFlags) {
        this.mValueToReturn = type;
        this.mValueForSerialization = type;
        this.mValueSetFlag = valueSetFlags;
    }
}
