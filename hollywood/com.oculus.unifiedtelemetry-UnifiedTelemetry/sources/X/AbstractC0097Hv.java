package X;

import android.content.Context;
import androidx.annotation.VisibleForTesting;
import com.facebook.inject.ProviderWithInjector;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Set;

/* renamed from: X.Hv  reason: case insensitive filesystem */
public abstract class AbstractC0097Hv<T> implements ProviderWithInjector<T>, AbstractC0247Xu, Qg {
    public AbstractC0247Xu mInjector;

    @Override // X.AbstractC0247Xu
    public AbstractC0247Xu getApplicationInjector() {
        return this.mInjector.getApplicationInjector();
    }

    @Override // X.AbstractC0247Xu
    @Deprecated
    public QF getInjectorThreadStack() {
        return this.mInjector.getInjectorThreadStack();
    }

    @Override // X.QD
    public <S> AbstractC0246Xt<S> getLazy(C0475qE<S> qEVar, Context context) {
        return this.mInjector.getLazy(qEVar, context);
    }

    @Override // X.QD
    public <T> AbstractC0246Xt<List<T>> getLazyList(C0475qE<T> qEVar, Context context) {
        return this.mInjector.getLazyList(qEVar, context);
    }

    @Override // X.QD
    public <T> AbstractC0246Xt<Set<T>> getLazySet(C0475qE<T> qEVar, Context context) {
        return this.mInjector.getLazySet(qEVar, context);
    }

    @Override // X.QD
    public <T> List<T> getList(C0475qE<T> qEVar, Context context) {
        return this.mInjector.getList(qEVar, context);
    }

    @Override // X.QD
    public <T> eJ<List<T>> getListProvider(C0475qE<T> qEVar, Context context) {
        return this.mInjector.getScopeAwareInjector().getListProvider(qEVar, context);
    }

    @Override // X.QD
    public <S> eJ<S> getProvider(C0475qE<S> qEVar, Context context) {
        return this.mInjector.getScopeAwareInjector().getProvider(qEVar, context);
    }

    @Override // X.QD
    public <T extends AbstractC0133Qc> T getScope(Class<? extends Annotation> cls) {
        return (T) this.mInjector.getScope(cls);
    }

    @Override // X.AbstractC0247Xu
    @Deprecated
    public AbstractC0094Hs getScopeAwareInjector() {
        return this.mInjector.getScopeAwareInjector();
    }

    @VisibleForTesting
    public QD getScopeAwareInjectorInternal() {
        return this.mInjector.getScopeAwareInjector();
    }

    @Override // X.AbstractC0247Xu
    @Deprecated
    public C00103y getScopeUnawareInjector() {
        return this.mInjector.getScopeUnawareInjector();
    }

    @Override // X.QD
    public <T> Set<T> getSet(C0475qE<T> qEVar, Context context) {
        return this.mInjector.getSet(qEVar, context);
    }

    @Override // X.QD
    public <T> eJ<Set<T>> getSetProvider(C0475qE<T> qEVar, Context context) {
        return this.mInjector.getScopeAwareInjector().getSetProvider(qEVar, context);
    }

    public Object getObjectForBindingId(int i) {
        AbstractC0459mt.A00();
        throw new RuntimeException(AnonymousClass06.A04("Not Implemented. This should only be called from the test frameworks, and must be explicitly overridden\nName of injector: ", getClass().getCanonicalName()));
    }

    public void setInjector(AbstractC0247Xu xu) {
        this.mInjector = xu;
    }

    public Object getInstance(int i) {
        return getInstance(i, this.mInjector.getInjectorThreadStack().A00());
    }

    @Override // X.QD
    public Object getInstance(int i, Context context) {
        return this.mInjector.getInstance(i, context);
    }

    public <S> S getInstance(C0475qE<S> qEVar) {
        return (S) getInstance(qEVar, this.mInjector.getInjectorThreadStack().A00());
    }

    @Override // X.QD
    public <S> S getInstance(C0475qE<S> qEVar, Context context) {
        return (S) this.mInjector.getInstance(qEVar, context);
    }

    public <S> S getInstance(Class<S> cls) {
        return (S) getInstance(cls, this.mInjector.getInjectorThreadStack().A00());
    }

    @Override // X.QD
    public <S> S getInstance(Class<S> cls, Context context) {
        return (S) this.mInjector.getInstance(cls, context);
    }

    public <S> S getInstance(Class<S> cls, Class<? extends Annotation> cls2) {
        return (S) getInstance(cls, cls2, this.mInjector.getInjectorThreadStack().A00());
    }

    @Override // X.QD
    public <S> S getInstance(Class<S> cls, Class<? extends Annotation> cls2, Context context) {
        return (S) this.mInjector.getInstance(cls, cls2, context);
    }
}
