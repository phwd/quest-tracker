package X;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/* renamed from: X.0eI  reason: invalid class name */
public class AnonymousClass0eI implements AnonymousClass143<T> {
    public final /* synthetic */ AnonymousClass13k A00;
    public final /* synthetic */ Constructor A01;

    public AnonymousClass0eI(AnonymousClass13k r1, Constructor constructor) {
        this.A00 = r1;
        this.A01 = constructor;
    }

    @Override // X.AnonymousClass143
    public final T A2F() {
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
