package X;

import com.facebook.assistant.oacr.OacrConstants;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Type;
import java.util.HashMap;

public abstract class P9 {
    public final AbstractC1024qt A0I(C0299Pv pv) {
        if (this instanceof AnonymousClass0q) {
            AnonymousClass0q r1 = (AnonymousClass0q) this;
            return r1.A0S(pv, r1.A00.getTypeParameters());
        } else if (!(this instanceof C00090s)) {
            return pv.A03.A09(A0N(), pv);
        } else {
            C00090s r12 = (C00090s) this;
            return r12.A0S(pv, r12._constructor.getTypeParameters());
        }
    }

    public final Class A0J() {
        if (this instanceof Ss) {
            Type type = ((Ss) this)._type;
            if (type instanceof Class) {
                return (Class) type;
            }
            return C0300Pw.A02.A09(type, null)._class;
        } else if (this instanceof St) {
            return ((St) this).A00.getType();
        } else {
            if (this instanceof AnonymousClass0q) {
                return ((AnonymousClass0q) this).A00.getReturnType();
            }
            if (!(this instanceof C00090s)) {
                return ((C1043rI) this).A09;
            }
            return ((C00090s) this)._constructor.getDeclaringClass();
        }
    }

    public final String A0K() {
        if (this instanceof Ss) {
            return OacrConstants.AUTO_SPEECH_DOMAIN;
        }
        if (this instanceof St) {
            return ((St) this).A00.getName();
        }
        if (this instanceof AnonymousClass0q) {
            return ((AnonymousClass0q) this).A00.getName();
        }
        if (!(this instanceof C00090s)) {
            return ((C1043rI) this).A09.getName();
        }
        return ((C00090s) this)._constructor.getName();
    }

    public final Annotation A0L(Class cls) {
        C1045rK rKVar;
        if (this instanceof SV) {
            rKVar = ((AbstractC1044rJ) this).A00;
        } else if ((this instanceof Ss) || (this instanceof St)) {
            rKVar = ((AbstractC1044rJ) this).A00;
            if (rKVar == null) {
                return null;
            }
        } else {
            C1043rI rIVar = (C1043rI) this;
            if (rIVar.A02 == null) {
                C1043rI.A05(rIVar);
            }
            rKVar = rIVar.A02;
        }
        HashMap hashMap = rKVar.A00;
        if (hashMap != null) {
            return (Annotation) hashMap.get(cls);
        }
        return null;
    }

    public final AnnotatedElement A0M() {
        if (this instanceof Ss) {
            return null;
        }
        if (this instanceof St) {
            return ((St) this).A00;
        }
        if (this instanceof AnonymousClass0q) {
            return ((AnonymousClass0q) this).A00;
        }
        if (!(this instanceof C00090s)) {
            return ((C1043rI) this).A09;
        }
        return ((C00090s) this)._constructor;
    }

    public final Type A0N() {
        if (this instanceof Ss) {
            return ((Ss) this)._type;
        }
        if (this instanceof St) {
            return ((St) this).A00.getGenericType();
        }
        if (this instanceof AnonymousClass0q) {
            return ((AnonymousClass0q) this).A00.getGenericReturnType();
        }
        if (!(this instanceof C00090s)) {
            return ((C1043rI) this).A09;
        }
        return A0J();
    }

    public final boolean A0O(Class cls) {
        if (A0L(cls) != null) {
            return true;
        }
        return false;
    }
}
