package com.oculus.config.updater;

import X.AnonymousClass0J3;
import com.facebook.annotations.Generated;
import com.oculus.config.ConfigController;
import com.oculus.config.ConfigModule;

@Generated({"By: InjectorProcessor"})
public class ConfigUpdaterDumperPluginAutoProvider extends AnonymousClass0J3<ConfigUpdaterDumperPlugin> {
    public ConfigUpdaterDumperPlugin get() {
        return new ConfigUpdaterDumperPlugin(ConfigModule._UL__ULSEP_com_oculus_config_gatekeeper_GatekeeperRegistry_ULSEP_ACCESS_METHOD(this), ConfigController._UL__ULSEP_com_oculus_config_ConfigController_ULSEP_ACCESS_METHOD(this), ConfigUpdater._UL__ULSEP_com_oculus_config_updater_ConfigUpdater_ULSEP_ACCESS_METHOD(this));
    }
}
