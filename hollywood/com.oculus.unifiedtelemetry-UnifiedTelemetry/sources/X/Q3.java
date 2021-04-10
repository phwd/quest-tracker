package X;

import com.facebook.debug.tracer.Tracer;
import com.facebook.systrace.Systrace;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class Q3 {
    public final AbstractC0096Hu A00;
    public final Map<Class<? extends Annotation>, AbstractC0133Qc> A01 = new LinkedHashMap();
    public final Set<Class<?>> A02 = new HashSet();
    public final List<? extends AbstractC0243Xq> A03;
    public final Map<Integer, Pr> A04 = new HashMap();
    public final Map<C0475qE, Pv> A05 = new HashMap();
    public final Map<C0475qE, QN> A06 = new HashMap();

    /* JADX INFO: finally extract failed */
    public static Q2 A00(Q3 q3) {
        Tracer.A00("FbInjectorImpl.init#modules");
        try {
            C0520tF tFVar = new C0520tF();
            AbstractC0096Hu hu = q3.A00;
            Class<?> cls = tFVar.getClass();
            C0252Xz xz = new C0252Xz(hu, cls);
            tFVar.A1a(xz);
            q3.A01(xz, cls);
            Set<Class<?>> set = q3.A02;
            set.add(cls);
            Systrace.A00(32);
            return new Q2(q3.A04, q3.A01, q3.A05, set, q3.A06);
        } catch (Throwable th) {
            Systrace.A00(32);
            throw th;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0109, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x010c, code lost:
        if ((r1 instanceof java.lang.RuntimeException) != false) goto L_0x0118;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0117, code lost:
        throw new java.lang.RuntimeException(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0118, code lost:
        throw r1;
     */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0109 A[ExcHandler: IllegalAccessException (r1v25 'e' java.lang.IllegalAccessException A[CUSTOM_DECLARE]), Splitter:B:17:0x007b] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void A01(X.Pq r10, java.lang.Class<?> r11) {
        /*
        // Method dump skipped, instructions count: 490
        */
        throw new UnsupportedOperationException("Method not decompiled: X.Q3.A01(X.Pq, java.lang.Class):void");
    }

    /* JADX WARN: Incorrect args count in method signature: <T:Ljava/lang/Object;>(LX/Hu;Ljava/lang/Class<*>;LX/Pr<TT;>;Ljava/util/List<Ljava/lang/Class<*>;>;)V */
    private void A02(AbstractC0096Hu hu, Class cls, Pr pr) {
        int A002 = C0523tY.A00(pr.A01);
        eJ<? extends T> eJVar = pr.A04;
        pr.A03 = eJVar;
        Map<Integer, Pr> map = this.A04;
        Integer valueOf = Integer.valueOf(A002);
        Pr pr2 = map.get(valueOf);
        if (pr2 != null) {
            if (A03(pr2.A02) && !A03(cls)) {
                return;
            }
            if (!A03(cls) && (pr2.A00 & 1) != 1) {
                throw new IllegalArgumentException(String.format("Module %s illegally overriding binding for %s from module %s. Either require module %s(base module) from %s or provide %s as a default binding so it can be overridden in module %s(top module) .", pr.A02.getCanonicalName(), pr2.A01.toString(), pr2.A02.getCanonicalName(), pr2.A02.getCanonicalName(), pr.A02.getCanonicalName(), pr2.A01.toString(), pr.A02.getCanonicalName()));
            }
        }
        if (eJVar instanceof AbstractC0097Hv) {
            ((AbstractC0097Hv) eJVar).mInjector = hu;
        }
        pr.A04 = eJVar;
        map.put(valueOf, pr);
    }

    public static boolean A03(Class<?> cls) {
        if (Qt.class.isAssignableFrom(cls) || C0520tF.class.isAssignableFrom(cls)) {
            return true;
        }
        return false;
    }

    /* JADX WARN: Incorrect args count in method signature: (LX/Hu;Ljava/util/List<+LX/Xq;>;Z)V */
    public Q3(AbstractC0096Hu hu, List list) {
        this.A00 = hu;
        this.A03 = list;
    }
}
