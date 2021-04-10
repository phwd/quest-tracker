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
/* renamed from: X.0Qr  reason: invalid class name */
public interface AnonymousClass0Qr {
    <T> void A1V(AnonymousClass14P<T> v);

    <T> void A1W(Class<T> cls);

    <T> LinkedBindingBuilder<T> A1b(AnonymousClass14P<T> v);

    <T> AnnotatedBindingBuilder<T> A1c(Class<T> cls);

    <T> void A1d(Class<? extends AssistedProvider<T>> cls);

    <T> LinkedComponentBindingBuilder<T> A1e(Class<T> cls);

    <T> LinkedBindingBuilder<T> A1f(AnonymousClass14P<T> v);

    <T> AnnotatedBindingBuilder<T> A1g(Class<T> cls);

    <T> AnonymousClass0RT<T> A1h(AnonymousClass14P<T> v);

    <T> AnonymousClass0RT<T> A1i(Class<T> cls);

    <T> AnonymousClass0RT<T> A1j(Class<T> cls, Class<? extends Annotation> cls2);

    void A1k(Class<? extends Annotation> cls, AbstractC01120Rf v);

    void A2S(AnonymousClass14P<?> v);

    void A2T(Class<?> cls);

    void A2U(Class<?> cls, Class<? extends Annotation> cls2);

    List<AnonymousClass0Qs> A3S();

    List<AnonymousClass0Qw> A3b();

    AnonymousClass0RF A4A();

    Set<AnonymousClass14P> A4W();

    Map<AnonymousClass14P, AnonymousClass0RT> A4X();

    List<Class<?>> A4m();

    Map<Class<? extends Annotation>, AbstractC01120Rf> A4s();

    void A9H(Class<?> cls);
}
