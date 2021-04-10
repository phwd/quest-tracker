package X;

import com.facebook.inject.AssistedProvider;
import com.facebook.inject.binder.AnnotatedBindingBuilder;
import com.facebook.inject.binder.LinkedBindingBuilder;
import com.facebook.inject.binder.LinkedComponentBindingBuilder;
import java.lang.annotation.Annotation;

public abstract class Y3 implements QK {
    public Pq mBinder;

    public void configure() {
    }

    public <T> void bindAssistedProvider(Class<? extends AssistedProvider<T>> cls) {
        this.mBinder.A1M(cls);
    }

    public <T> LinkedComponentBindingBuilder<T> bindComponent(Class<T> cls) {
        return this.mBinder.A1N(cls);
    }

    public void bindScope(Class<? extends Annotation> cls, AbstractC0133Qc qc) {
        this.mBinder.A1T(cls, qc);
    }

    public void require(Class<?> cls) {
        this.mBinder.A4m(cls);
    }

    public Pq getBinder() {
        return this.mBinder;
    }

    public <T> void assertBindingInstalled(C0475qE<T> qEVar) {
        this.mBinder.A1G(qEVar);
    }

    public <T> void assertBindingInstalled(Class<T> cls) {
        this.mBinder.A1H(cls);
    }

    public <T> void assertBindingInstalled(Class<T> cls, Class<? extends Annotation> cls2) {
        this.mBinder.A1G(C0475qE.A01(cls, cls2));
    }

    public <T> AnnotatedBindingBuilder<T> bind(Class<T> cls) {
        return this.mBinder.A1L(cls);
    }

    public <T> LinkedBindingBuilder<T> bind(C0475qE<T> qEVar) {
        return this.mBinder.A1K(qEVar);
    }

    public <T> AnnotatedBindingBuilder<T> bindDefault(Class<T> cls) {
        return this.mBinder.A1P(cls);
    }

    public <T> LinkedBindingBuilder<T> bindDefault(C0475qE<T> qEVar) {
        return this.mBinder.A1O(qEVar);
    }

    public <T> QR<T> bindMulti(C0475qE<T> qEVar) {
        return this.mBinder.A1Q(qEVar);
    }

    public <T> QR<T> bindMulti(Class<T> cls) {
        return this.mBinder.A1R(cls);
    }

    public <T> QR<T> bindMulti(Class<T> cls, Class<? extends Annotation> cls2) {
        return this.mBinder.A1S(cls, cls2);
    }

    public <T> void declareMultiBinding(C0475qE<T> qEVar) {
        this.mBinder.A1k(qEVar);
    }

    public <T> void declareMultiBinding(Class<T> cls) {
        this.mBinder.A1l(cls);
    }

    public <T> void declareMultiBinding(Class<T> cls, Class<? extends Annotation> cls2) {
        this.mBinder.A1m(cls, cls2);
    }
}
