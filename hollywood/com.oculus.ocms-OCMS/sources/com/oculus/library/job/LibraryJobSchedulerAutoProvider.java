package com.oculus.library.job;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class LibraryJobSchedulerAutoProvider extends AbstractProvider<LibraryJobScheduler> {
    @Override // javax.inject.Provider
    public LibraryJobScheduler get() {
        return new LibraryJobScheduler(this);
    }
}
