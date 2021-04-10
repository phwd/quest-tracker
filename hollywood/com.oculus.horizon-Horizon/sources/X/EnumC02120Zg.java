package X;

import java.util.Collections;
import java.util.Map;

/* renamed from: X.0Zg  reason: invalid class name and case insensitive filesystem */
public enum EnumC02120Zg {
    CONNECT(1),
    CONNACK(2),
    PUBLISH(3),
    PUBACK(4),
    PUBREC(5),
    PUBREL(6),
    PUBCOMP(7),
    SUBSCRIBE(8),
    SUBACK(9),
    UNSUBSCRIBE(10),
    UNSUBACK(11),
    PINGREQ(12),
    PINGRESP(13),
    DISCONNECT(14);
    
    public static final Map<Integer, EnumC02120Zg> map = Collections.unmodifiableMap(new C02110Zf());
    public final int mValue;

    public static EnumC02120Zg fromInt(int i) {
        return map.get(Integer.valueOf(i));
    }

    /* access modifiers changed from: public */
    EnumC02120Zg(int i) {
        this.mValue = i;
    }
}
