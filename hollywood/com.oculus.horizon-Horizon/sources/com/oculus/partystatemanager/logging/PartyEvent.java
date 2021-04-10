package com.oculus.partystatemanager.logging;

import com.oculus.logging.utils.Event;
import com.oculus.logging.utils.EventManager;

public abstract class PartyEvent {
    public EventManager mEventManager;

    public final void A00(Event event) {
        String str;
        String str2;
        if (!(this instanceof OculusPartyVoipPauseResumeEvent)) {
            OculusPartyVoipEvent oculusPartyVoipEvent = (OculusPartyVoipEvent) this;
            event.A15("party_id", oculusPartyVoipEvent.mPartyId);
            event.A16("success", oculusPartyVoipEvent.mExtra.mIsSuccess);
            str = oculusPartyVoipEvent.mExtra.mReason;
            if (str != null) {
                str2 = "reason";
            } else {
                return;
            }
        } else {
            OculusPartyVoipPauseResumeEvent oculusPartyVoipPauseResumeEvent = (OculusPartyVoipPauseResumeEvent) this;
            event.A15("party_id", oculusPartyVoipPauseResumeEvent.mPartyId);
            str = oculusPartyVoipPauseResumeEvent.mCaller;
            str2 = PartyEventFields.CALLER;
        }
        event.A15(str2, str);
    }

    public PartyEvent(EventManager eventManager) {
        this.mEventManager = eventManager;
    }
}
