package X;

import android.content.Context;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Set;

public interface On {
    @Deprecated
    Object getInstance(int i, Context context);

    @Deprecated
    <T> T getInstance(gz<T> gzVar, Context context);

    @Deprecated
    <T> T getInstance(Class<T> cls, Context context);

    @Deprecated
    <T> T getInstance(Class<T> cls, Class<? extends Annotation> cls2, Context context);

    @Deprecated
    <T> SY<T> getLazy(gz<T> gzVar, Context context);

    @Deprecated
    <T> SY<List<T>> getLazyList(gz<T> gzVar, Context context);

    @Deprecated
    <T> SY<Set<T>> getLazySet(gz<T> gzVar, Context context);

    @Deprecated
    <T> List<T> getList(gz<T> gzVar, Context context);

    @Deprecated
    <T> AbstractC0192Xx<List<T>> getListProvider(gz<T> gzVar, Context context);

    @Deprecated
    <T> AbstractC0192Xx<T> getProvider(gz<T> gzVar, Context context);

    <T extends PC> T getScope(Class<? extends Annotation> cls);

    @Deprecated
    <T> Set<T> getSet(gz<T> gzVar, Context context);

    @Deprecated
    <T> AbstractC0192Xx<Set<T>> getSetProvider(gz<T> gzVar, Context context);
}
