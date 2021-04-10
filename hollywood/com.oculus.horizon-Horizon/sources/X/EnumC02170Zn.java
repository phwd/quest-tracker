package X;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0Zn  reason: invalid class name and case insensitive filesystem */
public enum EnumC02170Zn {
    FIRE_AND_FORGET(0),
    ACKNOWLEDGED_DELIVERY(1),
    ASSURED_DELIVERY(2);
    
    public final int mValue;

    public static EnumC02170Zn fromInt(int i) {
        if (i == 0) {
            return FIRE_AND_FORGET;
        }
        if (i == 1) {
            return ACKNOWLEDGED_DELIVERY;
        }
        if (i == 2) {
            return ASSURED_DELIVERY;
        }
        throw new IllegalArgumentException(AnonymousClass006.A01("Unknown QOS level ", i));
    }

    /* access modifiers changed from: public */
    EnumC02170Zn(int i) {
        this.mValue = i;
    }

    public int getValue() {
        return this.mValue;
    }
}
