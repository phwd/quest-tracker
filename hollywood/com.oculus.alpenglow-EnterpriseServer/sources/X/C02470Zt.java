package X;

import java.io.IOException;
import java.io.Serializable;

/* renamed from: X.0Zt  reason: invalid class name and case insensitive filesystem */
public final class C02470Zt extends AnonymousClass0nB implements Serializable {
    public static final long serialVersionUID = 1;
    public final boolean _cfgEmptyStringsAsObjects;
    public AnonymousClass0F3[] _constructorArguments;
    public AnonymousClass0KA _defaultCreator;
    public AnonymousClass0F3[] _delegateArguments;
    public AnonymousClass0KA _delegateCreator;
    public AnonymousClass0aI _delegateType;
    public AnonymousClass0KA _fromBooleanCreator;
    public AnonymousClass0KA _fromDoubleCreator;
    public AnonymousClass0KA _fromIntCreator;
    public AnonymousClass0KA _fromLongCreator;
    public AnonymousClass0KA _fromStringCreator;
    public AnonymousClass0KB _incompleteParameter;
    public final String _valueTypeDesc;
    public AnonymousClass0KA _withArgsCreator;

    @Override // X.AnonymousClass0nB
    public final AnonymousClass0KB A02() {
        return this._incompleteParameter;
    }

    @Override // X.AnonymousClass0nB
    public final AnonymousClass0KA A03() {
        return this._defaultCreator;
    }

    @Override // X.AnonymousClass0nB
    public final AnonymousClass0KA A04() {
        return this._delegateCreator;
    }

    @Override // X.AnonymousClass0nB
    public final Object A05(AbstractC02570aK r3) throws IOException, C05910ld {
        AnonymousClass0KA r0 = this._defaultCreator;
        if (r0 != null) {
            try {
                return r0.A0U();
            } catch (Exception | ExceptionInInitializerError e) {
                throw A00(e);
            }
        } else {
            throw new IllegalStateException(AnonymousClass006.A05("No default constructor for ", A0D()));
        }
    }

    @Override // X.AnonymousClass0nB
    public final Object A06(AbstractC02570aK r4, double d) throws IOException, C05910ld {
        try {
            AnonymousClass0KA r1 = this._fromDoubleCreator;
            if (r1 != null) {
                return r1.A0V(Double.valueOf(d));
            }
            throw new AnonymousClass0aG(AnonymousClass006.A07("Can not instantiate value of type ", A0D(), " from Floating-point number; no one-double/Double-arg constructor/factory method"));
        } catch (Exception | ExceptionInInitializerError e) {
            throw A00(e);
        }
    }

    @Override // X.AnonymousClass0nB
    public final Object A07(AbstractC02570aK r4, int i) throws IOException, C05910ld {
        try {
            AnonymousClass0KA r1 = this._fromIntCreator;
            if (r1 != null) {
                return r1.A0V(Integer.valueOf(i));
            }
            AnonymousClass0KA r2 = this._fromLongCreator;
            if (r2 != null) {
                return r2.A0V(Long.valueOf((long) i));
            }
            throw new AnonymousClass0aG(AnonymousClass006.A07("Can not instantiate value of type ", A0D(), " from Integral number; no single-int-arg constructor/factory method"));
        } catch (Exception | ExceptionInInitializerError e) {
            throw A00(e);
        }
    }

    @Override // X.AnonymousClass0nB
    public final Object A08(AbstractC02570aK r4, long j) throws IOException, C05910ld {
        try {
            AnonymousClass0KA r1 = this._fromLongCreator;
            if (r1 != null) {
                return r1.A0V(Long.valueOf(j));
            }
            throw new AnonymousClass0aG(AnonymousClass006.A07("Can not instantiate value of type ", A0D(), " from Long integral number; no single-long-arg constructor/factory method"));
        } catch (Exception | ExceptionInInitializerError e) {
            throw A00(e);
        }
    }

    @Override // X.AnonymousClass0nB
    public final Object A09(AbstractC02570aK r7, Object obj) throws IOException, C05910ld {
        AnonymousClass0KA r5 = this._delegateCreator;
        if (r5 != null) {
            try {
                AnonymousClass0F3[] r4 = this._delegateArguments;
                if (r4 == null) {
                    return r5.A0V(obj);
                }
                int length = r4.length;
                Object[] objArr = new Object[length];
                for (int i = 0; i < length; i++) {
                    AnonymousClass0F3 r0 = r4[i];
                    if (r0 == null) {
                        objArr[i] = obj;
                    } else {
                        r7.A0N(r0.A04());
                        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                    }
                }
                return r5.A0W(objArr);
            } catch (Exception | ExceptionInInitializerError e) {
                throw A00(e);
            }
        } else {
            throw new IllegalStateException(AnonymousClass006.A05("No delegate constructor for ", A0D()));
        }
    }

