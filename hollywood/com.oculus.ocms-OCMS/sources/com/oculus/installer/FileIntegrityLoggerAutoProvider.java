package com.oculus.installer;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class FileIntegrityLoggerAutoProvider extends AbstractProvider<FileIntegrityLogger> {
    @Override // javax.inject.Provider
    public FileIntegrityLogger get() {
        return new FileIntegrityLogger(this);
    }
}
