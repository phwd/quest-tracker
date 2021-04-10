package com.oculus.config;

import X.AnonymousClass0VG;
import com.facebook.annotations.Generated;
import com.oculus.config.gatekeeper.GatekeeperRegistry;
import com.oculus.config.gatekeeper.SetBindingGatekeeperRegistry;

@Generated({"By: InjectorProcessor"})
public class GatekeeperRegistryMethodAutoProvider extends AnonymousClass0VG<GatekeeperRegistry> {
    public GatekeeperRegistry get() {
        return new SetBindingGatekeeperRegistry(ConfigModule._UL__ULSEP_java_util_Set_ULLT_java_lang_String_ULGT__ULSEP_com_oculus_config_annotations_GatekeeperSet_ULSEP_ACCESS_METHOD(this));
    }
}
