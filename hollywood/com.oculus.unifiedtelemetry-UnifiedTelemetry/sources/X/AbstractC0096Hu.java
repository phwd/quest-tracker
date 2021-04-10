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

/* renamed from: X.Hu  reason: case insensitive filesystem */
public abstract class AbstractC0096Hu implements AbstractC0247Xu {
    public static Context A00;
    public static final IP<Context, AnonymousClass05> A01 = new IP<>(new C0248Xv());
    public static final Object A02 = new Object();

    public static <T> C0475qE<List<T>> A00(C0475qE<T> qEVar) {
        AbstractC0476qF qFVar;
        C0474qC qCVar = new C0474qC(new Rb(null, List.class, qEVar.A01.type));
        AbstractC0476qF qFVar2 = qEVar.A00;
        Annotation annotation = qFVar2.getAnnotation();
        Class<? extends Annotation> annotationType = qFVar2.getAnnotationType();
        if (annotation != null) {
            qFVar = C0475qE.A00(annotation);
        } else if (annotationType != null) {
            if (annotationType == Named.class) {
                annotationType = com.google.inject.name.Named.class;
            }
            qFVar = new Rr(annotationType, null);
        } else {
            qFVar = Rp.INSTANCE;
        }
        return new C0475qE<>(qCVar, qFVar);
    }

    public static <T> C0475qE<Set<T>> A01(C0475qE<T> qEVar) {
        AbstractC0476qF qFVar;
        C0474qC qCVar = new C0474qC(new Rb(null, Set.class, qEVar.A01.type));
        AbstractC0476qF qFVar2 = qEVar.A00;
        Annotation annotation = qFVar2.getAnnotation();
        Class<? extends Annotation> annotationType = qFVar2.getAnnotationType();
        if (annotation != null) {
            qFVar = C0475qE.A00(annotation);
        } else if (annotationType != null) {
            if (annotationType == Named.class) {
                annotationType = com.google.inject.name.Named.class;
            }
            qFVar = new Rr(annotationType, null);
        } else {
            qFVar = Rp.INSTANCE;
        }
        return new C0475qE<>(qCVar, qFVar);
    }

    public static <T> T A03(int i, int i2, @Nullable QC qc) {
        if (qc == null) {
            return null;
        }
        return (T) A02(i, i2, qc.A00, qc.A01, qc.A02);
    }

    /* JADX WARN: Incorrect args count in method signature: <T:Ljava/lang/Object;>(ILX/Hs;)TT; */
    public static Object A04(AbstractC0094Hs hs) {
        Object A1y = hs.A1y();
        try {
            Object A002 = C0515sp.A00(51, hs.getScopeUnawareInjector());
            hs.A20(A1y);
            return A002;
        } catch (RuntimeException e) {
            String num = Integer.toString(51);
            AbstractC0459mt.A00();
            throw new RuntimeException(AnonymousClass06.A04("Failed to local inject class ", num), e);
        } catch (Throwable th) {
            hs.A20(A1y);
            throw th;
        }
    }

    @DoNotStrip
    public static AbstractC0096Hu get(Context context) {
        V A3N;
        IP<Context, AnonymousClass05> ip = A01;
        WeakHashMap<K, WeakReference<V>> weakHashMap = ip.A01;
        WeakReference<V> weakReference = weakHashMap.get(context);
        if (weakReference == null || (A3N = weakReference.get()) == null) {
            A3N = ip.A00.A3N(context);
            weakHashMap.put(context, new WeakReference<>(A3N));
        }
        return A3N;
    }

    public static <T> T A02(int i, int i2, byte b, AbstractC0094Hs hs, AtomicReferenceArray atomicReferenceArray) {
        Object obj;
        T t = (T) atomicReferenceArray.get(i);
        if (t == null) {
            Qe qe = Qe.A01.get();
            byte b2 = qe.A00;
            qe.A00 = (byte) (b | b2);
            Object A1y = hs.A1y();
            try {
                t = (T) C0515sp.A00(i2, hs.getScopeUnawareInjector());
                hs.A20(A1y);
                qe.A00 = b2;
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
                AbstractC0459mt.A00();
                StringBuilder sb = new StringBuilder();
                sb.append("Failed to lazy inject class ");
                sb.append(num);
                sb.append(" and localId ");
                sb.append(i);
                throw new RuntimeException(sb.toString(), e);
            } catch (Throwable th) {
                hs.A20(A1y);
                qe.A00 = b2;
                throw th;
            }
        }
        if (t == A02) {
            return null;
        }
        return t;
    }
}
