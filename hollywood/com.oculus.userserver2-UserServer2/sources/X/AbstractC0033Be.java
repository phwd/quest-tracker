package X;

import android.content.Context;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Set;

/* renamed from: X.Be  reason: case insensitive filesystem */
public abstract class AbstractC0033Be<T> implements SZ {
    public final SZ mInjector;

    @Override // X.SZ
    public final SZ getApplicationInjector() {
        return this.mInjector.getApplicationInjector();
    }

    @Override // X.SZ
    @Deprecated
    public final Op getInjectorThreadStack() {
        return this.mInjector.getInjectorThreadStack();
    }

    @Override // X.On
    public final <S> SY<S> getLazy(gz<S> gzVar, Context context) {
        return this.mInjector.getLazy(gzVar, context);
    }

    @Override // X.On
    public final <T> SY<List<T>> getLazyList(gz<T> gzVar, Context context) {
        return this.mInjector.getLazyList(gzVar, context);
    }

    @Override // X.On
    public final <T> SY<Set<T>> getLazySet(gz<T> gzVar, Context context) {
        return this.mInjector.getLazySet(gzVar, context);
    }

    @Override // X.On
    public final <T> List<T> getList(gz<T> gzVar, Context context) {
        return this.mInjector.getList(gzVar, context);
    }

    @Override // X.On
    public final <T> AbstractC0192Xx<List<T>> getListProvider(gz<T> gzVar, Context context) {
        return this.mInjector.getScopeAwareInjector().getListProvider(gzVar, context);
    }

    @Override // X.On
    public final <S> AbstractC0192Xx<S> getProvider(gz<S> gzVar, Context context) {
        return this.mInjector.getScopeAwareInjector().getProvider(gzVar, context);
    }

    @Override // X.On
    public final <T extends PC> T getScope(Class<? extends Annotation> cls) {
        return (T) this.mInjector.getScope(cls);
    }

    @Override // X.SZ
    @Deprecated
    public final BX getScopeAwareInjector() {
        return this.mInjector.getScopeAwareInjector();
    }

    @Override // X.SZ
    @Deprecated
    public final AnonymousClass3G getScopeUnawareInjector() {
        return this.mInjector.getScopeUnawareInjector();
    }

    @Override // X.On
    public final <S> Set<S> getSet(gz<S> gzVar, Context context) {
        return this.mInjector.getSet(gzVar, context);
    }

    @Override // X.On
    public final <T> AbstractC0192Xx<Set<T>> getSetProvider(gz<T> gzVar, Context context) {
        return this.mInjector.getScopeAwareInjector().getSetProvider(gzVar, context);
    }

    @Override // X.On
    public final Object getInstance(int i, Context context) {
        return this.mInjector.getInstance(i, context);
    }

    @Override // X.On
    public final <S> S getInstance(gz<S> gzVar, Context context) {
        return (S) this.mInjector.getInstance(gzVar, context);
    }

    @Override // X.On
    public final <S> S getInstance(Class<S> cls, Context context) {
        return (S) this.mInjector.getInstance(cls, context);
    }

    @Override // X.On
    public final <S> S getInstance(Class<S> cls, Class<? extends Annotation> cls2, Context context) {
        return (S) this.mInjector.getInstance(cls, cls2, context);
    }
}
