package com.oculus.logging.utils;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class EventManagerMethodAutoProvider extends AbstractProvider<EventManager> {
    @Override // javax.inject.Provider
    public EventManager get() {
        return UtilsModule.provideEventManager(EventManagerImpl._UL__ULSEP_com_oculus_logging_utils_EventManagerImpl_ULSEP_ACCESS_METHOD(this));
    }
}
