package X;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: X.6S  reason: invalid class name */
public abstract class AnonymousClass6S {
    public static final Iterator A01 = new ArrayList(0).iterator();
    public final File A00;

    public final Iterator A00() {
        if (!(this instanceof AbstractC0671ep)) {
            return A01;
        }
        AbstractC0671ep epVar = (AbstractC0671ep) this;
        return new AnonymousClass6Q(epVar, new AnonymousClass6T(((AnonymousClass6S) epVar).A00, epVar.A00, epVar.A01).iterator());
    }

    public AnonymousClass6S(File file) {
        this.A00 = file;
    }
}
