package X;

import java.io.Serializable;
import java.lang.Enum;
import java.util.HashMap;

/* renamed from: X.0op  reason: invalid class name and case insensitive filesystem */
public final class C07160op<T extends Enum<T>> implements Serializable {
    public static final long serialVersionUID = 1;
    public final Class<T> _enumClass;
    public final T[] _enums;
    public final HashMap<String, T> _enumsById;

    public C07160op(Class<T> cls, T[] tArr, HashMap<String, T> hashMap) {
        this._enumClass = cls;
        this._enums = tArr;
        this._enumsById = hashMap;
    }
}
