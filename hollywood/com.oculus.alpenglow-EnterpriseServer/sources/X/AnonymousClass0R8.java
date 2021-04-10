package X;

import android.content.Context;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Set;

/* renamed from: X.0R8  reason: invalid class name */
public interface AnonymousClass0R8 {
    @Deprecated
    Object getInstance(int i, Context context);

    @Deprecated
    <T> T getInstance(C01440Gz<T> v, Context context);

    @Deprecated
    <T> T getInstance(Class<T> cls, Context context);

    @Deprecated
    <T> T getInstance(Class<T> cls, Class<? extends Annotation> cls2, Context context);

    @Deprecated
    <T> AbstractC02980bI<T> getLazy(C01440Gz<T> v, Context context);

    @Deprecated
    <T> AbstractC02980bI<List<T>> getLazyList(C01440Gz<T> v, Context context);

    @Deprecated
    <T> AbstractC02980bI<Set<T>> getLazySet(C01440Gz<T> v, Context context);

    @Deprecated
    <T> List<T> getList(C01440Gz<T> v, Context context);

    @Deprecated
    <T> AbstractC07240oz<List<T>> getListProvider(C01440Gz<T> v, Context context);

    @Deprecated
    <T> AbstractC07240oz<T> getProvider(C01440Gz<T> v, Context context);

    <T extends AnonymousClass0RX> T getScope(Class<? extends Annotation> cls);

    @Deprecated
    <T> Set<T> getSet(C01440Gz<T> v, Context context);

    @Deprecated
    <T> AbstractC07240oz<Set<T>> getSetProvider(C01440Gz<T> v, Context context);
}
