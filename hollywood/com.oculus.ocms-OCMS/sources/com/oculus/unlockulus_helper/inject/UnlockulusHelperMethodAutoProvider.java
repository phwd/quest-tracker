package com.oculus.unlockulus_helper.inject;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.oculus.unlockulus_helper.UnlockulusHelper;

@Generated({"By: InjectorProcessor"})
public class UnlockulusHelperMethodAutoProvider extends AbstractProvider<UnlockulusHelper> {
    @Override // javax.inject.Provider
    public UnlockulusHelper get() {
        return UnlockulusModule.provideUnlockulusHelper();
    }
}
