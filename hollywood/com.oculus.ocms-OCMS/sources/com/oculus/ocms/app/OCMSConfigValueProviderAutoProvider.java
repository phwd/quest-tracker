package com.oculus.ocms.app;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class OCMSConfigValueProviderAutoProvider extends AbstractProvider<OCMSConfigValueProvider> {
    @Override // javax.inject.Provider
    public OCMSConfigValueProvider get() {
        return new OCMSConfigValueProvider(this);
    }
}
