package X;

import com.facebook.infer.annotation.Nullsafe;
import java.util.HashMap;
import java.util.Map;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0xs  reason: invalid class name and case insensitive filesystem */
public enum EnumC08820xs {
    GET_PREF_BASED_CONFIG(0, true),
    SET_PREF_BASED_CONFIG(1, false),
    GET_APPS_STATISTICS(2, true),
    GET_ANALYTICS_CONFIG(3, true),
    SET_ANALYTICS_CONFIG(4, false),
    GET_FLYTRAP_REPORT(5, true),
    GET_PREF_IDS(6, true),
    SET_PREF_IDS(7, false),
    NOT_EXIST(Integer.MAX_VALUE, false);
    
    public static final Map<Integer, EnumC08820xs> mLookup = new HashMap();
    public final boolean mHasReturn;
    public final int mOperationType;

    /* access modifiers changed from: public */
    static {
        EnumC08820xs[] values = values();
        for (EnumC08820xs r2 : values) {
            mLookup.put(Integer.valueOf(r2.mOperationType), r2);
        }
    }

    public static EnumC08820xs fromOperationType(int i) {
        EnumC08820xs r0 = mLookup.get(Integer.valueOf(i));
        if (r0 == null) {
            return NOT_EXIST;
        }
        return r0;
    }

    /* access modifiers changed from: public */
    EnumC08820xs(int i, boolean z) {
        this.mOperationType = i;
        this.mHasReturn = z;
    }

    public boolean hasReturn() {
        return this.mHasReturn;
    }

    public int toOperationType() {
        return this.mOperationType;
    }
}
