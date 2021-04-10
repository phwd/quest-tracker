package X;

import com.facebook.inject.AssistedProvider;
import com.facebook.inject.binder.AnnotatedBindingBuilder;
import com.facebook.inject.binder.LinkedBindingBuilder;
import com.facebook.inject.binder.LinkedComponentBindingBuilder;
import java.lang.annotation.Annotation;

/* renamed from: X.0mL  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC03390mL implements AnonymousClass0RM {
    public AnonymousClass0Qr mBinder;

    public void configure() {
    }

    public <T> void bindAssistedProvider(Class<? extends AssistedProvider<T>> cls) {
        this.mBinder.A1d(cls);
    }

    public <T> LinkedComponentBindingBuilder<T> bindComponent(Class<T> cls) {
        return this.mBinder.A1e(cls);
    }

    public void bindScope(Class<? extends Annotation> cls, AbstractC01120Rf r3) {
        this.mBinder.A1k(cls, r3);
    }

    public void require(Class<?> cls) {
        this.mBinder.A9H(cls);
    }

    public AnonymousClass0Qr getBinder() {
        return this.mBinder;
    }

    public <T> void assertBindingInstalled(AnonymousClass14P<T> r2) {
        this.mBinder.A1V(r2);
    }

    public <T> void assertBindingInstalled(Class<T> cls) {
        this.mBinder.A1W(cls);
    }

    public <T> void assertBindingInstalled(Class<T> cls, Class<? extends Annotation> cls2) {
        this.mBinder.A1V(AnonymousClass14P.A01(cls, cls2));
    }

    public <T> AnnotatedBindingBuilder<T> bind(Class<T> cls) {
        return this.mBinder.A1c(cls);
    }

    public <T> LinkedBindingBuilder<T> bind(AnonymousClass14P<T> r2) {
        return this.mBinder.A1b(r2);
    }

    public <T> AnnotatedBindingBuilder<T> bindDefault(Class<T> cls) {
        return this.mBinder.A1g(cls);
    }

    public <T> LinkedBindingBuilder<T> bindDefault(AnonymousClass14P<T> r2) {
        return this.mBinder.A1f(r2);
    }

    public <T> AnonymousClass0RT<T> bindMulti(AnonymousClass14P<T> r2) {
        return this.mBinder.A1h(r2);
    }

    public <T> AnonymousClass0RT<T> bindMulti(Class<T> cls) {
        return this.mBinder.A1i(cls);
    }

    public <T> AnonymousClass0RT<T> bindMulti(Class<T> cls, Class<? extends Annotation> cls2) {
        return this.mBinder.A1j(cls, cls2);
    }

    public <T> void declareMultiBinding(AnonymousClass14P<T> r2) {
        this.mBinder.A2S(r2);
    }

    public <T> void declareMultiBinding(Class<T> cls) {
        this.mBinder.A2T(cls);
    }

    public <T> void declareMultiBinding(Class<T> cls, Class<? extends Annotation> cls2) {
        this.mBinder.A2U(cls, cls2);
    }
}
