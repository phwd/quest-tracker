package com.facebook.gk.store;

import com.facebook.common.util.TriState;
import com.facebook.infer.annotation.Functional;
import com.facebook.infer.annotation.ThreadSafe;

@ThreadSafe
public interface GatekeeperStore {
    boolean containsInitializedGatekeepers();

    @Functional
    TriState get(int i);

    @Functional
    boolean get(int i, boolean z);

    @Functional
    TriState getActual(int i);

    @Functional
    boolean isInitialized(int i);

    @Functional
    boolean isOverridden(int i);

    void refreshSessionState(boolean z);

    GatekeeperWriter writer();
}
