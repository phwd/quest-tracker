package com.facebook.inject;

import javax.inject.Provider;

public interface ProviderWithInjector<T> extends Provider<T> {
    void setInjector(InjectorLike injectorLike);
}
