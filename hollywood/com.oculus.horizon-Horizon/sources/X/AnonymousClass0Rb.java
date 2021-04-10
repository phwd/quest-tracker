package X;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0Rb  reason: invalid class name */
public enum AnonymousClass0Rb {
    Facebook(0),
    Oculus(1),
    Flash(2),
    Onavo(4),
    ExpressWiFi(5),
    Anna(6),
    INSTAGRAM(7),
    HARDWARE(8);
    
    public int value;

    /* access modifiers changed from: public */
    AnonymousClass0Rb(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}
