package com.oculus.autoupdates.dumper;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.oculus.autoupdates.AutoUpdatesManager;

@Generated({"By: InjectorProcessor"})
public class AutoUpdatesDumperPluginAutoProvider extends AbstractProvider<AutoUpdatesDumperPlugin> {
    @Override // javax.inject.Provider
    public AutoUpdatesDumperPlugin get() {
        return new AutoUpdatesDumperPlugin(AutoUpdatesManager._UL__ULSEP_com_oculus_autoupdates_AutoUpdatesManager_ULSEP_ACCESS_METHOD(this));
    }
}
