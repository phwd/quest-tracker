package java.time.zone;

import java.io.Externalizable;

final class Ser implements Externalizable {
    private static final long serialVersionUID = -8885321777449118786L;
    private Object object;
    private byte type;

    public Ser() {
    }

    Ser(byte b, Object obj) {
        this.type = b;
        this.object = obj;
    }

    private Object readResolve() {
        return this.object;
    }
}
