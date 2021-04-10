package com.oculus.ossdk.inject;

import X.AnonymousClass0VG;
import com.facebook.annotations.Generated;
import com.oculus.os.Controllers;

@Generated({"By: InjectorProcessor"})
public class ControllersMethodAutoProvider extends AnonymousClass0VG<Controllers> {
    public Controllers get() {
        return OsSdkModule.provideControllers();
    }
}
