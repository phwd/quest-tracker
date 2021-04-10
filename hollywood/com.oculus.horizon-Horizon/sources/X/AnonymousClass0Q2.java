package X;

import com.facebook.debug.tracer.Tracer;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.inject.Provider;

/* renamed from: X.0Q2  reason: invalid class name */
public final class AnonymousClass0Q2 {
    public final AnonymousClass0J2 A00;
    public final Map<Class<? extends Annotation>, AbstractC01320Qc> A01 = new LinkedHashMap();
    public final Set<Class<?>> A02 = new HashSet();
    public final List<? extends AnonymousClass0ot> A03;
    public final Map<Integer, AnonymousClass0Pq> A04 = new HashMap();
    public final Map<C09160zY, AnonymousClass0Pu> A05 = new HashMap();
    public final Map<C09160zY, AnonymousClass0QN> A06 = new HashMap();

    /* JADX INFO: finally extract failed */
    public static AnonymousClass0Q1 A00(AnonymousClass0Q2 r5) {
        Tracer.A01("FbInjectorImpl.init#modules", 0, null);
        try {
            AnonymousClass11Q r3 = new AnonymousClass11Q();
            AnonymousClass0J2 r2 = r5.A00;
            Class<?> cls = r3.getClass();
            AnonymousClass0pK r0 = new AnonymousClass0pK(r2, cls);
            r3.A1n(r0);
            r5.A01(r0, cls);
            Set<Class<?>> set = r5.A02;
            set.add(cls);
            Tracer.A00();
            return new AnonymousClass0Q1(r5.A04, r5.A01, r5.A05, set, r5.A06);
        } catch (Throwable th) {
            Tracer.A00();
            throw th;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:0x010a, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x010b, code lost:
        com.google.common.base.Throwables.propagate(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0115, code lost:
        throw new java.lang.RuntimeException("Redex: Unreachable code after no-return invoke");
     */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x010a A[ExcHandler: IllegalAccessException (r0v48 'e' java.lang.IllegalAccessException A[CUSTOM_DECLARE]), Splitter:B:17:0x007b] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void A01(X.AnonymousClass0Pp r10, java.lang.Class<?> r11) {
        /*
        // Method dump skipped, instructions count: 495
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0Q2.A01(X.0Pp, java.lang.Class):void");
    }

    /* JADX WARN: Incorrect args count in method signature: <T:Ljava/lang/Object;>(LX/0J2;Ljava/lang/Class<*>;LX/0Pq<TT;>;Ljava/util/List<Ljava/lang/Class<*>;>;)V */
    private void A02(AnonymousClass0J2 r10, Class cls, AnonymousClass0Pq r12) {
        C09160zY<T> r6 = r12.A01;
        int A002 = AnonymousClass11P.A00(r6);
        Provider<? extends T> provider = r12.A05;
        r12.A04 = provider;
        Map<Integer, AnonymousClass0Pq> map = this.A04;
        Integer valueOf = Integer.valueOf(A002);
        AnonymousClass0Pq r1 = map.get(valueOf);
        if (r1 != null) {
            if (A03(r1.A02) && !A03(cls)) {
                return;
            }
            if (!A03(cls) && (r1.A00 & 1) != 1) {
                throw new IllegalArgumentException(String.format("Module %s illegally overriding binding for %s from module %s. Either require module %s(base module) from %s or provide %s as a default binding so it can be overridden in module %s(top module) .", r12.A02.getCanonicalName(), r1.A01.toString(), r1.A02.getCanonicalName(), r1.A02.getCanonicalName(), r12.A02.getCanonicalName(), r1.A01.toString(), r12.A02.getCanonicalName()));
            }
        }
        if (provider instanceof AnonymousClass0J3) {
            ((AnonymousClass0J3) provider).mInjector = r10;
        }
        Class<? extends Annotation> cls2 = r12.A03;
        if (cls2 != null) {
            AbstractC01320Qc r0 = this.A01.get(cls2);
            if (r0 != null) {
                provider = r0.A8U(r6, provider);
                if (provider instanceof AnonymousClass0J3) {
                    ((AnonymousClass0J3) provider).mInjector = r10;
                }
            } else {
                StringBuilder sb = new StringBuilder("No scope registered for ");
                sb.append(cls2);
                throw new AnonymousClass0QZ(sb.toString());
            }
        }
        r12.A05 = provider;
        map.put(valueOf, r12);
    }

    public static boolean A03(Class<?> cls) {
        if (AnonymousClass0Qt.class.isAssignableFrom(cls) || AnonymousClass11Q.class.isAssignableFrom(cls)) {
            return true;
        }
        return false;
    }

    /* JADX WARN: Incorrect args count in method signature: (LX/0J2;Ljava/util/List<+LX/0ot;>;Z)V */
    public AnonymousClass0Q2(AnonymousClass0J2 r2, List list) {
        this.A00 = r2;
        this.A03 = list;
    }
}
