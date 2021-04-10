package X;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0TC  reason: invalid class name */
public enum AnonymousClass0TC {
    NO_OP,
    FORCE_MC_FETCH;

    public static AnonymousClass0TC get(String str) {
        AnonymousClass0TC r5 = NO_OP;
        AnonymousClass0TC[] values = values();
        for (AnonymousClass0TC r1 : values) {
            if (r1.toString().equals(str)) {
                return r1;
            }
        }
        return r5;
    }
}
