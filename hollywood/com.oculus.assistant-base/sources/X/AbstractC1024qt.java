package X;

import java.io.Serializable;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;

/* renamed from: X.qt  reason: case insensitive filesystem */
public abstract class AbstractC1024qt extends AbstractC0256Nu implements Serializable, Type {
    public static final long serialVersionUID = 6774285981275451126L;
    public final boolean _asStatic;
    public final Class _class;
    public final int _hashCode;
    public final Object _typeHandler;
    public final Object _valueHandler;

    public abstract boolean equals(Object obj);

    public abstract String toString();

    private final void A01(Class cls) {
        if (!this._class.isAssignableFrom(cls)) {
            throw new IllegalArgumentException(AnonymousClass08.A06("Class ", cls.getName(), " is not assignable to ", this._class.getName()));
        }
    }

    public final int A03() {
        if (this instanceof fF) {
            AbstractC1024qt[] qtVarArr = ((fF) this)._typeParameters;
            if (qtVarArr != null) {
                return qtVarArr.length;
            }
            return 0;
        } else if (this instanceof C0681fG) {
            return 2;
        } else {
            if ((this instanceof C0682fH) || (this instanceof C0683fI)) {
                return 1;
            }
            return 0;
        }
    }

    public final AbstractC1024qt A04() {
        if (this instanceof C0681fG) {
            return ((C0681fG) this)._valueType;
        }
        if (this instanceof C0682fH) {
            return ((C0682fH) this)._elementType;
        }
        if (!(this instanceof C0683fI)) {
            return null;
        }
        return ((C0683fI) this)._componentType;
    }

    public final AbstractC1024qt A05() {
        if (!(this instanceof C0681fG)) {
            return null;
        }
        return ((C0681fG) this)._keyType;
    }

    public final AbstractC1024qt A06(int i) {
        AbstractC1024qt[] qtVarArr;
        if (this instanceof fF) {
            fF fFVar = (fF) this;
            if (i < 0 || (qtVarArr = fFVar._typeParameters) == null || i >= qtVarArr.length) {
                return null;
            }
            return qtVarArr[i];
        } else if (this instanceof C0681fG) {
            C0681fG fGVar = (C0681fG) this;
            if (i == 0) {
                return fGVar._keyType;
            }
            if (i == 1) {
                return fGVar._valueType;
            }
            return null;
        } else if (this instanceof C0682fH) {
            C0682fH fHVar = (C0682fH) this;
            if (i == 0) {
                return fHVar._elementType;
            }
            return null;
        } else if (!(this instanceof C0683fI)) {
            return null;
        } else {
            C0683fI fIVar = (C0683fI) this;
            if (i == 0) {
                return fIVar._componentType;
            }
            return null;
        }
    }

    public final AbstractC1024qt A07(Class cls) {
        if (this instanceof fF) {
            fF fFVar = (fF) this;
            return new fF(cls, fFVar._typeNames, fFVar._typeParameters, fFVar._valueHandler, fFVar._typeHandler, fFVar._asStatic);
        } else if (this instanceof C0681fG) {
            C0681fG fGVar = (C0681fG) this;
            if (!(fGVar instanceof C00313p)) {
                return new C0681fG(cls, fGVar._keyType, fGVar._valueType, fGVar._valueHandler, fGVar._typeHandler, fGVar._asStatic);
            }
            return new C00313p(cls, fGVar._keyType, fGVar._valueType, fGVar._valueHandler, fGVar._typeHandler, fGVar._asStatic);
        } else if (this instanceof C0682fH) {
            C0682fH fHVar = (C0682fH) this;
            if (!(fHVar instanceof C00323q)) {
                return new C0682fH(cls, fHVar._elementType, fHVar._valueHandler, fHVar._typeHandler, fHVar._asStatic);
            }
            return new C00323q(cls, fHVar._elementType, null, null, fHVar._asStatic);
        } else if (cls.isArray()) {
            return C0683fI.A00(C0300Pw.A02.A09(cls.getComponentType(), null));
        } else {
            throw new IllegalArgumentException(AnonymousClass08.A06("Incompatible narrowing operation: trying to narrow ", toString(), " to class ", cls.getName()));
        }
    }

