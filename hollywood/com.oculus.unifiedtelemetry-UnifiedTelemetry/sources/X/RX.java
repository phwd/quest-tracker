package X;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public enum RX {
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
    RX(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}
