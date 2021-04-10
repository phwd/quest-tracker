package com.oculus.config;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.oculus.config.gatekeeper.DynamicGatekeeperRegistry;

@Generated({"By: InjectorProcessor"})
public class DynamicGatekeeperRegistryMethodAutoProvider extends AbstractProvider<DynamicGatekeeperRegistry> {
    @Override // javax.inject.Provider
    public DynamicGatekeeperRegistry get() {
        return ConfigModule.provideDynamicGatekeeperStore(ConfigModule._UL__ULSEP_android_content_SharedPreferences_ULSEP_com_oculus_config_annotations_GatekeeperSharedPrefs_ULSEP_ACCESS_METHOD(this), ConfigModule._UL__ULSEP_java_util_Set_ULLT_java_lang_String_ULGT__ULSEP_com_oculus_config_annotations_GatekeeperSet_ULSEP_ACCESS_METHOD(this));
    }
}
