package X;

/* renamed from: X.0hs  reason: invalid class name and case insensitive filesystem */
public enum EnumC04880hs {
    NO_ERROR(0),
    PROTOCOL_ERROR(1),
    INTERNAL_ERROR(2),
    FLOW_CONTROL_ERROR(3),
    REFUSED_STREAM(7),
    CANCEL(8);
    
    public final int httpCode;

    /* access modifiers changed from: public */
    EnumC04880hs(int i) {
        this.httpCode = i;
    }

    public static EnumC04880hs fromHttp2(int i) {
        EnumC04880hs[] values = values();
        for (EnumC04880hs r1 : values) {
            if (r1.httpCode == i) {
                return r1;
            }
        }
        return null;
    }
}
