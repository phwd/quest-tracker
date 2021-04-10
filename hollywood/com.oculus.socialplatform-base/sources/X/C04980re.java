package X;

import javax.annotation.Nullable;

/* renamed from: X.0re  reason: invalid class name and case insensitive filesystem */
public class C04980re<T> {
    public Class<T> A00;

    public void A01(T t) {
    }

    public void A02(T t) {
    }

    @Nullable
    public T A00() {
        try {
            return this.A00.newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
            AnonymousClass0MD.A01(AnonymousClass0Jd.class, "Couldn't instantiate object", e);
            return null;
        }
    }

    public C04980re(Class<T> cls) {
        this.A00 = cls;
    }
}
