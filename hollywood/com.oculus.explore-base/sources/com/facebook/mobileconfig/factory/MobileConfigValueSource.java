package com.facebook.mobileconfig.factory;

import com.facebook.debug.log.BLog;
import java.util.HashMap;
import java.util.Map;

public enum MobileConfigValueSource {
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
    
    private static final Map<Integer, MobileConfigValueSource> intToSource = new HashMap();
    private int source;

    static {
        MobileConfigValueSource[] values = values();
        for (MobileConfigValueSource type : values) {
            intToSource.put(Integer.valueOf(type.source), type);
        }
    }

    private MobileConfigValueSource(int source2) {
        this.source = source2;
    }

    public int getSource() {
        return this.source;
    }

    public static MobileConfigValueSource fromInt(int i) {
        MobileConfigValueSource type = intToSource.get(Integer.valueOf(i));
        if (type != null) {
            return type;
        }
        BLog.e("MobileConfigValueSource", "Could not convert source from int '%s'", Integer.valueOf(i));
        return UNKNOWN;
    }
}
