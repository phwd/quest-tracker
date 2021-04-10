package X;

import java.io.Serializable;

/* renamed from: X.21T  reason: invalid class name */
public final class AnonymousClass21T implements Serializable {
    public static final long serialVersionUID = -7482590109178395495L;
    public final AbstractC12271xB upstream;

    public final String toString() {
        StringBuilder sb = new StringBuilder("NotificationLite.Disposable[");
        sb.append(this.upstream);
        sb.append("]");
        return sb.toString();
    }

    public AnonymousClass21T(AbstractC12271xB r1) {
        this.upstream = r1;
    }
}
