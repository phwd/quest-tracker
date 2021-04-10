package X;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;

/* renamed from: X.rJ  reason: case insensitive filesystem */
public abstract class AbstractC1044rJ extends P9 implements Serializable {
    public static final long serialVersionUID = 7364428299211355871L;
    public final transient C1045rK A00;

    public final Class A0P() {
        if (this instanceof Ss) {
            return ((Ss) this)._owner.A0P();
        }
        if (this instanceof St) {
            return ((St) this).A00.getDeclaringClass();
        }
        if (!(this instanceof AnonymousClass0q)) {
            return ((C00090s) this)._constructor.getDeclaringClass();
        }
        return ((AnonymousClass0q) this).A00.getDeclaringClass();
    }

    public final Object A0Q(Object obj) {
        if (this instanceof Ss) {
            throw new UnsupportedOperationException(AnonymousClass08.A04("Cannot call getValue() on constructor parameter of ", A0P().getName()));
        } else if (this instanceof St) {
            St st = (St) this;
            try {
                return st.A00.get(obj);
            } catch (IllegalAccessException e) {
                throw new IllegalArgumentException(AnonymousClass08.A06("Failed to getValue() for field ", st.A0S(), ": ", e.getMessage()), e);
            }
        } else if (!(this instanceof AnonymousClass0q)) {
            throw new UnsupportedOperationException(AnonymousClass08.A04("Cannot call getValue() on constructor of ", A0P().getName()));
        } else {
            AnonymousClass0q r5 = (AnonymousClass0q) this;
            try {
                return r5.A00.invoke(obj, new Object[0]);
            } catch (IllegalAccessException e2) {
                throw new IllegalArgumentException(AnonymousClass08.A06("Failed to getValue() with method ", r5.A0Z(), ": ", e2.getMessage()), e2);
            } catch (InvocationTargetException e3) {
                throw new IllegalArgumentException(AnonymousClass08.A06("Failed to getValue() with method ", r5.A0Z(), ": ", e3.getMessage()), e3);
            }
        }
    }

    public final Member A0R() {
        if (this instanceof Ss) {
            return ((Ss) this)._owner.A0R();
        }
        if (this instanceof St) {
            return ((St) this).A00;
        }
        if (!(this instanceof AnonymousClass0q)) {
            return ((C00090s) this)._constructor;
        }
        return ((AnonymousClass0q) this).A00;
    }

    public AbstractC1044rJ(C1045rK rKVar) {
        this.A00 = rKVar;
    }
}
