package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.oculus.vrshell.panels.AndroidPanelLayer;
import java.io.IOException;
import java.util.HashMap;

/* renamed from: X.0pv  reason: invalid class name and case insensitive filesystem */
public final class C04400pv {
    public final HashMap<String, AbstractC01170Rz> A00 = new HashMap<>();
    public final int A01;
    public final AbstractC04300pk A02;
    public final AbstractC01170Rz[] A03;
    public final Object[] A04;

    public static C04400pv A00(AbstractC02210iH r7, AbstractC04300pk r8, AbstractC01170Rz[] r9) throws C02180iD {
        char c;
        int length = r9.length;
        AbstractC01170Rz[] r4 = new AbstractC01170Rz[length];
        Object[] objArr = null;
        for (int i = 0; i < length; i++) {
            AbstractC01170Rz r6 = r9[i];
            JsonDeserializer<Object> jsonDeserializer = r6._valueDeserializer;
            if (jsonDeserializer == null || jsonDeserializer == AbstractC01170Rz.A01) {
                r6 = r6.A02(r7.A09(r6.A59(), r6));
            }
            r4[i] = r6;
            JsonDeserializer<Object> jsonDeserializer2 = r6._valueDeserializer;
            if (jsonDeserializer2 == AbstractC01170Rz.A01 || jsonDeserializer2 == null || (c = jsonDeserializer2.A08()) == null) {
                if (!r6.A59()._class.isPrimitive()) {
                    continue;
                } else {
                    Class<?> cls = r6.A59()._class;
                    if (cls == Integer.TYPE) {
                        c = 0;
                    } else if (cls == Long.TYPE) {
                        c = 0L;
                    } else if (cls == Boolean.TYPE) {
                        c = Boolean.FALSE;
                    } else if (cls == Double.TYPE) {
                        c = Double.valueOf(0.0d);
                    } else if (cls == Float.TYPE) {
                        c = Float.valueOf((float) AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z);
                    } else if (cls == Byte.TYPE) {
                        c = (byte) 0;
                    } else if (cls == Short.TYPE) {
                        c = (short) 0;
                    } else if (cls == Character.TYPE) {
                        c = (char) 0;
                    } else {
                        throw new IllegalArgumentException(AnonymousClass006.A09("Class ", cls.getName(), " is not a primitive type"));
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
        return new C04400pv(r8, r4, objArr);
    }

    public final C04420px A01(AbstractC02280iQ r6, AbstractC02210iH r7, C04390pu r8) {
        C04420px r4 = new C04420px(r6, r7, this.A01, r8);
        AbstractC01170Rz[] r3 = this.A03;
        if (r3 != null) {
            for (AbstractC01170Rz r0 : r3) {
                if (r0 != null) {
                    r4.A03.A0O(r0.A04());
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
            }
        }
        return r4;
    }

    public final Object A02(AbstractC02210iH r7, C04420px r8) throws IOException {
        Object obj;
        Object obj2;
        AbstractC04300pk r5 = this.A02;
        Object[] objArr = this.A04;
        if (objArr != null) {
            Object[] objArr2 = r8.A05;
            int length = objArr2.length;
            for (int i = 0; i < length; i++) {
                if (objArr2[i] == null && (obj2 = objArr[i]) != null) {
                    objArr2[i] = obj2;
                }
            }
        }
        Object A0C = r5.A0C(r7, r8.A05);
        C04390pu r3 = r8.A04;
        if (!(r3 == null || (obj = r8.A01) == null)) {
            r7.A0J(obj, r3.generator).A00(A0C);
            AbstractC01170Rz r2 = r3.idProperty;
            if (r2 != null) {
                A0C = r2.A07(A0C, r8.A01);
            }
        }
        for (AbstractC04410pw r0 = r8.A00; r0 != null; r0 = r0.A00) {
            r0.A00(A0C);
        }
        return A0C;
    }

    public C04400pv(AbstractC04300pk r7, AbstractC01170Rz[] r8, Object[] objArr) {
        this.A02 = r7;
        int length = r8.length;
        this.A01 = length;
        AbstractC01170Rz[] r4 = null;
        for (int i = 0; i < length; i++) {
            AbstractC01170Rz r2 = r8[i];
            this.A00.put(r2._propName, r2);
            if (r2.A04() != null) {
                r4 = r4 == null ? new AbstractC01170Rz[length] : r4;
                r4[i] = r2;
            }
        }
        this.A04 = objArr;
        this.A03 = r4;
    }
}
