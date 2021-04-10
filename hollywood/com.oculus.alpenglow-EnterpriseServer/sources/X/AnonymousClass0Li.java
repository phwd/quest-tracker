package X;

import android.content.Context;
import androidx.annotation.VisibleForTesting;
import com.facebook.inject.ProviderWithInjector;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Set;

/* renamed from: X.0Li  reason: invalid class name */
public abstract class AnonymousClass0Li<T> implements ProviderWithInjector<T>, AbstractC02990bJ, AbstractC02020Rb {
    public AbstractC02990bJ mInjector;

    @Override // X.AbstractC02990bJ
    public AbstractC02990bJ getApplicationInjector() {
        return this.mInjector.getApplicationInjector();
    }

    @Override // X.AbstractC02990bJ
    @Deprecated
    public AnonymousClass0RA getInjectorThreadStack() {
        return this.mInjector.getInjectorThreadStack();
    }

    @Override // X.AnonymousClass0R8
    public <S> AbstractC02980bI<S> getLazy(C01440Gz<S> r2, Context context) {
        return this.mInjector.getLazy(r2, context);
    }

    @Override // X.AnonymousClass0R8
    public <T> AbstractC02980bI<List<T>> getLazyList(C01440Gz<T> r2, Context context) {
        return this.mInjector.getLazyList(r2, context);
    }

    @Override // X.AnonymousClass0R8
    public <T> AbstractC02980bI<Set<T>> getLazySet(C01440Gz<T> r2, Context context) {
        return this.mInjector.getLazySet(r2, context);
    }

    @Override // X.AnonymousClass0R8
    public <T> List<T> getList(C01440Gz<T> r2, Context context) {
        return this.mInjector.getList(r2, context);
    }

    @Override // X.AnonymousClass0R8
    public <T> AbstractC07240oz<List<T>> getListProvider(C01440Gz<T> r2, Context context) {
        return this.mInjector.getScopeAwareInjector().getListProvider(r2, context);
    }

    @Override // X.AnonymousClass0R8
    public <S> AbstractC07240oz<S> getProvider(C01440Gz<S> r2, Context context) {
        return this.mInjector.getScopeAwareInjector().getProvider(r2, context);
    }

    @Override // X.AnonymousClass0R8
    public <T extends AnonymousClass0RX> T getScope(Class<? extends Annotation> cls) {
        return (T) this.mInjector.getScope(cls);
    }

    @Override // X.AbstractC02990bJ
    @Deprecated
    public AnonymousClass0Lf getScopeAwareInjector() {
        return this.mInjector.getScopeAwareInjector();
    }

    @VisibleForTesting
    public AnonymousClass0R8 getScopeAwareInjectorInternal() {
        return this.mInjector.getScopeAwareInjector();
    }

    @Override // X.AbstractC02990bJ
    @Deprecated
    public C007508o getScopeUnawareInjector() {
        return this.mInjector.getScopeUnawareInjector();
    }

    @Override // X.AnonymousClass0R8
    public <T> Set<T> getSet(C01440Gz<T> r2, Context context) {
        return this.mInjector.getSet(r2, context);
    }

    @Override // X.AnonymousClass0R8
    public <T> AbstractC07240oz<Set<T>> getSetProvider(C01440Gz<T> r2, Context context) {
        return this.mInjector.getScopeAwareInjector().getSetProvider(r2, context);
    }

    public Object getObjectForBindingId(int i) {
        AbstractC05530jm.A00();
        throw new RuntimeException(AnonymousClass006.A05("Not Implemented. This should only be called from the test frameworks, and must be explicitly overridden\nName of injector: ", getClass().getCanonicalName()));
    }

    public void setInjector(AbstractC02990bJ r1) {
        this.mInjector = r1;
    }

    public Object getInstance(int i) {
        return getInstance(i, this.mInjector.getInjectorThreadStack().A00());
    }

    @Override // X.AnonymousClass0R8
    public Object getInstance(int i, Context context) {
        return this.mInjector.getInstance(i, context);
    }

    public <S> S getInstance(C01440Gz<S> r2) {
        return (S) getInstance(r2, this.mInjector.getInjectorThreadStack().A00());
    }

    @Override // X.AnonymousClass0R8
    public <S> S getInstance(C01440Gz<S> r2, Context context) {
        return (S) this.mInjector.getInstance(r2, context);
    }

    public <S> S getInstance(Class<S> cls) {
        return (S) getInstance(cls, this.mInjector.getInjectorThreadStack().A00());
    }

    @Override // X.AnonymousClass0R8
    public <S> S getInstance(Class<S> cls, Context context) {
        return (S) this.mInjector.getInstance(cls, context);
    }

    public <S> S getInstance(Class<S> cls, Class<? extends Annotation> cls2) {
        return (S) getInstance(cls, cls2, this.mInjector.getInjectorThreadStack().A00());
    }

    @Override // X.AnonymousClass0R8
    public <S> S getInstance(Class<S> cls, Class<? extends Annotation> cls2, Context context) {
        return (S) this.mInjector.getInstance(cls, cls2, context);
    }
}
