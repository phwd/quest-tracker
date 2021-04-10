package X;

import com.facebook.inject.AssistedProvider;
import com.facebook.inject.binder.AnnotatedBindingBuilder;
import com.facebook.inject.binder.LinkedBindingBuilder;
import com.facebook.inject.binder.LinkedComponentBindingBuilder;
import java.lang.annotation.Annotation;

/* renamed from: X.0bS  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC03070bS implements AnonymousClass0RF {
    public AnonymousClass0Ql mBinder;

    public void configure() {
    }

    public <T> void bindAssistedProvider(Class<? extends AssistedProvider<T>> cls) {
        this.mBinder.A1K(cls);
    }

    public <T> LinkedComponentBindingBuilder<T> bindComponent(Class<T> cls) {
        return this.mBinder.A1M(cls);
    }

    public void bindScope(Class<? extends Annotation> cls, AnonymousClass0RX r3) {
        this.mBinder.A1V(cls, r3);
    }

    public AnonymousClass0Ql getBinder() {
        return this.mBinder;
    }

    public void require(Class<?> cls) {
        this.mBinder.A7S(cls);
    }

    public <T> void assertBindingInstalled(C01440Gz<T> r2) {
        this.mBinder.A1C(r2);
    }

    public <T> void assertBindingInstalled(Class<T> cls) {
        this.mBinder.A1D(cls);
    }

    public <T> void assertBindingInstalled(Class<T> cls, Class<? extends Annotation> cls2) {
        this.mBinder.A1C(C01440Gz.A01(cls, cls2));
    }

    public <T> AnnotatedBindingBuilder<T> bind(Class<T> cls) {
        return this.mBinder.A1J(cls);
    }

    public <T> LinkedBindingBuilder<T> bind(C01440Gz<T> r2) {
        return this.mBinder.A1I(r2);
    }

    public <T> AnnotatedBindingBuilder<T> bindDefault(Class<T> cls) {
        return this.mBinder.A1O(cls);
    }

    public <T> LinkedBindingBuilder<T> bindDefault(C01440Gz<T> r2) {
        return this.mBinder.A1N(r2);
    }

    public <T> AnonymousClass0RM<T> bindMulti(C01440Gz<T> r2) {
        return this.mBinder.A1R(r2);
    }

    public <T> AnonymousClass0RM<T> bindMulti(Class<T> cls) {
        return this.mBinder.A1S(cls);
    }

    public <T> AnonymousClass0RM<T> bindMulti(Class<T> cls, Class<? extends Annotation> cls2) {
        return this.mBinder.A1T(cls, cls2);
    }

    public <T> void declareMultiBinding(C01440Gz<T> r2) {
        this.mBinder.A22(r2);
    }

    public <T> void declareMultiBinding(Class<T> cls) {
        this.mBinder.A23(cls);
    }

    public <T> void declareMultiBinding(Class<T> cls, Class<? extends Annotation> cls2) {
        this.mBinder.A24(cls, cls2);
    }
}
