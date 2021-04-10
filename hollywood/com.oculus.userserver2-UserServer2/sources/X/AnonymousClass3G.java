package X;

import android.content.Context;
import android.os.Looper;
import com.facebook.debug.tracer.Tracer;
import com.facebook.inject.ApplicationScoped;
import com.facebook.inject.ContextScoped;
import com.facebook.systrace.Systrace;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

/* renamed from: X.3G  reason: invalid class name */
public final class AnonymousClass3G extends AnonymousClass8H implements SZ, PG {
    public static Op A07;
    @Nullable
    public static final Thread A08;
    public boolean A00;
    public Map<Integer, OR> A01;
    public Map<gz, OV> A02;
    public Map<Class<? extends Annotation>, PC> A03;
    public final Context A04;
    public final AnonymousClass05 A05;
    public final ThreadLocal<Op> A06 = new Ob(this);

    @Override // X.SZ
    public final AnonymousClass3G getScopeUnawareInjector() {
        return this;
    }

    @Override // X.SZ
    public final Op getInjectorThreadStack() {
        Thread thread = A08;
        if (thread == null) {
            throw new AssertionError();
        } else if (Thread.currentThread() == thread) {
            return A07;
        } else {
            return this.A06.get();
        }
    }

    /* JADX DEBUG: Type inference failed for r0v9. Raw type applied. Possible types: X.Xx<? extends T>, X.Xx<T> */
    @Override // X.On, X.AnonymousClass8H
    public final <T> AbstractC0192Xx<T> getProvider(gz<T> gzVar, Context context) {
        if (this.A00) {
            OR or = this.A01.get(0);
            if (or != null) {
                return (AbstractC0192Xx<? extends T>) or.A04;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("No provider bound for ");
            sb.append(gzVar);
            throw new P9(sb.toString());
        }
        throw new RuntimeException("Called injector during binding");
    }

    @Override // X.On
    public final <T extends PC> T getScope(Class<? extends Annotation> cls) {
        PC pc = this.A03.get(cls);
        if (pc != null) {
            return (T) pc;
        }
        throw new AssertionError();
    }

    @Override // X.SZ
    @Deprecated
    public final BX getScopeAwareInjector() {
        BX bx;
        if (this.A00) {
            List<BX> list = getInjectorThreadStack().A02;
            if (!list.isEmpty() && (bx = list.get(list.size() - 1)) != null) {
                return bx;
            }
            throw new IllegalStateException("Should never call getScopeAwareInjector without an active ThreadStack");
        }
        throw new RuntimeException("Called injector during binding");
    }

    static {
        Thread thread;
        if (Looper.getMainLooper() == null) {
            thread = null;
        } else {
            thread = Looper.getMainLooper().getThread();
        }
        A08 = thread;
    }

    /* JADX INFO: finally extract failed */
    public AnonymousClass3G(Context context, List<? extends SV> list) {
        Tracer.A00("FbInjectorImpl.init", 0, null);
        try {
            this.A04 = context;
            A07 = new Op(context);
            this.A05 = new AnonymousClass05(this, context);
            if (context == context.getApplicationContext()) {
                Od od = new Od(this, list);
                Tracer.A00("FbInjectorImpl.init#modules", 0, null);
                try {
                    AnonymousClass7z r7 = new AnonymousClass7z();
                    BZ bz = od.A00;
                    Class<?> cls = r7.getClass();
                    Se se = new Se(bz, cls);
                    r7.mBinder = se;
                    BZ bz2 = (BZ) se.A1i();
                    r7.bindScope(ContextScoped.class, new C0132Sc(bz2));
                    r7.bindScope(ApplicationScoped.class, new Sh(bz2));
                    Od.A00(od, se, cls);
                    Set<Class<?>> set = od.A05;
                    set.add(cls);
                    Systrace.A00(32);
                    Oc oc = new Oc(od.A01, od.A04, od.A02, set, od.A03);
                    this.A01 = oc.A00;
                    this.A03 = oc.A03;
                    this.A02 = oc.A01;
                    this.A00 = true;
                    Systrace.A00(32);
                } catch (Throwable th) {
                    Systrace.A00(32);
                    throw th;
                }
            } else {
                throw new IllegalArgumentException();
            }
        } catch (Throwable th2) {
            Systrace.A00(32);
            throw th2;
        }
    }

    @Override // X.SZ, X.AnonymousClass8H
    public final SZ getApplicationInjector() {
        return this.A05;
    }

    @Override // X.On, X.AnonymousClass8H
    public final <T> SY<T> getLazy(gz<T> gzVar, Context context) {
        AbstractC0192Xx<T> provider = getProvider(gzVar, context);
        BX scopeAwareInjector = getScopeAwareInjector();
        if (provider instanceof SY) {
            return (SY) provider;
        }
        return new BY(provider, scopeAwareInjector);
    }

    @Override // X.On
    public final Object getInstance(int i, Context context) {
        if (this.A00) {
            Map<Integer, OR> map = this.A01;
            Integer valueOf = Integer.valueOf(i);
            if (!map.containsKey(valueOf)) {
                PB.A00();
                new StringBuilder("No provider bound for ");
                throw null;
            }
            OR or = this.A01.get(valueOf);
            if (or != null) {
                return or.A04.get();
            }
            throw new AssertionError();
        }
        throw new RuntimeException("Called injector during binding");
    }

    @Override // X.On, X.AnonymousClass8H
    public final <T> T getInstance(gz<T> gzVar, Context context) {
        return getProvider(gzVar, context).get();
    }
}
