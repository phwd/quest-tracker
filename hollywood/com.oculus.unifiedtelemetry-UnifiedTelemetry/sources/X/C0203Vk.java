package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.util.HashMap;

/* renamed from: X.Vk  reason: case insensitive filesystem */
public final class C0203Vk {
    public final HashMap<String, AbstractC0073Cr> A00 = new HashMap<>();
    public final int A01;
    public final AbstractC0262Ym A02;
    public final AbstractC0073Cr[] A03;
    public final Object[] A04;

    public static C0203Vk A00(AbstractC0226Wn wn, AbstractC0262Ym ym, AbstractC0073Cr[] crArr) throws C0223Wj {
        char c;
        int length = crArr.length;
        AbstractC0073Cr[] crArr2 = new AbstractC0073Cr[length];
        Object[] objArr = null;
        for (int i = 0; i < length; i++) {
            AbstractC0073Cr cr = crArr[i];
            JsonDeserializer<Object> jsonDeserializer = cr._valueDeserializer;
            if (jsonDeserializer == null || jsonDeserializer == AbstractC0073Cr.A01) {
                cr = cr.A02(wn.A06(cr._type, cr));
            }
            crArr2[i] = cr;
            JsonDeserializer<Object> jsonDeserializer2 = cr._valueDeserializer;
            if (jsonDeserializer2 == AbstractC0073Cr.A01 || jsonDeserializer2 == null || (c = jsonDeserializer2.A08()) == null) {
                if (!cr._type._class.isPrimitive()) {
                    continue;
                } else {
                    Class<?> cls = cr._type._class;
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
                        throw new IllegalArgumentException(AnonymousClass06.A05("Class ", cls.getName(), " is not a primitive type"));
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
        return new C0203Vk(ym, crArr2, objArr);
    }

    public final C0201Vh A01(AbstractC0232Ww ww, AbstractC0226Wn wn, C0204Vm vm) {
        C0201Vh vh = new C0201Vh(ww, wn, this.A01, vm);
        AbstractC0073Cr[] crArr = this.A03;
        if (crArr != null) {
            for (AbstractC0073Cr cr : crArr) {
                if (cr != null) {
                    vh.A03.A0K(cr.A04());
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
            }
        }
        return vh;
    }

    public final Object A02(AbstractC0226Wn wn, C0201Vh vh) throws IOException {
        Object obj;
        Object obj2;
        AbstractC0262Ym ym = this.A02;
        Object[] objArr = this.A04;
        if (objArr != null) {
            Object[] objArr2 = vh.A05;
            int length = objArr2.length;
            for (int i = 0; i < length; i++) {
                if (objArr2[i] == null && (obj2 = objArr[i]) != null) {
                    objArr2[i] = obj2;
                }
            }
        }
        Object A0C = ym.A0C(wn, vh.A05);
        C0204Vm vm = vh.A04;
        if (!(vm == null || (obj = vh.A01) == null)) {
            wn.A0F(obj, vm.generator).A00(A0C);
            AbstractC0073Cr cr = vm.idProperty;
            if (cr != null) {
                A0C = cr.A07(A0C, vh.A01);
            }
        }
        for (AbstractC0202Vi vi = vh.A00; vi != null; vi = vi.A00) {
            vi.A00(A0C);
        }
        return A0C;
    }

    public C0203Vk(AbstractC0262Ym ym, AbstractC0073Cr[] crArr, Object[] objArr) {
        this.A02 = ym;
        int length = crArr.length;
        this.A01 = length;
        AbstractC0073Cr[] crArr2 = null;
        for (int i = 0; i < length; i++) {
            AbstractC0073Cr cr = crArr[i];
            this.A00.put(cr._propName, cr);
            if (cr.A04() != null) {
                crArr2 = crArr2 == null ? new AbstractC0073Cr[length] : crArr2;
                crArr2[i] = cr;
            }
        }
        this.A04 = objArr;
        this.A03 = crArr2;
    }
}
