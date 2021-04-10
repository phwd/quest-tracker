package com.facebook.gk.storelogger;

import android.os.SystemClock;
import com.facebook.debug.tracer.Tracer;
import com.facebook.gk.store.GatekeeperStoreLogger;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class TraceGatekeeperStoreLogger implements GatekeeperStoreLogger {
    private long mLoadStart;
    private long mLoadStop;
    private final String mTag;

    public TraceGatekeeperStoreLogger(String str) {
        this.mTag = str;
    }

    @Override // com.facebook.gk.store.GatekeeperStoreLogger
    public void beforeSave() {
        Tracer.startTracer("%s.save", this.mTag);
    }

    @Override // com.facebook.gk.store.GatekeeperStoreLogger
    public void afterSave() {
        Tracer.stopTracer();
    }

    @Override // com.facebook.gk.store.GatekeeperStoreLogger
    public void beforeLoad() {
        this.mLoadStart = SystemClock.uptimeMillis();
        Tracer.startTracer("%s.load", this.mTag);
    }

    @Override // com.facebook.gk.store.GatekeeperStoreLogger
    public void afterLoad() {
        Tracer.stopTracer();
        this.mLoadStop = SystemClock.uptimeMillis();
    }
}
