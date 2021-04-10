package com.oculus.profileapi;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class OVRProfileAutoProvider extends AbstractProvider<OVRProfile> {
    @Override // javax.inject.Provider
    public OVRProfile get() {
        return new OVRProfile(this);
    }
}
