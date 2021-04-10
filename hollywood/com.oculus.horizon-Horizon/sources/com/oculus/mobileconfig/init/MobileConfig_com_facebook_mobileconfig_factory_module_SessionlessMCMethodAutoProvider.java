package com.oculus.mobileconfig.init;

import X.AbstractC06600ny;
import X.AnonymousClass0J3;
import X.AnonymousClass117;
import X.AnonymousClass1Ae;
import X.AnonymousClass1ar;
import X.C003108z;
import X.C09311at;
import android.content.Context;
import com.facebook.annotations.Generated;
import java.io.File;

@Generated({"By: InjectorProcessor"})
public class MobileConfig_com_facebook_mobileconfig_factory_module_SessionlessMCMethodAutoProvider extends AnonymousClass0J3<AbstractC06600ny> {
    public final Object get() {
        Context A00 = C003108z.A00(this);
        AnonymousClass1Ae r2 = new AnonymousClass1Ae((C09311at) AnonymousClass117.A00(97, this));
        File filesDir = A00.getFilesDir();
        AnonymousClass1ar r1 = r2.A00;
        r1.A04 = filesDir;
        r1.A00 = A00.getAssets();
        return r2.A00();
    }
}
