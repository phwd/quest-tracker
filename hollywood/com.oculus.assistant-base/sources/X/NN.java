package X;

import java.io.Serializable;

public abstract class NN implements Serializable {
    public final Object A00(Object obj) {
        if (!(this instanceof AnonymousClass2x)) {
            throw new UnsupportedOperationException();
        }
        AnonymousClass2x r1 = (AnonymousClass2x) this;
        try {
            return r1._property.A02(obj);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e2) {
            throw new IllegalStateException(AnonymousClass08.A06("Problem accessing property '", r1._property.A06.getValue(), "': ", e2.getMessage()), e2);
        }
    }
}
