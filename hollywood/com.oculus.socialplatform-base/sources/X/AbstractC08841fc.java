package X;

/* renamed from: X.1fc  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC08841fc {
    public static final AbstractC08841fc A00 = new C08851fd();
    public static final AbstractC08841fc A01 = new C08831fb();
    public static final AbstractC08841fc A02 = new C08861fe();
    public static final AbstractC08841fc A03 = new C08901fi();
    public static final AbstractC08841fc A04 = new C08871ff();

    public final boolean A00(AnonymousClass1fM r3) {
        if (!(this instanceof C08831fb)) {
            if (this instanceof C08871ff) {
                return false;
            }
            if (!(this instanceof C08861fe)) {
                if (this instanceof C08901fi) {
                    return false;
                }
            } else if (r3 == AnonymousClass1fM.DATA_DISK_CACHE || r3 == AnonymousClass1fM.MEMORY_CACHE) {
                return false;
            } else {
                return true;
            }
        }
        if (r3 == AnonymousClass1fM.REMOTE) {
            return true;
        }
        return false;
    }
}
