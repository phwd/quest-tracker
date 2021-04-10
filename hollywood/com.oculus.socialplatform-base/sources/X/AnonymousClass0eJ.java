package X;

import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

/* renamed from: X.0eJ  reason: invalid class name */
public class AnonymousClass0eJ implements AnonymousClass143<T> {
    public final AnonymousClass148 A00;
    public final /* synthetic */ AnonymousClass13k A01;
    public final /* synthetic */ Class A02;
    public final /* synthetic */ Type A03;

    public AnonymousClass0eJ(AnonymousClass13k r8, Class cls, Type type) {
        AnonymousClass148 r2;
        this.A01 = r8;
        this.A02 = cls;
        this.A03 = type;
        try {
            Class<?> cls2 = Class.forName("sun.misc.Unsafe");
            Field declaredField = cls2.getDeclaredField("theUnsafe");
            declaredField.setAccessible(true);
            r2 = new AnonymousClass0e7(cls2.getMethod("allocateInstance", Class.class), declaredField.get(null));
        } catch (Exception unused) {
            try {
                Method declaredMethod = ObjectStreamClass.class.getDeclaredMethod("getConstructorId", Class.class);
                declaredMethod.setAccessible(true);
                int intValue = ((Integer) declaredMethod.invoke(null, Object.class)).intValue();
                Method declaredMethod2 = ObjectStreamClass.class.getDeclaredMethod("newInstance", Class.class, Integer.TYPE);
                declaredMethod2.setAccessible(true);
                r2 = new AnonymousClass0e6(declaredMethod2, intValue);
            } catch (Exception unused2) {
                try {
                    Method declaredMethod3 = ObjectInputStream.class.getDeclaredMethod("newInstance", Class.class, Class.class);
                    declaredMethod3.setAccessible(true);
                    r2 = new AnonymousClass0e5(declaredMethod3);
                } catch (Exception unused3) {
                    r2 = new AnonymousClass0e4();
                }
            }
        }
        this.A00 = r2;
    }

    @Override // X.AnonymousClass143
    public final T A2F() {
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
