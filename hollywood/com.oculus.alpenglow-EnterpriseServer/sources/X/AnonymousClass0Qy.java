package X;

import com.facebook.debug.tracer.Tracer;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* renamed from: X.0Qy  reason: invalid class name */
public final class AnonymousClass0Qy {
    public final AnonymousClass0Lh A00;
    public final Map<Class<? extends Annotation>, AnonymousClass0RX> A01 = new LinkedHashMap();
    public final Set<Class<?>> A02 = new HashSet();
    public final List<? extends AbstractC02970bF> A03;
    public final Map<Integer, AnonymousClass0Qm> A04 = new HashMap();
    public final Map<C01440Gz, AnonymousClass0Qq> A05 = new HashMap();
    public final Map<C01440Gz, AnonymousClass0RI> A06 = new HashMap();

    /* JADX INFO: finally extract failed */
    public static AnonymousClass0Qx A00(AnonymousClass0Qy r5) {
        Tracer.A01("FbInjectorImpl.init#modules");
        try {
            AnonymousClass15N r3 = new AnonymousClass15N();
            AnonymousClass0Lh r2 = r5.A00;
            Class<?> cls = r3.getClass();
            AnonymousClass0bO r0 = new AnonymousClass0bO(r2, cls);
            r3.A1m(r0);
            r5.A01(r0, cls);
            Set<Class<?>> set = r5.A02;
            set.add(cls);
            Tracer.A00();
            return new AnonymousClass0Qx(r5.A04, r5.A01, r5.A05, set, r5.A06);
        } catch (Throwable th) {
            Tracer.A00();
            throw th;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0115, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0118, code lost:
        if ((r1 instanceof java.lang.RuntimeException) != false) goto L_0x0124;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0123, code lost:
        throw new java.lang.RuntimeException(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x0124, code lost:
        throw r1;
     */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0115 A[ExcHandler: IllegalAccessException (r1v25 'e' java.lang.IllegalAccessException A[CUSTOM_DECLARE]), Splitter:B:17:0x007b] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void A01(X.AnonymousClass0Ql r10, java.lang.Class<?> r11) {
        /*
        // Method dump skipped, instructions count: 504
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0Qy.A01(X.0Ql, java.lang.Class):void");
    }

    /* JADX WARN: Incorrect args count in method signature: <T:Ljava/lang/Object;>(LX/0Lh;Ljava/lang/Class<*>;LX/0Qm<TT;>;Ljava/util/List<Ljava/lang/Class<*>;>;)V */
    private void A02(AnonymousClass0Lh r11, Class cls, AnonymousClass0Qm r13) {
        C01440Gz<T> r6 = r13.A01;
        int A002 = AnonymousClass162.A00(r6);
        AbstractC07240oz<? extends T> r5 = r13.A05;
        r13.A04 = r5;
        Map<Integer, AnonymousClass0Qm> map = this.A04;
        Integer valueOf = Integer.valueOf(A002);
        AnonymousClass0Qm r2 = map.get(valueOf);
        if (r2 != null) {
            if (A03(r2.A02) && !A03(cls)) {
                return;
            }
            if (!A03(cls) && (r2.A00 & 1) != 1) {
                throw new IllegalArgumentException(String.format("Module %s illegally overriding binding for %s from module %s. Either require module %s(base module) from %s or provide %s as a default binding so it can be overridden in module %s(top module) .", r13.A02.getCanonicalName(), r2.A01.toString(), r2.A02.getCanonicalName(), r2.A02.getCanonicalName(), r13.A02.getCanonicalName(), r2.A01.toString(), r13.A02.getCanonicalName()));
            }
        }
        if (r5 instanceof AnonymousClass0Li) {
            ((AnonymousClass0Li) r5).mInjector = r11;
        }
        Class<? extends Annotation> cls2 = r13.A03;
        if (cls2 != null) {
            AnonymousClass0RX r0 = this.A01.get(cls2);
            if (r0 != null) {
                r5 = r0.A7Z(r6, r5);
                if (r5 instanceof AnonymousClass0Li) {
                    ((AnonymousClass0Li) r5).mInjector = r11;
                }
            } else {
                throw new AnonymousClass0RU("No scope registered for " + cls2);
            }
        }
        r13.A05 = r5;
        map.put(valueOf, r13);
    }

    public static boolean A03(Class<?> cls) {
        if (AnonymousClass0Ro.class.isAssignableFrom(cls) || AnonymousClass15N.class.isAssignableFrom(cls)) {
            return true;
        }
        return false;
    }

    /* JADX WARN: Incorrect args count in method signature: (LX/0Lh;Ljava/util/List<+LX/0bF;>;Z)V */
    public AnonymousClass0Qy(AnonymousClass0Lh r2, List list) {
        this.A00 = r2;
        this.A03 = list;
    }
}
