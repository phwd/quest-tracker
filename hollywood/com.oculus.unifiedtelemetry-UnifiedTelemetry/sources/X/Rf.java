package X;

import com.facebook.infer.annotation.Nullsafe;
import java.util.HashMap;
import java.util.Map;

@Nullsafe(Nullsafe.Mode.LOCAL)
public enum Rf {
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
    public static final Map<Integer, Rf> intToSource = new HashMap();
    public int source;

    /* access modifiers changed from: public */
    static {
        Rf[] values = values();
        for (Rf rf : values) {
            intToSource.put(Integer.valueOf(rf.source), rf);
        }
    }

    public static Rf fromInt(int i) {
        Map<Integer, Rf> map = intToSource;
        Integer valueOf = Integer.valueOf(i);
        Rf rf = map.get(valueOf);
        if (rf != null) {
            return rf;
        }
        Mu.A05(TAG, "Could not convert source from int '%s'", valueOf);
        return UNKNOWN;
    }

    /* access modifiers changed from: public */
    Rf(int i) {
        this.source = i;
    }

    public int getSource() {
        return this.source;
    }
}
