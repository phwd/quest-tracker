package X;

import android.annotation.SuppressLint;
import com.facebook.inject.AssistedProvider;
import java.lang.reflect.Constructor;
import javax.annotation.Nullable;

@SuppressLint({"ExplicitComplexProvider", "FbInjectorGet"})
/* renamed from: X.8F  reason: invalid class name */
public final class AnonymousClass8F<T> extends AbstractC0029Ba<AssistedProvider<T>> {
    public final Class<AssistedProvider<T>> A00;
    @Nullable
    public volatile Constructor<AssistedProvider<T>> A01;

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x003c, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x003f, code lost:
        if ((r1 instanceof java.lang.RuntimeException) != false) goto L_0x004b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x004a, code lost:
        throw new java.lang.RuntimeException(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x004b, code lost:
        throw r1;
     */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x003c A[ExcHandler: IllegalAccessException | InstantiationException | InvocationTargetException (r1v2 'e' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:6:0x0016] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object get() {
        /*
            r3 = this;
            java.lang.reflect.Constructor<com.facebook.inject.AssistedProvider<T>> r1 = r3.A01
            if (r1 != 0) goto L_0x002f
            java.lang.Class<com.facebook.inject.AssistedProvider<T>> r1 = r3.A00     // Catch:{ NoSuchMethodException -> 0x0013 }
            java.lang.Class<X.SZ> r0 = X.SZ.class
            java.lang.Class[] r0 = new java.lang.Class[]{r0}     // Catch:{ NoSuchMethodException -> 0x0013 }
            java.lang.reflect.Constructor r1 = r1.getDeclaredConstructor(r0)     // Catch:{ NoSuchMethodException -> 0x0013 }
            r3.A01 = r1     // Catch:{ NoSuchMethodException -> 0x0013 }
            goto L_0x002f
        L_0x0013:
            r2 = move-exception
            java.lang.String r0 = "Assisted provider "
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ IllegalAccessException | InstantiationException | InvocationTargetException -> 0x003c, IllegalAccessException | InstantiationException | InvocationTargetException -> 0x003c }
            r1.<init>(r0)     // Catch:{ IllegalAccessException | InstantiationException | InvocationTargetException -> 0x003c, IllegalAccessException | InstantiationException | InvocationTargetException -> 0x003c }
            java.lang.Class<com.facebook.inject.AssistedProvider<T>> r0 = r3.A00     // Catch:{ IllegalAccessException | InstantiationException | InvocationTargetException -> 0x003c, IllegalAccessException | InstantiationException | InvocationTargetException -> 0x003c }
            r1.append(r0)     // Catch:{ IllegalAccessException | InstantiationException | InvocationTargetException -> 0x003c, IllegalAccessException | InstantiationException | InvocationTargetException -> 0x003c }
            java.lang.String r0 = " doesn't have default constructor."
            r1.append(r0)     // Catch:{ IllegalAccessException | InstantiationException | InvocationTargetException -> 0x003c, IllegalAccessException | InstantiationException | InvocationTargetException -> 0x003c }
            java.lang.String r1 = r1.toString()     // Catch:{ IllegalAccessException | InstantiationException | InvocationTargetException -> 0x003c, IllegalAccessException | InstantiationException | InvocationTargetException -> 0x003c }
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException     // Catch:{ IllegalAccessException | InstantiationException | InvocationTargetException -> 0x003c, IllegalAccessException | InstantiationException | InvocationTargetException -> 0x003c }
            r0.<init>(r1, r2)     // Catch:{ IllegalAccessException | InstantiationException | InvocationTargetException -> 0x003c, IllegalAccessException | InstantiationException | InvocationTargetException -> 0x003c }
            throw r0     // Catch:{ IllegalAccessException | InstantiationException | InvocationTargetException -> 0x003c, IllegalAccessException | InstantiationException | InvocationTargetException -> 0x003c }
        L_0x002f:
            X.BX r0 = r3.getScopeAwareInjector()     // Catch:{ IllegalAccessException | InstantiationException | InvocationTargetException -> 0x003c, IllegalAccessException | InstantiationException | InvocationTargetException -> 0x003c }
            java.lang.Object[] r0 = new java.lang.Object[]{r0}     // Catch:{ IllegalAccessException | InstantiationException | InvocationTargetException -> 0x003c, IllegalAccessException | InstantiationException | InvocationTargetException -> 0x003c }
            r1.newInstance(r0)     // Catch:{ IllegalAccessException | InstantiationException | InvocationTargetException -> 0x003c, IllegalAccessException | InstantiationException | InvocationTargetException -> 0x003c }
            r0 = 0
            return r0
        L_0x003c:
            r1 = move-exception
            boolean r0 = r1 instanceof java.lang.RuntimeException
            if (r0 != 0) goto L_0x004b
            boolean r0 = r1 instanceof java.lang.Error
            if (r0 != 0) goto L_0x004b
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            r0.<init>(r1)
            throw r0
        L_0x004b:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass8F.get():java.lang.Object");
    }

    public AnonymousClass8F(Class<AssistedProvider<T>> cls) {
        this.A00 = cls;
    }
}