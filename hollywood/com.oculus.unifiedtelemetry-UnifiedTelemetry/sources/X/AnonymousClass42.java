package X;

import android.content.Context;
import java.lang.annotation.Annotation;

/* renamed from: X.42  reason: invalid class name */
public abstract class AnonymousClass42 extends AnonymousClass8g implements Qg {
    public final AbstractC0096Hu A00;

    @Override // X.QD, X.AnonymousClass8g
    public abstract <T> T getInstance(C0475qE<T> qEVar, Context context);

    @Override // X.QD, X.AnonymousClass8g
    public abstract <T> AbstractC0246Xt<T> getLazy(C0475qE<T> qEVar, Context context);

    @Override // X.QD, X.AnonymousClass8g
    public abstract <T> eJ<T> getProvider(C0475qE<T> qEVar, Context context);

    @Override // X.AbstractC0247Xu, X.AnonymousClass8g
    public final AbstractC0247Xu getApplicationInjector() {
        return this.A00.getApplicationInjector();
    }

    @Override // X.AbstractC0247Xu
    public final QF getInjectorThreadStack() {
        return this.A00.getInjectorThreadStack();
    }

    @Override // X.QD
    public final <T extends AbstractC0133Qc> T getScope(Class<? extends Annotation> cls) {
        return (T) this.A00.getScope(cls);
    }

    @Override // X.AbstractC0247Xu
    public AbstractC0094Hs getScopeAwareInjector() {
        return this.A00.getScopeAwareInjector();
    }

    @Override // X.AbstractC0247Xu
    public final C00103y getScopeUnawareInjector() {
        return this.A00.getScopeUnawareInjector();
    }

    public AnonymousClass42(AbstractC0096Hu hu) {
        this.A00 = hu;
    }
}
