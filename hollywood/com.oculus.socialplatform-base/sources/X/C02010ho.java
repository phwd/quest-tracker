package X;

import java.io.IOException;
import java.io.Serializable;

/* renamed from: X.0ho  reason: invalid class name and case insensitive filesystem */
public final class C02010ho extends AbstractC04300pk implements Serializable {
    public static final long serialVersionUID = 1;
    public final boolean _cfgEmptyStringsAsObjects;
    public AnonymousClass0HD[] _constructorArguments;
    public AnonymousClass0Ow _defaultCreator;
    public AnonymousClass0HD[] _delegateArguments;
    public AnonymousClass0Ow _delegateCreator;
    public AbstractC02190iF _delegateType;
    public AnonymousClass0Ow _fromBooleanCreator;
    public AnonymousClass0Ow _fromDoubleCreator;
    public AnonymousClass0Ow _fromIntCreator;
    public AnonymousClass0Ow _fromLongCreator;
    public AnonymousClass0Ow _fromStringCreator;
    public AnonymousClass0Ox _incompleteParameter;
    public final String _valueTypeDesc;
    public AnonymousClass0Ow _withArgsCreator;

    @Override // X.AbstractC04300pk
    public final Object A05(AbstractC02210iH r3) throws IOException, C03620oC {
        AnonymousClass0Ow r0 = this._defaultCreator;
        if (r0 != null) {
            try {
                return r0.A0U();
            } catch (ExceptionInInitializerError e) {
                throw A00(e);
            } catch (Exception e2) {
                throw A00(e2);
            }
        } else {
            throw new IllegalStateException(AnonymousClass006.A07("No default constructor for ", A0D()));
        }
    }

    @Override // X.AbstractC04300pk
    public final Object A06(AbstractC02210iH r4, double d) throws IOException, C03620oC {
        try {
            AnonymousClass0Ow r1 = this._fromDoubleCreator;
            if (r1 != null) {
                return r1.A0V(Double.valueOf(d));
            }
            throw new C02180iD(AnonymousClass006.A09("Can not instantiate value of type ", A0D(), " from Floating-point number; no one-double/Double-arg constructor/factory method"));
        } catch (Exception e) {
            throw A00(e);
        } catch (ExceptionInInitializerError e2) {
            throw A00(e2);
        }
    }

    @Override // X.AbstractC04300pk
    public final Object A07(AbstractC02210iH r4, int i) throws IOException, C03620oC {
        try {
            AnonymousClass0Ow r1 = this._fromIntCreator;
            if (r1 != null) {
                return r1.A0V(Integer.valueOf(i));
            }
            AnonymousClass0Ow r2 = this._fromLongCreator;
            if (r2 != null) {
                return r2.A0V(Long.valueOf((long) i));
            }
            throw new C02180iD(AnonymousClass006.A09("Can not instantiate value of type ", A0D(), " from Integral number; no single-int-arg constructor/factory method"));
        } catch (Exception e) {
            throw A00(e);
        } catch (ExceptionInInitializerError e2) {
            throw A00(e2);
        }
    }

    @Override // X.AbstractC04300pk
    public final Object A08(AbstractC02210iH r4, long j) throws IOException, C03620oC {
        try {
            AnonymousClass0Ow r1 = this._fromLongCreator;
            if (r1 != null) {
                return r1.A0V(Long.valueOf(j));
            }
            throw new C02180iD(AnonymousClass006.A09("Can not instantiate value of type ", A0D(), " from Long integral number; no single-long-arg constructor/factory method"));
        } catch (Exception e) {
            throw A00(e);
        } catch (ExceptionInInitializerError e2) {
            throw A00(e2);
        }
    }

    @Override // X.AbstractC04300pk
    public final Object A09(AbstractC02210iH r7, Object obj) throws IOException, C03620oC {
        AnonymousClass0Ow r5 = this._delegateCreator;
        if (r5 != null) {
            try {
                AnonymousClass0HD[] r4 = this._delegateArguments;
                if (r4 == null) {
                    return r5.A0V(obj);
                }
                int length = r4.length;
                Object[] objArr = new Object[length];
                for (int i = 0; i < length; i++) {
                    AnonymousClass0HD r0 = r4[i];
                    if (r0 == null) {
                        objArr[i] = obj;
                    } else {
                        r7.A0O(r0.A04());
                        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                    }
                }
                return r5.A0W(objArr);
            } catch (ExceptionInInitializerError e) {
                throw A00(e);
            } catch (Exception e2) {
                throw A00(e2);
            }
        } else {
            throw new IllegalStateException(AnonymousClass006.A07("No delegate constructor for ", A0D()));
        }
    }

