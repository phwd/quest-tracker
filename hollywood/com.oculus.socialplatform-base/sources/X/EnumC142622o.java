package X;

import java.util.Collections;
import java.util.Map;

/* renamed from: X.22o  reason: invalid class name and case insensitive filesystem */
public enum EnumC142622o {
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
    
    public static final Map<Integer, EnumC142622o> map = Collections.unmodifiableMap(new AnonymousClass23G());
    public final int mValue;

    public static EnumC142622o fromInt(int i) {
        return map.get(Integer.valueOf(i));
    }

    /* access modifiers changed from: public */
    EnumC142622o(int i) {
        this.mValue = i;
    }
}
