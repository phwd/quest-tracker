package X;

import com.facebook.infer.annotation.Nullsafe;
import java.util.HashMap;
import java.util.Map;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0Sq  reason: invalid class name */
public enum AnonymousClass0Sq {
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
    public static final Map<Integer, AnonymousClass0Sq> intToSource = new HashMap();
    public int source;

    /* access modifiers changed from: public */
    static {
        AnonymousClass0Sq[] values = values();
        for (AnonymousClass0Sq r2 : values) {
            intToSource.put(Integer.valueOf(r2.source), r2);
        }
    }

    public static AnonymousClass0Sq fromInt(int i) {
        Map<Integer, AnonymousClass0Sq> map = intToSource;
        Integer valueOf = Integer.valueOf(i);
        AnonymousClass0Sq r0 = map.get(valueOf);
        if (r0 != null) {
            return r0;
        }
        AnonymousClass0MD.A09(TAG, "Could not convert source from int '%s'", valueOf);
        return UNKNOWN;
    }

    /* access modifiers changed from: public */
    AnonymousClass0Sq(int i) {
        this.source = i;
    }

    public int getSource() {
        return this.source;
    }
}
