package X;

import javax.annotation.Nullable;

/* renamed from: X.0bu  reason: invalid class name and case insensitive filesystem */
public class C03250bu<T> {
    public Class<T> A00;

    public void A01(T t) {
    }

    public void A02(T t) {
    }

    @Nullable
    public T A00() {
        try {
            return this.A00.newInstance();
        } catch (InstantiationException e) {
            if (!AnonymousClass0NK.A01.A5V(6)) {
                return null;
            }
            AnonymousClass0NK.A01.A2D(C01660Ko.class.getSimpleName(), "Couldn't instantiate object", e);
            return null;
        } catch (IllegalAccessException e2) {
            if (!AnonymousClass0NK.A01.A5V(6)) {
                return null;
            }
            AnonymousClass0NK.A01.A2D(C01660Ko.class.getSimpleName(), "Couldn't instantiate object", e2);
            return null;
        }
    }

    public C03250bu(Class<T> cls) {
        this.A00 = cls;
    }
}
