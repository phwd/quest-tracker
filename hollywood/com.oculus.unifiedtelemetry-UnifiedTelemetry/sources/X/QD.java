package X;

import android.content.Context;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Set;

public interface QD {
    @Deprecated
    Object getInstance(int i, Context context);

    @Deprecated
    <T> T getInstance(C0475qE<T> qEVar, Context context);

    @Deprecated
    <T> T getInstance(Class<T> cls, Context context);

    @Deprecated
    <T> T getInstance(Class<T> cls, Class<? extends Annotation> cls2, Context context);

    @Deprecated
    <T> AbstractC0246Xt<T> getLazy(C0475qE<T> qEVar, Context context);

    @Deprecated
    <T> AbstractC0246Xt<List<T>> getLazyList(C0475qE<T> qEVar, Context context);

    @Deprecated
    <T> AbstractC0246Xt<Set<T>> getLazySet(C0475qE<T> qEVar, Context context);

    @Deprecated
    <T> List<T> getList(C0475qE<T> qEVar, Context context);

    @Deprecated
    <T> eJ<List<T>> getListProvider(C0475qE<T> qEVar, Context context);

    @Deprecated
    <T> eJ<T> getProvider(C0475qE<T> qEVar, Context context);

    <T extends AbstractC0133Qc> T getScope(Class<? extends Annotation> cls);

    @Deprecated
    <T> Set<T> getSet(C0475qE<T> qEVar, Context context);

    @Deprecated
    <T> eJ<Set<T>> getSetProvider(C0475qE<T> qEVar, Context context);
}
