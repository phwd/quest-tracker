package android.icu.text;

import android.icu.impl.IDNA2003;

public abstract class IDNA {
    public static StringBuffer convertIDNToASCII(String str, int i) {
        IDNA2003.convertIDNToASCII(str, i);
        throw null;
    }
}
