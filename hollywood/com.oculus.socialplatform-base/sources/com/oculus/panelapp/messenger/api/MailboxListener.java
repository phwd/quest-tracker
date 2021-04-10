package com.oculus.panelapp.messenger.api;

import com.oculus.messengervr.interfaces.MessengerThread;
import java.util.List;

public interface MailboxListener {
    void onMailboxUpdate(List<MessengerThread> list);
}
