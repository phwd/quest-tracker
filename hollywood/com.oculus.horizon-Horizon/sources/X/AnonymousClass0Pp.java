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
/* renamed from: X.0Pp  reason: invalid class name */
public interface AnonymousClass0Pp {
    <T> void A1I(C09160zY<T> v);

    <T> void A1J(Class<T> cls);

    <T> LinkedBindingBuilder<T> A1L(C09160zY<T> v);

    <T> AnnotatedBindingBuilder<T> A1M(Class<T> cls);

    <T> void A1N(Class<? extends AssistedProvider<T>> cls);

    <T> LinkedComponentBindingBuilder<T> A1O(Class<T> cls);

    <T> LinkedBindingBuilder<T> A1P(C09160zY<T> v);

    <T> AnnotatedBindingBuilder<T> A1Q(Class<T> cls);

    <T> AnonymousClass0QR<T> A1R(C09160zY<T> v);

    <T> AnonymousClass0QR<T> A1S(Class<T> cls);

    <T> AnonymousClass0QR<T> A1T(Class<T> cls, Class<? extends Annotation> cls2);

    void A1U(Class<? extends Annotation> cls, AbstractC01320Qc v);

    void A28(C09160zY<?> v);

    void A29(Class<?> cls);

    void A2A(Class<?> cls, Class<? extends Annotation> cls2);

    List<AnonymousClass0Pq> A35();

    List<AnonymousClass0Pu> A3E();

    AnonymousClass0QD A3Y();

    Set<C09160zY> A3x();

    Map<C09160zY, AnonymousClass0QR> A3y();

    List<Class<?>> A4H();

    Map<Class<? extends Annotation>, AbstractC01320Qc> A4J();

    void A8J(Class<?> cls);
}
