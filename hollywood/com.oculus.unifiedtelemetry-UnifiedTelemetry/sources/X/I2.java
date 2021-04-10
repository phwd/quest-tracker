package X;

import android.content.Context;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Set;

public abstract class I2<T> implements AbstractC0247Xu {
    public final AbstractC0247Xu mInjector;

    @Override // X.AbstractC0247Xu
    public final AbstractC0247Xu getApplicationInjector() {
        return this.mInjector.getApplicationInjector();
    }

    @Override // X.AbstractC0247Xu
    @Deprecated
    public final QF getInjectorThreadStack() {
        return this.mInjector.getInjectorThreadStack();
    }

    @Override // X.QD
    public final <S> AbstractC0246Xt<S> getLazy(C0475qE<S> qEVar, Context context) {
        return this.mInjector.getLazy(qEVar, context);
    }

    @Override // X.QD
    public final <T> AbstractC0246Xt<List<T>> getLazyList(C0475qE<T> qEVar, Context context) {
        return this.mInjector.getLazyList(qEVar, context);
    }

    @Override // X.QD
    public final <T> AbstractC0246Xt<Set<T>> getLazySet(C0475qE<T> qEVar, Context context) {
        return this.mInjector.getLazySet(qEVar, context);
    }

    @Override // X.QD
    public final <T> List<T> getList(C0475qE<T> qEVar, Context context) {
        return this.mInjector.getList(qEVar, context);
    }

    @Override // X.QD
    public final <T> eJ<List<T>> getListProvider(C0475qE<T> qEVar, Context context) {
        return this.mInjector.getScopeAwareInjector().getListProvider(qEVar, context);
    }

    @Override // X.QD
    public final <S> eJ<S> getProvider(C0475qE<S> qEVar, Context context) {
        return this.mInjector.getScopeAwareInjector().getProvider(qEVar, context);
    }

    @Override // X.QD
    public final <T extends AbstractC0133Qc> T getScope(Class<? extends Annotation> cls) {
        return (T) this.mInjector.getScope(cls);
    }

    @Override // X.AbstractC0247Xu
    @Deprecated
    public final AbstractC0094Hs getScopeAwareInjector() {
        return this.mInjector.getScopeAwareInjector();
    }

    @Override // X.AbstractC0247Xu
    @Deprecated
    public final C00103y getScopeUnawareInjector() {
        return this.mInjector.getScopeUnawareInjector();
    }

    @Override // X.QD
    public final <S> Set<S> getSet(C0475qE<S> qEVar, Context context) {
        return this.mInjector.getSet(qEVar, context);
    }

    @Override // X.QD
    public final <T> eJ<Set<T>> getSetProvider(C0475qE<T> qEVar, Context context) {
        return this.mInjector.getScopeAwareInjector().getSetProvider(qEVar, context);
    }

    @Override // X.QD
    public final Object getInstance(int i, Context context) {
        return this.mInjector.getInstance(i, context);
    }

    @Override // X.QD
    public final <S> S getInstance(C0475qE<S> qEVar, Context context) {
        return (S) this.mInjector.getInstance(qEVar, context);
    }

    @Override // X.QD
    public final <S> S getInstance(Class<S> cls, Context context) {
        return (S) this.mInjector.getInstance(cls, context);
    }

    @Override // X.QD
    public final <S> S getInstance(Class<S> cls, Class<? extends Annotation> cls2, Context context) {
        return (S) this.mInjector.getInstance(cls, cls2, context);
    }
}
