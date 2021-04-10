package X;

import java.util.HashMap;
import java.util.Map;

/* renamed from: X.Fu  reason: case insensitive filesystem */
public enum EnumC0169Fu {
    UNKNOWN(-1),
    SERVER(0),
    OVERRIDE(1),
    DEFAULT__SERVER_RETURNED_NULL(2),
    DEFAULT__ACCESSED_BEFORE_MC_INIT(3),
    DEFAULT__NO_DATA_ON_DISK(4),
    DEFAULT__ACCESSED_AFTER_MC_DISPOSE(5),
    DEFAULT__MISSING_SERVER_VALUE(6),
    DEFAULT__INVALID_CONFIG_PARAM_NAME(7),
    DEFAULT__SERVICE_NOT_FOUND(8);
    
    public static final String TAG = "MobileConfigValueSource";
    public static final Map intToSource = new HashMap();
    public int source;

    /* access modifiers changed from: public */
    static {
        EnumC0169Fu[] values = values();
        for (EnumC0169Fu fu : values) {
            intToSource.put(Integer.valueOf(fu.source), fu);
        }
    }

    public static EnumC0169Fu fromInt(int i) {
        Map map = intToSource;
        Integer valueOf = Integer.valueOf(i);
        EnumC0169Fu fu = (EnumC0169Fu) map.get(valueOf);
        if (fu != null) {
            return fu;
        }
        C0139Dd.A0O(TAG, "Could not convert source from int '%s'", valueOf);
        return UNKNOWN;
    }

    /* access modifiers changed from: public */
    EnumC0169Fu(int i) {
        this.source = i;
    }

    public int getSource() {
        return this.source;
    }
}
