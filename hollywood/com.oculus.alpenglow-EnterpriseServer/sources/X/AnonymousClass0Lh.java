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

/* renamed from: X.0Lh  reason: invalid class name */
public abstract class AnonymousClass0Lh implements AbstractC02990bJ {
    public static Context A00;
    public static final AnonymousClass0JG<Context, AnonymousClass00A> A01 = new AnonymousClass0JG<>(new C03000bK());
    public static final Object A02 = new Object();

    public static <T> C01440Gz<List<T>> A00(C01440Gz<T> r4) {
        AbstractC01290Ga r0;
        AnonymousClass0H2 r2 = new AnonymousClass0H2(new AnonymousClass0Vu(null, List.class, r4.A01.type));
        AbstractC01290Ga r1 = r4.A00;
        Annotation annotation = r1.getAnnotation();
        Class<? extends Annotation> annotationType = r1.getAnnotationType();
        if (annotation != null) {
            r0 = C01440Gz.A00(annotation);
        } else if (annotationType != null) {
            Preconditions.checkNotNull(annotationType, "annotation type");
            if (annotationType == Named.class) {
                annotationType = com.google.inject.name.Named.class;
            }
            r0 = new AnonymousClass0Vy(annotationType, null);
        } else {
            r0 = AnonymousClass0Vx.INSTANCE;
        }
        return new C01440Gz<>(r2, r0);
    }

    public static <T> C01440Gz<Set<T>> A01(C01440Gz<T> r4) {
        AbstractC01290Ga r0;
        AnonymousClass0H2 r2 = new AnonymousClass0H2(new AnonymousClass0Vu(null, Set.class, r4.A01.type));
        AbstractC01290Ga r1 = r4.A00;
        Annotation annotation = r1.getAnnotation();
        Class<? extends Annotation> annotationType = r1.getAnnotationType();
        if (annotation != null) {
            r0 = C01440Gz.A00(annotation);
        } else if (annotationType != null) {
            Preconditions.checkNotNull(annotationType, "annotation type");
            if (annotationType == Named.class) {
                annotationType = com.google.inject.name.Named.class;
            }
            r0 = new AnonymousClass0Vy(annotationType, null);
        } else {
            r0 = AnonymousClass0Vx.INSTANCE;
        }
        return new C01440Gz<>(r2, r0);
    }

    public static <T> T A03(int i, int i2, @Nullable AnonymousClass0R7 r5) {
        if (r5 == null) {
            return null;
        }
        return (T) A02(i, i2, r5.A00, r5.A01, r5.A02);
    }

    /* JADX WARN: Incorrect args count in method signature: <T:Ljava/lang/Object;>(ILX/0Lf;)TT; */
    public static Object A04(AnonymousClass0Lf r4) {
        Object A2P = r4.A2P();
        try {
            Object A002 = AnonymousClass13m.A00(44, r4.getScopeUnawareInjector());
            r4.A2U(A2P);
            return A002;
        } catch (RuntimeException e) {
            String num = Integer.toString(44);
            AbstractC05530jm.A00();
            throw new RuntimeException(AnonymousClass006.A05("Failed to local inject class ", num), e);
        } catch (Throwable th) {
            r4.A2U(A2P);
            throw th;
        }
    }

    @DoNotStrip
    public static AnonymousClass0Lh get(Context context) {
        V A5e;
        AnonymousClass0JG<Context, AnonymousClass00A> r3 = A01;
        WeakHashMap<K, WeakReference<V>> weakHashMap = r3.A01;
        WeakReference<V> weakReference = weakHashMap.get(context);
        if (weakReference == null || (A5e = weakReference.get()) == null) {
            A5e = r3.A00.A5e(context);
            weakHashMap.put(context, new WeakReference<>(A5e));
        }
        return A5e;
    }

    public static <T> T A02(int i, int i2, byte b, AnonymousClass0Lf r10, AtomicReferenceArray atomicReferenceArray) {
        Object obj;
        T t = (T) atomicReferenceArray.get(i);
        if (t == null) {
            AnonymousClass0RZ r5 = AnonymousClass0RZ.A01.get();
            byte b2 = r5.A00;
            r5.A00 = (byte) (b | b2);
            Object A2P = r10.A2P();
            try {
                t = (T) AnonymousClass13m.A00(i2, r10.getScopeUnawareInjector());
                r10.A2U(A2P);
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
                AbstractC05530jm.A00();
                throw new RuntimeException("Failed to lazy inject class " + num + " and localId " + i, e);
            } catch (Throwable th) {
                r10.A2U(A2P);
                r5.A00 = b2;
                throw th;
            }
        }
        if (t == A02) {
            return null;
        }
        return t;
    }
}
