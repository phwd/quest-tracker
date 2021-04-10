package com.oculus.panelapp.messenger.api;

import com.oculus.messengervr.interfaces.MessengerMessage;
import com.oculus.messengervr.interfaces.MessengerThread;
import java.util.List;

public interface ThreadListener {
    void onThreadUpdate(MessengerThread messengerThread, List<MessengerMessage> list);
}
