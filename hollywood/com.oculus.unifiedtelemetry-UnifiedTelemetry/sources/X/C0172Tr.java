package X;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/* renamed from: X.Tr  reason: case insensitive filesystem */
public class C0172Tr implements VE<T> {
    public final /* synthetic */ TW A00;
    public final /* synthetic */ Constructor A01;

    public C0172Tr(TW tw, Constructor constructor) {
        this.A00 = tw;
        this.A01 = constructor;
    }

    @Override // X.VE
    public final T A1b() {
        try {
            return (T) this.A01.newInstance(null);
        } catch (InstantiationException e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Failed to invoke ");
            sb.append(this.A01);
            sb.append(" with no args");
            throw new RuntimeException(sb.toString(), e);
        } catch (InvocationTargetException e2) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Failed to invoke ");
            sb2.append(this.A01);
            sb2.append(" with no args");
            throw new RuntimeException(sb2.toString(), e2.getTargetException());
        } catch (IllegalAccessException e3) {
            throw new AssertionError(e3);
        }
    }
}
