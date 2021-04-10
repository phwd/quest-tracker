package X;

import java.io.Serializable;

/* renamed from: X.21U  reason: invalid class name */
public final class AnonymousClass21U implements Serializable {
    public static final long serialVersionUID = -1322257508628817540L;
    public final AbstractC12551xm upstream;

    public final String toString() {
        StringBuilder sb = new StringBuilder("NotificationLite.Subscription[");
        sb.append(this.upstream);
        sb.append("]");
        return sb.toString();
    }

    public AnonymousClass21U(AbstractC12551xm r1) {
        this.upstream = r1;
    }
}
