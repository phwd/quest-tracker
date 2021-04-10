package X;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/* renamed from: X.0bZ  reason: invalid class name and case insensitive filesystem */
public class C02810bZ implements AbstractC09010zC<T> {
    public final /* synthetic */ C08920yx A00;
    public final /* synthetic */ Constructor A01;

    public C02810bZ(C08920yx r1, Constructor constructor) {
        this.A00 = r1;
        this.A01 = constructor;
    }

    @Override // X.AbstractC09010zC
    public final T A1q() {
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
