package com.facebook.msys.mci;

import X.AnonymousClass0l0;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.simplejni.NativeHolder;
import javax.annotation.Nullable;

@DoNotStrip
@Nullsafe(Nullsafe.Mode.LOCAL)
public final class CQLResultSetImpl implements CQLResultSet {
    @DoNotStrip
    public final NativeHolder mNativeHolder;

    @DoNotStrip
    public native CQLResultSet copy(int i, int i2);

    @DoNotStrip
    @Nullable
    public native byte[] getBlob(int i, int i2);

    @Override // com.facebook.msys.mci.CQLResultSet
    @DoNotStrip
    public native boolean getBoolean(int i, int i2);

    @Override // com.facebook.msys.mci.CQLResultSet
    @DoNotStrip
    public native int getCount();

    @DoNotStrip
    public native double getDouble(int i, int i2);

    @Override // com.facebook.msys.mci.CQLResultSet
    @DoNotStrip
    public native int getInteger(int i, int i2);

    @DoNotStrip
    public native boolean getIsEncoded(int i);

    @Override // com.facebook.msys.mci.CQLResultSet
    @DoNotStrip
    public native long getLong(int i, int i2);

    @Override // com.facebook.msys.mci.CQLResultSet
    @DoNotStrip
    @Nullable
    public native String getString(int i, int i2);

    @DoNotStrip
    public native boolean isNull(int i, int i2);

    public native long rowHashCode(int i);

    public native boolean rowsEqual(int i, CQLResultSet cQLResultSet, int i2);

    public native boolean rowsSame(int i, CQLResultSet cQLResultSet, int i2);

    static {
        AnonymousClass0l0.A06("msysjniinfra");
    }

    @DoNotStrip
    public CQLResultSetImpl(NativeHolder nativeHolder) {
        this.mNativeHolder = nativeHolder;
    }

    @Override // com.facebook.msys.mci.CQLResultSet
    @Nullable
    public Integer getNullableInteger(int i, int i2) {
        if (isNull(i, i2)) {
            return null;
        }
        return Integer.valueOf(getInteger(i, i2));
    }

    @Override // com.facebook.msys.mci.CQLResultSet
    @Nullable
    public Long getNullableLong(int i, int i2) {
        if (isNull(i, i2)) {
            return null;
        }
        return Long.valueOf(getLong(i, i2));
    }
}
