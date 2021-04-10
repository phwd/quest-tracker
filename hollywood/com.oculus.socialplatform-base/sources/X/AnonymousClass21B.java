package X;

import java.io.Serializable;

/* renamed from: X.21B  reason: invalid class name */
public final class AnonymousClass21B implements Serializable {
    public static final long serialVersionUID = -8759979445933046293L;
    public final Throwable e;

    public final boolean equals(Object obj) {
        if (!(obj instanceof AnonymousClass21B)) {
            return false;
        }
        Throwable th = this.e;
        Throwable th2 = ((AnonymousClass21B) obj).e;
        if (th == th2) {
            return true;
        }
        if (th == null || !th.equals(th2)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return this.e.hashCode();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("NotificationLite.Error[");
        sb.append(this.e);
        sb.append("]");
        return sb.toString();
    }

    public AnonymousClass21B(Throwable th) {
        this.e = th;
    }
}
