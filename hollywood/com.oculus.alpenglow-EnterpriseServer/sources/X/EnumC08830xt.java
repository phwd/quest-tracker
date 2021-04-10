package X;

import java.util.Collections;
import java.util.Map;

/* renamed from: X.0xt  reason: invalid class name and case insensitive filesystem */
public enum EnumC08830xt {
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
    
    public static final Map<Integer, EnumC08830xt> map = Collections.unmodifiableMap(new C09130yi());
    public final int mValue;

    public static EnumC08830xt fromInt(int i) {
        return map.get(Integer.valueOf(i));
    }

    /* access modifiers changed from: public */
    EnumC08830xt(int i) {
        this.mValue = i;
    }
}
