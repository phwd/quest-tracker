package X;

/* renamed from: X.0vj  reason: invalid class name and case insensitive filesystem */
public enum EnumC08000vj {
    NO_ERROR(0),
    PROTOCOL_ERROR(1),
    INTERNAL_ERROR(2),
    FLOW_CONTROL_ERROR(3),
    REFUSED_STREAM(7),
    CANCEL(8);
    
    public final int httpCode;

    /* access modifiers changed from: public */
    EnumC08000vj(int i) {
        this.httpCode = i;
    }

    public static EnumC08000vj fromHttp2(int i) {
        EnumC08000vj[] values = values();
        for (EnumC08000vj r1 : values) {
            if (r1.httpCode == i) {
                return r1;
            }
        }
        return null;
    }
}
