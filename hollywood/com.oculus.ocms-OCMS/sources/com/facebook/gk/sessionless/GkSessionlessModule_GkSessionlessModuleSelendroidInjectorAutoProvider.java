package com.facebook.gk.sessionless;

import com.facebook.gk.sessionless.GkSessionlessModule;
import com.facebook.inject.AbstractComponentProvider;

public class GkSessionlessModule_GkSessionlessModuleSelendroidInjectorAutoProvider extends AbstractComponentProvider<GkSessionlessModule.GkSessionlessModuleSelendroidInjector> {
    public void inject(GkSessionlessModule.GkSessionlessModuleSelendroidInjector gkSessionlessModuleSelendroidInjector) {
        GkSessionlessModule.GkSessionlessModuleSelendroidInjector._UL_staticInjectMe(this, gkSessionlessModuleSelendroidInjector);
    }

    public boolean equals(Object obj) {
        return obj instanceof GkSessionlessModule_GkSessionlessModuleSelendroidInjectorAutoProvider;
    }
}
