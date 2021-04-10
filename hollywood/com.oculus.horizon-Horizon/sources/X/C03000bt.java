package X;

import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

/* renamed from: X.0bt  reason: invalid class name and case insensitive filesystem */
public class C03000bt implements AbstractC09010zC<T> {
    public final AbstractC09050zH A00;
    public final /* synthetic */ C08920yx A01;
    public final /* synthetic */ Class A02;
    public final /* synthetic */ Type A03;

    public C03000bt(C08920yx r11, Class cls, Type type) {
        AbstractC09050zH r1;
        this.A01 = r11;
        this.A02 = cls;
        this.A03 = type;
        try {
            Class<?> cls2 = Class.forName("sun.misc.Unsafe");
            Field declaredField = cls2.getDeclaredField("theUnsafe");
            declaredField.setAccessible(true);
            r1 = new C02160Zm(cls2.getMethod("allocateInstance", Class.class), declaredField.get(null));
        } catch (Exception unused) {
            try {
                Method declaredMethod = ObjectStreamClass.class.getDeclaredMethod("getConstructorId", Class.class);
                declaredMethod.setAccessible(true);
                int intValue = ((Integer) declaredMethod.invoke(null, Object.class)).intValue();
                Method declaredMethod2 = ObjectStreamClass.class.getDeclaredMethod("newInstance", Class.class, Integer.TYPE);
                declaredMethod2.setAccessible(true);
                r1 = new C02090Zd(declaredMethod2, intValue);
            } catch (Exception unused2) {
                try {
                    Method declaredMethod3 = ObjectInputStream.class.getDeclaredMethod("newInstance", Class.class, Class.class);
                    declaredMethod3.setAccessible(true);
                    r1 = new AnonymousClass0ZI(declaredMethod3);
                } catch (Exception unused3) {
                    r1 = new AnonymousClass0ZH();
                }
            }
        }
        this.A00 = r1;
    }

    @Override // X.AbstractC09010zC
    public final T A1q() {
        try {
            return (T) this.A00.A01(this.A02);
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder("Unable to invoke no-args constructor for ");
            sb.append(this.A03);
            sb.append(". Registering an InstanceCreator with Gson for this type may fix this problem.");
            throw new RuntimeException(sb.toString(), e);
        }
    }
}
