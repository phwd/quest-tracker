package com.facebook.tigon;

import X.MG;
import com.facebook.debug.tracer.Tracer;
import com.facebook.jni.HybridData;

public abstract class TigonXplatBodyProvider extends TigonBodyProvider {
    private native HybridData initHybrid();

    static {
        MG.A00();
    }

    public TigonXplatBodyProvider() {
        Tracer.A01("TigonXplatBodyProvider");
        try {
            this.mHybridData = initHybrid();
        } finally {
            Tracer.A00();
        }
    }
}
