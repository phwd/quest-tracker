package com.oculus.logging.utils;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class StorageLoggingUtilsAutoProvider extends AbstractProvider<StorageLoggingUtils> {
    @Override // javax.inject.Provider
    public StorageLoggingUtils get() {
        return new StorageLoggingUtils(this);
    }
}
