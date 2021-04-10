package com.facebook.inject;

public interface ComponentProviderWithInjector<T> extends ComponentProvider<T> {
    void setInjector(InjectorLike injectorLike);
}
