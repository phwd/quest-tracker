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
/* renamed from: X.0Ql  reason: invalid class name */
public interface AnonymousClass0Ql {
    <T> void A1C(C01440Gz<T> v);

    <T> void A1D(Class<T> cls);

    <T> LinkedBindingBuilder<T> A1I(C01440Gz<T> v);

    <T> AnnotatedBindingBuilder<T> A1J(Class<T> cls);

    <T> void A1K(Class<? extends AssistedProvider<T>> cls);

    <T> LinkedComponentBindingBuilder<T> A1M(Class<T> cls);

    <T> LinkedBindingBuilder<T> A1N(C01440Gz<T> v);

    <T> AnnotatedBindingBuilder<T> A1O(Class<T> cls);

    <T> AnonymousClass0RM<T> A1R(C01440Gz<T> v);

    <T> AnonymousClass0RM<T> A1S(Class<T> cls);

    <T> AnonymousClass0RM<T> A1T(Class<T> cls, Class<? extends Annotation> cls2);

    void A1V(Class<? extends Annotation> cls, AnonymousClass0RX v);

    void A22(C01440Gz<?> v);

    void A23(Class<?> cls);

    void A24(Class<?> cls, Class<? extends Annotation> cls2);

    List<AnonymousClass0Qm> A35();

    List<AnonymousClass0Qq> A3B();

    AnonymousClass0R8 A3l();

    Set<C01440Gz> A47();

    Map<C01440Gz, AnonymousClass0RM> A48();

    List<Class<?>> A4Q();

    Map<Class<? extends Annotation>, AnonymousClass0RX> A4S();

    void A7S(Class<?> cls);
}
