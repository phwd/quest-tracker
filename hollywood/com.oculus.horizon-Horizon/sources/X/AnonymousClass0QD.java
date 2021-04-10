package X;

import android.content.Context;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Set;
import javax.inject.Provider;

/* renamed from: X.0QD  reason: invalid class name */
public interface AnonymousClass0QD {
    @Deprecated
    Object getInstance(int i, Context context);

    @Deprecated
    <T> T getInstance(C09160zY<T> v, Context context);

    @Deprecated
    <T> T getInstance(Class<T> cls, Context context);

    @Deprecated
    <T> T getInstance(Class<T> cls, Class<? extends Annotation> cls2, Context context);

    @Deprecated
    <T> AnonymousClass0p1<T> getLazy(C09160zY<T> v, Context context);

    @Deprecated
    <T> AnonymousClass0p1<List<T>> getLazyList(C09160zY<T> v, Context context);

    @Deprecated
    <T> AnonymousClass0p1<Set<T>> getLazySet(C09160zY<T> v, Context context);

    @Deprecated
    <T> List<T> getList(C09160zY<T> v, Context context);

    @Deprecated
    <T> Provider<List<T>> getListProvider(C09160zY<T> v, Context context);

    @Deprecated
    <T> Provider<T> getProvider(C09160zY<T> v, Context context);

    <T extends AbstractC01320Qc> T getScope(Class<? extends Annotation> cls);

    @Deprecated
    <T> Set<T> getSet(C09160zY<T> v, Context context);

    @Deprecated
    <T> Provider<Set<T>> getSetProvider(C09160zY<T> v, Context context);
}
