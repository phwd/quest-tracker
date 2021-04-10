package X;

import android.content.Context;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Set;

/* renamed from: X.0Gk  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC01370Gk extends AnonymousClass0Lh {
    @Override // X.AbstractC02990bJ
    public abstract AbstractC02990bJ getApplicationInjector();

    @Override // X.AnonymousClass0R8
    public abstract <T> T getInstance(C01440Gz<T> v, Context context);

    @Override // X.AnonymousClass0R8
    public abstract <T> AbstractC02980bI<T> getLazy(C01440Gz<T> v, Context context);

    @Override // X.AnonymousClass0R8
    public abstract <T> AbstractC07240oz<T> getProvider(C01440Gz<T> v, Context context);

    @Override // X.AnonymousClass0R8
    public final <T> AbstractC02980bI<List<T>> getLazyList(C01440Gz<T> r2, Context context) {
        return getLazy(AnonymousClass0Lh.A00(r2), context);
    }

    @Override // X.AnonymousClass0R8
    public final <T> AbstractC02980bI<Set<T>> getLazySet(C01440Gz<T> r2, Context context) {
        return getLazy(AnonymousClass0Lh.A01(r2), context);
    }

    @Override // X.AnonymousClass0R8
    public final <T> List<T> getList(C01440Gz<T> r2, Context context) {
        return (List) getInstance(AnonymousClass0Lh.A00(r2), context);
    }

    @Override // X.AnonymousClass0R8
    public final <T> AbstractC07240oz<List<T>> getListProvider(C01440Gz<T> r2, Context context) {
        return getProvider(AnonymousClass0Lh.A00(r2), context);
    }

    @Override // X.AnonymousClass0R8
    public final <T> Set<T> getSet(C01440Gz<T> r2, Context context) {
        return (Set) getInstance(AnonymousClass0Lh.A01(r2), context);
    }

    @Override // X.AnonymousClass0R8
    public final <T> AbstractC07240oz<Set<T>> getSetProvider(C01440Gz<T> r2, Context context) {
        return getProvider(AnonymousClass0Lh.A01(r2), context);
    }

    @Override // X.AnonymousClass0R8
    public final <T> T getInstance(Class<T> cls, Context context) {
        return (T) getInstance(new C01440Gz<>(cls, AnonymousClass0Vx.INSTANCE), context);
    }

    @Override // X.AnonymousClass0R8
    public final <T> T getInstance(Class<T> cls, Class<? extends Annotation> cls2, Context context) {
        return (T) getInstance(C01440Gz.A01(cls, cls2), context);
    }
}
