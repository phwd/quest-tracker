package X;

import android.content.Context;
import androidx.annotation.VisibleForTesting;
import com.facebook.inject.ProviderWithInjector;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Set;
import javax.inject.Provider;

/* renamed from: X.0J3  reason: invalid class name */
public abstract class AnonymousClass0J3<T> implements ProviderWithInjector<T>, AbstractC06640p5, AnonymousClass0Qg {
    public AbstractC06640p5 mInjector;

    @Override // X.AbstractC06640p5
    public AbstractC06640p5 getApplicationInjector() {
        return this.mInjector.getApplicationInjector();
    }

    @Override // X.AbstractC06640p5
    @Deprecated
    public AnonymousClass0QF getInjectorThreadStack() {
        return this.mInjector.getInjectorThreadStack();
    }

    @Override // X.AnonymousClass0QD
    public <S> AnonymousClass0p1<S> getLazy(C09160zY<S> r2, Context context) {
        return this.mInjector.getLazy(r2, context);
    }

    @Override // X.AnonymousClass0QD
    public <T> AnonymousClass0p1<List<T>> getLazyList(C09160zY<T> r2, Context context) {
        return this.mInjector.getLazyList(r2, context);
    }

    @Override // X.AnonymousClass0QD
    public <T> AnonymousClass0p1<Set<T>> getLazySet(C09160zY<T> r2, Context context) {
        return this.mInjector.getLazySet(r2, context);
    }

    @Override // X.AnonymousClass0QD
    public <T> List<T> getList(C09160zY<T> r2, Context context) {
        return this.mInjector.getList(r2, context);
    }

    @Override // X.AnonymousClass0QD
    public <T> Provider<List<T>> getListProvider(C09160zY<T> r2, Context context) {
        return this.mInjector.getScopeAwareInjector().getListProvider(r2, context);
    }

    @Override // X.AnonymousClass0QD
    public <S> Provider<S> getProvider(C09160zY<S> r2, Context context) {
        return this.mInjector.getScopeAwareInjector().getProvider(r2, context);
    }

    @Override // X.AnonymousClass0QD
    public <T extends AbstractC01320Qc> T getScope(Class<? extends Annotation> cls) {
        return (T) this.mInjector.getScope(cls);
    }

    @Override // X.AbstractC06640p5
    @Deprecated
    public AnonymousClass0Iy getScopeAwareInjector() {
        return this.mInjector.getScopeAwareInjector();
    }

    @VisibleForTesting
    public AnonymousClass0QD getScopeAwareInjectorInternal() {
        return this.mInjector.getScopeAwareInjector();
    }

    @Override // X.AbstractC06640p5
    @Deprecated
    public AnonymousClass04R getScopeUnawareInjector() {
        return this.mInjector.getScopeUnawareInjector();
    }

    @Override // X.AnonymousClass0QD
    public <T> Set<T> getSet(C09160zY<T> r2, Context context) {
        return this.mInjector.getSet(r2, context);
    }

    @Override // X.AnonymousClass0QD
    public <T> Provider<Set<T>> getSetProvider(C09160zY<T> r2, Context context) {
        return this.mInjector.getScopeAwareInjector().getSetProvider(r2, context);
    }

    public Object getObjectForBindingId(int i) {
        AbstractC04480hn.A00();
        throw new RuntimeException(AnonymousClass006.A05("Not Implemented. This should only be called from the test frameworks, and must be explicitly overridden\nName of injector: ", getClass().getCanonicalName()));
    }

    public void setInjector(AbstractC06640p5 r1) {
        this.mInjector = r1;
    }

    public Object getInstance(int i) {
        return getInstance(i, this.mInjector.getInjectorThreadStack().A00());
    }

    @Override // X.AnonymousClass0QD
    public Object getInstance(int i, Context context) {
        return this.mInjector.getInstance(i, context);
    }

    public <S> S getInstance(C09160zY<S> r2) {
        return (S) getInstance(r2, this.mInjector.getInjectorThreadStack().A00());
    }

    @Override // X.AnonymousClass0QD
    public <S> S getInstance(C09160zY<S> r2, Context context) {
        return (S) this.mInjector.getInstance(r2, context);
    }

    public <S> S getInstance(Class<S> cls) {
        return (S) getInstance(cls, this.mInjector.getInjectorThreadStack().A00());
    }

    @Override // X.AnonymousClass0QD
    public <S> S getInstance(Class<S> cls, Context context) {
        return (S) this.mInjector.getInstance(cls, context);
    }

    public <S> S getInstance(Class<S> cls, Class<? extends Annotation> cls2) {
        return (S) getInstance(cls, cls2, this.mInjector.getInjectorThreadStack().A00());
    }

    @Override // X.AnonymousClass0QD
    public <S> S getInstance(Class<S> cls, Class<? extends Annotation> cls2, Context context) {
        return (S) this.mInjector.getInstance(cls, cls2, context);
    }
}
