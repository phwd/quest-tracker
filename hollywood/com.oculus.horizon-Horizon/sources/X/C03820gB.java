package X;

import java.io.IOException;
import java.io.Serializable;

/* renamed from: X.0gB  reason: invalid class name and case insensitive filesystem */
public final class C03820gB extends AnonymousClass0lG implements Serializable {
    public static final long serialVersionUID = 1;
    public final boolean _cfgEmptyStringsAsObjects;
    public AnonymousClass08T[] _constructorArguments;
    public AnonymousClass0GV _defaultCreator;
    public AnonymousClass08T[] _delegateArguments;
    public AnonymousClass0GV _delegateCreator;
    public AbstractC04000gb _delegateType;
    public AnonymousClass0GV _fromBooleanCreator;
    public AnonymousClass0GV _fromDoubleCreator;
    public AnonymousClass0GV _fromIntCreator;
    public AnonymousClass0GV _fromLongCreator;
    public AnonymousClass0GV _fromStringCreator;
    public AnonymousClass0GW _incompleteParameter;
    public final String _valueTypeDesc;
    public AnonymousClass0GV _withArgsCreator;

    @Override // X.AnonymousClass0lG
    public final Object A05(AbstractC04020gg r3) throws IOException, AnonymousClass0jg {
        AnonymousClass0GV r0 = this._defaultCreator;
        if (r0 != null) {
            try {
                return r0.A0R();
            } catch (Exception | ExceptionInInitializerError e) {
                throw A00(e);
            }
        } else {
            throw new IllegalStateException(AnonymousClass006.A05("No default constructor for ", A0D()));
        }
    }

    @Override // X.AnonymousClass0lG
    public final Object A06(AbstractC04020gg r4, double d) throws IOException, AnonymousClass0jg {
        try {
            AnonymousClass0GV r1 = this._fromDoubleCreator;
            if (r1 != null) {
                return r1.A0S(Double.valueOf(d));
            }
            throw new C03990gZ(AnonymousClass006.A07("Can not instantiate value of type ", A0D(), " from Floating-point number; no one-double/Double-arg constructor/factory method"));
        } catch (Exception | ExceptionInInitializerError e) {
            throw A00(e);
        }
    }

    @Override // X.AnonymousClass0lG
    public final Object A07(AbstractC04020gg r4, int i) throws IOException, AnonymousClass0jg {
        try {
            AnonymousClass0GV r1 = this._fromIntCreator;
            if (r1 != null) {
                return r1.A0S(Integer.valueOf(i));
            }
            AnonymousClass0GV r2 = this._fromLongCreator;
            if (r2 != null) {
                return r2.A0S(Long.valueOf((long) i));
            }
            throw new C03990gZ(AnonymousClass006.A07("Can not instantiate value of type ", A0D(), " from Integral number; no single-int-arg constructor/factory method"));
        } catch (Exception | ExceptionInInitializerError e) {
            throw A00(e);
        }
    }

    @Override // X.AnonymousClass0lG
    public final Object A08(AbstractC04020gg r4, long j) throws IOException, AnonymousClass0jg {
        try {
            AnonymousClass0GV r1 = this._fromLongCreator;
            if (r1 != null) {
                return r1.A0S(Long.valueOf(j));
            }
            throw new C03990gZ(AnonymousClass006.A07("Can not instantiate value of type ", A0D(), " from Long integral number; no single-long-arg constructor/factory method"));
        } catch (Exception | ExceptionInInitializerError e) {
            throw A00(e);
        }
    }

    @Override // X.AnonymousClass0lG
    public final Object A09(AbstractC04020gg r7, Object obj) throws IOException, AnonymousClass0jg {
        AnonymousClass0GV r5 = this._delegateCreator;
        if (r5 != null) {
            try {
                AnonymousClass08T[] r4 = this._delegateArguments;
                if (r4 == null) {
                    return r5.A0S(obj);
                }
                int length = r4.length;
                Object[] objArr = new Object[length];
                for (int i = 0; i < length; i++) {
                    AnonymousClass08T r0 = r4[i];
                    if (r0 == null) {
                        objArr[i] = obj;
                    } else {
                        r7.A0H(r0.A04());
                        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                    }
                }
                return r5.A0T(objArr);
            } catch (Exception | ExceptionInInitializerError e) {
                throw A00(e);
            }
        } else {
            throw new IllegalStateException(AnonymousClass006.A05("No delegate constructor for ", A0D()));
        }
    }

