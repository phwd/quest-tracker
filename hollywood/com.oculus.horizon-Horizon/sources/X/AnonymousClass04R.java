package X;

import android.content.Context;
import android.os.Looper;
import com.facebook.debug.tracer.Tracer;
import com.google.common.base.Preconditions;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import javax.inject.Provider;

/* renamed from: X.04R  reason: invalid class name */
public final class AnonymousClass04R extends AnonymousClass092 implements AbstractC06640p5, AnonymousClass0Qg {
    public static AnonymousClass0QF A07;
    @Nullable
    public static final Thread A08;
    public boolean A00;
    public Map<Integer, AnonymousClass0Pq> A01;
    public Map<C09160zY, AnonymousClass0Pu> A02;
    public Map<Class<? extends Annotation>, AbstractC01320Qc> A03;
    public final Context A04;
    public final AnonymousClass005 A05;
    public final ThreadLocal<AnonymousClass0QF> A06 = new AnonymousClass0Q0(this);

    @Override // X.AbstractC06640p5
    public final AnonymousClass04R getScopeUnawareInjector() {
        return this;
    }

    @Override // X.AbstractC06640p5
    public final AnonymousClass0QF getInjectorThreadStack() {
        Thread thread = A08;
        AnonymousClass0P5.A00(thread);
        if (Thread.currentThread() == thread) {
            return A07;
        }
        return this.A06.get();
    }

    /* JADX DEBUG: Type inference failed for r0v9. Raw type applied. Possible types: javax.inject.Provider<? extends T>, javax.inject.Provider<T> */
    @Override // X.AnonymousClass0QD, X.AnonymousClass092
    public final <T> Provider<T> getProvider(C09160zY<T> r3, Context context) {
        if (this.A00) {
            AnonymousClass0Pq r0 = this.A01.get(Integer.valueOf(AnonymousClass11P.A00(r3)));
            if (r0 != null) {
                return (Provider<? extends T>) r0.A05;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("No provider bound for ");
            sb.append(r3);
            throw new AnonymousClass0QZ(sb.toString());
        }
        throw new RuntimeException("Called injector during binding");
    }

    @Override // X.AnonymousClass0QD
    public final <T extends AbstractC01320Qc> T getScope(Class<? extends Annotation> cls) {
        AbstractC01320Qc r0 = this.A03.get(cls);
        AnonymousClass0P5.A00(r0);
        return (T) r0;
    }

    @Override // X.AbstractC06640p5
    @Deprecated
    public final AnonymousClass0Iy getScopeAwareInjector() {
        AnonymousClass0Iy r0;
        if (this.A00) {
            List<AnonymousClass0Iy> list = getInjectorThreadStack().A02;
            if (!list.isEmpty() && (r0 = list.get(list.size() - 1)) != null) {
                return r0;
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

    public AnonymousClass04R(Context context, List<? extends AnonymousClass0ot> list) {
        boolean z = false;
        Tracer.A01("FbInjectorImpl.init", 0, null);
        try {
            this.A04 = context;
            A07 = new AnonymousClass0QF(context);
            this.A05 = new AnonymousClass005(this, context);
            Preconditions.checkArgument(context == context.getApplicationContext() ? true : z);
            AnonymousClass0Q1 A002 = AnonymousClass0Q2.A00(new AnonymousClass0Q2(this, list));
            this.A01 = A002.A00;
            this.A03 = A002.A03;
            this.A02 = A002.A01;
            this.A00 = true;
        } finally {
            Tracer.A00();
        }
    }

    @Override // X.AbstractC06640p5, X.AnonymousClass092
    public final AbstractC06640p5 getApplicationInjector() {
        return this.A05;
    }

    @Override // X.AnonymousClass0QD, X.AnonymousClass092
    public final <T> AnonymousClass0p1<T> getLazy(C09160zY<T> r4, Context context) {
        Provider<T> provider = getProvider(r4, context);
        AnonymousClass0Iy scopeAwareInjector = getScopeAwareInjector();
        if (provider instanceof AnonymousClass0p1) {
            return (AnonymousClass0p1) provider;
        }
        return new C01030Iz(provider, scopeAwareInjector);
    }

    @Override // X.AnonymousClass0QD
    public final Object getInstance(int i, Context context) {
        if (this.A00) {
            Map<Integer, AnonymousClass0Pq> map = this.A01;
            Integer valueOf = Integer.valueOf(i);
            if (!map.containsKey(valueOf)) {
                AbstractC04480hn.A00();
                new StringBuilder("No provider bound for ");
                throw null;
            }
            AnonymousClass0Pq r0 = this.A01.get(valueOf);
            AnonymousClass0P5.A00(r0);
            return r0.A05.get();
        }
        throw new RuntimeException("Called injector during binding");
    }

    @Override // X.AnonymousClass0QD, X.AnonymousClass092
    public final <T> T getInstance(C09160zY<T> r2, Context context) {
        return getProvider(r2, context).get();
    }
}
