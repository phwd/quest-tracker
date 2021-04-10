package com.oculus.unifiedtelemetry.unifiedlogging;

import X.AbstractC0097Hv;
import X.C00208d;
import android.os.UserManager;
import com.facebook.annotations.Generated;
import com.oculus.auth.service.contract.ServiceContract;

@Generated({"By: InjectorProcessor"})
public class UserManagerMethodAutoProvider extends AbstractC0097Hv<UserManager> {
    public final Object get() {
        return C00208d.A00(this).getSystemService(ServiceContract.EXTRA_USER);
    }
}
