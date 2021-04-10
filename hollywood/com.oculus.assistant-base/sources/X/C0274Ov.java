package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import java.util.HashMap;
import java.util.Map;

/* renamed from: X.Ov  reason: case insensitive filesystem */
public final class C0274Ov {
    public final HashMap A00 = new HashMap();
    public final int A01;
    public final Ok A02;
    public final AbstractC1034r7[] A03;
    public final Object[] A04;

    public static C0274Ov A00(AbstractC1022qr qrVar, Ok ok, AbstractC1034r7[] r7VarArr) {
        char c;
        int length = r7VarArr.length;
        AbstractC1034r7[] r7VarArr2 = new AbstractC1034r7[length];
        Object[] objArr = null;
        for (int i = 0; i < length; i++) {
            AbstractC1034r7 r7Var = r7VarArr[i];
            JsonDeserializer jsonDeserializer = r7Var._valueDeserializer;
            if (jsonDeserializer == null || jsonDeserializer == AbstractC1034r7.A01) {
                r7Var = r7Var.A02(qrVar.A08(r7Var.A34(), r7Var));
            }
            r7VarArr2[i] = r7Var;
            JsonDeserializer jsonDeserializer2 = r7Var._valueDeserializer;
            if (jsonDeserializer2 == AbstractC1034r7.A01 || jsonDeserializer2 == null || (c = jsonDeserializer2.A08()) == null) {
                if (!r7Var.A34()._class.isPrimitive()) {
                    continue;
                } else {
                    Class cls = r7Var.A34()._class;
                    if (cls == Integer.TYPE) {
                        c = 0;
                    } else if (cls == Long.TYPE) {
                        c = 0L;
                    } else if (cls == Boolean.TYPE) {
                        c = Boolean.FALSE;
                    } else if (cls == Double.TYPE) {
                        c = Double.valueOf(0.0d);
                    } else if (cls == Float.TYPE) {
                        c = Float.valueOf(0.0f);
                    } else if (cls == Byte.TYPE) {
                        c = (byte) 0;
                    } else if (cls == Short.TYPE) {
                        c = (short) 0;
                    } else if (cls == Character.TYPE) {
                        c = (char) 0;
                    } else {
                        throw new IllegalArgumentException(AnonymousClass08.A05("Class ", cls.getName(), " is not a primitive type"));
                    }
                    if (c == null) {
                    }
                }
            }
            if (objArr == null) {
                objArr = new Object[length];
            }
            objArr[i] = c;
        }
        return new C0274Ov(ok, r7VarArr2, objArr);
    }

    public final C0276Ox A01(AbstractC1014qi qiVar, AbstractC1022qr qrVar, C0273Ou ou) {
        Object obj;
        C0276Ox ox = new C0276Ox(qiVar, qrVar, this.A01, ou);
        AbstractC1034r7[] r7VarArr = this.A03;
        if (r7VarArr != null) {
            for (AbstractC1034r7 r7Var : r7VarArr) {
                if (r7Var != null) {
                    AbstractC1022qr qrVar2 = ox.A03;
                    if (!(r7Var instanceof C0372Ui)) {
                        obj = null;
                    } else {
                        obj = ((C0372Ui) r7Var)._injectableValueId;
                    }
                    qrVar2.A0N(obj);
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
            }
        }
        return ox;
    }

    public final Object A02(AbstractC1022qr qrVar, C0276Ox ox) {
        Object obj;
        Object obj2;
        long longValue;
        long longValue2;
        int intValue;
        int intValue2;
        Object obj3;
        Ok ok = this.A02;
        Object[] objArr = this.A04;
        if (objArr != null) {
            Object[] objArr2 = ox.A05;
            int length = objArr2.length;
            for (int i = 0; i < length; i++) {
                if (objArr2[i] == null && (obj3 = objArr[i]) != null) {
                    objArr2[i] = obj3;
                }
            }
        }
        Object[] objArr3 = ox.A05;
        if (ok instanceof C1042rH) {
            C1042rH rHVar = (C1042rH) ok;
            SV sv = rHVar._withArgsCreator;
            if (sv != null) {
                try {
                    if (!(sv instanceof AnonymousClass0q)) {
                        obj = ((C00090s) sv)._constructor.newInstance(objArr3);
                    } else {
                        obj = ((AnonymousClass0q) sv).A00.invoke(null, objArr3);
                    }
                } catch (ExceptionInInitializerError e) {
                    throw C1042rH.A00(rHVar, e);
                } catch (Exception e2) {
                    throw C1042rH.A00(rHVar, e2);
                }
            } else {
                throw new IllegalStateException(AnonymousClass08.A04("No with-args constructor for ", rHVar.A05()));
            }
        } else if (!(ok instanceof C1038rC)) {
            throw new C1025qv(AnonymousClass08.A05("Can not instantiate value of type ", ok.A05(), " with arguments"));
        } else {
            Object obj4 = objArr3[0];
            Object obj5 = objArr3[1];
            if (obj5 == null) {
                longValue = 0;
            } else {
                longValue = ((Number) obj5).longValue();
            }
            Object obj6 = objArr3[2];
            if (obj6 == null) {
                longValue2 = 0;
            } else {
                longValue2 = ((Number) obj6).longValue();
            }
            Object obj7 = objArr3[3];
            if (obj7 == null) {
                intValue = 0;
            } else {
                intValue = ((Number) obj7).intValue();
            }
            Object obj8 = objArr3[4];
            if (obj8 == null) {
                intValue2 = 0;
            } else {
                intValue2 = ((Number) obj8).intValue();
            }
            obj = new NT(obj4, longValue, longValue2, intValue, intValue2);
        }
        C0273Ou ou = ox.A04;
        if (!(ou == null || (obj2 = ox.A01) == null)) {
            qrVar.A0F(obj2, ou.generator).A00(obj);
            AbstractC1034r7 r7Var = ou.idProperty;
            if (r7Var != null) {
                obj = r7Var.A06(obj, ox.A01);
            }
        }
        for (AbstractC0275Ow ow = ox.A00; ow != null; ow = ow.A00) {
            if (ow instanceof C1037rA) {
                C1037rA rAVar = (C1037rA) ow;
                rAVar.A00.A09(obj, rAVar.A01);
            } else if (!(ow instanceof C1036r9)) {
                C1035r8 r8Var = (C1035r8) ow;
                r8Var.A00.A02(obj, r8Var.A01, ((AbstractC0275Ow) r8Var).A01);
            } else {
                C1036r9 r9Var = (C1036r9) ow;
                ((Map) obj).put(r9Var.A00, r9Var.A01);
            }
        }
        return obj;
    }

    public C0274Ov(Ok ok, AbstractC1034r7[] r7VarArr, Object[] objArr) {
        this.A02 = ok;
        int length = r7VarArr.length;
        this.A01 = length;
        AbstractC1034r7[] r7VarArr2 = null;
        for (int i = 0; i < length; i++) {
            AbstractC1034r7 r7Var = r7VarArr[i];
            this.A00.put(r7Var._propName, r7Var);
            if ((r7Var instanceof C0372Ui) && ((C0372Ui) r7Var)._injectableValueId != null) {
                r7VarArr2 = r7VarArr2 == null ? new AbstractC1034r7[length] : r7VarArr2;
                r7VarArr2[i] = r7Var;
            }
        }
        this.A04 = objArr;
        this.A03 = r7VarArr2;
    }
}
