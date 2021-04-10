package com.facebook.inject;

import javax.annotation.Nullable;

public interface BasicScopeAwareInjector extends Injector {
    @Nullable
    Object enterPreamble();

    void exitPostamble(Object obj);
}
