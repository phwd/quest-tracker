package X;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.22v  reason: invalid class name and case insensitive filesystem */
public enum EnumC143322v {
    FIRE_AND_FORGET(0),
    ACKNOWLEDGED_DELIVERY(1),
    ASSURED_DELIVERY(2);
    
    public final int mValue;

    public static EnumC143322v fromInt(int i) {
        if (i == 0) {
            return FIRE_AND_FORGET;
        }
        if (i == 1) {
            return ACKNOWLEDGED_DELIVERY;
        }
        if (i == 2) {
            return ASSURED_DELIVERY;
        }
        throw new IllegalArgumentException(AnonymousClass006.A03("Unknown QOS level ", i));
    }

    public int getValue() {
        return this.mValue;
    }

    /* access modifiers changed from: public */
    EnumC143322v(int i) {
        this.mValue = i;
    }
}
