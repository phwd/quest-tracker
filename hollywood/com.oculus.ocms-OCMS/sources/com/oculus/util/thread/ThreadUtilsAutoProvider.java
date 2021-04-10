package com.oculus.util.thread;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class ThreadUtilsAutoProvider extends AbstractProvider<ThreadUtils> {
    @Override // javax.inject.Provider
    public ThreadUtils get() {
        return new ThreadUtils();
    }
}