    @Override // X.AbstractC04300pk
    public final Object A0A(AbstractC02210iH r4, String str) throws IOException, C03620oC {
        boolean z;
        AnonymousClass0Ow r0 = this._fromStringCreator;
        if (r0 != null) {
            try {
                return r0.A0V(str);
            } catch (Exception e) {
                throw A00(e);
            } catch (ExceptionInInitializerError e2) {
                throw A00(e2);
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
            throw new C02180iD(AnonymousClass006.A09("Can not instantiate value of type ", A0D(), " from String value; no single-String constructor/factory method"));
        }
    }

    @Override // X.AbstractC04300pk
    public final Object A0B(AbstractC02210iH r4, boolean z) throws IOException, C03620oC {
        try {
            AnonymousClass0Ow r1 = this._fromBooleanCreator;
            if (r1 != null) {
                return r1.A0V(Boolean.valueOf(z));
            }
            throw new C02180iD(AnonymousClass006.A09("Can not instantiate value of type ", A0D(), " from Boolean value; no single-boolean/Boolean-arg constructor/factory method"));
        } catch (Exception e) {
            throw A00(e);
        } catch (ExceptionInInitializerError e2) {
            throw A00(e2);
        }
    }

    @Override // X.AbstractC04300pk
    public final Object A0C(AbstractC02210iH r3, Object[] objArr) throws IOException, C03620oC {
        AnonymousClass0Ow r0 = this._withArgsCreator;
        if (r0 != null) {
            try {
                return r0.A0W(objArr);
            } catch (ExceptionInInitializerError e) {
                throw A00(e);
            } catch (Exception e2) {
                throw A00(e2);
            }
        } else {
            throw new IllegalStateException(AnonymousClass006.A07("No with-args constructor for ", A0D()));
        }
    }

    @Override // X.AbstractC04300pk
    public final boolean A0E() {
        if (this._fromBooleanCreator != null) {
            return true;
        }
        return false;
    }

    @Override // X.AbstractC04300pk
    public final boolean A0F() {
        if (this._fromDoubleCreator != null) {
            return true;
        }
        return false;
    }

    @Override // X.AbstractC04300pk
    public final boolean A0G() {
        if (this._fromIntCreator != null) {
            return true;
        }
        return false;
    }

    @Override // X.AbstractC04300pk
    public final boolean A0H() {
        if (this._fromLongCreator != null) {
            return true;
        }
        return false;
    }

    @Override // X.AbstractC04300pk
    public final boolean A0I() {
        if (this._withArgsCreator != null) {
            return true;
        }
        return false;
    }

    @Override // X.AbstractC04300pk
    public final boolean A0J() {
        if (this._fromStringCreator != null) {
            return true;
        }
        return false;
    }

    @Override // X.AbstractC04300pk
    public final boolean A0K() {
        if (this._defaultCreator != null) {
            return true;
        }
        return false;
    }

    @Override // X.AbstractC04300pk
    public final boolean A0L() {
        if (this._delegateType != null) {
            return true;
        }
        return false;
    }

    public C02010ho(AnonymousClass0HU r3, AbstractC02190iF r4) {
        boolean z;
        String obj;
        if (r3 == null) {
            z = false;
        } else {
            EnumC02200iG r1 = EnumC02200iG.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT;
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

    private final C02180iD A00(Throwable th) {
        while (th.getCause() != null) {
            th = th.getCause();
        }
        if (th instanceof C02180iD) {
            return (C02180iD) th;
        }
        return new C02180iD(AnonymousClass006.A0B("Instantiation of ", A0D(), " value failed: ", th.getMessage()), th);
    }

    @Override // X.AbstractC04300pk
    public final AnonymousClass0Ox A02() {
        return this._incompleteParameter;
    }

    @Override // X.AbstractC04300pk
    public final AnonymousClass0Ow A03() {
        return this._defaultCreator;
    }

    @Override // X.AbstractC04300pk
    public final AnonymousClass0Ow A04() {
        return this._delegateCreator;
    }

    @Override // X.AbstractC04300pk
    public final String A0D() {
        return this._valueTypeDesc;
    }

    @Override // X.AbstractC04300pk
    public final AbstractC02190iF A01(AnonymousClass0HU r2) {
        return this._delegateType;
    }

    @Override // X.AbstractC04300pk
    public final AbstractC01170Rz[] A0M(AnonymousClass0HU r2) {
        return this._constructorArguments;
    }
}
