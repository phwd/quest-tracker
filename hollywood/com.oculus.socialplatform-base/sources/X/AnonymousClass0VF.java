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

/* renamed from: X.0VF  reason: invalid class name */
public abstract class AnonymousClass0VF implements AnonymousClass0lg {
    public static Context A00;
    public static final AnonymousClass0HO<Context, AnonymousClass00A> A01 = new AnonymousClass0HO<>(new C03190lh());
    public static final Object A02 = new Object();

    public static <T> AnonymousClass14P<List<T>> A00(AnonymousClass14P<T> r5) {
        AnonymousClass14O r0;
        AnonymousClass14R r3 = new AnonymousClass14R(new AnonymousClass0cv(null, List.class, r5.A01.type));
        AnonymousClass14O r1 = r5.A00;
        Annotation annotation = r1.getAnnotation();
        Class<? extends Annotation> annotationType = r1.getAnnotationType();
        if (annotation != null) {
            r0 = AnonymousClass14P.A00(annotation);
        } else if (annotationType != null) {
            Preconditions.checkNotNull(annotationType, "annotation type");
            if (annotationType == Named.class) {
                annotationType = com.google.inject.name.Named.class;
            }
            return new AnonymousClass14P<>(r3, new AnonymousClass0cz(annotationType, null));
        } else {
            r0 = AnonymousClass0cy.INSTANCE;
        }
        return new AnonymousClass14P<>(r3, r0);
    }

    public static <T> AnonymousClass14P<Set<T>> A01(AnonymousClass14P<T> r5) {
        AnonymousClass14O r0;
        AnonymousClass14R r3 = new AnonymousClass14R(new AnonymousClass0cv(null, Set.class, r5.A01.type));
        AnonymousClass14O r1 = r5.A00;
        Annotation annotation = r1.getAnnotation();
        Class<? extends Annotation> annotationType = r1.getAnnotationType();
        if (annotation != null) {
            r0 = AnonymousClass14P.A00(annotation);
        } else if (annotationType != null) {
            Preconditions.checkNotNull(annotationType, "annotation type");
            if (annotationType == Named.class) {
                annotationType = com.google.inject.name.Named.class;
            }
            return new AnonymousClass14P<>(r3, new AnonymousClass0cz(annotationType, null));
        } else {
            r0 = AnonymousClass0cy.INSTANCE;
        }
        return new AnonymousClass14P<>(r3, r0);
    }

    public static <T> T A03(int i, int i2, @Nullable AnonymousClass0RE r5) {
        if (r5 == null) {
            return null;
        }
        return (T) A02(i, i2, r5.A00, r5.A01, r5.A02);
    }

    public static <T> T A04(int i, @Nullable AnonymousClass0RE r2) {
        if (r2 != null) {
            return (T) A05(i, r2.A01);
        }
        throw new AnonymousClass0n5("A local injection was attempted before the constructor completed or before injectMe was called.");
    }

    @DoNotStrip
    public static AnonymousClass0VF get(Context context) {
        V A6G;
        AnonymousClass0HO<Context, AnonymousClass00A> r3 = A01;
        WeakHashMap<K, WeakReference<V>> weakHashMap = r3.A01;
        WeakReference<V> weakReference = weakHashMap.get(context);
        if (weakReference == null || (A6G = weakReference.get()) == null) {
            A6G = r3.A00.A6G(context);
            weakHashMap.put(context, new WeakReference<>(A6G));
        }
        return A6G;
    }

    public static <T> T A02(int i, int i2, byte b, AnonymousClass0VD r10, AtomicReferenceArray atomicReferenceArray) {
        Object obj;
        T t = (T) atomicReferenceArray.get(i);
        if (t == null) {
            C01130Rh r5 = C01130Rh.A01.get();
            byte b2 = r5.A00;
            r5.A00 = (byte) (b | b2);
            Object A2s = r10.A2s();
            try {
                t = (T) AnonymousClass1TK.A00(i2, r10.getScopeUnawareInjector(), null);
                r10.A2u(A2s);
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
                AbstractC03380mI.A00();
                throw new RuntimeException(AnonymousClass006.A0A("Failed to lazy inject class ", num, " and localId ", i), e);
            } catch (Throwable th) {
                r10.A2u(A2s);
                r5.A00 = b2;
                throw th;
            }
        }
        if (t == A02) {
            return null;
        }
        return t;
    }

    public static <T> T A05(int i, AnonymousClass0VD r5) {
        Object A2s = r5.A2s();
        try {
            T t = (T) AnonymousClass1TK.A00(i, r5.getScopeUnawareInjector(), null);
            r5.A2u(A2s);
            return t;
        } catch (RuntimeException e) {
            String num = Integer.toString(i);
            AbstractC03380mI.A00();
            throw new RuntimeException(AnonymousClass006.A07("Failed to local inject class ", num), e);
        } catch (Throwable th) {
            r5.A2u(A2s);
            throw th;
        }
    }
}
