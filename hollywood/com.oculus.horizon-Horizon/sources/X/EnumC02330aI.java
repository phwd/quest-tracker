package X;

import com.facebook.infer.annotation.Nullsafe;
import java.util.HashMap;
import java.util.Map;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0aI  reason: invalid class name and case insensitive filesystem */
public enum EnumC02330aI {
    GET_PREF_BASED_CONFIG(0, true),
    SET_PREF_BASED_CONFIG(1, false),
    GET_APPS_STATISTICS(2, true),
    GET_ANALYTICS_CONFIG(3, true),
    SET_ANALYTICS_CONFIG(4, false),
    GET_FLYTRAP_REPORT(5, true),
    GET_PREF_IDS(6, true),
    SET_PREF_IDS(7, false),
    NOT_EXIST(Integer.MAX_VALUE, false);
    
    public static final Map<Integer, EnumC02330aI> mLookup = new HashMap();
    public final boolean mHasReturn;
    public final int mOperationType;

    /* access modifiers changed from: public */
    static {
        EnumC02330aI[] values = values();
        for (EnumC02330aI r2 : values) {
            mLookup.put(Integer.valueOf(r2.mOperationType), r2);
        }
    }

    public static EnumC02330aI fromOperationType(int i) {
        EnumC02330aI r0 = mLookup.get(Integer.valueOf(i));
        if (r0 == null) {
            return NOT_EXIST;
        }
        return r0;
    }

    /* access modifiers changed from: public */
    EnumC02330aI(int i, boolean z) {
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
