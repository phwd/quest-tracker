package com.facebook.mobileconfig.factory;

import com.facebook.debug.log.BLog;
import com.facebook.infer.annotation.Nullsafe;
import java.util.HashMap;
import java.util.Map;

@Nullsafe(Nullsafe.Mode.LOCAL)
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
    
    private static final String TAG = "MobileConfigValueSource";
    private static final Map<Integer, MobileConfigValueSource> intToSource = new HashMap();
    private int source;

    static {
        MobileConfigValueSource[] values = values();
        for (MobileConfigValueSource mobileConfigValueSource : values) {
            intToSource.put(Integer.valueOf(mobileConfigValueSource.source), mobileConfigValueSource);
        }
    }

    private MobileConfigValueSource(int i) {
        this.source = i;
    }

    public int getSource() {
        return this.source;
    }

    public static MobileConfigValueSource fromInt(int i) {
        MobileConfigValueSource mobileConfigValueSource = intToSource.get(Integer.valueOf(i));
        if (mobileConfigValueSource != null) {
            return mobileConfigValueSource;
        }
        BLog.e(TAG, "Could not convert source from int '%s'", Integer.valueOf(i));
        return UNKNOWN;
    }
}
