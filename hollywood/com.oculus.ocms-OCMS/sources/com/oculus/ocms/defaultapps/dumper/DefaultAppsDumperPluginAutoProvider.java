package com.oculus.ocms.defaultapps.dumper;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class DefaultAppsDumperPluginAutoProvider extends AbstractProvider<DefaultAppsDumperPlugin> {
    @Override // javax.inject.Provider
    public DefaultAppsDumperPlugin get() {
        return new DefaultAppsDumperPlugin(this);
    }
}
