package com.facebook.tigon.oktigon;

import X.KV;
import com.facebook.jni.HybridData;
import com.facebook.tigon.iface.TigonServiceHolder;

public class OkTigonServiceHolder extends TigonServiceHolder {
    public static native HybridData initHybrid(OkTigonService okTigonService);

    static {
        KV.A01("oktigon");
    }

    public OkTigonServiceHolder(OkTigonService okTigonService) {
        super(initHybrid(okTigonService));
    }
}
