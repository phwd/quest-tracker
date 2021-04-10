package X;

public abstract class Ok {
    public final Object A01(AbstractC1022qr qrVar) {
        if (!(this instanceof C1042rH)) {
            throw new C1025qv(AnonymousClass08.A05("Can not instantiate value of type ", A05(), "; no default creator found"));
        }
        C1042rH rHVar = (C1042rH) this;
        SV sv = rHVar._defaultCreator;
        if (sv != null) {
            try {
                if (!(sv instanceof AnonymousClass0q)) {
                    return ((C00090s) sv)._constructor.newInstance(new Object[0]);
                }
                return ((AnonymousClass0q) sv).A00.invoke(null, new Object[0]);
            } catch (ExceptionInInitializerError e) {
                throw C1042rH.A00(rHVar, e);
            } catch (Exception e2) {
                throw C1042rH.A00(rHVar, e2);
            }
        } else {
            throw new IllegalStateException(AnonymousClass08.A04("No default constructor for ", rHVar.A05()));
        }
    }

    public final Object A02(AbstractC1022qr qrVar, Object obj) {
        if (!(this instanceof C1042rH)) {
            throw new C1025qv(AnonymousClass08.A05("Can not instantiate value of type ", A05(), " using delegate"));
        }
        C1042rH rHVar = (C1042rH) this;
        SV sv = rHVar._delegateCreator;
        if (sv != null) {
            try {
                C0372Ui[] uiArr = rHVar._delegateArguments;
                if (uiArr == null) {
                    return sv.A0U(obj);
                }
                int length = uiArr.length;
                Object[] objArr = new Object[length];
                for (int i = 0; i < length; i++) {
                    C0372Ui ui = uiArr[i];
                    if (ui == null) {
                        objArr[i] = obj;
                    } else {
                        qrVar.A0N(ui._injectableValueId);
                        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                    }
                }
                if (!(sv instanceof AnonymousClass0q)) {
                    return ((C00090s) sv)._constructor.newInstance(objArr);
                }
                return ((AnonymousClass0q) sv).A00.invoke(null, objArr);
            } catch (ExceptionInInitializerError e) {
                throw C1042rH.A00(rHVar, e);
            } catch (Exception e2) {
                throw C1042rH.A00(rHVar, e2);
            }
        } else {
            throw new IllegalStateException(AnonymousClass08.A04("No delegate constructor for ", rHVar.A05()));
        }
    }

    public final Object A03(AbstractC1022qr qrVar, String str) {
        boolean z;
        if (!(this instanceof C1042rH)) {
            throw new C1025qv(AnonymousClass08.A05("Can not instantiate value of type ", A05(), " from String value"));
        }
        C1042rH rHVar = (C1042rH) this;
        SV sv = rHVar._fromStringCreator;
        if (sv != null) {
            try {
                return sv.A0U(str);
            } catch (Exception e) {
                throw C1042rH.A00(rHVar, e);
            } catch (ExceptionInInitializerError e2) {
                throw C1042rH.A00(rHVar, e2);
            }
        } else {
            if (rHVar._fromBooleanCreator != null) {
                String trim = str.trim();
                if ("true".equals(trim)) {
                    z = true;
                } else if ("false".equals(trim)) {
                    z = false;
                }
                return rHVar.A04(qrVar, z);
            }
            if (rHVar._cfgEmptyStringsAsObjects && str.length() == 0) {
                return null;
            }
            throw new C1025qv(AnonymousClass08.A05("Can not instantiate value of type ", rHVar.A05(), " from String value; no single-String constructor/factory method"));
        }
    }

    public final Object A04(AbstractC1022qr qrVar, boolean z) {
        if (!(this instanceof C1042rH)) {
            throw new C1025qv(AnonymousClass08.A05("Can not instantiate value of type ", A05(), " from Boolean value"));
        }
        C1042rH rHVar = (C1042rH) this;
        try {
            SV sv = rHVar._fromBooleanCreator;
            if (sv != null) {
                return sv.A0U(Boolean.valueOf(z));
            }
            throw new C1025qv(AnonymousClass08.A05("Can not instantiate value of type ", rHVar.A05(), " from Boolean value; no single-boolean/Boolean-arg constructor/factory method"));
        } catch (Exception e) {
            throw C1042rH.A00(rHVar, e);
        } catch (ExceptionInInitializerError e2) {
            throw C1042rH.A00(rHVar, e2);
        }
    }

    public final String A05() {
        if (!(this instanceof C1042rH)) {
            return NT.class.getName();
        }
        return ((C1042rH) this)._valueTypeDesc;
    }

    public final boolean A06() {
        if (!(this instanceof C1042rH)) {
            if (!(this instanceof C1038rC)) {
                return false;
            }
            return true;
        } else if (((C1042rH) this)._withArgsCreator != null) {
            return true;
        } else {
            return false;
        }
    }

    public final boolean A07() {
        C1042rH rHVar;
        SV sv;
        boolean z = this instanceof C1042rH;
        if (z) {
            rHVar = (C1042rH) this;
            sv = rHVar._defaultCreator;
        } else if (!z) {
            sv = null;
        } else {
            rHVar = (C1042rH) this;
            sv = rHVar._defaultCreator;
        }
        if (sv != null) {
            return true;
        }
        return false;
    }

    public final boolean A08() {
        if ((this instanceof C1042rH) && ((C1042rH) this)._delegateType != null) {
            return true;
        }
        return false;
    }

    public final AbstractC1034r7[] A09(AnonymousClass2I r7) {
        if (this instanceof C1042rH) {
            return ((C1042rH) this)._constructorArguments;
        }
        if (!(this instanceof C1038rC)) {
            return null;
        }
        AbstractC1024qt A03 = r7.A03(Integer.TYPE);
        AbstractC1024qt A032 = r7.A03(Long.TYPE);
        return new C0372Ui[]{C1038rC.A00("sourceRef", r7.A03(Object.class), 0), C1038rC.A00("byteOffset", A032, 1), C1038rC.A00("charOffset", A032, 2), C1038rC.A00("lineNr", A03, 3), C1038rC.A00("columnNr", A03, 4)};
    }
}
