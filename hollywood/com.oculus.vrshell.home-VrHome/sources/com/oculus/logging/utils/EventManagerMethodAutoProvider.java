package com.oculus.logging.utils;

import com.facebook.inject.AbstractProvider;

public class EventManagerMethodAutoProvider extends AbstractProvider<EventManager> {
    @Override // javax.inject.Provider
    public EventManager get() {
        return UtilsModule.provideEventManager(EventManagerImpl.$ul_$xXXcom_oculus_logging_utils_EventManagerImpl$xXXACCESS_METHOD(this));
    }
}
