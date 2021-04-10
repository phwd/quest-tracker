package com.oculus.autoupdates;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class AutoUpdateSchedulerAutoProvider extends AbstractProvider<AutoUpdateScheduler> {
    @Override // javax.inject.Provider
    public AutoUpdateScheduler get() {
        return new AutoUpdateScheduler(this);
    }
}
