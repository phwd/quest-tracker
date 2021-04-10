package X;

import android.content.Context;
import androidx.annotation.VisibleForTesting;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Set;
import javax.inject.Provider;

/* renamed from: X.0VK  reason: invalid class name */
public abstract class AnonymousClass0VK<T> implements AnonymousClass0lg {
    public AnonymousClass0lg mInjector;

    @Override // X.AnonymousClass0lg
    public AnonymousClass0lg getApplicationInjector() {
        return this.mInjector.getApplicationInjector();
    }

    @Override // X.AnonymousClass0lg
    @Deprecated
    public AnonymousClass0RH getInjectorThreadStack() {
        return this.mInjector.getInjectorThreadStack();
    }

    @Override // X.AnonymousClass0RF
    public <S> AbstractC03180ld<S> getLazy(AnonymousClass14P<S> r2, Context context) {
        return this.mInjector.getLazy(r2, context);
    }

    @Override // X.AnonymousClass0RF
    public <T> AbstractC03180ld<List<T>> getLazyList(AnonymousClass14P<T> r2, Context context) {
        return this.mInjector.getLazyList(r2, context);
    }

    @Override // X.AnonymousClass0RF
    public <T> AbstractC03180ld<Set<T>> getLazySet(AnonymousClass14P<T> r2, Context context) {
        return this.mInjector.getLazySet(r2, context);
    }

    @Override // X.AnonymousClass0RF
    public <T> List<T> getList(AnonymousClass14P<T> r2, Context context) {
        return this.mInjector.getList(r2, context);
    }

    @Override // X.AnonymousClass0RF
    public <T> Provider<List<T>> getListProvider(AnonymousClass14P<T> r2, Context context) {
        return this.mInjector.getScopeAwareInjector().getListProvider(r2, context);
    }

    @Override // X.AnonymousClass0RF
    public <S> Provider<S> getProvider(AnonymousClass14P<S> r2, Context context) {
        return this.mInjector.getScopeAwareInjector().getProvider(r2, context);
    }

    @Override // X.AnonymousClass0RF
    public <T extends AbstractC01120Rf> T getScope(Class<? extends Annotation> cls) {
        return (T) this.mInjector.getScope(cls);
    }

    @Override // X.AnonymousClass0lg
    @Deprecated
    public AnonymousClass0VD getScopeAwareInjector() {
        return this.mInjector.getScopeAwareInjector();
    }

    @VisibleForTesting
    public AnonymousClass0RF getScopeAwareInjectorInternal() {
        return this.mInjector.getScopeAwareInjector();
    }

    @Override // X.AnonymousClass0lg
    @Deprecated
    public C002105e getScopeUnawareInjector() {
        return this.mInjector.getScopeUnawareInjector();
    }

    @Override // X.AnonymousClass0RF
    public <S> Set<S> getSet(AnonymousClass14P<S> r2, Context context) {
        return this.mInjector.getSet(r2, context);
    }

    @Override // X.AnonymousClass0RF
    public <T> Provider<Set<T>> getSetProvider(AnonymousClass14P<T> r2, Context context) {
        return this.mInjector.getScopeAwareInjector().getSetProvider(r2, context);
    }

    public void setInjector(AnonymousClass0lg r1) {
        this.mInjector = r1;
    }

    public Object getInstance(int i) {
        return getInstance(i, this.mInjector.getInjectorThreadStack().A00());
    }

    @Override // X.AnonymousClass0RF
    public Object getInstance(int i, Context context) {
        return this.mInjector.getInstance(i, context);
    }

    public <S> S getInstance(AnonymousClass14P<S> r2) {
        return (S) getInstance(r2, this.mInjector.getInjectorThreadStack().A00());
    }

    @Override // X.AnonymousClass0RF
    public <S> S getInstance(AnonymousClass14P<S> r2, Context context) {
        return (S) this.mInjector.getInstance(r2, context);
    }

    public <S> S getInstance(Class<S> cls) {
        return (S) getInstance(cls, this.mInjector.getInjectorThreadStack().A00());
    }

    @Override // X.AnonymousClass0RF
    public <S> S getInstance(Class<S> cls, Context context) {
        return (S) this.mInjector.getInstance(cls, context);
    }

    public <S> S getInstance(Class<S> cls, Class<? extends Annotation> cls2) {
        return (S) getInstance(cls, cls2, this.mInjector.getInjectorThreadStack().A00());
    }

    @Override // X.AnonymousClass0RF
    public <S> S getInstance(Class<S> cls, Class<? extends Annotation> cls2, Context context) {
        return (S) this.mInjector.getInstance(cls, cls2, context);
    }
}
