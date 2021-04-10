package com.oculus.ocms.library;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class OVRLibraryInstallerEventListenerAutoProvider extends AbstractProvider<OVRLibraryInstallerEventListener> {
    @Override // javax.inject.Provider
    public OVRLibraryInstallerEventListener get() {
        return new OVRLibraryInstallerEventListener(this);
    }
}
