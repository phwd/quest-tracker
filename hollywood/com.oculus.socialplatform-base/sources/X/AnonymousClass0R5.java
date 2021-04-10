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
import javax.inject.Provider;

/* renamed from: X.0R5  reason: invalid class name */
public final class AnonymousClass0R5 {
    public final AnonymousClass0VF A00;
    public final Map<Class<? extends Annotation>, AbstractC01120Rf> A01 = new LinkedHashMap();
    public final Set<Class<?>> A02 = new HashSet();
    public final List<? extends AbstractC03170la> A03;
    public final Map<Integer, AnonymousClass0Qs> A04 = new HashMap();
    public final Map<AnonymousClass14P, AnonymousClass0Qw> A05 = new HashMap();
    public final Map<AnonymousClass14P, AnonymousClass0RP> A06 = new HashMap();

    /* JADX INFO: finally extract failed */
    public static AnonymousClass0R4 A00(AnonymousClass0R5 r5) {
        Tracer.A00("FbInjectorImpl.init#modules");
        try {
            AnonymousClass1To r3 = new AnonymousClass1To();
            AnonymousClass0VF r2 = r5.A00;
            Class<?> cls = r3.getClass();
            C03260ls r0 = new C03260ls(r2, cls);
            r3.A2D(r0);
            r5.A01(r0, cls);
            Set<Class<?>> set = r5.A02;
            set.add(cls);
            Systrace.A00(32);
            return new AnonymousClass0R4(r5.A04, r5.A01, r5.A05, set, r5.A06);
        } catch (Throwable th) {
            Systrace.A00(32);
            throw th;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x00a6, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x00a7, code lost:
        r2 = r0.getCause();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00bb, code lost:
        throw ((java.lang.Throwable) java.lang.RuntimeException.class.cast(r2));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00c1, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00c2, code lost:
        com.google.common.base.Throwables.propagate(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00cc, code lost:
        throw new java.lang.RuntimeException("Redex: Unreachable code after no-return invoke");
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x00a6 A[ExcHandler: InvocationTargetException (r0v32 'e' java.lang.reflect.InvocationTargetException A[CUSTOM_DECLARE]), Splitter:B:15:0x005c] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void A01(X.AnonymousClass0Qr r10, java.lang.Class<?> r11) {
        /*
        // Method dump skipped, instructions count: 542
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0R5.A01(X.0Qr, java.lang.Class):void");
    }

    /* JADX WARN: Incorrect args count in method signature: <T:Ljava/lang/Object;>(LX/0VF;Ljava/lang/Class<*>;LX/0Qs<TT;>;Ljava/util/List<Ljava/lang/Class<*>;>;)V */
    private void A02(AnonymousClass0VF r11, Class cls, AnonymousClass0Qs r13) {
        AnonymousClass14P<T> r6 = r13.A01;
        int A002 = AnonymousClass1Tu.A00(r6);
        Provider<? extends T> provider = r13.A05;
        r13.A04 = provider;
        Map<Integer, AnonymousClass0Qs> map = this.A04;
        Integer valueOf = Integer.valueOf(A002);
        AnonymousClass0Qs r2 = map.get(valueOf);
        if (r2 != null) {
            if (A03(r2.A02) && !A03(cls)) {
                return;
            }
            if (!A03(cls) && (r2.A00 & 1) != 1) {
                throw new IllegalArgumentException(String.format("Module %s illegally overriding binding for %s from module %s. Either require module %s(base module) from %s or provide %s as a default binding so it can be overridden in module %s(top module) .", r13.A02.getCanonicalName(), r2.A01.toString(), r2.A02.getCanonicalName(), r2.A02.getCanonicalName(), r13.A02.getCanonicalName(), r2.A01.toString(), r13.A02.getCanonicalName()));
            }
        }
        if (provider instanceof AnonymousClass0VG) {
            ((AnonymousClass0VG) provider).mInjector = r11;
        }
        Class<? extends Annotation> cls2 = r13.A03;
        if (cls2 != null) {
            AbstractC01120Rf r0 = this.A01.get(cls2);
            if (r0 != null) {
                provider = r0.A9W(r6, provider);
                if (provider instanceof AnonymousClass0VG) {
                    ((AnonymousClass0VG) provider).mInjector = r11;
                }
            } else {
                StringBuilder sb = new StringBuilder("No scope registered for ");
                sb.append(cls2);
                throw new AnonymousClass0Rb(sb.toString());
            }
        }
        r13.A05 = provider;
        map.put(valueOf, r13);
    }

    public static boolean A03(Class<?> cls) {
        if (AbstractC01160Ry.class.isAssignableFrom(cls) || AnonymousClass1To.class.isAssignableFrom(cls)) {
            return true;
        }
        return false;
    }

    /* JADX WARN: Incorrect args count in method signature: (LX/0VF;Ljava/util/List<+LX/0la;>;Z)V */
    public AnonymousClass0R5(AnonymousClass0VF r2, List list) {
        this.A00 = r2;
        this.A03 = list;
    }
}
