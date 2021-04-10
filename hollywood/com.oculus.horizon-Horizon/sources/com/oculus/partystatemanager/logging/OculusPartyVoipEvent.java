package com.oculus.partystatemanager.logging;

import com.oculus.logging.utils.EventManager;
import com.oculus.partystatemanager.logging.PartyEventFields;

public class OculusPartyVoipEvent extends PartyEvent {
    public String mEvent;
    public PartyEventFields.Extra mExtra = new PartyEventFields.Extra();
    public String mPartyId;

    public OculusPartyVoipEvent(EventManager eventManager, PartyEventFields.EventName eventName, String str) {
        super(eventManager);
        this.mEvent = eventName.value;
        this.mPartyId = str;
    }
}
