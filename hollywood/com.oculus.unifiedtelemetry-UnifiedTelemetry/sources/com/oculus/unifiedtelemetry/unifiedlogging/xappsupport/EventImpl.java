package com.oculus.unifiedtelemetry.unifiedlogging.xappsupport;

import com.oculus.logging.OculusLoggingEvent;
import com.oculus.logging.utils.Event;
import com.oculus.logging.utils.StorageLoggingUtils;
import com.oculus.unifiedtelemetry.unifiedlogging.LoggingHandler;
import javax.annotation.Nullable;

public class EventImpl implements Event {
    public final String mEventName;
    public final LoggingHandler mLoggingHandler;
    @Nullable
    public final OculusLoggingEvent mOculusLoggingEvent;
    public final StorageLoggingUtils mStorageLoggingUtils;

    @Override // com.oculus.logging.utils.Event
    public final Event A17(String str, int i) {
        OculusLoggingEvent oculusLoggingEvent = this.mOculusLoggingEvent;
        if (oculusLoggingEvent != null) {
            oculusLoggingEvent.A13(str, i);
        }
        return this;
    }

    @Override // com.oculus.logging.utils.Event
    public final Event A18(String str, @Nullable String str2) {
        OculusLoggingEvent oculusLoggingEvent = this.mOculusLoggingEvent;
        if (oculusLoggingEvent != null) {
            oculusLoggingEvent.A15(str, str2);
        }
        return this;
    }

    @Override // com.oculus.logging.utils.Event
    public final Event A19(String str, boolean z) {
        OculusLoggingEvent oculusLoggingEvent = this.mOculusLoggingEvent;
        if (oculusLoggingEvent != null) {
            oculusLoggingEvent.A16(str, z);
        }
        return this;
    }

    @Override // com.oculus.logging.utils.Event
    public final void A3Q() {
        OculusLoggingEvent oculusLoggingEvent = this.mOculusLoggingEvent;
        if (oculusLoggingEvent != null) {
            this.mLoggingHandler.A0A(this.mEventName, oculusLoggingEvent);
        }
    }

    @Override // com.oculus.logging.utils.Event
    public final void A5C() {
        OculusLoggingEvent oculusLoggingEvent = this.mOculusLoggingEvent;
        if (oculusLoggingEvent != null) {
            oculusLoggingEvent.A5C();
        }
    }

    public EventImpl(String str, LoggingHandler loggingHandler, @Nullable OculusLoggingEvent oculusLoggingEvent, StorageLoggingUtils storageLoggingUtils) {
        this.mEventName = str;
        this.mLoggingHandler = loggingHandler;
        this.mOculusLoggingEvent = oculusLoggingEvent;
        this.mStorageLoggingUtils = storageLoggingUtils;
    }
}
