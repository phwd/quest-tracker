package com.facebook.tigon.oktigon;

import X.AnonymousClass0lD;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.jni.HybridData;
import com.facebook.tigon.iface.TigonServiceHolder;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class OkTigonServiceHolder extends TigonServiceHolder {
    public static native HybridData initHybrid(OkTigonService okTigonService);

    static {
        AnonymousClass0lD.A01("oktigon");
    }

    public OkTigonServiceHolder(OkTigonService okTigonService) {
        super(initHybrid(okTigonService));
    }
}