    public final AbstractC1024qt A08(Class cls) {
        if (this instanceof fF) {
            throw new IllegalArgumentException("Internal error: SimpleType.narrowContentsBy() should never be called");
        } else if (this instanceof C0681fG) {
            C0681fG fGVar = (C0681fG) this;
            if (!(fGVar instanceof C00313p)) {
                AbstractC1024qt qtVar = fGVar._valueType;
                if (cls != qtVar._class) {
                    return new C0681fG(fGVar._class, fGVar._keyType, qtVar.A09(cls), fGVar._valueHandler, fGVar._typeHandler, fGVar._asStatic);
                }
                return fGVar;
            }
            AbstractC1024qt qtVar2 = fGVar._valueType;
            if (cls != qtVar2._class) {
                return new C00313p(fGVar._class, fGVar._keyType, qtVar2.A09(cls), fGVar._valueHandler, fGVar._typeHandler, fGVar._asStatic);
            }
            return fGVar;
        } else if (!(this instanceof C0682fH)) {
            C0683fI fIVar = (C0683fI) this;
            AbstractC1024qt qtVar3 = fIVar._componentType;
            if (cls != qtVar3._class) {
                return C0683fI.A00(qtVar3.A09(cls));
            }
            return fIVar;
        } else {
            C0682fH fHVar = (C0682fH) this;
            if (!(fHVar instanceof C00323q)) {
                AbstractC1024qt qtVar4 = fHVar._elementType;
                if (cls != qtVar4._class) {
                    return new C0682fH(fHVar._class, qtVar4.A09(cls), fHVar._valueHandler, fHVar._typeHandler, fHVar._asStatic);
                }
                return fHVar;
            }
            AbstractC1024qt qtVar5 = fHVar._elementType;
            if (cls != qtVar5._class) {
                return new C00323q(fHVar._class, qtVar5.A09(cls), fHVar._valueHandler, fHVar._typeHandler, fHVar._asStatic);
            }
            return fHVar;
        }
    }

    public final AbstractC1024qt A09(Class cls) {
        if (cls == this._class) {
            return this;
        }
        A01(cls);
        AbstractC1024qt A07 = A07(cls);
        Object obj = this._valueHandler;
        if (obj != A07._valueHandler) {
            A07 = A07.A0E(obj);
        }
        Object obj2 = this._typeHandler;
        if (obj2 != A07._typeHandler) {
            return A07.A0D(obj2);
        }
        return A07;
    }

    public final AbstractC1024qt A0A(Class cls) {
        Class cls2 = this._class;
        if (cls == cls2) {
            return this;
        }
        A01(cls2);
        return A07(cls);
    }

    public final AbstractC1024qt A0B(Object obj) {
        if (this instanceof fF) {
            throw new IllegalArgumentException("Simple types have no content types; can not call withContenTypeHandler()");
        } else if (this instanceof C0681fG) {
            C0681fG fGVar = (C0681fG) this;
            boolean z = fGVar instanceof C00313p;
            if (z || z) {
                return new C00313p(fGVar._class, fGVar._keyType, fGVar._valueType.A0D(obj), fGVar._valueHandler, fGVar._typeHandler, fGVar._asStatic);
            }
            return new C0681fG(fGVar._class, fGVar._keyType, fGVar._valueType.A0D(obj), fGVar._valueHandler, fGVar._typeHandler, fGVar._asStatic);
        } else if (!(this instanceof C0682fH)) {
            C0683fI fIVar = (C0683fI) this;
            AbstractC1024qt qtVar = fIVar._componentType;
            if (obj != qtVar._typeHandler) {
                return new C0683fI(qtVar.A0D(obj), fIVar._emptyArray, fIVar._valueHandler, fIVar._typeHandler, fIVar._asStatic);
            }
            return fIVar;
        } else {
            C0682fH fHVar = (C0682fH) this;
            boolean z2 = fHVar instanceof C00323q;
            if (z2 || z2) {
                return new C00323q(fHVar._class, fHVar._elementType.A0D(obj), fHVar._valueHandler, fHVar._typeHandler, fHVar._asStatic);
            }
            return new C0682fH(fHVar._class, fHVar._elementType.A0D(obj), fHVar._valueHandler, fHVar._typeHandler, fHVar._asStatic);
        }
    }

    public final AbstractC1024qt A0C(Object obj) {
        if (this instanceof fF) {
            throw new IllegalArgumentException("Simple types have no content types; can not call withContenValueHandler()");
        } else if (this instanceof C0681fG) {
            C0681fG fGVar = (C0681fG) this;
            boolean z = fGVar instanceof C00313p;
            if (z || z) {
                return new C00313p(fGVar._class, fGVar._keyType, fGVar._valueType.A0E(obj), fGVar._valueHandler, fGVar._typeHandler, fGVar._asStatic);
            }
            return new C0681fG(fGVar._class, fGVar._keyType, fGVar._valueType.A0E(obj), fGVar._valueHandler, fGVar._typeHandler, fGVar._asStatic);
        } else if (!(this instanceof C0682fH)) {
            C0683fI fIVar = (C0683fI) this;
            AbstractC1024qt qtVar = fIVar._componentType;
            if (obj != qtVar._valueHandler) {
                return new C0683fI(qtVar.A0E(obj), fIVar._emptyArray, fIVar._valueHandler, fIVar._typeHandler, fIVar._asStatic);
            }
            return fIVar;
        } else {
            C0682fH fHVar = (C0682fH) this;
            boolean z2 = fHVar instanceof C00323q;
            if (z2 || z2) {
                return new C00323q(fHVar._class, fHVar._elementType.A0E(obj), fHVar._valueHandler, fHVar._typeHandler, fHVar._asStatic);
            }
            return new C0682fH(fHVar._class, fHVar._elementType.A0E(obj), fHVar._valueHandler, fHVar._typeHandler, fHVar._asStatic);
        }
    }

