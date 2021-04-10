package X;

import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

/* renamed from: X.Lq  reason: case insensitive filesystem */
public class C0095Lq implements hL<T> {
    public final hG A00;
    public final /* synthetic */ C0232hV A01;
    public final /* synthetic */ Class A02;
    public final /* synthetic */ Type A03;

    public C0095Lq(C0232hV hVVar, Class cls, Type type) {
        hG hGVar;
        this.A01 = hVVar;
        this.A02 = cls;
        this.A03 = type;
        try {
            Class<?> cls2 = Class.forName("sun.misc.Unsafe");
            Field declaredField = cls2.getDeclaredField("theUnsafe");
            declaredField.setAccessible(true);
            hGVar = new C0083Le(cls2.getMethod("allocateInstance", Class.class), declaredField.get(null));
        } catch (Exception unused) {
            try {
                Method declaredMethod = ObjectStreamClass.class.getDeclaredMethod("getConstructorId", Class.class);
                declaredMethod.setAccessible(true);
                int intValue = ((Integer) declaredMethod.invoke(null, Object.class)).intValue();
                Method declaredMethod2 = ObjectStreamClass.class.getDeclaredMethod("newInstance", Class.class, Integer.TYPE);
                declaredMethod2.setAccessible(true);
                hGVar = new C0082Ld(declaredMethod2, intValue);
            } catch (Exception unused2) {
                try {
                    Method declaredMethod3 = ObjectInputStream.class.getDeclaredMethod("newInstance", Class.class, Class.class);
                    declaredMethod3.setAccessible(true);
                    hGVar = new C0081Lc(declaredMethod3);
                } catch (Exception unused3) {
                    hGVar = new C0080Lb();
                }
            }
        }
        this.A00 = hGVar;
    }

    @Override // X.hL
    public final T A1B() {
        try {
            hG hGVar = this.A00;
            Class cls = this.A02;
            if (hGVar instanceof C0080Lb) {
                StringBuilder sb = new StringBuilder("Cannot allocate ");
                sb.append(cls);
                throw new UnsupportedOperationException(sb.toString());
            } else if (hGVar instanceof C0081Lc) {
                hG.A00(cls);
                return (T) ((C0081Lc) hGVar).A00.invoke(null, cls, Object.class);
            } else if (!(hGVar instanceof C0082Ld)) {
                C0083Le le = (C0083Le) hGVar;
                hG.A00(cls);
                return (T) le.A01.invoke(le.A00, cls);
            } else {
                C0082Ld ld = (C0082Ld) hGVar;
                hG.A00(cls);
                return (T) ld.A01.invoke(null, cls, Integer.valueOf(ld.A00));
            }
        } catch (Exception e) {
            StringBuilder sb2 = new StringBuilder("Unable to invoke no-args constructor for ");
            sb2.append(this.A03);
            sb2.append(". Registering an InstanceCreator with Gson for this type may fix this problem.");
            throw new RuntimeException(sb2.toString(), e);
        }
    }
}
