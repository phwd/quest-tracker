package com.facebook.inject;

import android.content.Context;
import com.facebook.infer.annotation.ThreadSafe;

@ThreadSafe
public interface ScopeAwareInjector extends BasicScopeAwareInjector {
    Context getInjectorContext();

    ScopeUnawareInjector getScopeUnawareInjector();
}
