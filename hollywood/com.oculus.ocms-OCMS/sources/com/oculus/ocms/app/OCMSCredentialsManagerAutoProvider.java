package com.oculus.ocms.app;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class OCMSCredentialsManagerAutoProvider extends AbstractProvider<OCMSCredentialsManager> {
    @Override // javax.inject.Provider
    public OCMSCredentialsManager get() {
        return new OCMSCredentialsManager(this);
    }
}
