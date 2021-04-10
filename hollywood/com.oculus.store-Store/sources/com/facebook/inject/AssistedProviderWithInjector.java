package com.facebook.inject;

public interface AssistedProviderWithInjector<T> extends AssistedProvider<T> {
    void setInjector(InjectorLike injectorLike);
}
