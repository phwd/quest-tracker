package com.oculus.appmanager.info;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class InfoUtilsAutoProvider extends AbstractProvider<InfoUtils> {
    @Override // javax.inject.Provider
    public InfoUtils get() {
        return new InfoUtils(this);
    }
}
