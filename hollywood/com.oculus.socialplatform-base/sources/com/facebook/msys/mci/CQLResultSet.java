package com.facebook.msys.mci;

import javax.annotation.Nullable;

public interface CQLResultSet {
    boolean getBoolean(int i, int i2);

    int getCount();

    int getInteger(int i, int i2);

    long getLong(int i, int i2);

    @Nullable
    Integer getNullableInteger(int i, int i2);

    @Nullable
    Long getNullableLong(int i, int i2);

    @Nullable
    String getString(int i, int i2);
}
