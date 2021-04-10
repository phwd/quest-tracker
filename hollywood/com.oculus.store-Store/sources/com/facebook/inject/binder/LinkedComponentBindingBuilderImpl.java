package com.facebook.inject.binder;

import com.facebook.inject.ComponentBinding;
import com.facebook.inject.ComponentProvider;

public class LinkedComponentBindingBuilderImpl<T> implements LinkedComponentBindingBuilder<T> {
    protected final ComponentBinding<T> mBinding;

    public LinkedComponentBindingBuilderImpl(ComponentBinding<T> binding) {
        this.mBinding = binding;
    }

    @Override // com.facebook.inject.binder.LinkedComponentBindingBuilder
    public void toProvider(ComponentProvider<T> provider) {
        this.mBinding.setProvider(provider);
    }
}
