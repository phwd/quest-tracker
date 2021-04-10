package com.fasterxml.jackson.databind.util;

import java.io.Serializable;
import java.lang.Enum;
import java.util.HashMap;

public class EnumResolver<T extends Enum<T>> implements Serializable {
    private static final long serialVersionUID = 1;
    protected final Class<T> _enumClass;
    protected final T[] _enums;
    protected final HashMap<String, T> _enumsById;

    public T findEnum(String str) {
        return this._enumsById.get(str);
    }

    public T getEnum(int i) {
        if (i < 0) {
            return null;
        }
        T[] tArr = this._enums;
        if (i >= tArr.length) {
            return null;
        }
        return tArr[i];
    }

    public Class<T> getEnumClass() {
        return this._enumClass;
    }

    public int lastValidIndex() {
        return this._enums.length - 1;
    }
}
