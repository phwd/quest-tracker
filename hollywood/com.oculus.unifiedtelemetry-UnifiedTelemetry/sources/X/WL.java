package X;

import java.io.IOException;
import java.io.Serializable;

public final class WL extends AbstractC0262Ym implements Serializable {
    public static final long serialVersionUID = 1;
    public final boolean _cfgEmptyStringsAsObjects;
    public AnonymousClass8I[] _constructorArguments;
    public CB _defaultCreator;
    public AnonymousClass8I[] _delegateArguments;
    public CB _delegateCreator;
    public AbstractC0224Wl _delegateType;
    public CB _fromBooleanCreator;
    public CB _fromDoubleCreator;
    public CB _fromIntCreator;
    public CB _fromLongCreator;
    public CB _fromStringCreator;
    public CC _incompleteParameter;
    public final String _valueTypeDesc;
    public CB _withArgsCreator;

    @Override // X.AbstractC0262Ym
    public final Object A05(AbstractC0226Wn wn) throws IOException, q0 {
        CB cb = this._defaultCreator;
        if (cb != null) {
            try {
                return cb.A0R();
            } catch (Exception | ExceptionInInitializerError e) {
                throw A00(e);
            }
        } else {
            throw new IllegalStateException(AnonymousClass06.A04("No default constructor for ", A0D()));
        }
    }

    @Override // X.AbstractC0262Ym
    public final Object A06(AbstractC0226Wn wn, double d) throws IOException, q0 {
        try {
            CB cb = this._fromDoubleCreator;
            if (cb != null) {
                return cb.A0S(Double.valueOf(d));
            }
            throw new C0223Wj(AnonymousClass06.A05("Can not instantiate value of type ", A0D(), " from Floating-point number; no one-double/Double-arg constructor/factory method"));
        } catch (Exception | ExceptionInInitializerError e) {
            throw A00(e);
        }
    }

    @Override // X.AbstractC0262Ym
    public final Object A07(AbstractC0226Wn wn, int i) throws IOException, q0 {
        try {
            CB cb = this._fromIntCreator;
            if (cb != null) {
                return cb.A0S(Integer.valueOf(i));
            }
            CB cb2 = this._fromLongCreator;
            if (cb2 != null) {
                return cb2.A0S(Long.valueOf((long) i));
            }
            throw new C0223Wj(AnonymousClass06.A05("Can not instantiate value of type ", A0D(), " from Integral number; no single-int-arg constructor/factory method"));
        } catch (Exception | ExceptionInInitializerError e) {
            throw A00(e);
        }
    }

    @Override // X.AbstractC0262Ym
    public final Object A08(AbstractC0226Wn wn, long j) throws IOException, q0 {
        try {
            CB cb = this._fromLongCreator;
            if (cb != null) {
                return cb.A0S(Long.valueOf(j));
            }
            throw new C0223Wj(AnonymousClass06.A05("Can not instantiate value of type ", A0D(), " from Long integral number; no single-long-arg constructor/factory method"));
        } catch (Exception | ExceptionInInitializerError e) {
            throw A00(e);
        }
    }

    @Override // X.AbstractC0262Ym
    public final Object A09(AbstractC0226Wn wn, Object obj) throws IOException, q0 {
        CB cb = this._delegateCreator;
        if (cb != null) {
            try {
                AnonymousClass8I[] r4 = this._delegateArguments;
                if (r4 == null) {
                    return cb.A0S(obj);
                }
                int length = r4.length;
                Object[] objArr = new Object[length];
                for (int i = 0; i < length; i++) {
                    AnonymousClass8I r0 = r4[i];
                    if (r0 == null) {
                        objArr[i] = obj;
                    } else {
                        wn.A0K(r0.A04());
                        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                    }
                }
                return cb.A0T(objArr);
            } catch (Exception | ExceptionInInitializerError e) {
                throw A00(e);
            }
        } else {
            throw new IllegalStateException(AnonymousClass06.A04("No delegate constructor for ", A0D()));
        }
    }

