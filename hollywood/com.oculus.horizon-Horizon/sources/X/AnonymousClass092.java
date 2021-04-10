package X;

import android.content.Context;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Set;
import javax.inject.Provider;

/* renamed from: X.092  reason: invalid class name */
public abstract class AnonymousClass092 extends AnonymousClass0J2 {
    @Override // X.AbstractC06640p5
    public abstract AbstractC06640p5 getApplicationInjector();

    @Override // X.AnonymousClass0QD
    public abstract <T> T getInstance(C09160zY<T> v, Context context);

    @Override // X.AnonymousClass0QD
    public abstract <T> AnonymousClass0p1<T> getLazy(C09160zY<T> v, Context context);

    @Override // X.AnonymousClass0QD
    public abstract <T> Provider<T> getProvider(C09160zY<T> v, Context context);

    @Override // X.AnonymousClass0QD
    public final <T> AnonymousClass0p1<List<T>> getLazyList(C09160zY<T> r2, Context context) {
        return getLazy(AnonymousClass0J2.A00(r2), context);
    }

    @Override // X.AnonymousClass0QD
    public final <T> AnonymousClass0p1<Set<T>> getLazySet(C09160zY<T> r2, Context context) {
        return getLazy(AnonymousClass0J2.A01(r2), context);
    }

    @Override // X.AnonymousClass0QD
    public final <T> List<T> getList(C09160zY<T> r2, Context context) {
        return (List) getInstance(AnonymousClass0J2.A00(r2), context);
    }

    @Override // X.AnonymousClass0QD
    public final <T> Provider<List<T>> getListProvider(C09160zY<T> r2, Context context) {
        return getProvider(AnonymousClass0J2.A00(r2), context);
    }

    @Override // X.AnonymousClass0QD
    public final <T> Set<T> getSet(C09160zY<T> r2, Context context) {
        return (Set) getInstance(AnonymousClass0J2.A01(r2), context);
    }

    @Override // X.AnonymousClass0QD
    public final <T> Provider<Set<T>> getSetProvider(C09160zY<T> r2, Context context) {
        return getProvider(AnonymousClass0J2.A01(r2), context);
    }

    @Override // X.AnonymousClass0QD
    public final <T> T getInstance(Class<T> cls, Context context) {
        return (T) getInstance(new C09160zY<>(cls, AnonymousClass0US.INSTANCE), context);
    }

    @Override // X.AnonymousClass0QD
    public final <T> T getInstance(Class<T> cls, Class<? extends Annotation> cls2, Context context) {
        return (T) getInstance(C09160zY.A01(cls, cls2), context);
    }
}