    public final AbstractC1024qt A0D(Object obj) {
        if (this instanceof fF) {
            fF fFVar = (fF) this;
            return new fF(fFVar._class, fFVar._typeNames, fFVar._typeParameters, fFVar._valueHandler, obj, fFVar._asStatic);
        } else if (this instanceof C0681fG) {
            C0681fG fGVar = (C0681fG) this;
            boolean z = fGVar instanceof C00313p;
            if (z || z) {
                return new C00313p(fGVar._class, fGVar._keyType, fGVar._valueType, fGVar._valueHandler, obj, fGVar._asStatic);
            }
            return new C0681fG(fGVar._class, fGVar._keyType, fGVar._valueType, fGVar._valueHandler, obj, fGVar._asStatic);
        } else if (!(this instanceof C0682fH)) {
            C0683fI fIVar = (C0683fI) this;
            if (obj != fIVar._typeHandler) {
                return new C0683fI(fIVar._componentType, fIVar._emptyArray, fIVar._valueHandler, obj, fIVar._asStatic);
            }
            return fIVar;
        } else {
            C0682fH fHVar = (C0682fH) this;
            boolean z2 = fHVar instanceof C00323q;
            if (z2 || z2) {
                return new C00323q(fHVar._class, fHVar._elementType, fHVar._valueHandler, obj, fHVar._asStatic);
            }
            return new C0682fH(fHVar._class, fHVar._elementType, fHVar._valueHandler, obj, fHVar._asStatic);
        }
    }

    public final AbstractC1024qt A0E(Object obj) {
        if (this instanceof fF) {
            fF fFVar = (fF) this;
            if (obj != fFVar._valueHandler) {
                return new fF(fFVar._class, fFVar._typeNames, fFVar._typeParameters, obj, fFVar._typeHandler, fFVar._asStatic);
            }
            return fFVar;
        } else if (this instanceof C0681fG) {
            C0681fG fGVar = (C0681fG) this;
            boolean z = fGVar instanceof C00313p;
            if (z || z) {
                return new C00313p(fGVar._class, fGVar._keyType, fGVar._valueType, obj, fGVar._typeHandler, fGVar._asStatic);
            }
            return new C0681fG(fGVar._class, fGVar._keyType, fGVar._valueType, obj, fGVar._typeHandler, fGVar._asStatic);
        } else if (!(this instanceof C0682fH)) {
            C0683fI fIVar = (C0683fI) this;
            if (obj != fIVar._valueHandler) {
                return new C0683fI(fIVar._componentType, fIVar._emptyArray, obj, fIVar._typeHandler, fIVar._asStatic);
            }
            return fIVar;
        } else {
            C0682fH fHVar = (C0682fH) this;
            boolean z2 = fHVar instanceof C00323q;
            if (z2 || z2) {
                return new C00323q(fHVar._class, fHVar._elementType, obj, fHVar._typeHandler, fHVar._asStatic);
            }
            return new C0682fH(fHVar._class, fHVar._elementType, obj, fHVar._typeHandler, fHVar._asStatic);
        }
    }

    public final boolean A0F() {
        if (this instanceof C0683fI) {
            return ((C0683fI) this)._componentType.A0F();
        }
        if (A03() > 0) {
            return true;
        }
        return false;
    }

    public final boolean A0G() {
        if (!(this instanceof C0683fI)) {
            return Modifier.isAbstract(this._class.getModifiers());
        }
        return false;
    }

    public final boolean A0H() {
        if (!(this instanceof fF)) {
            return true;
        }
        return false;
    }

    public AbstractC1024qt(Class cls, int i, Object obj, Object obj2, boolean z) {
        this._class = cls;
        this._hashCode = cls.getName().hashCode() + i;
        this._valueHandler = obj;
        this._typeHandler = obj2;
        this._asStatic = z;
    }

    public final int hashCode() {
        return this._hashCode;
    }
}
