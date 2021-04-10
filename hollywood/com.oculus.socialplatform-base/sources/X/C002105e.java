package X;

import android.content.Context;
import android.os.Looper;
import com.facebook.debug.tracer.Tracer;
import com.facebook.systrace.Systrace;
import com.google.common.base.Preconditions;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import javax.inject.Provider;

/* renamed from: X.05e  reason: invalid class name and case insensitive filesystem */
public final class C002105e extends AbstractC00640Hx implements AnonymousClass0lg, AbstractC01140Ri {
    public static AnonymousClass0RH A07;
    @Nullable
    public static final Thread A08;
    public boolean A00;
    public Map<Integer, AnonymousClass0Qs> A01;
    public Map<AnonymousClass14P, AnonymousClass0Qw> A02;
    public Map<Class<? extends Annotation>, AbstractC01120Rf> A03;
    public final Context A04;
    public final AnonymousClass00A A05;
    public final ThreadLocal<AnonymousClass0RH> A06 = new AnonymousClass0R3(this);

    @Override // X.AnonymousClass0lg
    public final C002105e getScopeUnawareInjector() {
        return this;
    }

    @Override // X.AnonymousClass0lg
    public final AnonymousClass0RH getInjectorThreadStack() {
        Thread thread = A08;
        if (thread == null) {
            throw new AssertionError();
        } else if (Thread.currentThread() == thread) {
            return A07;
        } else {
            return this.A06.get();
        }
    }

    /* JADX DEBUG: Type inference failed for r0v9. Raw type applied. Possible types: javax.inject.Provider<? extends T>, javax.inject.Provider<T> */
    @Override // X.AnonymousClass0RF, X.AbstractC00640Hx
    public final <T> Provider<T> getProvider(AnonymousClass14P<T> r3, Context context) {
        if (this.A00) {
            AnonymousClass0Qs r0 = this.A01.get(Integer.valueOf(AnonymousClass1Tu.A00(r3)));
            if (r0 != null) {
                return (Provider<? extends T>) r0.A05;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("No provider bound for ");
            sb.append(r3);
            throw new AnonymousClass0Rb(sb.toString());
        }
        throw new RuntimeException("Called injector during binding");
    }

    @Override // X.AnonymousClass0RF
    public final <T extends AbstractC01120Rf> T getScope(Class<? extends Annotation> cls) {
        AbstractC01120Rf r0 = this.A03.get(cls);
        if (r0 != null) {
            return (T) r0;
        }
        throw new AssertionError();
    }

    @Override // X.AnonymousClass0lg
    @Deprecated
    public final AnonymousClass0VD getScopeAwareInjector() {
        AnonymousClass0VD r0;
        if (this.A00) {
            List<AnonymousClass0lg> list = getInjectorThreadStack().A02;
            if (!list.isEmpty() && (r0 = (AnonymousClass0VD) list.get(list.size() - 1)) != null) {
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

    public C002105e(Context context, List<? extends AbstractC03170la> list) {
        boolean z = false;
        Tracer.A00("FbInjectorImpl.init");
        try {
            Context applicationContext = context.getApplicationContext();
            this.A04 = applicationContext;
            A07 = new AnonymousClass0RH(applicationContext);
            this.A05 = new AnonymousClass00A(this, context);
            Preconditions.checkArgument(context == context.getApplicationContext() ? true : z);
            AnonymousClass0R4 A002 = AnonymousClass0R5.A00(new AnonymousClass0R5(this, list));
            this.A01 = A002.A00;
            this.A03 = A002.A03;
            this.A02 = A002.A01;
            this.A00 = true;
        } finally {
            Systrace.A00(32);
        }
    }

    @Override // X.AbstractC00640Hx, X.AnonymousClass0lg
    public final AnonymousClass0lg getApplicationInjector() {
        return this.A05;
    }

    @Override // X.AnonymousClass0RF, X.AbstractC00640Hx
    public final <T> AbstractC03180ld<T> getLazy(AnonymousClass14P<T> r4, Context context) {
        Provider<T> provider = getProvider(r4, context);
        AnonymousClass0VD scopeAwareInjector = getScopeAwareInjector();
        if (provider instanceof AbstractC03180ld) {
            return (AbstractC03180ld) provider;
        }
        return new AnonymousClass0VE(provider, scopeAwareInjector);
    }

    @Override // X.AnonymousClass0RF
    public final Object getInstance(int i, Context context) {
        if (this.A00) {
            Map<Integer, AnonymousClass0Qs> map = this.A01;
            Integer valueOf = Integer.valueOf(i);
            if (!map.containsKey(valueOf)) {
                AbstractC03380mI.A00();
                new StringBuilder("No provider bound for ");
                throw new NullPointerException("annotationStrategy");
            }
            AnonymousClass0Qs r0 = this.A01.get(valueOf);
            if (r0 != null) {
                return r0.A05.get();
            }
            throw new AssertionError();
        }
        throw new RuntimeException("Called injector during binding");
    }

    @Override // X.AnonymousClass0RF, X.AbstractC00640Hx
    public final <T> T getInstance(AnonymousClass14P<T> r2, Context context) {
        return getProvider(r2, context).get();
    }
}
