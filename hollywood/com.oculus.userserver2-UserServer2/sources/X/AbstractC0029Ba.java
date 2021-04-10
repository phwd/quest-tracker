package X;

import android.content.Context;
import androidx.annotation.VisibleForTesting;
import com.facebook.inject.ProviderWithInjector;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Set;

/* renamed from: X.Ba  reason: case insensitive filesystem */
public abstract class AbstractC0029Ba<T> implements ProviderWithInjector<T>, SZ, PG {
    public SZ mInjector;

    @Override // X.SZ
    public SZ getApplicationInjector() {
        return this.mInjector.getApplicationInjector();
    }

    @Override // X.SZ
    @Deprecated
    public Op getInjectorThreadStack() {
        return this.mInjector.getInjectorThreadStack();
    }

    @Override // X.On
    public <S> SY<S> getLazy(gz<S> gzVar, Context context) {
        return this.mInjector.getLazy(gzVar, context);
    }

    @Override // X.On
    public <T> SY<List<T>> getLazyList(gz<T> gzVar, Context context) {
        return this.mInjector.getLazyList(gzVar, context);
    }

    @Override // X.On
    public <T> SY<Set<T>> getLazySet(gz<T> gzVar, Context context) {
        return this.mInjector.getLazySet(gzVar, context);
    }

    @Override // X.On
    public <T> List<T> getList(gz<T> gzVar, Context context) {
        return this.mInjector.getList(gzVar, context);
    }

    @Override // X.On
    public <T> AbstractC0192Xx<List<T>> getListProvider(gz<T> gzVar, Context context) {
        return this.mInjector.getScopeAwareInjector().getListProvider(gzVar, context);
    }

    @Override // X.On
    public <S> AbstractC0192Xx<S> getProvider(gz<S> gzVar, Context context) {
        return this.mInjector.getScopeAwareInjector().getProvider(gzVar, context);
    }

    @Override // X.On
    public <T extends PC> T getScope(Class<? extends Annotation> cls) {
        return (T) this.mInjector.getScope(cls);
    }

    @Override // X.SZ
    @Deprecated
    public BX getScopeAwareInjector() {
        return this.mInjector.getScopeAwareInjector();
    }

    @VisibleForTesting
    public On getScopeAwareInjectorInternal() {
        return this.mInjector.getScopeAwareInjector();
    }

    @Override // X.SZ
    @Deprecated
    public AnonymousClass3G getScopeUnawareInjector() {
        return this.mInjector.getScopeUnawareInjector();
    }

    @Override // X.On
    public <T> Set<T> getSet(gz<T> gzVar, Context context) {
        return this.mInjector.getSet(gzVar, context);
    }

    @Override // X.On
    public <T> AbstractC0192Xx<Set<T>> getSetProvider(gz<T> gzVar, Context context) {
        return this.mInjector.getScopeAwareInjector().getSetProvider(gzVar, context);
    }

    public Object getObjectForBindingId(int i) {
        PB.A00();
        throw new RuntimeException(AnonymousClass06.A03("Not Implemented. This should only be called from the test frameworks, and must be explicitly overridden\nName of injector: ", getClass().getCanonicalName()));
    }

    public void setInjector(SZ sz) {
        this.mInjector = sz;
    }

    public Object getInstance(int i) {
        return getInstance(i, this.mInjector.getInjectorThreadStack().A00());
    }

    @Override // X.On
    public Object getInstance(int i, Context context) {
        return this.mInjector.getInstance(i, context);
    }

    public <S> S getInstance(gz<S> gzVar) {
        return (S) getInstance(gzVar, this.mInjector.getInjectorThreadStack().A00());
    }

    @Override // X.On
    public <S> S getInstance(gz<S> gzVar, Context context) {
        return (S) this.mInjector.getInstance(gzVar, context);
    }

    public <S> S getInstance(Class<S> cls) {
        return (S) getInstance(cls, this.mInjector.getInjectorThreadStack().A00());
    }

    @Override // X.On
    public <S> S getInstance(Class<S> cls, Context context) {
        return (S) this.mInjector.getInstance(cls, context);
    }

    public <S> S getInstance(Class<S> cls, Class<? extends Annotation> cls2) {
        return (S) getInstance(cls, cls2, this.mInjector.getInjectorThreadStack().A00());
    }

    @Override // X.On
    public <S> S getInstance(Class<S> cls, Class<? extends Annotation> cls2, Context context) {
        return (S) this.mInjector.getInstance(cls, cls2, context);
    }
}
