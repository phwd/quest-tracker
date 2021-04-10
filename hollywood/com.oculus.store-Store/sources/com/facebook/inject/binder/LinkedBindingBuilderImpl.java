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
    public ScopedBindingBuilder to(Class<? extends T> implementation) {
        this.mBinding.setProvider(new RedirectProvider(Key.get((Class) implementation)));
        return this;
    }

    @Override // com.facebook.inject.binder.LinkedBindingBuilder
    public ScopedBindingBuilder to(Class<? extends T> implementation, Class<? extends Annotation> annotation) {
        return to(Key.get((Class) implementation, annotation));
    }

    @Override // com.facebook.inject.binder.LinkedBindingBuilder
    public ScopedBindingBuilder to(TypeLiteral<? extends T> implementation) {
        this.mBinding.setProvider(new RedirectProvider(Key.get(implementation)));
        return this;
    }

    @Override // com.facebook.inject.binder.LinkedBindingBuilder
    public ScopedBindingBuilder to(Key<? extends T> targetKey) {
        this.mBinding.setProvider(new RedirectProvider(targetKey));
        return this;
    }

    @Override // com.facebook.inject.binder.LinkedBindingBuilder
    public void toInstance(T instance) {
        this.mBinding.setProvider(new InstanceProvider(instance));
    }

    @Override // com.facebook.inject.binder.LinkedBindingBuilder
    public ScopedBindingBuilder toProvider(Provider<? extends T> provider) {
        this.mBinding.setProvider(provider);
        return this;
    }
}
