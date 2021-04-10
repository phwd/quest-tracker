package X;

import com.facebook.inject.AssistedProvider;
import com.facebook.inject.binder.AnnotatedBindingBuilder;
import com.facebook.inject.binder.LinkedBindingBuilder;
import com.facebook.inject.binder.LinkedComponentBindingBuilder;
import java.lang.annotation.Annotation;

public abstract class Si implements Ou {
    public OQ mBinder;

    public void configure() {
    }

    public <T> void bindAssistedProvider(Class<? extends AssistedProvider<T>> cls) {
        this.mBinder.A0x(cls);
    }

    public <T> LinkedComponentBindingBuilder<T> bindComponent(Class<T> cls) {
        return this.mBinder.A0y(cls);
    }

    public void bindScope(Class<? extends Annotation> cls, PC pc) {
        this.mBinder.A14(cls, pc);
    }

    public void require(Class<?> cls) {
        this.mBinder.A3O(cls);
    }

    public OQ getBinder() {
        return this.mBinder;
    }

    public <T> void assertBindingInstalled(gz<T> gzVar) {
        this.mBinder.A0r(gzVar);
    }

    public <T> void assertBindingInstalled(Class<T> cls) {
        this.mBinder.A0s(cls);
    }

    public <T> void assertBindingInstalled(Class<T> cls, Class<? extends Annotation> cls2) {
        this.mBinder.A0r(gz.A01(cls, cls2));
    }

    public <T> AnnotatedBindingBuilder<T> bind(Class<T> cls) {
        return this.mBinder.A0w(cls);
    }

    public <T> LinkedBindingBuilder<T> bind(gz<T> gzVar) {
        return this.mBinder.A0v(gzVar);
    }

    public <T> AnnotatedBindingBuilder<T> bindDefault(Class<T> cls) {
        return this.mBinder.A10(cls);
    }

    public <T> LinkedBindingBuilder<T> bindDefault(gz<T> gzVar) {
        return this.mBinder.A0z(gzVar);
    }

    public <T> P1<T> bindMulti(gz<T> gzVar) {
        return this.mBinder.A11(gzVar);
    }

    public <T> P1<T> bindMulti(Class<T> cls) {
        return this.mBinder.A12(cls);
    }

    public <T> P1<T> bindMulti(Class<T> cls, Class<? extends Annotation> cls2) {
        return this.mBinder.A13(cls, cls2);
    }

    public <T> void declareMultiBinding(gz<T> gzVar) {
        this.mBinder.A1J(gzVar);
    }

    public <T> void declareMultiBinding(Class<T> cls) {
        this.mBinder.A1K(cls);
    }

    public <T> void declareMultiBinding(Class<T> cls, Class<? extends Annotation> cls2) {
        this.mBinder.A1L(cls, cls2);
    }
}
