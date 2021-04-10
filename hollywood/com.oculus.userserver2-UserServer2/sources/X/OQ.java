package X;

import com.facebook.infer.annotation.Nullsafe;
import com.facebook.inject.AssistedProvider;
import com.facebook.inject.binder.AnnotatedBindingBuilder;
import com.facebook.inject.binder.LinkedBindingBuilder;
import com.facebook.inject.binder.LinkedComponentBindingBuilder;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Nullsafe(Nullsafe.Mode.STRICT)
public interface OQ {
    <T> void A0r(gz<T> gzVar);

    <T> void A0s(Class<T> cls);

    <T> LinkedBindingBuilder<T> A0v(gz<T> gzVar);

    <T> AnnotatedBindingBuilder<T> A0w(Class<T> cls);

    <T> void A0x(Class<? extends AssistedProvider<T>> cls);

    <T> LinkedComponentBindingBuilder<T> A0y(Class<T> cls);

    <T> LinkedBindingBuilder<T> A0z(gz<T> gzVar);

    <T> AnnotatedBindingBuilder<T> A10(Class<T> cls);

    <T> P1<T> A11(gz<T> gzVar);

    <T> P1<T> A12(Class<T> cls);

    <T> P1<T> A13(Class<T> cls, Class<? extends Annotation> cls2);

    void A14(Class<? extends Annotation> cls, PC pc);

    void A1J(gz<?> gzVar);

    void A1K(Class<?> cls);

    void A1L(Class<?> cls, Class<? extends Annotation> cls2);

    List<OR> A1c();

    List<OV> A1d();

    On A1i();

    Set<gz> A1o();

    Map<gz, P1> A1p();

    List<Class<?>> A1w();

    Map<Class<? extends Annotation>, PC> A1x();

    void A3O(Class<?> cls);
}
