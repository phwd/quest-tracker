package X;

/* renamed from: X.1fE  reason: invalid class name */
public enum AnonymousClass1fE {
    Continuation(0),
    Text(1),
    Binary(2),
    Close(8),
    Ping(9),
    Pong(10);
    
    public final byte code;

    public boolean isControlFrame() {
        if (this == Close || this == Ping || this == Pong) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: public */
    AnonymousClass1fE(int i) {
        this.code = (byte) i;
    }

    public static AnonymousClass1fE find(byte b) {
        AnonymousClass1fE[] values = values();
        for (AnonymousClass1fE r1 : values) {
            if (r1.getValue() == b) {
                return r1;
            }
        }
        return null;
    }

    public byte getValue() {
        return this.code;
    }
}
