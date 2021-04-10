package com.oculus.logging.utils;

import com.facebook.inject.AbstractProvider;

public class StorageLoggingUtilsAutoProvider extends AbstractProvider<StorageLoggingUtils> {
    @Override // javax.inject.Provider
    public StorageLoggingUtils get() {
        return new StorageLoggingUtils(this);
    }
}
