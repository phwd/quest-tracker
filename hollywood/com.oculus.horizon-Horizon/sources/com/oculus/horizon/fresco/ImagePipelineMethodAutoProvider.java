package com.oculus.horizon.fresco;

import X.AnonymousClass0J3;
import X.AnonymousClass0KU;
import X.AnonymousClass117;
import X.C09801pc;
import X.C10051qt;
import com.facebook.annotations.Generated;
import com.oculus.horizon.fresco.init.FrescoInit;

@Generated({"By: InjectorProcessor"})
public class ImagePipelineMethodAutoProvider extends AnonymousClass0J3<C09801pc> {
    public final Object get() {
        ((FrescoInit) AnonymousClass117.A00(448, this)).init();
        C10051qt r1 = C10051qt.A0I;
        AnonymousClass0KU.A02(r1, "ImagePipelineFactory was not initialized!");
        C09801pc r0 = r1.A04;
        if (r0 != null) {
            return r0;
        }
        C09801pc A05 = C10051qt.A05(r1);
        r1.A04 = A05;
        return A05;
    }
}
