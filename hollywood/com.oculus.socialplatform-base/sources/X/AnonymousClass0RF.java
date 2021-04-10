package X;

import android.content.Context;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Set;
import javax.inject.Provider;

/* renamed from: X.0RF  reason: invalid class name */
public interface AnonymousClass0RF {
    @Deprecated
    Object getInstance(int i, Context context);

    @Deprecated
    <T> T getInstance(AnonymousClass14P<T> v, Context context);

    @Deprecated
    <T> T getInstance(Class<T> cls, Context context);

    @Deprecated
    <T> T getInstance(Class<T> cls, Class<? extends Annotation> cls2, Context context);

    @Deprecated
    <T> AbstractC03180ld<T> getLazy(AnonymousClass14P<T> v, Context context);

    @Deprecated
    <T> AbstractC03180ld<List<T>> getLazyList(AnonymousClass14P<T> v, Context context);

    @Deprecated
    <T> AbstractC03180ld<Set<T>> getLazySet(AnonymousClass14P<T> v, Context context);

    @Deprecated
    <T> List<T> getList(AnonymousClass14P<T> v, Context context);

    @Deprecated
    <T> Provider<List<T>> getListProvider(AnonymousClass14P<T> v, Context context);

    @Deprecated
    <T> Provider<T> getProvider(AnonymousClass14P<T> v, Context context);

    <T extends AbstractC01120Rf> T getScope(Class<? extends Annotation> cls);

    @Deprecated
    <T> Set<T> getSet(AnonymousClass14P<T> v, Context context);

    @Deprecated
    <T> Provider<Set<T>> getSetProvider(AnonymousClass14P<T> v, Context context);
}
