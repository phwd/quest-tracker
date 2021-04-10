package com.facebook.inject;

public interface ScopeAwareInjector extends BasicScopeAwareInjector {
    ScopeUnawareInjector getScopeUnawareInjector();
}
