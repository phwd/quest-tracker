package X;

/* renamed from: X.Wr  reason: case insensitive filesystem */
public enum EnumC0166Wr {
    NO_ERROR(0),
    PROTOCOL_ERROR(1),
    INTERNAL_ERROR(2),
    FLOW_CONTROL_ERROR(3),
    REFUSED_STREAM(7),
    CANCEL(8);
    
    public final int httpCode;

    /* access modifiers changed from: public */
    EnumC0166Wr(int i) {
        this.httpCode = i;
    }

    public static EnumC0166Wr fromHttp2(int i) {
        EnumC0166Wr[] values = values();
        for (EnumC0166Wr wr : values) {
            if (wr.httpCode == i) {
                return wr;
            }
        }
        return null;
    }
}
