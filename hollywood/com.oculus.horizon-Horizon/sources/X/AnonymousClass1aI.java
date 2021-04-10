package X;

import javax.annotation.concurrent.Immutable;

@Immutable
/* renamed from: X.1aI  reason: invalid class name */
public enum AnonymousClass1aI {
    NOT_ATTEMPTED(0),
    FOUND(1),
    NOT_FOUND(2);
    
    public int mValue;

    public static AnonymousClass1aI getDedupState(int i) {
        if (i == 1) {
            return FOUND;
        }
        if (i != 2) {
            return NOT_ATTEMPTED;
        }
        return NOT_FOUND;
    }

    /* access modifiers changed from: public */
    AnonymousClass1aI(int i) {
        this.mValue = i;
    }

    public int getValue() {
        return this.mValue;
    }
}
