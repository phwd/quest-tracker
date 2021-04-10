package X;

import android.annotation.SuppressLint;
import com.facebook.inject.AssistedProvider;
import java.lang.reflect.Constructor;
import javax.annotation.Nullable;

@SuppressLint({"ExplicitComplexProvider", "FbInjectorGet"})
/* renamed from: X.090  reason: invalid class name */
public final class AnonymousClass090<T> extends AnonymousClass0J3<AssistedProvider<T>> {
    public final Class<AssistedProvider<T>> A00;
    @Nullable
    public volatile Constructor<AssistedProvider<T>> A01;

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0040, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0041, code lost:
        com.google.common.base.Throwables.propagate(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x004b, code lost:
        throw new java.lang.RuntimeException("Redex: Unreachable code after no-return invoke");
     */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0040 A[ExcHandler: IllegalAccessException | InstantiationException | InvocationTargetException (r0v4 'e' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:6:0x0018] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object get() {
        /*
            r4 = this;
            java.lang.reflect.Constructor<com.facebook.inject.AssistedProvider<T>> r3 = r4.A01
            if (r3 != 0) goto L_0x0031
            java.lang.Class<com.facebook.inject.AssistedProvider<T>> r3 = r4.A00     // Catch:{ NoSuchMethodException -> 0x0015 }
            r0 = 1
            java.lang.Class[] r2 = new java.lang.Class[r0]     // Catch:{ NoSuchMethodException -> 0x0015 }
            r1 = 0
            java.lang.Class<X.0p5> r0 = X.AbstractC06640p5.class
            r2[r1] = r0     // Catch:{ NoSuchMethodException -> 0x0015 }
            java.lang.reflect.Constructor r3 = r3.getDeclaredConstructor(r2)     // Catch:{ NoSuchMethodException -> 0x0015 }
            r4.A01 = r3     // Catch:{ NoSuchMethodException -> 0x0015 }
            goto L_0x0031
        L_0x0015:
            r2 = move-exception
            java.lang.String r0 = "Assisted provider "
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ IllegalAccessException | InstantiationException | InvocationTargetException -> 0x0040, IllegalAccessException | InstantiationException | InvocationTargetException -> 0x0040 }
            r1.<init>(r0)     // Catch:{ IllegalAccessException | InstantiationException | InvocationTargetException -> 0x0040, IllegalAccessException | InstantiationException | InvocationTargetException -> 0x0040 }
            java.lang.Class<com.facebook.inject.AssistedProvider<T>> r0 = r4.A00     // Catch:{ IllegalAccessException | InstantiationException | InvocationTargetException -> 0x0040, IllegalAccessException | InstantiationException | InvocationTargetException -> 0x0040 }
            r1.append(r0)     // Catch:{ IllegalAccessException | InstantiationException | InvocationTargetException -> 0x0040, IllegalAccessException | InstantiationException | InvocationTargetException -> 0x0040 }
            java.lang.String r0 = " doesn't have default constructor."
            r1.append(r0)     // Catch:{ IllegalAccessException | InstantiationException | InvocationTargetException -> 0x0040, IllegalAccessException | InstantiationException | InvocationTargetException -> 0x0040 }
            java.lang.String r1 = r1.toString()     // Catch:{ IllegalAccessException | InstantiationException | InvocationTargetException -> 0x0040, IllegalAccessException | InstantiationException | InvocationTargetException -> 0x0040 }
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException     // Catch:{ IllegalAccessException | InstantiationException | InvocationTargetException -> 0x0040, IllegalAccessException | InstantiationException | InvocationTargetException -> 0x0040 }
            r0.<init>(r1, r2)     // Catch:{ IllegalAccessException | InstantiationException | InvocationTargetException -> 0x0040, IllegalAccessException | InstantiationException | InvocationTargetException -> 0x0040 }
            throw r0     // Catch:{ IllegalAccessException | InstantiationException | InvocationTargetException -> 0x0040, IllegalAccessException | InstantiationException | InvocationTargetException -> 0x0040 }
        L_0x0031:
            r0 = 1
            java.lang.Object[] r2 = new java.lang.Object[r0]     // Catch:{ IllegalAccessException | InstantiationException | InvocationTargetException -> 0x0040, IllegalAccessException | InstantiationException | InvocationTargetException -> 0x0040 }
            r1 = 0
            X.0Iy r0 = r4.getScopeAwareInjector()     // Catch:{ IllegalAccessException | InstantiationException | InvocationTargetException -> 0x0040, IllegalAccessException | InstantiationException | InvocationTargetException -> 0x0040 }
            r2[r1] = r0     // Catch:{ IllegalAccessException | InstantiationException | InvocationTargetException -> 0x0040, IllegalAccessException | InstantiationException | InvocationTargetException -> 0x0040 }
            java.lang.Object r0 = r3.newInstance(r2)     // Catch:{ IllegalAccessException | InstantiationException | InvocationTargetException -> 0x0040, IllegalAccessException | InstantiationException | InvocationTargetException -> 0x0040 }
            return r0
        L_0x0040:
            r0 = move-exception
            com.google.common.base.Throwables.propagate(r0)
            java.lang.String r1 = "Redex: Unreachable code after no-return invoke"
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass090.get():java.lang.Object");
    }

    public AnonymousClass090(Class<AssistedProvider<T>> cls) {
        this.A00 = cls;
    }
}