    @Override // X.AbstractC0262Ym
    public final Object A0A(AbstractC0226Wn wn, String str) throws IOException, q0 {
        boolean z;
        CB cb = this._fromStringCreator;
        if (cb != null) {
            try {
                return cb.A0S(str);
            } catch (Exception | ExceptionInInitializerError e) {
                throw A00(e);
            }
        } else {
            if (this._fromBooleanCreator != null) {
                String trim = str.trim();
                if ("true".equals(trim)) {
                    z = true;
                } else if ("false".equals(trim)) {
                    z = false;
                }
                return A0B(wn, z);
            }
            if (this._cfgEmptyStringsAsObjects && str.length() == 0) {
                return null;
            }
            throw new C0223Wj(AnonymousClass06.A05("Can not instantiate value of type ", A0D(), " from String value; no single-String constructor/factory method"));
        }
    }

    @Override // X.AbstractC0262Ym
    public final Object A0B(AbstractC0226Wn wn, boolean z) throws IOException, q0 {
        try {
            CB cb = this._fromBooleanCreator;
            if (cb != null) {
                return cb.A0S(Boolean.valueOf(z));
            }
            throw new C0223Wj(AnonymousClass06.A05("Can not instantiate value of type ", A0D(), " from Boolean value; no single-boolean/Boolean-arg constructor/factory method"));
        } catch (Exception | ExceptionInInitializerError e) {
            throw A00(e);
        }
    }

    @Override // X.AbstractC0262Ym
    public final Object A0C(AbstractC0226Wn wn, Object[] objArr) throws IOException, q0 {
        CB cb = this._withArgsCreator;
        if (cb != null) {
            try {
                return cb.A0T(objArr);
            } catch (Exception | ExceptionInInitializerError e) {
                throw A00(e);
            }
        } else {
            throw new IllegalStateException(AnonymousClass06.A04("No with-args constructor for ", A0D()));
        }
    }

    @Override // X.AbstractC0262Ym
    public final boolean A0E() {
        if (this._fromBooleanCreator != null) {
            return true;
        }
        return false;
    }

    @Override // X.AbstractC0262Ym
    public final boolean A0F() {
        if (this._fromDoubleCreator != null) {
            return true;
        }
        return false;
    }

    @Override // X.AbstractC0262Ym
    public final boolean A0G() {
        if (this._fromIntCreator != null) {
            return true;
        }
        return false;
    }

    @Override // X.AbstractC0262Ym
    public final boolean A0H() {
        if (this._fromLongCreator != null) {
            return true;
        }
        return false;
    }

    @Override // X.AbstractC0262Ym
    public final boolean A0I() {
        if (this._withArgsCreator != null) {
            return true;
        }
        return false;
    }

    @Override // X.AbstractC0262Ym
    public final boolean A0J() {
        if (this._fromStringCreator != null) {
            return true;
        }
        return false;
    }

    @Override // X.AbstractC0262Ym
    public final boolean A0K() {
        if (this._defaultCreator != null) {
            return true;
        }
        return false;
    }

    @Override // X.AbstractC0262Ym
    public final boolean A0L() {
        if (this._delegateType != null) {
            return true;
        }
        return false;
    }

    public WL(AnonymousClass8M r2, AbstractC0224Wl wl) {
        boolean A06;
        String obj;
        if (r2 == null) {
            A06 = false;
        } else {
            A06 = r2.A06(EnumC0225Wm.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        }
        this._cfgEmptyStringsAsObjects = A06;
        if (wl == null) {
            obj = "UNKNOWN TYPE";
        } else {
            obj = wl.toString();
        }
        this._valueTypeDesc = obj;
    }

    private final C0223Wj A00(Throwable th) {
        while (th.getCause() != null) {
            th = th.getCause();
        }
        if (th instanceof C0223Wj) {
            return (C0223Wj) th;
        }
        return new C0223Wj(AnonymousClass06.A06("Instantiation of ", A0D(), " value failed: ", th.getMessage()), th);
    }

    @Override // X.AbstractC0262Ym
    public final CC A02() {
        return this._incompleteParameter;
    }

    @Override // X.AbstractC0262Ym
    public final CB A03() {
        return this._defaultCreator;
    }

    @Override // X.AbstractC0262Ym
    public final CB A04() {
        return this._delegateCreator;
    }

    @Override // X.AbstractC0262Ym
    public final String A0D() {
        return this._valueTypeDesc;
    }

    @Override // X.AbstractC0262Ym
    public final AbstractC0224Wl A01(AnonymousClass8M r2) {
        return this._delegateType;
    }

    @Override // X.AbstractC0262Ym
    public final AbstractC0073Cr[] A0M(AnonymousClass8M r2) {
        return this._constructorArguments;
    }
}
