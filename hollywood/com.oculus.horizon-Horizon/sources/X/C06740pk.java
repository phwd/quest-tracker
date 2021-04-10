package X;

import javax.annotation.Nullable;

/* renamed from: X.0pk  reason: invalid class name and case insensitive filesystem */
public class C06740pk<T> {
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
            AnonymousClass0NO.A03(C01140Ko.class, "Couldn't instantiate object", e);
            return null;
        }
    }

    public C06740pk(Class<T> cls) {
        this.A00 = cls;
    }
}
