package X;

import com.facebook.inject.AssistedProvider;
import com.facebook.inject.binder.AnnotatedBindingBuilder;
import com.facebook.inject.binder.LinkedBindingBuilder;
import com.facebook.inject.binder.LinkedComponentBindingBuilder;
import java.lang.annotation.Annotation;

/* renamed from: X.0pO  reason: invalid class name */
public abstract class AnonymousClass0pO implements AnonymousClass0QK {
    public AnonymousClass0Pp mBinder;

    public void configure() {
    }

    public <T> void bindAssistedProvider(Class<? extends AssistedProvider<T>> cls) {
        this.mBinder.A1N(cls);
    }

    public <T> LinkedComponentBindingBuilder<T> bindComponent(Class<T> cls) {
        return this.mBinder.A1O(cls);
    }

    public void bindScope(Class<? extends Annotation> cls, AbstractC01320Qc r3) {
        this.mBinder.A1U(cls, r3);
    }

    public void require(Class<?> cls) {
        this.mBinder.A8J(cls);
    }

    public AnonymousClass0Pp getBinder() {
        return this.mBinder;
    }

    public <T> void assertBindingInstalled(C09160zY<T> r2) {
        this.mBinder.A1I(r2);
    }

    public <T> void assertBindingInstalled(Class<T> cls) {
        this.mBinder.A1J(cls);
    }

    public <T> void assertBindingInstalled(Class<T> cls, Class<? extends Annotation> cls2) {
        this.mBinder.A1I(C09160zY.A01(cls, cls2));
    }

    public <T> AnnotatedBindingBuilder<T> bind(Class<T> cls) {
        return this.mBinder.A1M(cls);
    }

    public <T> LinkedBindingBuilder<T> bind(C09160zY<T> r2) {
        return this.mBinder.A1L(r2);
    }

    public <T> AnnotatedBindingBuilder<T> bindDefault(Class<T> cls) {
        return this.mBinder.A1Q(cls);
    }

    public <T> LinkedBindingBuilder<T> bindDefault(C09160zY<T> r2) {
        return this.mBinder.A1P(r2);
    }

    public <T> AnonymousClass0QR<T> bindMulti(C09160zY<T> r2) {
        return this.mBinder.A1R(r2);
    }

    public <T> AnonymousClass0QR<T> bindMulti(Class<T> cls) {
        return this.mBinder.A1S(cls);
    }

    public <T> AnonymousClass0QR<T> bindMulti(Class<T> cls, Class<? extends Annotation> cls2) {
        return this.mBinder.A1T(cls, cls2);
    }

    public <T> void declareMultiBinding(C09160zY<T> r2) {
        this.mBinder.A28(r2);
    }

    public <T> void declareMultiBinding(Class<T> cls) {
        this.mBinder.A29(cls);
    }

    public <T> void declareMultiBinding(Class<T> cls, Class<? extends Annotation> cls2) {
        this.mBinder.A2A(cls, cls2);
    }
}
