package X;

import android.content.Context;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Set;

/* renamed from: X.8H  reason: invalid class name */
public abstract class AnonymousClass8H extends BZ {
    @Override // X.SZ
    public abstract SZ getApplicationInjector();

    @Override // X.On
    public abstract <T> T getInstance(gz<T> gzVar, Context context);

    @Override // X.On
    public abstract <T> SY<T> getLazy(gz<T> gzVar, Context context);

    @Override // X.On
    public abstract <T> AbstractC0192Xx<T> getProvider(gz<T> gzVar, Context context);

    @Override // X.On
    public final <T> SY<List<T>> getLazyList(gz<T> gzVar, Context context) {
        return getLazy(BZ.A00(gzVar), context);
    }

    @Override // X.On
    public final <T> SY<Set<T>> getLazySet(gz<T> gzVar, Context context) {
        return getLazy(BZ.A01(gzVar), context);
    }

    @Override // X.On
    public final <T> List<T> getList(gz<T> gzVar, Context context) {
        return (List) getInstance(BZ.A00(gzVar), context);
    }

    @Override // X.On
    public final <T> AbstractC0192Xx<List<T>> getListProvider(gz<T> gzVar, Context context) {
        return getProvider(BZ.A00(gzVar), context);
    }

    @Override // X.On
    public final <T> Set<T> getSet(gz<T> gzVar, Context context) {
        return (Set) getInstance(BZ.A01(gzVar), context);
    }

    @Override // X.On
    public final <T> AbstractC0192Xx<Set<T>> getSetProvider(gz<T> gzVar, Context context) {
        return getProvider(BZ.A01(gzVar), context);
    }

    @Override // X.On
    public final <T> T getInstance(Class<T> cls, Context context) {
        return (T) getInstance(new gz<>(cls, KA.INSTANCE), context);
    }

    @Override // X.On
    public final <T> T getInstance(Class<T> cls, Class<? extends Annotation> cls2, Context context) {
        return (T) getInstance(gz.A01(cls, cls2), context);
    }
}