    @Override // X.AnonymousClass0lG
    public final Object A0A(AbstractC04020gg r4, String str) throws IOException, AnonymousClass0jg {
        boolean z;
        AnonymousClass0GV r0 = this._fromStringCreator;
        if (r0 != null) {
            try {
                return r0.A0S(str);
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
                return A0B(r4, z);
            }
            if (this._cfgEmptyStringsAsObjects && str.length() == 0) {
                return null;
            }
            throw new C03990gZ(AnonymousClass006.A07("Can not instantiate value of type ", A0D(), " from String value; no single-String constructor/factory method"));
        }
    }

    @Override // X.AnonymousClass0lG
    public final Object A0B(AbstractC04020gg r4, boolean z) throws IOException, AnonymousClass0jg {
        try {
            AnonymousClass0GV r1 = this._fromBooleanCreator;
            if (r1 != null) {
                return r1.A0S(Boolean.valueOf(z));
            }
            throw new C03990gZ(AnonymousClass006.A07("Can not instantiate value of type ", A0D(), " from Boolean value; no single-boolean/Boolean-arg constructor/factory method"));
        } catch (Exception | ExceptionInInitializerError e) {
            throw A00(e);
        }
    }

    @Override // X.AnonymousClass0lG
    public final Object A0C(AbstractC04020gg r3, Object[] objArr) throws IOException, AnonymousClass0jg {
        AnonymousClass0GV r0 = this._withArgsCreator;
        if (r0 != null) {
            try {
                return r0.A0T(objArr);
            } catch (Exception | ExceptionInInitializerError e) {
                throw A00(e);
            }
        } else {
            throw new IllegalStateException(AnonymousClass006.A05("No with-args constructor for ", A0D()));
        }
    }

    @Override // X.AnonymousClass0lG
    public final boolean A0E() {
        if (this._fromBooleanCreator != null) {
            return true;
        }
        return false;
    }

    @Override // X.AnonymousClass0lG
    public final boolean A0F() {
        if (this._fromDoubleCreator != null) {
            return true;
        }
        return false;
    }

    @Override // X.AnonymousClass0lG
    public final boolean A0G() {
        if (this._fromIntCreator != null) {
            return true;
        }
        return false;
    }

    @Override // X.AnonymousClass0lG
    public final boolean A0H() {
        if (this._fromLongCreator != null) {
            return true;
        }
        return false;
    }

    @Override // X.AnonymousClass0lG
    public final boolean A0I() {
        if (this._withArgsCreator != null) {
            return true;
        }
        return false;
    }

    @Override // X.AnonymousClass0lG
    public final boolean A0J() {
        if (this._fromStringCreator != null) {
            return true;
        }
        return false;
    }

    @Override // X.AnonymousClass0lG
    public final boolean A0K() {
        if (this._defaultCreator != null) {
            return true;
        }
        return false;
    }

    @Override // X.AnonymousClass0lG
    public final boolean A0L() {
        if (this._delegateType != null) {
            return true;
        }
        return false;
    }

    public C03820gB(AnonymousClass08X r3, AbstractC04000gb r4) {
        boolean z;
        String obj;
        if (r3 == null) {
            z = false;
        } else {
            EnumC04010gf r1 = EnumC04010gf.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT;
            z = false;
            if ((r1.getMask() & r3._deserFeatures) != 0) {
                z = true;
            }
        }
        this._cfgEmptyStringsAsObjects = z;
        if (r4 == null) {
            obj = "UNKNOWN TYPE";
        } else {
            obj = r4.toString();
        }
        this._valueTypeDesc = obj;
    }

    private final C03990gZ A00(Throwable th) {
        while (th.getCause() != null) {
            th = th.getCause();
        }
        if (th instanceof C03990gZ) {
            return (C03990gZ) th;
        }
        return new C03990gZ(AnonymousClass006.A08("Instantiation of ", A0D(), " value failed: ", th.getMessage()), th);
    }

    @Override // X.AnonymousClass0lG
    public final AnonymousClass0GW A02() {
        return this._incompleteParameter;
    }

    @Override // X.AnonymousClass0lG
    public final AnonymousClass0GV A03() {
        return this._defaultCreator;
    }

    @Override // X.AnonymousClass0lG
    public final AnonymousClass0GV A04() {
        return this._delegateCreator;
    }

    @Override // X.AnonymousClass0lG
    public final String A0D() {
        return this._valueTypeDesc;
    }

    @Override // X.AnonymousClass0lG
    public final AbstractC04000gb A01(AnonymousClass08X r2) {
        return this._delegateType;
    }

    @Override // X.AnonymousClass0lG
    public final AnonymousClass0HD[] A0M(AnonymousClass08X r2) {
        return this._constructorArguments;
    }
}
