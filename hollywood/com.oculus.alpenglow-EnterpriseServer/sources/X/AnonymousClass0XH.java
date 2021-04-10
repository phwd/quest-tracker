package X;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/* renamed from: X.0XH  reason: invalid class name */
public class AnonymousClass0XH implements AbstractC01170Du<T> {
    public final /* synthetic */ AnonymousClass0Cp A00;
    public final /* synthetic */ Constructor A01;

    public AnonymousClass0XH(AnonymousClass0Cp r1, Constructor constructor) {
        this.A00 = r1;
        this.A01 = constructor;
    }

    @Override // X.AbstractC01170Du
    public final T A1o() {
        try {
            return (T) this.A01.newInstance(null);
        } catch (InstantiationException e) {
            throw new RuntimeException("Failed to invoke " + this.A01 + " with no args", e);
        } catch (InvocationTargetException e2) {
            throw new RuntimeException("Failed to invoke " + this.A01 + " with no args", e2.getTargetException());
        } catch (IllegalAccessException e3) {
            throw new AssertionError(e3);
        }
    }
}
