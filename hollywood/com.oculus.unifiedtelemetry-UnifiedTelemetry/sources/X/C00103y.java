package X;

import android.content.Context;
import android.os.Looper;
import com.facebook.debug.tracer.Tracer;
import com.facebook.systrace.Systrace;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;

/* renamed from: X.3y  reason: invalid class name and case insensitive filesystem */
public final class C00103y extends AnonymousClass8g implements AbstractC0247Xu, Qg {
    public static QF A07;
    @Nullable
    public static final Thread A08;
    public boolean A00;
    public Map<Integer, Pr> A01;
    public Map<C0475qE, Pv> A02;
    public Map<Class<? extends Annotation>, AbstractC0133Qc> A03;
    public final Context A04;
    public final AnonymousClass05 A05;
    public final ThreadLocal<QF> A06 = new Q1(this);

    @Override // X.AbstractC0247Xu
    public final C00103y getScopeUnawareInjector() {
        return this;
    }

    @Override // X.AbstractC0247Xu
    public final QF getInjectorThreadStack() {
        Thread thread = A08;
        P6.A00(thread);
        if (Thread.currentThread() == thread) {
            return A07;
        }
        return this.A06.get();
    }

    /* JADX DEBUG: Type inference failed for r0v9. Raw type applied. Possible types: X.eJ<? extends T>, X.eJ<T> */
    @Override // X.QD, X.AnonymousClass8g
    public final <T> eJ<T> getProvider(C0475qE<T> qEVar, Context context) {
        if (this.A00) {
            Pr pr = this.A01.get(Integer.valueOf(C0523tY.A00(qEVar)));
            if (pr != null) {
                return (eJ<? extends T>) pr.A04;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("No provider bound for ");
            sb.append(qEVar);
            throw new QZ(sb.toString());
        }
        throw new RuntimeException("Called injector during binding");
    }

    @Override // X.QD
    public final <T extends AbstractC0133Qc> T getScope(Class<? extends Annotation> cls) {
        AbstractC0133Qc qc = this.A03.get(cls);
        P6.A00(qc);
        return (T) qc;
    }

    @Override // X.AbstractC0247Xu
    @Deprecated
    public final AbstractC0094Hs getScopeAwareInjector() {
        AbstractC0094Hs hs;
        if (this.A00) {
            List<AbstractC0094Hs> list = getInjectorThreadStack().A02;
            if (!list.isEmpty() && (hs = list.get(list.size() - 1)) != null) {
                return hs;
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

    public C00103y(Context context, List<? extends AbstractC0243Xq> list) {
        Tracer.A00("FbInjectorImpl.init");
        try {
            this.A04 = context;
            A07 = new QF(context);
            this.A05 = new AnonymousClass05(this, context);
            if (context == context.getApplicationContext()) {
                Q2 A002 = Q3.A00(new Q3(this, list));
                this.A01 = A002.A00;
                this.A03 = A002.A03;
                this.A02 = A002.A01;
                this.A00 = true;
                return;
            }
            throw new IllegalArgumentException();
        } finally {
            Systrace.A00(32);
        }
    }

    @Override // X.QD, X.AnonymousClass8g
    public final <T> AbstractC0246Xt<T> getLazy(C0475qE<T> qEVar, Context context) {
        eJ<T> provider = getProvider(qEVar, context);
        AbstractC0094Hs scopeAwareInjector = getScopeAwareInjector();
        if (provider instanceof AbstractC0246Xt) {
            return (AbstractC0246Xt) provider;
        }
        return new C0095Ht(provider, scopeAwareInjector);
    }

    @Override // X.AbstractC0247Xu, X.AnonymousClass8g
    public final AbstractC0247Xu getApplicationInjector() {
        return this.A05;
    }

    @Override // X.QD
    public final Object getInstance(int i, Context context) {
        if (this.A00) {
            Map<Integer, Pr> map = this.A01;
            Integer valueOf = Integer.valueOf(i);
            if (!map.containsKey(valueOf)) {
                AbstractC0459mt.A00();
                new StringBuilder("No provider bound for ");
                throw null;
            }
            Pr pr = this.A01.get(valueOf);
            P6.A00(pr);
            return pr.A04.get();
        }
        throw new RuntimeException("Called injector during binding");
    }

    @Override // X.QD, X.AnonymousClass8g
    public final <T> T getInstance(C0475qE<T> qEVar, Context context) {
        return getProvider(qEVar, context).get();
    }
}
