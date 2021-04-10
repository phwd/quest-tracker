package X;

import android.content.Context;
import android.os.Looper;
import com.facebook.debug.tracer.Tracer;
import com.google.common.base.Preconditions;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;

/* renamed from: X.08o  reason: invalid class name and case insensitive filesystem */
public final class C007508o extends AbstractC01370Gk implements AbstractC02990bJ, AbstractC02020Rb {
    public static AnonymousClass0RA A07;
    @Nullable
    public static final Thread A08;
    public boolean A00;
    public Map<Integer, AnonymousClass0Qm> A01;
    public Map<C01440Gz, AnonymousClass0Qq> A02;
    public Map<Class<? extends Annotation>, AnonymousClass0RX> A03;
    public final Context A04;
    public final AnonymousClass00A A05;
    public final ThreadLocal<AnonymousClass0RA> A06 = new AnonymousClass0Qw(this);

    @Override // X.AbstractC02990bJ
    public final C007508o getScopeUnawareInjector() {
        return this;
    }

    @Override // X.AbstractC01370Gk, X.AbstractC02990bJ
    public final AbstractC02990bJ getApplicationInjector() {
        return this.A05;
    }

    @Override // X.AbstractC02990bJ
    public final AnonymousClass0RA getInjectorThreadStack() {
        Thread thread = A08;
        AnonymousClass0Q1.A00(thread);
        if (Thread.currentThread() == thread) {
            return A07;
        }
        return this.A06.get();
    }

    /* JADX DEBUG: Type inference failed for r0v9. Raw type applied. Possible types: X.0oz<? extends T>, X.0oz<T> */
    @Override // X.AbstractC01370Gk, X.AnonymousClass0R8
    public final <T> AbstractC07240oz<T> getProvider(C01440Gz<T> r3, Context context) {
        if (this.A00) {
            AnonymousClass0Qm r0 = this.A01.get(Integer.valueOf(AnonymousClass162.A00(r3)));
            if (r0 != null) {
                return (AbstractC07240oz<? extends T>) r0.A05;
            }
            throw new AnonymousClass0RU("No provider bound for " + r3);
        }
        throw new RuntimeException("Called injector during binding");
    }

    @Override // X.AnonymousClass0R8
    public final <T extends AnonymousClass0RX> T getScope(Class<? extends Annotation> cls) {
        AnonymousClass0RX r0 = this.A03.get(cls);
        AnonymousClass0Q1.A00(r0);
        return (T) r0;
    }

    @Override // X.AbstractC02990bJ
    @Deprecated
    public final AnonymousClass0Lf getScopeAwareInjector() {
        AnonymousClass0Lf r0;
        if (this.A00) {
            List<AnonymousClass0Lf> list = getInjectorThreadStack().A02;
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

    public C007508o(Context context, List<? extends AbstractC02970bF> list) {
        boolean z = false;
        Tracer.A01("FbInjectorImpl.init");
        try {
            this.A04 = context;
            A07 = new AnonymousClass0RA(context);
            this.A05 = new AnonymousClass00A(this, context);
            Preconditions.checkArgument(context == context.getApplicationContext() ? true : z);
            AnonymousClass0Qx A002 = AnonymousClass0Qy.A00(new AnonymousClass0Qy(this, list));
            this.A01 = A002.A00;
            this.A03 = A002.A03;
            this.A02 = A002.A01;
            this.A00 = true;
        } finally {
            Tracer.A00();
        }
    }

    @Override // X.AbstractC01370Gk, X.AnonymousClass0R8
    public final <T> AbstractC02980bI<T> getLazy(C01440Gz<T> r4, Context context) {
        AbstractC07240oz<T> provider = getProvider(r4, context);
        AnonymousClass0Lf scopeAwareInjector = getScopeAwareInjector();
        if (provider instanceof AbstractC02980bI) {
            return (AbstractC02980bI) provider;
        }
        return new C01740Lg(provider, scopeAwareInjector);
    }

    @Override // X.AnonymousClass0R8
    public final Object getInstance(int i, Context context) {
        if (this.A00) {
            Map<Integer, AnonymousClass0Qm> map = this.A01;
            Integer valueOf = Integer.valueOf(i);
            if (!map.containsKey(valueOf)) {
                AbstractC05530jm.A00();
                new StringBuilder("No provider bound for ");
                throw null;
            }
            AnonymousClass0Qm r0 = this.A01.get(valueOf);
            AnonymousClass0Q1.A00(r0);
            return r0.A05.get();
        }
        throw new RuntimeException("Called injector during binding");
    }

    @Override // X.AbstractC01370Gk, X.AnonymousClass0R8
    public final <T> T getInstance(C01440Gz<T> r2, Context context) {
        return getProvider(r2, context).get();
    }
}
