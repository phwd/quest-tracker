package X;

import android.content.Context;
import com.facebook.inject.AssistedProviderWithInjector;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Set;
import javax.inject.Provider;

/* renamed from: X.0JA  reason: invalid class name */
public class AnonymousClass0JA<T> implements AbstractC06640p5, AssistedProviderWithInjector<T> {
    public AbstractC06640p5 mInjector;

    @Override // X.AbstractC06640p5
    public final AbstractC06640p5 getApplicationInjector() {
        return this.mInjector.getApplicationInjector();
    }

    @Override // X.AbstractC06640p5
    @Deprecated
    public final AnonymousClass0QF getInjectorThreadStack() {
        return this.mInjector.getInjectorThreadStack();
    }

    @Override // X.AnonymousClass0QD
    public final <S> AnonymousClass0p1<S> getLazy(C09160zY<S> r2, Context context) {
        return this.mInjector.getLazy(r2, context);
    }

    @Override // X.AnonymousClass0QD
    public final <T> AnonymousClass0p1<List<T>> getLazyList(C09160zY<T> r2, Context context) {
        return this.mInjector.getLazyList(r2, context);
    }

    @Override // X.AnonymousClass0QD
    public final <T> AnonymousClass0p1<Set<T>> getLazySet(C09160zY<T> r2, Context context) {
        return this.mInjector.getLazySet(r2, context);
    }

    @Override // X.AnonymousClass0QD
    public final <T> List<T> getList(C09160zY<T> r2, Context context) {
        return this.mInjector.getList(r2, context);
    }

    @Override // X.AnonymousClass0QD
    public final <T> Provider<List<T>> getListProvider(C09160zY<T> r2, Context context) {
        return this.mInjector.getScopeAwareInjector().getListProvider(r2, context);
    }

    @Override // X.AnonymousClass0QD
    public final <S> Provider<S> getProvider(C09160zY<S> r2, Context context) {
        return this.mInjector.getScopeAwareInjector().getProvider(r2, context);
    }

    @Override // X.AnonymousClass0QD
    public final <T extends AbstractC01320Qc> T getScope(Class<? extends Annotation> cls) {
        return (T) this.mInjector.getScope(cls);
    }

    @Override // X.AbstractC06640p5
    @Deprecated
    public final AnonymousClass0Iy getScopeAwareInjector() {
        return this.mInjector.getScopeAwareInjector();
    }

    @Override // X.AbstractC06640p5
    @Deprecated
    public final AnonymousClass04R getScopeUnawareInjector() {
        return this.mInjector.getScopeUnawareInjector();
    }

    @Override // X.AnonymousClass0QD
    public final <T> Set<T> getSet(C09160zY<T> r2, Context context) {
        return this.mInjector.getSet(r2, context);
    }

    @Override // X.AnonymousClass0QD
    public final <T> Provider<Set<T>> getSetProvider(C09160zY<T> r2, Context context) {
        return this.mInjector.getScopeAwareInjector().getSetProvider(r2, context);
    }

    public AnonymousClass0JA() {
    }

    public AnonymousClass0JA(AbstractC06640p5 r2) {
        this.mInjector = (AbstractC06640p5) r2.getScopeAwareInjector();
    }

    @Override // X.AnonymousClass0QD
    public final Object getInstance(int i, Context context) {
        return this.mInjector.getInstance(i, context);
    }

    @Override // X.AnonymousClass0QD
    public final <S> S getInstance(C09160zY<S> r2, Context context) {
        return (S) this.mInjector.getInstance(r2, context);
    }

    @Override // X.AnonymousClass0QD
    public final <S> S getInstance(Class<S> cls, Context context) {
        return (S) this.mInjector.getInstance(cls, context);
    }

    @Override // X.AnonymousClass0QD
    public final <S> S getInstance(Class<S> cls, Class<? extends Annotation> cls2, Context context) {
        return (S) this.mInjector.getInstance(cls, cls2, context);
    }
}
