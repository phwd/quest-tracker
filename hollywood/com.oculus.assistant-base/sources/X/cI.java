package X;

public enum cI {
    NO_ERROR(0),
    PROTOCOL_ERROR(1),
    INTERNAL_ERROR(2),
    FLOW_CONTROL_ERROR(3),
    REFUSED_STREAM(7),
    CANCEL(8);
    
    public final int httpCode;

    /* access modifiers changed from: public */
    cI(int i) {
        this.httpCode = i;
    }

    public static cI fromHttp2(int i) {
        cI[] values = values();
        for (cI cIVar : values) {
            if (cIVar.httpCode == i) {
                return cIVar;
            }
        }
        return null;
    }
}
