package com.oculus.partystatemanager.logging;

import com.oculus.logging.utils.Event;
import com.oculus.logging.utils.EventManager;
import com.oculus.partystatemanager.logging.PartyEventFields;
import javax.annotation.Nullable;

public class OculusPartyVoipEvent extends PartyEvent {
    public String mEvent;
    public PartyEventFields.Extra mExtra;
    public String mPartyId;

    @Override // com.oculus.partystatemanager.logging.PartyEvent
    public void addExtras(Event event) {
        event.addExtra("party_id", this.mPartyId);
        event.addExtra("success", this.mExtra.mIsSuccess);
        String str = this.mExtra.mReason;
        if (str != null) {
            event.addExtra("reason", str);
        }
    }

    @Override // com.oculus.partystatemanager.logging.PartyEvent
    public String getEventName() {
        return this.mEvent;
    }

    public OculusPartyVoipEvent(EventManager eventManager, PartyEventFields.EventName eventName, String str, @Nullable PartyEventFields.Extra extra) {
        super(eventManager);
        this.mEvent = eventName.value;
        this.mPartyId = str;
        if (extra == null) {
            this.mExtra = new PartyEventFields.Extra(true, null);
        } else {
            this.mExtra = extra;
        }
    }
}
