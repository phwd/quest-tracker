package com.oculus.installer;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class AssetManagerAutoProvider extends AbstractProvider<AssetManager> {
    @Override // javax.inject.Provider
    public AssetManager get() {
        return new AssetManager(this);
    }
}
