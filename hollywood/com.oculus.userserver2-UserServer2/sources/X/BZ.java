package X;

import android.content.Context;
import com.facebook.proguard.annotations.DoNotStrip;
import java.lang.annotation.Annotation;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicReferenceArray;
import javax.annotation.Nullable;
import javax.inject.Named;

public abstract class BZ implements SZ {
    public static Context A00;
    public static final Object A01 = new Object();
    public static final Il<Context, AnonymousClass05> A02 = new Il<>(new C0131Sa());

    public static <T> gz<List<T>> A00(gz<T> gzVar) {
        h0 h0Var;
        gx gxVar = new gx(new K7(null, List.class, gzVar.A01.type));
        h0 h0Var2 = gzVar.A00;
        Annotation annotation = h0Var2.getAnnotation();
        Class<? extends Annotation> annotationType = h0Var2.getAnnotationType();
        if (annotation != null) {
            h0Var = gz.A00(annotation);
        } else if (annotationType != null) {
            if (annotationType == Named.class) {
                annotationType = com.google.inject.name.Named.class;
            }
            h0Var = new KB(annotationType, null);
        } else {
            h0Var = KA.INSTANCE;
        }
        return new gz<>(gxVar, h0Var);
    }

    public static <T> gz<Set<T>> A01(gz<T> gzVar) {
        h0 h0Var;
        gx gxVar = new gx(new K7(null, Set.class, gzVar.A01.type));
        h0 h0Var2 = gzVar.A00;
        Annotation annotation = h0Var2.getAnnotation();
        Class<? extends Annotation> annotationType = h0Var2.getAnnotationType();
        if (annotation != null) {
            h0Var = gz.A00(annotation);
        } else if (annotationType != null) {
            if (annotationType == Named.class) {
                annotationType = com.google.inject.name.Named.class;
            }
            h0Var = new KB(annotationType, null);
        } else {
            h0Var = KA.INSTANCE;
        }
        return new gz<>(gxVar, h0Var);
    }

    public static <T> T A02(int i, int i2, @Nullable Om om) {
        Object obj;
        if (om == null) {
            return null;
        }
        byte b = om.A00;
        BX bx = om.A01;
        AtomicReferenceArray atomicReferenceArray = om.A02;
        T t = (T) atomicReferenceArray.get(i);
        if (t == null) {
            PE pe = PE.A01.get();
            byte b2 = pe.A00;
            pe.A00 = (byte) (b | b2);
            Object A1U = bx.A1U();
            try {
                t = (T) IX.A00(i2, bx.getScopeUnawareInjector());
                bx.A1W(A1U);
                pe.A00 = b2;
                if (t == null) {
                    obj = A01;
                } else {
                    obj = t;
                }
                if (!atomicReferenceArray.compareAndSet(i, null, obj)) {
                    t = (T) atomicReferenceArray.get(i);
                }
            } catch (RuntimeException e) {
                String num = Integer.toString(i2);
                PB.A00();
                StringBuilder sb = new StringBuilder();
                sb.append("Failed to lazy inject class ");
                sb.append(num);
                sb.append(" and localId ");
                sb.append(i);
                throw new RuntimeException(sb.toString(), e);
            } catch (Throwable th) {
                bx.A1W(A1U);
                pe.A00 = b2;
                throw th;
            }
        }
        if (t == A01) {
            return null;
        }
        return t;
    }

    public static <T> T A03(int i, @Nullable Om om) {
        if (om == null) {
            throw new JT("A local injection was attempted before the constructor completed or before injectMe was called.");
        }
        BX bx = om.A01;
        Object A1U = bx.A1U();
        try {
            T t = (T) IX.A00(i, bx.getScopeUnawareInjector());
            bx.A1W(A1U);
            return t;
        } catch (RuntimeException e) {
            String num = Integer.toString(i);
            PB.A00();
            throw new RuntimeException(AnonymousClass06.A03("Failed to local inject class ", num), e);
        } catch (Throwable th) {
            bx.A1W(A1U);
            throw th;
        }
    }

    @DoNotStrip
    public static BZ get(Context context) {
        V A2H;
        Il<Context, AnonymousClass05> il = A02;
        WeakHashMap<K, WeakReference<V>> weakHashMap = il.A01;
        WeakReference<V> weakReference = weakHashMap.get(context);
        if (weakReference == null || (A2H = weakReference.get()) == null) {
            A2H = il.A00.A2H(context);
            weakHashMap.put(context, new WeakReference<>(A2H));
        }
        return A2H;
    }
}
