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
public interface Pq {
    <T> void A1G(C0475qE<T> qEVar);

    <T> void A1H(Class<T> cls);

    <T> LinkedBindingBuilder<T> A1K(C0475qE<T> qEVar);

    <T> AnnotatedBindingBuilder<T> A1L(Class<T> cls);

    <T> void A1M(Class<? extends AssistedProvider<T>> cls);

    <T> LinkedComponentBindingBuilder<T> A1N(Class<T> cls);

    <T> LinkedBindingBuilder<T> A1O(C0475qE<T> qEVar);

    <T> AnnotatedBindingBuilder<T> A1P(Class<T> cls);

    <T> QR<T> A1Q(C0475qE<T> qEVar);

    <T> QR<T> A1R(Class<T> cls);

    <T> QR<T> A1S(Class<T> cls, Class<? extends Annotation> cls2);

    void A1T(Class<? extends Annotation> cls, AbstractC0133Qc qc);

    void A1k(C0475qE<?> qEVar);

    void A1l(Class<?> cls);

    void A1m(Class<?> cls, Class<? extends Annotation> cls2);

    List<Pr> A2K();

    List<Pv> A2O();

    QD A2V();

    Set<C0475qE> A2g();

    Map<C0475qE, QR> A2h();

    List<Class<?>> A2o();

    Map<Class<? extends Annotation>, AbstractC0133Qc> A2p();

    void A4m(Class<?> cls);
}
