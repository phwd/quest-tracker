package X;

import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

/* renamed from: X.Ts  reason: case insensitive filesystem */
public class C0173Ts implements VE<T> {
    public final Zk A00;
    public final /* synthetic */ TW A01;
    public final /* synthetic */ Class A02;
    public final /* synthetic */ Type A03;

    public C0173Ts(TW tw, Class cls, Type type) {
        Zk zk;
        this.A01 = tw;
        this.A02 = cls;
        this.A03 = type;
        try {
            Class<?> cls2 = Class.forName("sun.misc.Unsafe");
            Field declaredField = cls2.getDeclaredField("theUnsafe");
            declaredField.setAccessible(true);
            zk = new Tg(cls2.getMethod("allocateInstance", Class.class), declaredField.get(null));
        } catch (Exception unused) {
            try {
                Method declaredMethod = ObjectStreamClass.class.getDeclaredMethod("getConstructorId", Class.class);
                declaredMethod.setAccessible(true);
                int intValue = ((Integer) declaredMethod.invoke(null, Object.class)).intValue();
                Method declaredMethod2 = ObjectStreamClass.class.getDeclaredMethod("newInstance", Class.class, Integer.TYPE);
                declaredMethod2.setAccessible(true);
                zk = new C0167Tf(declaredMethod2, intValue);
            } catch (Exception unused2) {
                try {
                    Method declaredMethod3 = ObjectInputStream.class.getDeclaredMethod("newInstance", Class.class, Class.class);
                    declaredMethod3.setAccessible(true);
                    zk = new Te(declaredMethod3);
                } catch (Exception unused3) {
                    zk = new C0166Td();
                }
            }
        }
        this.A00 = zk;
    }

    @Override // X.VE
    public final T A1b() {
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
