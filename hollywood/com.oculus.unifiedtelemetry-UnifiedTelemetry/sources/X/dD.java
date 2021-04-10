package X;

public enum dD {
    NO_ERROR(0),
    PROTOCOL_ERROR(1),
    INTERNAL_ERROR(2),
    FLOW_CONTROL_ERROR(3),
    REFUSED_STREAM(7),
    CANCEL(8);
    
    public final int httpCode;

    /* access modifiers changed from: public */
    dD(int i) {
        this.httpCode = i;
    }

    public static dD fromHttp2(int i) {
        dD[] values = values();
        for (dD dDVar : values) {
            if (dDVar.httpCode == i) {
                return dDVar;
            }
        }
        return null;
    }
}
