package com.facebook.inject.binder;

import com.facebook.inject.Binding;
import com.facebook.inject.InstanceProvider;
import com.facebook.inject.RedirectProvider;
import com.google.inject.Key;
import com.google.inject.TypeLiteral;
import java.lang.annotation.Annotation;
import javax.inject.Provider;

public class LinkedBindingBuilderImpl<T> extends ScopedBindingBuilderImpl<T> implements LinkedBindingBuilder<T> {
    public LinkedBindingBuilderImpl(Binding<T> binding) {
        super(binding);
    }

    @Override // com.facebook.inject.binder.LinkedBindingBuilder
    public ScopedBindingBuilder to(Class<? extends T> cls) {
        this.mBinding.setProvider(new RedirectProvider(Key.get((Class) cls)));
        return this;
    }

    @Override // com.facebook.inject.binder.LinkedBindingBuilder
    public ScopedBindingBuilder to(Class<? extends T> cls, Class<? extends Annotation> cls2) {
        return to(Key.get((Class) cls, cls2));
    }

    @Override // com.facebook.inject.binder.LinkedBindingBuilder
    public ScopedBindingBuilder to(TypeLiteral<? extends T> typeLiteral) {
        this.mBinding.setProvider(new RedirectProvider(Key.get(typeLiteral)));
        return this;
    }

    @Override // com.facebook.inject.binder.LinkedBindingBuilder
    public ScopedBindingBuilder to(Key<? extends T> key) {
        this.mBinding.setProvider(new RedirectProvider(key));
        return this;
    }

    @Override // com.facebook.inject.binder.LinkedBindingBuilder
    public void toInstance(T t) {
        this.mBinding.setProvider(new InstanceProvider(t));
    }

    @Override // com.facebook.inject.binder.LinkedBindingBuilder
    public ScopedBindingBuilder toProvider(Provider<? extends T> provider) {
        this.mBinding.setProvider(provider);
        return this;
    }
}
