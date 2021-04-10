package com.facebook.inject.binder;

import com.facebook.inject.Binding;
import com.google.inject.Key;
import java.lang.annotation.Annotation;

public class AnnotatedBindingBuilderImpl<T> extends LinkedBindingBuilderImpl<T> implements AnnotatedBindingBuilder<T> {
    public AnnotatedBindingBuilderImpl(Binding<T> binding) {
        super(binding);
    }

    @Override // com.facebook.inject.binder.AnnotatedBindingBuilder
    public LinkedBindingBuilder<T> annotatedWith(Class<? extends Annotation> annotationType) {
        this.mBinding.setKey(Key.get(this.mBinding.getKey().getTypeLiteral(), annotationType));
        return this;
    }

    @Override // com.facebook.inject.binder.AnnotatedBindingBuilder
    public LinkedBindingBuilder<T> annotatedWith(Annotation annotation) {
        this.mBinding.setKey(Key.get(this.mBinding.getKey().getTypeLiteral(), annotation));
        return this;
    }
}
