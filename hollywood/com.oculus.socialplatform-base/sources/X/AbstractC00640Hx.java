package X;

import android.content.Context;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Set;
import javax.inject.Provider;

/* renamed from: X.0Hx  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC00640Hx extends AnonymousClass0VF {
    @Override // X.AnonymousClass0lg
    public abstract AnonymousClass0lg getApplicationInjector();

    @Override // X.AnonymousClass0RF
    public abstract <T> T getInstance(AnonymousClass14P<T> v, Context context);

    @Override // X.AnonymousClass0RF
    public abstract <T> AbstractC03180ld<T> getLazy(AnonymousClass14P<T> v, Context context);

    @Override // X.AnonymousClass0RF
    public abstract <T> Provider<T> getProvider(AnonymousClass14P<T> v, Context context);

    @Override // X.AnonymousClass0RF
    public final <T> AbstractC03180ld<List<T>> getLazyList(AnonymousClass14P<T> r2, Context context) {
        return getLazy(AnonymousClass0VF.A00(r2), context);
    }

    @Override // X.AnonymousClass0RF
    public final <T> AbstractC03180ld<Set<T>> getLazySet(AnonymousClass14P<T> r2, Context context) {
        return getLazy(AnonymousClass0VF.A01(r2), context);
    }

    @Override // X.AnonymousClass0RF
    public final <T> List<T> getList(AnonymousClass14P<T> r2, Context context) {
        return (List) getInstance(AnonymousClass0VF.A00(r2), context);
    }

    @Override // X.AnonymousClass0RF
    public final <T> Provider<List<T>> getListProvider(AnonymousClass14P<T> r2, Context context) {
        return getProvider(AnonymousClass0VF.A00(r2), context);
    }

    @Override // X.AnonymousClass0RF
    public final <T> Set<T> getSet(AnonymousClass14P<T> r2, Context context) {
        return (Set) getInstance(AnonymousClass0VF.A01(r2), context);
    }

    @Override // X.AnonymousClass0RF
    public final <T> Provider<Set<T>> getSetProvider(AnonymousClass14P<T> r2, Context context) {
        return getProvider(AnonymousClass0VF.A01(r2), context);
    }

    @Override // X.AnonymousClass0RF
    public final <T> T getInstance(Class<T> cls, Context context) {
        return (T) getInstance(new AnonymousClass14P<>(cls, AnonymousClass0cy.INSTANCE), context);
    }

    @Override // X.AnonymousClass0RF
    public final <T> T getInstance(Class<T> cls, Class<? extends Annotation> cls2, Context context) {
        return (T) getInstance(AnonymousClass14P.A01(cls, cls2), context);
    }
}
