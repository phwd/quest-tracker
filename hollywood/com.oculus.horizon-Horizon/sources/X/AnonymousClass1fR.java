package X;

/* renamed from: X.1fR  reason: invalid class name */
public enum AnonymousClass1fR {
    NormalClosure(1000),
    GoingAway(1001),
    ProtocolError(1002),
    UnsupportedData(1003),
    NoStatusRcvd(1005),
    AbnormalClosure(1006),
    InvalidFramePayloadData(1007),
    PolicyViolation(1008),
    MessageTooBig(1009),
    MandatoryExt(1010),
    InternalServerError(1011),
    TLSHandshake(1015);
    
    public final int code;

    /* access modifiers changed from: public */
    AnonymousClass1fR(int i) {
        this.code = i;
    }

    public static AnonymousClass1fR find(int i) {
        AnonymousClass1fR[] values = values();
        for (AnonymousClass1fR r1 : values) {
            if (r1.getValue() == i) {
                return r1;
            }
        }
        return null;
    }

    public int getValue() {
        return this.code;
    }
}
