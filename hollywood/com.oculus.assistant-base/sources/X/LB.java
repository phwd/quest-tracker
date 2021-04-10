package X;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class LB implements Serializable {
    public static Map A00 = new ConcurrentHashMap();
    public final String fieldName;
    public final byte requirementType = 3;
    public final LC valueMetaData;

    public LB(String str, LC lc) {
        this.fieldName = str;
        this.valueMetaData = lc;
    }
}
