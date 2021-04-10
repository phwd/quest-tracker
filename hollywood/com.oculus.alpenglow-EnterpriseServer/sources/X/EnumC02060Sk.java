package X;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0Sk  reason: invalid class name and case insensitive filesystem */
public enum EnumC02060Sk {
    Facebook(0),
    Oculus(1),
    Flash(2),
    Onavo(4),
    ExpressWiFi(5),
    Anna(6),
    INSTAGRAM(7),
    HARDWARE(8);
    
    public int value;

    public int getValue() {
        return this.value;
    }

    /* access modifiers changed from: public */
    EnumC02060Sk(int i) {
        this.value = i;
    }
}
