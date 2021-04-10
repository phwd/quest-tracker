package com.oculus.config;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.oculus.config.gatekeeper.GatekeeperRegistry;

@Generated({"By: InjectorProcessor"})
public class GatekeeperRegistryMethodAutoProvider extends AbstractProvider<GatekeeperRegistry> {
    @Override // javax.inject.Provider
    public GatekeeperRegistry get() {
        return ConfigModule.provideGatekeeperStore(ConfigModule._UL__ULSEP_java_util_Set_ULLT_java_lang_String_ULGT__ULSEP_com_oculus_config_annotations_GatekeeperSet_ULSEP_ACCESS_METHOD(this));
    }
}
