package X;

import android.content.Context;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Set;

/* renamed from: X.8g  reason: invalid class name */
public abstract class AnonymousClass8g extends AbstractC0096Hu {
    @Override // X.AbstractC0247Xu
    public abstract AbstractC0247Xu getApplicationInjector();

    @Override // X.QD
    public abstract <T> T getInstance(C0475qE<T> qEVar, Context context);

    @Override // X.QD
    public abstract <T> AbstractC0246Xt<T> getLazy(C0475qE<T> qEVar, Context context);

    @Override // X.QD
    public abstract <T> eJ<T> getProvider(C0475qE<T> qEVar, Context context);

    @Override // X.QD
    public final <T> AbstractC0246Xt<List<T>> getLazyList(C0475qE<T> qEVar, Context context) {
        return getLazy(AbstractC0096Hu.A00(qEVar), context);
    }

    @Override // X.QD
    public final <T> AbstractC0246Xt<Set<T>> getLazySet(C0475qE<T> qEVar, Context context) {
        return getLazy(AbstractC0096Hu.A01(qEVar), context);
    }

    @Override // X.QD
    public final <T> List<T> getList(C0475qE<T> qEVar, Context context) {
        return (List) getInstance(AbstractC0096Hu.A00(qEVar), context);
    }

    @Override // X.QD
    public final <T> eJ<List<T>> getListProvider(C0475qE<T> qEVar, Context context) {
        return getProvider(AbstractC0096Hu.A00(qEVar), context);
    }

    @Override // X.QD
    public final <T> Set<T> getSet(C0475qE<T> qEVar, Context context) {
        return (Set) getInstance(AbstractC0096Hu.A01(qEVar), context);
    }

    @Override // X.QD
    public final <T> eJ<Set<T>> getSetProvider(C0475qE<T> qEVar, Context context) {
        return getProvider(AbstractC0096Hu.A01(qEVar), context);
    }

    @Override // X.QD
    public final <T> T getInstance(Class<T> cls, Context context) {
        return (T) getInstance(new C0475qE<>(cls, Rp.INSTANCE), context);
    }

    @Override // X.QD
    public final <T> T getInstance(Class<T> cls, Class<? extends Annotation> cls2, Context context) {
        return (T) getInstance(C0475qE.A01(cls, cls2), context);
    }
}
