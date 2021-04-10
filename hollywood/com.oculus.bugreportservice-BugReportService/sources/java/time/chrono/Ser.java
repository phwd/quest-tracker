package java.time.chrono;

import java.io.Externalizable;

/* access modifiers changed from: package-private */
public final class Ser implements Externalizable {
    private static final long serialVersionUID = -6103370247208168577L;
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
