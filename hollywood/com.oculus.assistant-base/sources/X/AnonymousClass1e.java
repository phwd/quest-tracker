package X;

import java.io.Serializable;

/* renamed from: X.1e  reason: invalid class name */
public abstract class AnonymousClass1e implements Serializable, bI {
    public static final Object A01 = b6.A00;
    public transient bI A00;
    public final boolean isTopLevel = false;
    public final String name;
    public final Class owner;
    public final Object receiver;
    public final String signature;

    public AnonymousClass1e(Object obj, Class cls, String str, String str2) {
        this.receiver = obj;
        this.owner = cls;
        this.name = str;
        this.signature = str2;
    }

    public final AbstractC0520bJ A00() {
        Class cls = this.owner;
        if (cls == null) {
            return null;
        }
        if (this.isTopLevel) {
            return new AnonymousClass3B(cls);
        }
        return new AnonymousClass3D(cls);
    }
}
