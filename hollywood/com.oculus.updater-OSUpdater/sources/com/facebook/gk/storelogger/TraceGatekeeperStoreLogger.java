package com.facebook.gk.storelogger;

import com.facebook.gk.store.GatekeeperStoreLogger;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class TraceGatekeeperStoreLogger implements GatekeeperStoreLogger {
    private final String mTag;

    public TraceGatekeeperStoreLogger(String str) {
        this.mTag = str;
    }
}