    @Override // X.AnonymousClass0nB
    public final Object A0A(AbstractC02570aK r4, String str) throws IOException, C05910ld {
        boolean z;
        AnonymousClass0KA r0 = this._fromStringCreator;
        if (r0 != null) {
            try {
                return r0.A0V(str);
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
            throw new AnonymousClass0aG(AnonymousClass006.A07("Can not instantiate value of type ", A0D(), " from String value; no single-String constructor/factory method"));
        }
    }

    @Override // X.AnonymousClass0nB
    public final Object A0B(AbstractC02570aK r4, boolean z) throws IOException, C05910ld {
        try {
            AnonymousClass0KA r1 = this._fromBooleanCreator;
            if (r1 != null) {
                return r1.A0V(Boolean.valueOf(z));
            }
            throw new AnonymousClass0aG(AnonymousClass006.A07("Can not instantiate value of type ", A0D(), " from Boolean value; no single-boolean/Boolean-arg constructor/factory method"));
        } catch (Exception | ExceptionInInitializerError e) {
            throw A00(e);
        }
    }

    @Override // X.AnonymousClass0nB
    public final Object A0C(AbstractC02570aK r3, Object[] objArr) throws IOException, C05910ld {
        AnonymousClass0KA r0 = this._withArgsCreator;
        if (r0 != null) {
            try {
                return r0.A0W(objArr);
            } catch (Exception | ExceptionInInitializerError e) {
                throw A00(e);
            }
        } else {
            throw new IllegalStateException(AnonymousClass006.A05("No with-args constructor for ", A0D()));
        }
    }

    @Override // X.AnonymousClass0nB
    public final String A0D() {
        return this._valueTypeDesc;
    }

    @Override // X.AnonymousClass0nB
    public final boolean A0E() {
        if (this._fromBooleanCreator != null) {
            return true;
        }
        return false;
    }

    @Override // X.AnonymousClass0nB
    public final boolean A0F() {
        if (this._fromDoubleCreator != null) {
            return true;
        }
        return false;
    }

    @Override // X.AnonymousClass0nB
    public final boolean A0G() {
        if (this._fromIntCreator != null) {
            return true;
        }
        return false;
    }

    @Override // X.AnonymousClass0nB
    public final boolean A0H() {
        if (this._fromLongCreator != null) {
            return true;
        }
        return false;
    }

    @Override // X.AnonymousClass0nB
    public final boolean A0I() {
        if (this._withArgsCreator != null) {
            return true;
        }
        return false;
    }

    @Override // X.AnonymousClass0nB
    public final boolean A0J() {
        if (this._fromStringCreator != null) {
            return true;
        }
        return false;
    }

    @Override // X.AnonymousClass0nB
    public final boolean A0K() {
        if (this._defaultCreator != null) {
            return true;
        }
        return false;
    }

    @Override // X.AnonymousClass0nB
    public final boolean A0L() {
        if (this._delegateType != null) {
            return true;
        }
        return false;
    }

    public C02470Zt(C01260Fu r3, AnonymousClass0aI r4) {
        boolean z;
        String r0;
        if (r3 == null) {
            z = false;
        } else {
            EnumC02560aJ r1 = EnumC02560aJ.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT;
            z = false;
            if ((r1.getMask() & r3._deserFeatures) != 0) {
                z = true;
            }
        }
        this._cfgEmptyStringsAsObjects = z;
        if (r4 == null) {
            r0 = "UNKNOWN TYPE";
        } else {
            r0 = r4.toString();
        }
        this._valueTypeDesc = r0;
    }

    private final AnonymousClass0aG A00(Throwable th) {
        while (th.getCause() != null) {
            th = th.getCause();
        }
        if (th instanceof AnonymousClass0aG) {
            return (AnonymousClass0aG) th;
        }
        return new AnonymousClass0aG(AnonymousClass006.A08("Instantiation of ", A0D(), " value failed: ", th.getMessage()), th);
    }

    @Override // X.AnonymousClass0nB
    public final AnonymousClass0aI A01(C01260Fu r2) {
        return this._delegateType;
    }

    @Override // X.AnonymousClass0nB
    public final AbstractC01680Ku[] A0M(C01260Fu r2) {
        return this._constructorArguments;
    }
}
