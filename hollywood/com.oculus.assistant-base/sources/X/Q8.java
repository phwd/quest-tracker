package X;

import java.io.Serializable;
import java.util.HashMap;

public final class Q8 implements Serializable {
    public static final long serialVersionUID = 1;
    public final Class _enumClass;
    public final Enum[] _enums;
    public final HashMap _enumsById;

    public Q8(Class cls, Enum[] enumArr, HashMap hashMap) {
        this._enumClass = cls;
        this._enums = enumArr;
        this._enumsById = hashMap;
    }
}
