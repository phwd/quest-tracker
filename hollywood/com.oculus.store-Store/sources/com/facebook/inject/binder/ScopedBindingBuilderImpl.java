package com.facebook.inject.binder;

import com.facebook.inject.ApplicationScoped;
import com.facebook.inject.Binding;
import com.facebook.inject.ContextScoped;
import java.lang.annotation.Annotation;

public class ScopedBindingBuilderImpl<T> implements ScopedBindingBuilder {
    protected final Binding<T> mBinding;

    public ScopedBindingBuilderImpl(Binding<T> binding) {
        this.mBinding = binding;
    }

    @Override // com.facebook.inject.binder.ScopedBindingBuilder
    public void asSingleton() {
        this.mBinding.setScope(ApplicationScoped.class);
    }

    @Override // com.facebook.inject.binder.ScopedBindingBuilder
    public void asContextLocal() {
        this.mBinding.setScope(ContextScoped.class);
    }

    @Override // com.facebook.inject.binder.ScopedBindingBuilder
    public void in(Class<? extends Annotation> annotationType) {
        this.mBinding.setScope(annotationType);
    }
}
