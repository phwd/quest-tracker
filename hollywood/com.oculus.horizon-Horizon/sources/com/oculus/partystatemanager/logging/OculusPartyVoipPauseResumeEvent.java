package com.oculus.partystatemanager.logging;

import com.oculus.logging.utils.EventManager;
import com.oculus.partystatemanager.logging.PartyEventFields;

public class OculusPartyVoipPauseResumeEvent extends PartyEvent {
    public String mCaller;
    public String mEvent;
    public String mPartyId;

    public OculusPartyVoipPauseResumeEvent(EventManager eventManager, PartyEventFields.EventName eventName, String str, String str2) {
        super(eventManager);
        this.mEvent = eventName.value;
        this.mPartyId = str;
        this.mCaller = str2;
    }
}
