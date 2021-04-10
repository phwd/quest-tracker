package X;

import javax.annotation.Nullable;

public class YN<T> {
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
            if (!Mu.A01.A3F(6)) {
                return null;
            }
            Mu.A01.A1r(KG.class.getSimpleName(), "Couldn't instantiate object", e);
            return null;
        } catch (IllegalAccessException e2) {
            if (!Mu.A01.A3F(6)) {
                return null;
            }
            Mu.A01.A1r(KG.class.getSimpleName(), "Couldn't instantiate object", e2);
            return null;
        }
    }

    public YN(Class<T> cls) {
        this.A00 = cls;
    }
}
