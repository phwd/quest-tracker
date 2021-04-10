package com.oculus.horizon.foreground;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class ApplicationForegroundTrackerAutoProvider extends AbstractProvider<ApplicationForegroundTracker> {
    @Override // javax.inject.Provider
    public ApplicationForegroundTracker get() {
        return new ApplicationForegroundTracker(this);
    }
}
