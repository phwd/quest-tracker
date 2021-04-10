package com.facebook.gk.store;

import com.facebook.infer.annotation.ThreadSafe;

@ThreadSafe
public interface GatekeeperStore {
    void refreshSessionState(boolean z);
}
