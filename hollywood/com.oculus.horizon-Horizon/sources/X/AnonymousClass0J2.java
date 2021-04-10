package X;

import android.content.Context;
import com.facebook.proguard.annotations.DoNotStrip;
import com.google.common.base.Preconditions;
import java.lang.annotation.Annotation;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicReferenceArray;
import javax.annotation.Nullable;
import javax.inject.Named;

/* renamed from: X.0J2  reason: invalid class name */
public abstract class AnonymousClass0J2 implements AbstractC06640p5 {
    public static Context A00;
    public static final AnonymousClass0JL<Context, AnonymousClass005> A01 = new AnonymousClass0JL<>(new AnonymousClass0p7());
    public static final Object A02 = new Object();

    public static <T> C09160zY<List<T>> A00(C09160zY<T> r5) {
        AbstractC09150zX r0;
        C09170za r3 = new C09170za(new AnonymousClass0UP(null, List.class, r5.A01.type));
        AbstractC09150zX r1 = r5.A00;
        Annotation annotation = r1.getAnnotation();
        Class<? extends Annotation> annotationType = r1.getAnnotationType();
        if (annotation != null) {
            r0 = C09160zY.A00(annotation);
        } else if (annotationType != null) {
            Preconditions.checkNotNull(annotationType, "annotation type");
            if (annotationType == Named.class) {
                annotationType = com.google.inject.name.Named.class;
            }
            return new C09160zY<>(r3, new AnonymousClass0UT(annotationType, null));
        } else {
            r0 = AnonymousClass0US.INSTANCE;
        }
        return new C09160zY<>(r3, r0);
    }

    public static <T> C09160zY<Set<T>> A01(C09160zY<T> r5) {
        AbstractC09150zX r0;
        C09170za r3 = new C09170za(new AnonymousClass0UP(null, Set.class, r5.A01.type));
        AbstractC09150zX r1 = r5.A00;
        Annotation annotation = r1.getAnnotation();
        Class<? extends Annotation> annotationType = r1.getAnnotationType();
        if (annotation != null) {
            r0 = C09160zY.A00(annotation);
        } else if (annotationType != null) {
            Preconditions.checkNotNull(annotationType, "annotation type");
            if (annotationType == Named.class) {
                annotationType = com.google.inject.name.Named.class;
            }
            return new C09160zY<>(r3, new AnonymousClass0UT(annotationType, null));
        } else {
            r0 = AnonymousClass0US.INSTANCE;
        }
        return new C09160zY<>(r3, r0);
    }

    public static <T> T A03(int i, int i2, @Nullable AnonymousClass0QC r5) {
        if (r5 == null) {
            return null;
        }
        return (T) A02(i, i2, r5.A00, r5.A01, r5.A02);
    }

    public static <T> T A04(int i, @Nullable AnonymousClass0QC r2) {
        if (r2 != null) {
            return (T) A05(i, r2.A01);
        }
        throw new C04610iW("A local injection was attempted before the constructor completed or before injectMe was called.");
    }

    @DoNotStrip
    public static AnonymousClass0J2 get(Context context) {
        V A5D;
        AnonymousClass0JL<Context, AnonymousClass005> r3 = A01;
        WeakHashMap<K, WeakReference<V>> weakHashMap = r3.A01;
        WeakReference<V> weakReference = weakHashMap.get(context);
        if (weakReference == null || (A5D = weakReference.get()) == null) {
            A5D = r3.A00.A5D(context);
            weakHashMap.put(context, new WeakReference<>(A5D));
        }
        return A5D;
    }

    public static <T> T A02(int i, int i2, byte b, AnonymousClass0Iy r10, AtomicReferenceArray atomicReferenceArray) {
        Object obj;
        T t = (T) atomicReferenceArray.get(i);
        if (t == null) {
            AnonymousClass0Qe r5 = AnonymousClass0Qe.A01.get();
            byte b2 = r5.A00;
            r5.A00 = (byte) (b | b2);
            Object A2Y = r10.A2Y();
            try {
                t = (T) AnonymousClass117.A00(i2, r10.getScopeUnawareInjector());
                r10.A2b(A2Y);
                r5.A00 = b2;
                if (t == null) {
                    obj = A02;
                } else {
                    obj = t;
                }
                if (!atomicReferenceArray.compareAndSet(i, null, obj)) {
                    t = (T) atomicReferenceArray.get(i);
                }
            } catch (RuntimeException e) {
                String num = Integer.toString(i2);
                AbstractC04480hn.A00();
                StringBuilder sb = new StringBuilder();
                sb.append("Failed to lazy inject class ");
                sb.append(num);
                sb.append(" and localId ");
                sb.append(i);
                throw new RuntimeException(sb.toString(), e);
            } catch (Throwable th) {
                r10.A2b(A2Y);
                r5.A00 = b2;
                throw th;
            }
        }
        if (t == A02) {
            return null;
        }
        return t;
    }

    public static <T> T A05(int i, AnonymousClass0Iy r5) {
        Object A2Y = r5.A2Y();
        try {
            T t = (T) AnonymousClass117.A00(i, r5.getScopeUnawareInjector());
            r5.A2b(A2Y);
            return t;
        } catch (RuntimeException e) {
            String num = Integer.toString(i);
            AbstractC04480hn.A00();
            throw new RuntimeException(AnonymousClass006.A05("Failed to local inject class ", num), e);
        } catch (Throwable th) {
            r5.A2b(A2Y);
            throw th;
        }
    }
}
