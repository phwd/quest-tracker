package com.facebook.inject;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public interface InjectorLike extends Injector {
    @Deprecated
    InjectorLike getApplicationInjector();

    @Deprecated
    InjectorThreadStack getInjectorThreadStack();

    @Deprecated
    ScopeAwareInjector getScopeAwareInjector();

    @Deprecated
    ScopeUnawareInjector getScopeUnawareInjector();
}
