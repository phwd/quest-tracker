package com.oculus.partystatemanager.logging;

import com.oculus.logging.utils.Event;
import com.oculus.logging.utils.EventManager;

public abstract class PartyEvent {
    public EventManager mEventManager;

    public abstract void addExtras(Event event);

    public abstract String getEventName();

    public void log() {
        Event createEvent = this.mEventManager.createEvent(getEventName());
        addExtras(createEvent);
        createEvent.logAndRelease();
    }

    public PartyEvent(EventManager eventManager) {
        this.mEventManager = eventManager;
    }
}
