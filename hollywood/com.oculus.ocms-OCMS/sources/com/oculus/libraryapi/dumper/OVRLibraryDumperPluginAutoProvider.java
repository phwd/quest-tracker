package com.oculus.libraryapi.dumper;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class OVRLibraryDumperPluginAutoProvider extends AbstractProvider<OVRLibraryDumperPlugin> {
    @Override // javax.inject.Provider
    public OVRLibraryDumperPlugin get() {
        return new OVRLibraryDumperPlugin(this);
    }
}
